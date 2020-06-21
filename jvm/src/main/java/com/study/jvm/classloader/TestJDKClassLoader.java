/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 测试jdk类加载
 * @author study
 * @version : TestJDKClassLoader.java, v 0.1 2020年06月20日 23:54 study Exp $
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        //引导类加载器，底层是C++实现的，打印出来为null
        System.out.println(String.class.getClassLoader());
        //扩展类加载器，sun.misc.Launcher$ExtClassLoader
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        //应用程序类加载器，sun.misc.Launcher$ExtClassLoader
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("the bootstrapLoader :" + bootstrapLoader);
        System.out.println("the extClassLoader :" + extClassLoader);
        System.out.println("the appClassLoader :" + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url:urLs
             ) {
            System.out.println(url);
        }

        System.out.println();
        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}