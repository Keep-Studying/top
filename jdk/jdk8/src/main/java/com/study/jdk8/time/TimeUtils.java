/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jdk8.time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author study
 * @version : TimeUtils.java, v 0.1 2020年09月08日 23:30 study Exp $
 */
public class TimeUtils {

    /**
     * 获取一个月前的当天时间
     */
    @Test
    public void test01(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        Date monday = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String preMonday = sdf.format(monday);
        System.out.println(preMonday);
    }
}