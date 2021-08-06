/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.domain;

import lombok.Data;

/**
 *
 * @author boyan
 * @version : KylinHttpConfig.java, v 0.1 2021年07月11日 9:58 上午 boyan Exp $
 */
@Data
public class KylinHttpConfig {

    private boolean ssl;
    private String server;
    private Integer port;
    private String baseUrl;
    private String userName;
    private String password;
    //private CloseableHttpClient httpClient;
}