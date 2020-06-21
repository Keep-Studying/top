/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器
 * @author study
 * @version : MyClassLoaderTest.java, v 0.1 2020年06月21日 12:56 study Exp $
 */
public class MyClassLoaderTest {

    static class MyClassLoader extends ClassLoader{
        private String classPath;

        public MyClassLoader(String classPath) {
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
    }

    public static void main(String[] args) throws Exception{
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为
        //应用程序类加载器AppClassLoader
        MyClassLoader myClassLoader = new MyClassLoader("D:/test");
        //在D盘创建 test/com/study/jvm/classloader 几何目录，将User类的复制类User1.class丢入该目录
        Class clazz = myClassLoader.loadClass("com.study.jvm.classloader.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("output", null);
        method.invoke(obj,null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}