/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.jdk8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DataSource
 *
 * @author boyan
 * @version : DataSource.java, v 0.1 2021-08-19 12:02 boyan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataSource {
    private String guid;
    private String driver;
    private String url;
    private String userName;
    private String password;
    private String dbName;
}