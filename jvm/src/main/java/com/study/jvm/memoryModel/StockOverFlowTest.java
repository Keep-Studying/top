/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

/**
 * 堆栈溢出测试
 * JVM设置 -Xss128k(默认1M)
 *
 * 输出结果：
 * count is 24874
 * Exception in thread "main" java.lang.StackOverflowError
 * 	at com.study.jvm.memoryModel.StockOverFlowTest.redo(StockOverFlowTest.java:15)
 * 	at com.study.jvm.memoryModel.StockOverFlowTest.redo(StockOverFlowTest.java:15)
 * 	at com.study.jvm.memoryModel.StockOverFlowTest.redo(StockOverFlowTest.java:15)
 * 	...
 * @author study
 * @version : StockOverFlowTest.java, v 0.1 2020年06月22日 23:27 study Exp $
 */
public class StockOverFlowTest {
    static int count = 0;

    static void redo(){
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("count is "+count);
        }
    }
}