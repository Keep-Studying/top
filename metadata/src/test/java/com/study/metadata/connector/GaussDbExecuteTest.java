package com.study.metadata.connector;

import com.alibaba.fastjson.JSON;
import com.study.metadata.domain.DataColumnDO;
import com.study.metadata.domain.DataTableDO;
import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.GaussTable;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * GaussDbExecuteTest
 *
 * @author boyan
 * @version : GaussDbExecuteTest.java, v 0.1 2021/8/15 00:55 boyan Exp $
 */
public class GaussDbExecuteTest {

    GaussDbExecute gaussDbExecute;
    DatasourceInfo datasourceInfo ;

    @Test public void getTableInfo() {
        init();
        List<GaussTable> tableList = new ArrayList<>();
        tableList.add(new GaussTable().setTableName("table1"));
        tableList.add(new GaussTable().setTableName("table2"));
        List<DataTableDO> tableInfo = gaussDbExecute.getTableInfo(datasourceInfo,tableList);
        System.out.println(JSON.toJSONString(tableInfo));
    }

    @Test public void getColumnInfo() {
        init();
        GaussTable gaussTable = new GaussTable().setTableSchema("public").setTableName("table1");
        List<DataColumnDO> columnInfo = gaussDbExecute.getColumnInfo(datasourceInfo, gaussTable);
        System.out.println(JSON.toJSONString(columnInfo));
    }

    @Test public void querySql() {
    }

    public void init() {
        gaussDbExecute = new GaussDbExecute();
        datasourceInfo= new DatasourceInfo();
        datasourceInfo.setUrl("jdbc:postgresql://47.96.161.128:15432/db_tpcds");
        datasourceInfo.setDriver("org.postgresql.Driver");
        datasourceInfo.setUserName("gaussdb");
        datasourceInfo.setPassword("Aloudata@20210520");
        datasourceInfo.setDbName("db_tpcds");
    }

    @Test
    public void test01(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("abc1");
        strings.add("abc2");
        strings.add("abc3");
        StringBuilder stringBuilder = new StringBuilder();

        for (String s:strings) {
            stringBuilder.append("'").append(s).append("',");
        }
        System.out.println(StringUtils.left(stringBuilder.toString(),stringBuilder.length()-1));
    }
}