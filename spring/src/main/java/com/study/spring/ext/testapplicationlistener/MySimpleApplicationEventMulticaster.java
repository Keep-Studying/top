/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testapplicationlistener;

import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * @author study
 * @version : MySimpleApplicationEventMulticaster.java, v 0.1 2020年08月13日 11:42 study Exp $
 */
@Component(value = "applicationEventMulticaster")
public class MySimpleApplicationEventMulticaster extends SimpleApplicationEventMulticaster {
    public MySimpleApplicationEventMulticaster() {
        setTaskExecutor(Executors.newSingleThreadExecutor());
    }
}