package com.study.metadata.connector;

import com.alibaba.fastjson.JSON;
import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.TableMeta;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * KylinMetaExecuteTest
 *
 * @author boyan
 * @version : KylinMetaExecuteTest.java, v 0.1 2021/8/17 00:23 boyan Exp $
 */
public class KylinMetaExecuteTest {

    KylinMetaExecute execute;
    DatasourceInfo datasourceInfo ;

    @Test public void getMetadata() {
        init();
        List<TableMeta> tableList = new ArrayList<>();
//        tableList.add(new TableMeta().setTABLE_NAME("ams_board"));
//        tableList.add(new TableMeta().setTABLE_NAME("ams_auth"));
        tableList.add(new TableMeta().setTABLE_NAME("ams_datacolumn"));
        List<TableMeta> tableNames = execute.getMetadata(datasourceInfo,tableList);
        System.out.println(JSON.toJSONString(tableNames));
    }

    public void init() {
        execute = new KylinMetaExecute();
        datasourceInfo= new DatasourceInfo();
        datasourceInfo.setUrl("jdbc:mysql://rm-bp13v78q00709p391co.mysql.rds.aliyuncs.com:3306/ams?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
        datasourceInfo.setDriver("com.mysql.cj.jdbc.Driver");
        datasourceInfo.setUserName("root");
        datasourceInfo.setPassword("Aloudata20210520");
        datasourceInfo.setDbName("ams");
    }

    @Test public void getMetadata2() {
        init2();
        List<TableMeta> tableList = new ArrayList<>();
        tableList.add(new TableMeta().setTABLE_NAME("table1"));
        //        tableList.add(new TableMeta().setTABLE_NAME("table2"));
        List<TableMeta> tableNames = execute.getMetadata(datasourceInfo,tableList);
        System.out.println(JSON.toJSONString(tableNames));
    }

    public void init2() {
        execute = new KylinMetaExecute();
        datasourceInfo= new DatasourceInfo();
        datasourceInfo.setUrl("jdbc:postgresql://47.96.161.128:15432/db_tpcds");
        datasourceInfo.setDriver("org.postgresql.Driver");
        datasourceInfo.setUserName("gaussdb");
        datasourceInfo.setPassword("Aloudata@20210520");
        datasourceInfo.setDbName("db_tpcds");
    }
}