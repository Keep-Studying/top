/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DatasourceInfo
 *
 * @author boyan
 * @version : DatasourceInfo.java, v 0.1 2021-08-12 15:18 boyan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DatasourceInfo {

    public static final String KEY_DRIVER = "driver";
    public static final String KEY_URL = "url";
    public static final String KEY_USER = "userName";
    public static final String KEY_PASS = "password";

    private String driver;
    private String url;
    private String userName;
    private String password;
    private String dbName;
}