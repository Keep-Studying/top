/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.xmlbeanfacotry;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月11日 22:29 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("XmlBean.xml"));

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("XmlBean.xml");
    }
}