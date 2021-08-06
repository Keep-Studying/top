package com.study.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.study.kafka.domain.UniformEvent;
import com.study.kafka.domain.UpdateNotice;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author boyan
 * @version : RestControllerTest, v0.1 2021年07月15日 1:12 下午 boyan Exp $
 */

public class RestControllerTest {
    private final static String TOPIC_META = "TP_S_METADATA";

    @Test
    public void test(){
        UniformEvent<UpdateNotice> authUniformEvent = new UniformEvent<>();
        authUniformEvent.setTopic(TOPIC_META);
        authUniformEvent.setEventCode("EC_UPDATENOTICE");
        UpdateNotice payload = new UpdateNotice();
        payload.setDataSourceId("13");
        payload.setTableName("tableName13");
        payload.setUpdateType("FULL");
        payload.setBizDate("20210711");
        payload.setEndTime("2021-07-11 21:38:00");
        authUniformEvent.setPayload(payload);
        System.out.println(JSON.toJSONString(authUniformEvent));
    }

}