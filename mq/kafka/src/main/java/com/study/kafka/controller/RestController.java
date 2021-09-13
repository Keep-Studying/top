/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.study.kafka.convert.BaseConverterManager;
import com.study.kafka.convert.ConverterManagerFactory;
import com.study.kafka.dataobject.DataSetDO;
import com.study.kafka.domain.*;
import com.study.kafka.manager.DataSetManager;
import com.study.kafka.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author boyan
 * @version : RestController.java, v 0.1 2021年07月15日 11:49 上午 boyan Exp $
 */
@org.springframework.web.bind.annotation.RestController
@EnableScheduling
public class RestController {
    private static final Logger LOG = LoggerFactory.getLogger(RestController.class);

    private final static String TOPIC_NAME = "test-topic";
    private final static String TOPIC_META = "TP_S_METADATA";
    private static final String TNT_INST_ID = "default";
    private static final String SCENE_CODE = "sceneCode";
    private static final String CHANNEL = "channel";
    private static final String INVOKE_SYSTEM = "invokeSystem";

    @Autowired
    private SenderService sendService;

    @Autowired
    private DataSetManager dataSetManager;

    @RequestMapping(value = "/send/updateNotices", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendUpdateNotices() {
        ModelMap map = new ModelMap();
        List<UniformEvent<UpdateNotice>> uniformEvents = this.scheduleExecute();
        map.put("uniformEvents", uniformEvents);
        return map;
    }
    /**
     * 定时任务
     * 每天晚上22点执行
     */
    @Scheduled(cron="0 0 22 1/1 * ? ")
    public List<UniformEvent<UpdateNotice>> scheduleExecute(){
        List<UniformEvent<UpdateNotice>> uniformEvents = new ArrayList<>();
        BaseConverterManager<DataSetDO, DataSet> converter = ConverterManagerFactory.getDataSetConverter();
        List<DataSetDO> dataSetList = dataSetManager.selectList();
        if(!CollectionUtils.isEmpty(dataSetList)){
            dataSetList.stream().forEach(item->{
                if(item != null){
                    DataSet dataSet = converter.convertFromDto(item);
                    if(!CollectionUtils.isEmpty(dataSet.getConnections())){
                        UniformEvent<UpdateNotice> uniformEvent = new UniformEvent<>();
                        uniformEvent.setTopic(TOPIC_META);
                        uniformEvent.setEventCode("EC_UPDATENOTICE");
                        uniformEvent.setEventId(UUID.randomUUID().toString());
                        uniformEvent.setSceneCode(SCENE_CODE);
                        uniformEvent.setChannel(CHANNEL);
                        uniformEvent.setEventTime(new Date());
                        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
                        uniformEvent.setTntInstId(TNT_INST_ID);

                        UpdateNotice payload = new UpdateNotice();
                        payload.setDataSourceId(dataSet.getConnections().get(0).getDataSourceId());
                        payload.setTableName(dataSet.getConnections().get(0).getTableName());
                        uniformEvent.setPayload(payload);

                        sendService.sendMsg(uniformEvent);
                        uniformEvents.add(uniformEvent);
                    }
                }
            });
        }
        LOG.info("uniformEvents is :{}",JSON.toJSONString(uniformEvents));
        return uniformEvents;
    }

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

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

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

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

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

    @RequestMapping(value = "/send/dataSetConnectorType", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendDataSetConnectorType(@RequestParam String datasourceId,@RequestParam String connectorType,@RequestParam String version) {
        UniformEvent<Object> uniformEvent = new UniformEvent();

        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_DATASET");
        uniformEvent.setEventId(UUID.randomUUID().toString());

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

        DataSet payload = new DataSet();
        payload.setDataSetId("DataSetId"+datasourceId);
        payload.setVersion(version);
        payload.setUk(payload.getDataSetId() + "+" + payload.getVersion());
        payload.setPortal("CAP");
        payload.setName("Name");

        ArrayList<DataAuth> dataAuths = new ArrayList<>();
        DataAuth dataAuth = new DataAuth();
        dataAuth.setAuthId("CAP+rule1");
        dataAuth.setAuthColumn("AuthColumn");
        dataAuths.add(dataAuth);
        payload.setDataAuths(dataAuths);

        ArrayList<DataColumnSub> dataColumnSubs = new ArrayList<>();
        DataColumnSub dataColumnSub = new DataColumnSub();
        dataColumnSub.setColumnId("col112");
        dataColumnSub.setColumnIndex(1);
        dataColumnSub.setColumnType("VARCHAR(4096)");
        dataColumnSub.setType("String");
        dataColumnSubs.add(dataColumnSub);

        ConnectorTypeEnum connector = ConnectorTypeEnum.getByCode(connectorType);
        switch (connector) {
            case MYSQL: {
                ArrayList<Connection> connections = new ArrayList<>();
                Connection connection = new Connection();
                connection.setDataSourceId(datasourceId);
                connection.setType(ConnectorTypeEnum.MYSQL.getCode());
                connection.setTableName("TableName");
                connections.add(connection);
                payload.setConnections(connections);

                ArrayList<DataTableSub> dataTableSubs = new ArrayList<>();

                ArrayList<DataColumnSub> dataColumnSubList = new ArrayList<>();
                DataColumnSub dataColumnSub1 = new DataColumnSub();
                dataColumnSub1.setColumnId("id");
                dataColumnSub1.setColumnName("id");
                dataColumnSubList.add(dataColumnSub1);

//                DataColumnSub dataColumnSub2 = new DataColumnSub();
//                dataColumnSub2.setColumnId("gmt_create");
//                dataColumnSub2.setColumnName("gmt_create");
//                dataColumnSubList.add(dataColumnSub2);

//                dataTableSubs.add(new DataTableSub().setName("test_wd001").setDataTableId("test_wd001").setDataSourceId(datasourceId).setColumns(dataColumnSubList));
//                dataTableSubs.add(new DataTableSub().setName("ams_auth").setDataTableId("ams_auth").setDataSourceId(datasourceId).setColumns(dataColumnSubList));
                dataTableSubs.add(new DataTableSub().setName("ams_board").setDataTableId("ams_board").setDataSourceId(datasourceId).setColumns(dataColumnSubList));
                dataTableSubs.add(new DataTableSub().setName("ams_datasource").setDataTableId("ams_datasource").setDataSourceId(datasourceId).setColumns(dataColumnSubList));
                payload.setTables(dataTableSubs);
                uniformEvent.setPayload(payload);
                break;
            }
            case GAUSS: {
                ArrayList<Connection> connections = new ArrayList<>();
                Connection connection = new Connection();
                connection.setDataSourceId(datasourceId);
                connection.setType(ConnectorTypeEnum.GAUSS.getCode());
                connection.setTableName("TableName");
                connections.add(connection);
                payload.setConnections(connections);

                ArrayList<DataColumnSub> dataColumnSubList = new ArrayList<>();
                DataColumnSub dataColumnSub1 = new DataColumnSub();
                dataColumnSub1.setColumnId("id");
                dataColumnSub1.setColumnName("id");
                dataColumnSubList.add(dataColumnSub1);

                ArrayList<DataTableSub> dataTableSubs = new ArrayList<>();
                dataTableSubs.add(new DataTableSub().setName("table1").setSchemaName("public").setColumns(dataColumnSubList));
                dataTableSubs.add(new DataTableSub().setName("table2").setSchemaName("public").setColumns(dataColumnSubList));
//                dataTableSubs.add(new DataTableSub().setName("mytable").setColumns(dataColumnSubs));
                payload.setTables(dataTableSubs);
                uniformEvent.setPayload(payload);
                break;
            }
            case KYLIN: {
                ArrayList<Connection> connections = new ArrayList<>();
                Connection connection = new Connection();
                connection.setDataSourceId(datasourceId);
                connection.setType(ConnectorTypeEnum.KYLIN.getCode());
                connection.setTableName("TableName");
                connections.add(connection);
                payload.setConnections(connections);

                ArrayList<DataTableSub> dataTableSubs = new ArrayList<>();
                dataTableSubs.add(new DataTableSub().setName("DEFAULT.KYLIN_SALES"));
                dataTableSubs.add(new DataTableSub().setName("DEFAULT.KYLIN_ACCOUNT"));
                dataTableSubs.add(new DataTableSub().setName("DEFAULT.STAPLES"));
                dataTableSubs.add(new DataTableSub().setName("DEFAULT.CALCS"));
                dataTableSubs.add(new DataTableSub().setName("DEFAULT.KYLIN_CAL_DT"));
//                dataTableSubs.add(new DataTableSub().setName("DEFAULT.KYLIN_COUNTRY").setColumns(dataColumnSubs));
                payload.setTables(dataTableSubs);
                uniformEvent.setPayload(payload);
                break;
            }
            default:
                throw new RuntimeException();
        }
        uniformEvent.setPayload(payload);

        System.out.println(JSON.toJSONString(uniformEvent));
        sendService.sendMsg(uniformEvent);

        ModelMap map = new ModelMap();
        map.put("uniformEvent", uniformEvent);
        return map;
    }

    @RequestMapping(value = "/send/dataSource", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap sendDataSource(@RequestBody DataSource dataSource) {

        UniformEvent<DataSource> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(TOPIC_META);
        uniformEvent.setEventCode("EC_DATASOURCE");
        uniformEvent.setEventId(UUID.randomUUID().toString());

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

/*        DataSource payload = new DataSource();

        payload.setId(datasourceId);
        payload.setName("数据源名字");
        payload.setServer("1111.111.11.11");
        payload.setPort("20");
        payload.setProject(project);
        payload.setUserName("zhangsan");
        payload.setPassword("zhangsan");
        payload.setType("KYLIN");*/

        uniformEvent.setPayload(dataSource);

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

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

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

        uniformEvent.setSceneCode(SCENE_CODE);
        uniformEvent.setChannel(CHANNEL);
        uniformEvent.setEventTime(new Date());
        uniformEvent.setInvokeSystem(INVOKE_SYSTEM);
        uniformEvent.setTntInstId(TNT_INST_ID);

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