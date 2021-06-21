/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * @author boyan
 * @version : AccessHdfs.java, v 0.1 2021年06月19日 10:51 上午 boyan Exp $
 */
public class AccessHdfs {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS", "hdfs://localhost:9000");

            FileSystem fileSystem = FileSystem.get(URI.create("/user/hadoop/input"), configuration);
            FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/user/hadoop/input"));
            for (int i = 0; i < fileStatuses.length; i++) {
                FileStatus fileStatus = fileStatuses[i];
                System.out.println("File name:" + fileStatus.getPath().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}