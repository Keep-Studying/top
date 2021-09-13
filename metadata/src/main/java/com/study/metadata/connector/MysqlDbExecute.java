/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.connector;

import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.MysqlColumn;
import com.study.metadata.domain.MysqlTable;
import com.study.metadata.util.DatasourcePool;
import com.study.metadata.util.JDBCUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * MysqlDbExecute
 *
 * @author boyan
 * @version : MysqlDbExecute.java, v 0.1 2021-08-12 15:07 boyan
 */
@Slf4j
public class MysqlDbExecute {
    //    private static String Mysql_Tables = "SELECT table_schema schemaName, table_name simpleTableName, table_comment alias FROM information_schema.tables WHERE table_schema = '%s'";
    private static String Mysql_Tables = "SELECT TABLE_CATALOG tableCatalog,TABLE_SCHEMA tableSchema,TABLE_NAME tableName,TABLE_TYPE tableType,ENGINE engine,VERSION version,ROW_FORMAT rowFormat,TABLE_ROWS tableRows,AVG_ROW_LENGTH avgRowLength,DATA_LENGTH dataLength,MAX_DATA_LENGTH maxDataLength,INDEX_LENGTH indexLength,DATA_FREE dataFree,AUTO_INCREMENT autoIncrement,CREATE_TIME createTime,UPDATE_TIME updateTime,CHECK_TIME checkTime,TABLE_COLLATION tableCollation,CHECKSUM checksum,CREATE_OPTIONS createOptions,TABLE_COMMENT remarks FROM information_schema.tables WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME in (%s);";


    /*private static String Mysql_Table_COLUMNS = "SELECT table_schema                                                                                      schemaName,\n" +

       "       table_name                                                                                        tableName,\n" +

       "       column_name                                                                                       columnName,\n" +

       "       data_type                                                                                         columnType,\n" +

       "       column_key                                                                                        columnIndex,\n" +

       "       column_comment                                                                                    alias,\n" +

       "       case is_nullable when 'NO' then false else true end                                               nullAble,\n" +

       "       case when character_maximum_length > 2147483647 then 2147483647 else character_maximum_length end width,\n" +

       "       case when numeric_precision > 2147483647 then 2147483647 else numeric_precision end               'precision',\n" +

       "       numeric_scale                                                                                     scale,\n" +

       "       ordinal_position                                                                                  position\n" +

       "FROM information_schema.columns\n" +

       "WHERE table_schema = '%s'\n" +

       "  AND table_name = '%s'\n" +

       "order by table_name, ordinal_position";*/
    private static String Mysql_Table_COLUMNS = "SELECT TABLE_CATALOG\ttableCatalog,TABLE_SCHEMA\ttableSchema,TABLE_NAME\ttableName,COLUMN_NAME\tcolumnName,ORDINAL_POSITION\tordinalPosition,COLUMN_DEFAULT\tcolumnDefault,IS_NULLABLE\tisNullable,DATA_TYPE\tdataType,CHARACTER_MAXIMUM_LENGTH characterMaximumLength,CHARACTER_OCTET_LENGTH\tcharacterOctetLength,NUMERIC_PRECISION\tnumericPrecision,NUMERIC_SCALE\tnumericScale,DATETIME_PRECISION\tdatetimePrecision,CHARACTER_SET_NAME\tcharacterSetName,COLLATION_NAME\tcollationName,COLUMN_TYPE\tcolumnType,COLUMN_KEY columnKey,\tEXTRA\textra,PRIVILEGES\tprivileges,COLUMN_COMMENT remarks,\tGENERATION_EXPRESSION\tgenerationExpression,SRS_ID srsId\n"
        + "FROM\n" + "\tinformation_schema.COLUMNS \n" + "WHERE\n" + "\ttable_schema = '%s' \n"
        + "\tAND table_name in ('%s') \n" + "ORDER BY\n" + "\tTABLE_NAME,\n" + "\tORDINAL_POSITION;";



    public List<MysqlTable> getTableNames(DatasourceInfo info,List<MysqlTable> tableList) {

        String dbName = info.getDbName();

        StringBuilder stringBuilder = new StringBuilder();

        for (MysqlTable table:tableList) {
            stringBuilder.append("'").append(table.getTableName()).append("',");
        }
        String tableNames = StringUtils.left(stringBuilder.toString(), stringBuilder.length() - 1);
        List<MysqlTable> tables = selectTables(DatasourcePool.getHikariDataSource(info), MysqlTable.class, dbName,tableNames);

        log.info("获取Mysql表元数据成功");

        if (CollectionUtils.isEmpty(tables)) {

            return new ArrayList<>();

        }

        return tables;

    }



    public List<MysqlColumn> getColumnInfo(DatasourceInfo info, MysqlTable table) {

        boolean tabAuth = checkTableAuth(DatasourcePool.getHikariDataSource(info), table);

        if (!tabAuth) {

            throw new RuntimeException((table.getTableName() + "无查询权限/网络不通"));

        }

        List<MysqlColumn> mysqlColumns = selectTableColumns(DatasourcePool.getHikariDataSource(info), MysqlColumn.class, info.getDbName(), table);

        /*for (MysqlColumn mysqlColumn : mysqlColumns) {

            mysqlColumn.calcRole();

        }*/

        return mysqlColumns;

    }



    public SqlRowSetMetaData querySql(DatasourceInfo info, String sql) {

        SqlRowSet set = JDBCUtil.queryRowSet(DatasourcePool.getHikariDataSource(info), sql);

        return set.getMetaData();

    }



    private <T> List<T> selectTables(DataSource dataSource, Class<T> clazz, String tableSchema,String tableNames) {

        return JDBCUtil.selectList(dataSource, clazz, String.format(Mysql_Tables, tableSchema,tableNames));

    }



    private boolean checkTableAuth(DataSource dataSource, MysqlTable table) {

        try {

            JDBCUtil.queryRowSet(dataSource, String.format("select 1 from `%s`", table.getTableName()));

        } catch (Exception e) {

            return false;

        }

        return true;

    }



    private <T> List<T> selectTableColumns(DataSource dataSource, Class<T> clazz, String tableSchema, MysqlTable table) {

        return JDBCUtil.selectList(dataSource, clazz, String.format(Mysql_Table_COLUMNS, tableSchema, table.getTableName()));

    }
}