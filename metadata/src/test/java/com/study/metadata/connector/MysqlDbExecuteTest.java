package com.study.metadata.connector;

import com.alibaba.fastjson.JSON;
import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.MysqlColumn;
import com.study.metadata.domain.MysqlTable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * MysqlDbExecuteTest
 *
 * @author boyan
 * @version : MysqlDbExecuteTest.java, v 0.1 2021/8/14 22:53 boyan Exp $
 */
public class MysqlDbExecuteTest {


    MysqlDbExecute mysqlDbExecute;
    DatasourceInfo datasourceInfo ;

    @Test public void getTableNames() {
        init();
        List<MysqlTable> tableList = new ArrayList<>();
        tableList.add(new MysqlTable().setTableName("ams_board"));
        tableList.add(new MysqlTable().setTableName("ams_auth"));
        tableList.add(new MysqlTable().setTableName("ams_datacolumn"));
        List<MysqlTable> tableNames = mysqlDbExecute.getTableNames(datasourceInfo,tableList);
        System.out.println(JSON.toJSONString(tableNames));
    }

    @Test public void getColumnInfo() {
        init();
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setTableName("ams_datacolumn");
        List<MysqlColumn> columnInfo = mysqlDbExecute.getColumnInfo(datasourceInfo, mysqlTable);
        System.out.println(JSON.toJSONString(columnInfo));
    }

    @Test public void querySql() {
    }

    public void init() {
        mysqlDbExecute = new MysqlDbExecute();
        datasourceInfo= new DatasourceInfo();
        datasourceInfo.setUrl("jdbc:mysql://rm-bp13v78q00709p391co.mysql.rds.aliyuncs.com:3306/ams?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
        datasourceInfo.setDriver("com.mysql.cj.jdbc.Driver");
        datasourceInfo.setUserName("root");
        datasourceInfo.setPassword("Aloudata20210520");
        datasourceInfo.setDbName("ams");
    }
}