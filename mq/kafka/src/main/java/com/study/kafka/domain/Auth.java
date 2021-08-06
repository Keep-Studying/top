/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

import java.util.List;

/**
 * 认证
 * @author boyan
 * @version : Auth.java, v 0.1 2021年07月02日 5:55 下午 boyan Exp $
 */
@Data
public class Auth extends ToString {

    private static final long serialVersionUID = -7367467436392059395L;

    /**认证ID*/
    private String       authId;
    /**门户：火眼 / 智策*/
    private String       portal;
    /**权限规则*/
    private String       rule;
    /**备注*/
    private String       remarks;
    /**
     * 角色集合
     * ('1','2')
     * (1,2)*/
    private List<String> roles;
}