/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.postprocessor;

/**
 * @author study
 * @version : Compent.java, v 0.1 2020年08月13日 0:10 study Exp $
 */
public class Compent {

    public void init(){
        System.out.println("Compent的初始化方法");
    }

    public Compent() {
        System.out.println("Compent的构造方法");
    }
}