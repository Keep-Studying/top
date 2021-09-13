/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

/**
 * Student
 *
 * @author boyan
 * @version : Student.java, v 0.1 2021-08-12 00:27 boyan
 */
@Data
@Document(collation = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = -3982539743726158777L;
    @MongoId
    private String id;
    private String name;
    private String unicode;

}