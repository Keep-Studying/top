/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.connector;

/**
 * Db2DbExecute 暂不支持
 *
 * @author boyan
 * @version : Db2DbExecute.java, v 0.1 2021-08-12 15:15 boyan
 */
public class Db2DbExecute {

    /*private static String DB2_Table_COLUMNS = "SELECT c.TABSCHEMA schemaName,c.TABNAME tableName,c.COLNAME columnName,c.COLNO position,c.TYPESCHEMA,c.TYPENAME columnType,c.LENGTH width,c.LENGTH scale,c.SCALE precision,c.KEYSEQ,c.PAGEVARIANCERATIO,c.SECLABELNAME,c.REMARKS alias,k.COLSEQ columnIndex FROM syscat.columns c \n" +

        "left join syscat.keycoluse k on  c.TABSCHEMA = k.TABSCHEMA and c.TABNAME = k.TABNAME and c.COLNAME = k.COLNAME \n" +

        "WHERE c.tabschema = '%s' and c.TABNAME  = '%s' group by c.TABSCHEMA,c.TABNAME,c.COLNAME,c.COLNO,c.TYPESCHEMA,c.TYPENAME,c.LENGTH,c.SCALE,c.KEYSEQ,c.PAGEVARIANCERATIO,c.SECLABELNAME,c.REMARKS,k.COLSEQ\n" +

        "order by c.colno";

    private static String DB2_SCHEMA = "SELECT tabschema schemaName, tabname simpleTableName, remarks alias FROM SYSCAT.TABLES WHERE TABSCHEMA IN ( SELECT SCHEMANAME FROM SYSCAT.SCHEMATA WHERE DEFINERTYPE = 'U') order by tabschema,tabname";

    public List<DB2Table> getTableInfo(DatasourceInfo info) {

        List<DB2Table> schemas = selectSchemas(DatasourcePool.getHikariDataSource(info), DB2Table.class);

        log.info("获取DB2表元数据成功");

        if (CollectionUtils.isEmpty(schemas)) {

            return new ArrayList<>();

        }

        return schemas;

    }



    public List<DB2Column> getColumnInfo(DatasourceInfo info, DB2Table table) {

        boolean tabAuth = checkTableAuth(DatasourcePool.getHikariDataSource(info), table);

        if (!tabAuth) {

            throw new RemoteServerException(table.getTableName() + "无查询权限/网络不通");

        }

        List<DB2Column> db2Columns = selectTableColumns(DatasourcePool.getHikariDataSource(info), DB2Column.class, table);

        for (DB2Column db2Column : db2Columns) {

            db2Column.calcRole();

        }

        return db2Columns;

    }



    public SqlRowSetMetaData querySql(DatasourceInfo info, String sql) {

        SqlRowSet set = JDBCUtil.queryRowSet(DatasourcePool.getHikariDataSource(info), sql);

        return set.getMetaData();

    }



    private <T> List<T> selectSchemas(DataSource dataSource, Class<T> clazz) {

        return JDBCUtil.selectList(dataSource, clazz, DB2_SCHEMA);

    }



    private boolean checkTableAuth(DataSource dataSource, DB2Table table) {

        try {

            JDBCUtil.queryRowSet(dataSource, String.format("select 1 from %s.\"%s\"", table.getSchemaName(), table.getSimpleTableName()));

        } catch (Exception e) {

            return false;

        }

        return true;

    }



    private <T> List<T> selectTableColumns(DataSource dataSource, Class<T> clazz, DB2Table table) {

        return JDBCUtil.selectList(dataSource, clazz, String.format(DB2_Table_COLUMNS, table.getSchemaName(), table.getSimpleTableName()));

    }*/
}