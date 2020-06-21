/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器
 * 打破双亲委派机制
 * @author study
 * @version : MyClassLoaderBreakTest.java, v 0.1 2020年06月21日 13:22 study Exp $
 */
public class MyClassLoaderBreakTest {
    static class MyClassLoaderBreak extends ClassLoader{
        private String classPath;

        public MyClassLoaderBreak(String classPath) {
            this.classPath = classPath;
        }

        public byte[] loadByte(String name) throws Exception{
            name = name.replaceAll("\\.","/");
            FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
            int len = fileInputStream.available();
            byte[] data = new byte[len];
            fileInputStream.read(data);
            fileInputStream.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         * */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);

                if (c == null) {
                    long t0 = System.nanoTime();
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //当name以我们自己的包名开始的时候，自己加载
                    if(name.startsWith("com.study.jvm.classloader")){
                        c = findClass(name);
                    }else {
                        //其他的时候，调用父类的加载器加装
                        c = this.getParent().loadClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }

                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    /**
     *
     * 同一个JVM内，两个相同包名和类名的类对象可以共存，因为他们的类加载器可以不一样，
     * 所以看两个类对象是否是同一个，除了看类的包名和类名是否都相同之外，还需要他们的
     * 类加载器也是同一个才能认为他们是同一个
     *
     * 输出结果：
     * Tihs is the output method,User [        name=null,        age=null]
     * com.study.jvm.classloader.MyClassLoaderBreakTest$MyClassLoaderBreak@7440e464
     * Other User1,Tihs is the output method,User [        name=null,        age=null]
     * com.study.jvm.classloader.MyClassLoaderBreakTest$MyClassLoaderBreak@27c170f0
     * */
    public static void main(String[] args) throws Exception{
        MyClassLoaderBreak myClassLoaderBreak = new MyClassLoaderBreak("D:/test");
        //在D盘创建 test/com/study/jvm/classloader 几何目录，将User类的复制类User1.class丢入该目录
        Class clazz = myClassLoaderBreak.loadClass("com.study.jvm.classloader.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("output", null);
        method.invoke(obj,null);
        System.out.println(clazz.getClassLoader());

        MyClassLoaderBreak myClassLoaderBreak1 = new MyClassLoaderBreak("D:/test1");
        //在D盘创建 test1/com/study/jvm/classloader 几何目录，将User类的复制类User1.class丢入该目录
        Class clazz1 = myClassLoaderBreak1.loadClass("com.study.jvm.classloader.User1");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("output", null);
        method1.invoke(obj1,null);
        System.out.println(clazz1.getClassLoader());
    }
}