/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * @author boyan
 * @version : WordCountMain.java, v 0.1 2021年06月19日 11:04 上午 boyan Exp $
 */
public class WordCountMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration configuration = new Configuration();
        // write your code here
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");

        if (args.length != 2) {
            System.err.println("Usage:wordcount <input><output>");
            System.exit(2);
        }

        String[] remainingArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
        Path path = new Path(remainingArgs[1]);
        FileSystem fileSystem = path.getFileSystem(configuration);
        if (fileSystem.exists(path)) {
            fileSystem.delete(path, true);
        }

        Job job = Job.getInstance();
        job.setJarByClass(WordCountMain.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WorkCountReducer.class);
        job.setReducerClass(WorkCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}