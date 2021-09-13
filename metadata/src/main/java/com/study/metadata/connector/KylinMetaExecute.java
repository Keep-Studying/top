/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.connector;

import com.study.metadata.domain.ColumnMeta;
import com.study.metadata.domain.Constant;
import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.TableMeta;
import com.study.metadata.util.DBUtils;
import com.study.metadata.util.DatasourcePool;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KylinMetaExecute
 *
 * @author boyan
 * @version : KylinMetaExecute.java, v 0.1 2021-08-17 00:15 boyan
 */
public class KylinMetaExecute {

    public List<TableMeta> getMetadata(DatasourceInfo info,List<TableMeta> tableList) {
        Connection conn = null;
        ResultSet columnMeta = null;
        List<TableMeta> tableMetas = null;

        ResultSet JDBCTableMeta = null;
        try {
            conn = DatasourcePool.getHikariDataSource(info).getConnection();
            DatabaseMetaData metaData = null;
            metaData = conn.getMetaData();

            JDBCTableMeta = metaData.getTables(info.getDbName(), null, null, null);
//            JDBCTableMeta = metaData.getTables(null, null, null, null);

            tableMetas = new LinkedList<>();
            Map<String, TableMeta> tableMap = new HashMap<>(16);
            while (JDBCTableMeta.next()) {
                String catalogName = JDBCTableMeta.getString(1);
                String schemaName = JDBCTableMeta.getString(2);

                // Not every JDBC data provider offers full 10 columns, e.g., PostgreSQL has only 5
                TableMeta tblMeta = new TableMeta(catalogName == null ? Constant.FakeCatalogName : catalogName,
                    schemaName == null ? Constant.FakeSchemaName : schemaName, JDBCTableMeta.getString(3),
                    JDBCTableMeta.getString(4), JDBCTableMeta.getString(5), null, null, null, null, null);

/*                if (!"metadata".equalsIgnoreCase(tblMeta.getTABLE_SCHEM())) {
                }*/
                tableMetas.add(tblMeta);
                tableMap.put(tblMeta.getTABLE_SCHEM() + "#" + tblMeta.getTABLE_NAME(), tblMeta);
            }

            columnMeta = metaData.getColumns(null, null, null, null);

            while (columnMeta.next()) {
                String catalogName = columnMeta.getString(1);
                String schemaName = columnMeta.getString(2);

                // kylin(optiq) is not strictly following JDBC specification
                ColumnMeta colmnMeta = new ColumnMeta(catalogName == null ? Constant.FakeCatalogName : catalogName,
                    schemaName == null ? Constant.FakeSchemaName : schemaName, columnMeta.getString(3),
                    columnMeta.getString(4), columnMeta.getInt(5), columnMeta.getString(6), columnMeta.getInt(7),
                    getInt(columnMeta.getString(8)), columnMeta.getInt(9), columnMeta.getInt(10),
                    columnMeta.getInt(11), columnMeta.getString(12), columnMeta.getString(13),
                    getInt(columnMeta.getString(14)), getInt(columnMeta.getString(15)), columnMeta.getInt(16),
                    columnMeta.getInt(17), columnMeta.getString(18), columnMeta.getString(19),
                    columnMeta.getString(20), columnMeta.getString(21), getShort(columnMeta.getString(22)),
                    columnMeta.getString(23));

                if (tableMap.get(colmnMeta.getTABLE_SCHEM() + "#" + colmnMeta.getTABLE_NAME()) != null) {
                    tableMap.get(colmnMeta.getTABLE_SCHEM() + "#" + colmnMeta.getTABLE_NAME()).addColumn(colmnMeta);
                }
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        } finally {
            close(columnMeta, null, conn);
            if (JDBCTableMeta != null) {
                try {
                    JDBCTableMeta.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        Map<String, String> collect =
            tableList.stream().collect(Collectors.toMap(s -> s.getTABLE_NAME(), s -> s.getTABLE_NAME()));
        List<TableMeta> result =
            tableMetas.stream().filter(key -> collect.containsKey(key.getTABLE_NAME())).collect(Collectors.toList());

        return result;

    }

    protected int getInt(String content) {
        try {
            return Integer.parseInt(content);
        } catch (Exception e) {
            return -1;
        }
    }

    protected short getShort(String content) {
        try {
            return Short.parseShort(content);
        } catch (Exception e) {
            return -1;
        }
    }

    protected static void close(ResultSet resultSet, Statement stat, Connection conn) {
        DBUtils.closeQuietly(resultSet);
        DBUtils.closeQuietly(stat);
        DBUtils.closeQuietly(conn);
    }
}