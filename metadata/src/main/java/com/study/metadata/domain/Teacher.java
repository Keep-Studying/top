/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

/**
 * Teacher
 *
 * @author boyan
 * @version : Teacher.java, v 0.1 2021-08-12 00:27 boyan
 */
@Data
@Document(collation = "teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = 3029473052639520466L;

    @MongoId
    private String id;
    private String name;
    private String project;
    private List<Student> students;

}