/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.schedule;

import lombok.Data;

/**
 * @author study
 * @version : HeartBeat.java, v 0.1 2020年09月04日 0:51 study Exp $
 */
@Data
public class HeartBeat {
    private String ip;
    private int port;
    private String appName;
    private String instanceId;
}