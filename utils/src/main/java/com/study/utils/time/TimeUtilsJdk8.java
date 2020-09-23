/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.utils.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author study
 * @version : TimeUtilsJdk8.java, v 0.1 2020年09月09日 0:20 study Exp $
 */
public class TimeUtilsJdk8 {
    /**
     * Java8 时间工具
     * of() 用于获取自定义时间
     * now() 用于获取当前时间
     * plusDays(),plusHours(),plusMinutes(),plusSeconds() 参数是Lang类型 整数是加时间 负数减时间
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        /**
         * 自定义时间
         */

        // 获取某个时间 时间格式 yyyy-MM-dd
        LocalDate getDay = LocalDate.of(1993, 2, 2);
        //获取当前个月1号
        LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        //获取当前月个月31号
        LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 30);
        // 自定义时间 时间格式 23:34:34.221
        LocalTime getLocalTime = LocalTime.of(23, 34, 34);
        //自定义时间 时间格式1993-01-01T06:53
        LocalDateTime getLocalDateTime = LocalDateTime.of(1993, 1, 1, 6, 53);
        System.out.println(getLocalDateTime);


        /**
         * 获取当前时间
         */
        // 获取当前时间 时间格式 08:59:23.221
        LocalTime localTime = LocalTime.now();
        //获取当前时间  时间格式 2019-08-01T08:56:38.914
        LocalDateTime localDateTime = LocalDateTime.now();
        //获取当前时间 时间格式 yyyy-MM-dd
        LocalDate day = LocalDate.now();

        /**
         * 时间加减
         */
        //加10天 负数是减
        LocalDateTime plusDays = localDateTime.plusDays(10l);
        //加10小时
        localDateTime.plusHours(10l);
        //加10分钟
        localDateTime.plusMinutes(10l);
        //加10秒
        localDateTime.plusSeconds(10l);
        System.out.println("当前时间是" + localDateTime + "," + "加10天时间是" + plusDays);

        //获取当前时间 时间格式 2019-08-01T02:06:26.519Z 格林威治时间
        Instant instantTime = Instant.now();
        System.out.println(instantTime);
        /**
         * 获取特定时间
         * TemporalAdjusters 时间工具类 可以获取特定时间
         */
        //获取每月第一天
        LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        //获取每月的最后1天
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime lastDayTime = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());

        /**
         * 时间格式转化
         * HH 大写24  hh小写12小时
         */
        String str = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss").format(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //String 转 LocalDate  参数的时间格式必须是2019-07-11,否则会报错
        LocalDate parse = LocalDate.parse("2019-07-11");
        LocalDateTime parseDataTime = LocalDateTime.of(parse, LocalTime.of(00, 00, 00));
        System.out.println("转换后的时间" + formatter.format(parseDataTime));

        /**
         * 时间类型转换
         * LocalDateTime 转Date
         */

        Date from = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        /**
         * Date 转换 LocalDateTime
         */
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        /*
         * 计算时间差
         * Period 方法getYears（），getMonths（）和getDays（）来计算
         * Duration 基于时间的值（如秒，纳秒）测量时间量的方法。
         * ChronoUnit  用于在单个时间单位内测量一段时间，例如天数或秒
         * ChronoUnit.HOURS ChronoUnit.MONTHS 等
         */
        /**/
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.of(day.getYear(), day.getMonth(), 30);
        Period between = Period.between(end, start);
        System.out.println(between.getYears() + "," + between.getDays() + "," + between.getMonths());
        Duration.between(LocalTime.now(), LocalTime.now());
        /*计算时间差 秒*/
        ChronoUnit.SECONDS.between(LocalDateTime.now(), LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()));
        /*计算时间差 分钟*/
        ChronoUnit.MILLIS.between(LocalDateTime.now(), LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()));
        /*其他类似*/

    }
}