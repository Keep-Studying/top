/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.zookeeper.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author study
 * @version : MyConfig.java, v 0.1 2021年06月22日 0:18 study Exp $
 */
@Data
@ToString
@NoArgsConstructor
public class MyConfig {

    private String key;
    private String name;
}