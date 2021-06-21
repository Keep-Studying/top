/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * @author boyan
 * @version : HdfsUpload.java, v 0.1 2021年06月19日 11:02 上午 boyan Exp $
 */
public class HdfsUpload {

    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://localhost:9000");

            conf.set("dfs.replication", "1");
            FileSystem fs = FileSystem.get(URI.create("/user/hadoop/input"), conf);

            fs.copyFromLocalFile(new Path("/Users/boyan/local/targets/doc/2.txt"),
                    new Path("./2.txt"));

            //fs.delete(new Path("./Simple.thrift"), true);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}