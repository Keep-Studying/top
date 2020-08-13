/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testspringiocstarter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author study
 * @version : MyDao.java, v 0.1 2020年08月13日 10:42 study Exp $
 */
@Component
@Scope(value = "prototype")
public class MyDao {

    private DataSource dataSource;

    public MyDao(DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("本类的DataSource"+this.dataSource);
    }
}