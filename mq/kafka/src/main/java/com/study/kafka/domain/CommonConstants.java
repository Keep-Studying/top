/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

/**
 * CommonConstants
 *
 * @author boyan
 * @version : CommonConstants.java, v 0.1 2021-09-01 00:26 boyan
 */
public class CommonConstants {
    /**
     * 系统名称
     */
    public static final String SYSTEM_NAME = "mq";
    /**
     * 线程池
     */
    public static final String THREAD_POOL_TASK_EXECUTOR = "-threadPoolTaskExecutor-";



    /**
     * 日志默认值
     */
    public static final String LOG_DEFAULT = "-";

    /**
     * 日志前缀
     */
    public static final String LOG_PREFIX = "[";

    /**
     * 日志后缀
     */
    public static final String LOG_SUFFIX = "]";

    /**
     * 日志参数前缀
     */
    public static final String LOG_PARAM_PREFIX = "(";

    /**
     * 日志参数后缀
     */
    public static final String LOG_PARAM_SUFFIX = ")";

    /**
     * 日志分隔符(英文分号)
     */
    public static final String LOG_SEP_COMMA = ",";

    /**
     * 日志分隔符(英文点号)
     */
    public static final String LOG_SEP_POINT = ".";

    /**
     * 冒号
     */
    public static final String LOG_COLON = ":";

    /**
     * 成功
     */
    public static final String YES = "Y";

    /**
     * 失败
     */
    public static final String NO = "N";

    /**
     * 时间
     */
    public static final String TIME_UNIT = "ms";


    /**
     * DAO层摘要日志
     *
     * <ol>
     * <li>level:INFO
     * <li>输出目标:AMS-dal-digest.log
     * </ol>
     */
    public static String MQ_DAL_DIGEST = "MQ-DAL-DIGEST";
}