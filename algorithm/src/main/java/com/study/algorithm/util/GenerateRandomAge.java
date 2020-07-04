/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * 生成一份随机年龄的txt文件
 * @author study
 * @version : GenerateRandomAge.java, v 0.1 2020年07月02日 22:54 study Exp $
 */
public class GenerateRandomAge {
    public static void main(String[] args) throws Exception {
        final String fileName = "E:\\IDEA_ITEM_TARGET\\age1.txt";
        final Random random = new Random();
        BufferedWriter objWriter = null;
        objWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        for (int i = 0; i < 1400000000; i++) {
            int age = random.nextInt(180);
            objWriter.write(age + "\r\n");
        }
        objWriter.flush();
        objWriter.close();
    }
}