/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.study.kafka.domain.*;
import com.study.kafka.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 *
 * @author boyan
 * @version : RestController.java, v 0.1 2021年07月15日 11:49 上午 boyan Exp $
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final static String TOPIC_NAME = "test-topic";
    private final static String TOPIC_META = "TP_S_METADATA";

    @Autowired
    private SenderService sendService;

    /**
     * 
     * @author boyan
     * @date 2021/7/26 23:16
     * @param datasourceId
     * @return org.springframework.ui.ModelMap
     */

    @RequestMapping(value = "/send/updateNotice", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap send(@RequestParam String datasourceId) {

        UniformEvent<UpdateNotice> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_UPDATENOTICE");
        uniformEvent.setEventId(UUID.randomUUID().toString());
        UpdateNotice payload = new UpdateNotice();
        payload.setDataSourceId(datasourceId);
        payload.setTableName("kylin_sales_cube");
        payload.setUpdateType("FULL");
        payload.setBizDate("20210716");
        payload.setEndTime("2021-07-16 14:22:00");
        uniformEvent.setPayload(payload);

        // Object parse = JSON.parseObject(json, new TypeReference<UniformEvent<UpdateNotice>>() {});

        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @RequestMapping(value = "/send/updateDataSet", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendDataSet(@RequestParam String version, @RequestParam String datasourceId) {

        UniformEvent<DataSet> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_DATASET");
        uniformEvent.setEventId(UUID.randomUUID().toString());
        DataSet payload = new DataSet();

        payload.setUk("管会数据月表分析数据源(移动).905d0576-113a-47e6-966b-337f6832418f+" + version);
        payload.setDataSetId("管会数据月表分析数据源(移动).905d0576-113a-47e6-966b-337f6832418f");
        payload.setVersion(version);
        payload.setPortal("火眼");
        payload.setName("管会数据月表分析数据源(移动)");

        ArrayList<Connection> connections = new ArrayList<>();
        Connection connection = new Connection();
        connection.setDataSourceId(datasourceId);
        connection.setType("KYLIN");
        connection.setTableName("cube1");
        connections.add(connection);
        payload.setConnections(connections);

        ArrayList<DataAuth> dataAuths = new ArrayList<>();
        DataAuth dataAuth = new DataAuth();
        dataAuth.setAuthId("火眼+rule1");
        dataAuth.setAuthColumn("col112");
        dataAuths.add(dataAuth);
        payload.setDataAuths(dataAuths);

        ArrayList<DataTableSub> dataTableSubs = new ArrayList<>();
        DataTableSub dataTableSub = new DataTableSub();
        dataTableSub.setDataSourceId(datasourceId);
        dataTableSub.setDataTableId("table111");
        dataTableSub.setName("name22");
        dataTableSubs.add(dataTableSub);

        ArrayList<DataColumnSub> dataColumnSubs = new ArrayList<>();
        DataColumnSub dataColumnSub = new DataColumnSub();
        dataColumnSub.setColumnId("col112");
        dataColumnSub.setColumnIndex(1);
        dataColumnSub.setColumnType("VARCHAR(4096)");
        dataColumnSub.setType("String");
        dataColumnSubs.add(dataColumnSub);
        dataTableSub.setColumns(dataColumnSubs);

        payload.setTables(dataTableSubs);

        uniformEvent.setPayload(payload);

        // Object parse = JSON.parseObject(json, new TypeReference<UniformEvent<UpdateNotice>>() {});

        System.out.println(JSON.toJSONString(uniformEvent));
        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @RequestMapping(value = "/send/dataSource", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendDataSource(@RequestParam String datasourceId, @RequestParam String project) {

        UniformEvent<DataSource> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_DATASOURCE");
        uniformEvent.setEventId(UUID.randomUUID().toString());

        DataSource payload = new DataSource();

        payload.setId(datasourceId);
        payload.setName("数据源名字");
        payload.setServer("1111.111.11.11");
        payload.setPort("20");
        payload.setProject(project);
        payload.setUserName("zhangsan");
        payload.setPassword("zhangsan");
        payload.setType("KYLIN");

        uniformEvent.setPayload(payload);

        // Object parse = JSON.parseObject(json, new TypeReference<UniformEvent<UpdateNotice>>() {});

        System.out.println(JSON.toJSONString(uniformEvent));
        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @RequestMapping(value = "/send/auth", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendAuth(@RequestParam String portal, @RequestParam String rule) {

        UniformEvent<Auth> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_AUTH");
        uniformEvent.setEventId(UUID.randomUUID().toString());

        Auth payload = new Auth();

        payload.setAuthId(portal + "+" + rule);
        payload.setPortal(portal);
        payload.setRule(rule);
        payload.setRule("备注");
        List<String> roles = new ArrayList<>();
        roles.add("(1,2)");
        roles.add("(1,3)");
        roles.add("(1,2,3)");
        payload.setRoles(roles);

        uniformEvent.setPayload(payload);

        System.out.println(JSON.toJSONString(uniformEvent));
        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @RequestMapping(value = "/send/board", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendBoard(@RequestParam String boardId, @RequestParam String portal,
        @RequestParam String dataSetId) {

        UniformEvent<Board> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_BOARD");
        uniformEvent.setEventId(UUID.randomUUID().toString());

        Board payload = new Board();

        payload.setBoardId(boardId);
        payload.setPortal(portal);
        payload.setName("看板名");
        payload.setType("RPT");
        payload.setChannel("bix");
        payload.setPriority(2);

        List<String> ids = new ArrayList<>();
        ids.add(dataSetId);
        payload.setDataSetIds(ids);

        uniformEvent.setPayload(payload);

        System.out.println(JSON.toJSONString(uniformEvent));
        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @GetMapping("/send/hello")
    public String sendHello() {
        return "hello world" + new Date();

    }

    @RequestMapping("/send/object")
    @ResponseBody
    public ModelMap sendObject(@RequestBody Board board) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("data", board);
        return modelMap;

    }
}