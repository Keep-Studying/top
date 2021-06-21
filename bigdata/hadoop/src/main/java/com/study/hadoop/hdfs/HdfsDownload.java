/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.hadoop.hdfs;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @author boyan
 * @version : HdfsDownload.java, v 0.1 2021年06月19日 11:00 上午 boyan Exp $
 */
public class HdfsDownload {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS", "hdfs://localhost:9000");

            FileSystem fileSystem = FileSystem.get(URI.create("/user/bigdata/input"), configuration);
            FSDataInputStream is = fileSystem.open(new Path("hdfs://localhost:9000/user/hadoop/input/2.txt"));
            FileOutputStream os = new FileOutputStream("/Users/boyan/local/targets/doc/3.txt");

            IOUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}