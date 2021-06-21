/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author boyan
 * @version : WorkCountReducer.java, v 0.1 2021年06月19日 11:04 上午 boyan Exp $
 */
public class WorkCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    public WorkCountReducer(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        super();

        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }

        result.set(sum);
        context.write(key, result);
    }
}