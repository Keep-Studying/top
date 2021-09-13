/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author boyan
 * @version : User.java, v 0.1 2021-08-11 20:28 boyan
 */
@Data
@ToString
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 3578643502135717716L;
    /**
     * 使用 @MongoID 能更清晰的指定 _id 主键
     */
    @MongoId
    private String id;
    private String name;
    private String sex;
    private Integer salary;
    private Integer age;
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date birthday;
    private String remake;
    private Status status;
}