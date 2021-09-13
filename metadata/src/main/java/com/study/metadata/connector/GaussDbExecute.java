/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.connector;

import com.study.metadata.domain.DataColumnDO;
import com.study.metadata.domain.DataTableDO;
import com.study.metadata.domain.DatasourceInfo;
import com.study.metadata.domain.GaussTable;
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
 * GaussDbExecute
 *
 * @author boyan
 * @version : GaussDbExecute.java, v 0.1 2021-08-12 15:12 boyan
 */
@Slf4j
public class GaussDbExecute {

    //20200824 LM

//    private static String Gauss_Tables = "SELECT table_schema schemaName, table_name simpleTableName FROM information_schema.table_privileges WHERE grantee = '%s' AND privilege_type = 'SELECT' ORDER BY schemaName, simpleTableName";
   /* private static String Gauss_Tables = "SELECT \n"
    + "table_catalog tableCatalog,table_schema\ttableSchema,table_name tableName,\ttable_type tableType,\tself_referencing_column_name selfReferencingColumnName,\treference_generation referenceGeneration,\tuser_defined_type_catalog userDefinedTypeCatalog,\tuser_defined_type_schema\tuserDefinedTypeSchema,user_defined_type_name userDefinedTypeName,\tis_insertable_into isInsertableInto,\tis_typed isTyped,\tcommit_action commitAction\n"
    + "FROM\n" + "\tinformation_schema.tables \n" + "WHERE\n" + " table_catalog = '%s'\n"
    + " AND table_name in (%s)\n" + "ORDER BY  table_name";*/

    private static String Gauss_Tables ="SELECT table_catalog tableCat,table_schema tableSchema,table_name tableName,table_type tableType FROM information_schema.tables WHERE table_catalog = '%s' AND table_name in (%s) ORDER BY table_name;";

//    private static String Gauss_Table_COLUMNS = "select schemaname, tablename, columnname, columntype, columnindex, alias, nullable, width, precision, scale, position from (select nsp.nspname as schemaname, tbl.relname as tablename, col.attname as columnname, ty.typname as columntype, ind.index_name as columnindex, des.description as alias, case when col.attnotnull then 'n'::bpchar else 'y'::bpchar end as nullable, case col.attlen < 0 and ty.typname = 'varchar'::name and spa.nspname = 'pg_catalog'::name when true then col.atttypmod - 4 else col.attlen::integer end as width, case when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod <> (-1) then ((col.atttypmod - 4) >> 16) & 65535 else null::integer end as precision, case when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod <> (-1) then (col.atttypmod - 4) & 65535 when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod = (-1) then null::integer else 0 end as scale, col.attnum::integer as position, row_number() over(partition by nsp.nspname, tbl.relname, col.attname, ty.typname, des.description, case when col.attnotnull then 'n'::bpchar else 'y'::bpchar end, case col.attlen < 0 and ty.typname = 'varchar'::name and spa.nspname = 'pg_catalog'::name when true then col.atttypmod - 4 else col.attlen::integer end, case when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod <> (-1) then ((col.atttypmod - 4) >> 16) & 65535 else null::integer end, case when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod <> (-1) then (col.atttypmod - 4) & 65535 when ty.typname = 'numeric'::name and spa.nspname = 'pg_catalog'::name and col.atttypmod = (-1) then null::integer else 0 end, col.attnum::integer order by ind.index_name ) as INDEX_NBR from pg_attribute col join pg_class tbl on col.attrelid = tbl.oid join pg_type ty on col.atttypid = ty.oid left join pg_description des on col.attrelid = des.objoid and col.attnum = des.objsubid join pg_namespace spa on spa.oid = ty.typnamespace join pg_namespace nsp on nsp.oid = tbl.relnamespace left join all_ind_columns ind on tbl.relname = ind.table_name and nsp.nspname = ind.table_owner and col.attname = ind.column_name where col.attnum > 0 and tbl.relkind in ('r', 'v') and not col.attisdropped and (pg_has_role(tbl.relowner, 'usage') or has_table_privilege(tbl.oid, 'select') or has_any_column_privilege(tbl.oid, 'select')) and schemaname = '%s' and tablename = '%s'order by position ) s where s.Index_NBR = 1";
    private static String Gauss_Table_COLUMNS1 = "SELECT\n" + "\tnsp.nspname AS tableSchema,\n"
    + "\ttbl.relname AS tableName,\n" + "\tcol.attname AS columnName,\n" + "\tty.typname AS columnType,\n"
    + "\tdes.description AS columnComment,\n" + "CASE\n" + "\t\t\n" + "\t\tWHEN col.attnotnull THEN\n"
    + "\t\t'NO' :: bpchar ELSE'YES' :: bpchar \n" + "\tEND AS isNullable,\n" + "CASE\n" + "\t\tcol.attlen < 0 \n"
    + "\t\tAND ty.typname = 'varchar' :: NAME \n" + "\t\tAND spa.nspname = 'pg_catalog' :: NAME \n"
    + "\tWHEN TRUE THEN\n" + "\t\tcol.atttypmod - 4 ELSE col.attlen :: INTEGER \n"
    + "\tEND AS characterMaximumLength,\n" + "CASE\n" + "\t\tWHEN ty.typname = 'numeric' :: NAME \n"
    + "\t\tAND spa.nspname = 'pg_catalog' :: NAME \n" + "\t\tAND col.atttypmod <> ( - 1 ) THEN\n"
    + "\t\t\t( ( col.atttypmod - 4 ) >> 16 ) & 65535 ELSE NULL :: INTEGER \n" + "\t\tEND AS numericPrecision,\n"
    + "\tCASE\n" + "\t\t\tWHEN ty.typname = 'numeric' :: NAME \n" + "\t\t\tAND spa.nspname = 'pg_catalog' :: NAME \n"
    + "\t\t\tAND col.atttypmod <> ( - 1 ) THEN\n" + "\t\t\t\t( col.atttypmod - 4 ) & 65535 \n"
    + "\t\t\t\tWHEN ty.typname = 'numeric' :: NAME \n" + "\t\t\t\tAND spa.nspname = 'pg_catalog' :: NAME \n"
    + "\t\t\t\tAND col.atttypmod = ( - 1 ) THEN\n" + "\t\t\t\t\tNULL :: INTEGER ELSE 0 \n"
    + "\t\t\t\tEND AS numericScale,\n" + "\t\t\t\tcol.attnum :: INTEGER AS ordinalPosition \n" + "\t\t\tFROM\n"
    + "\t\t\t\tpg_attribute col\n" + "\t\t\t\tJOIN pg_class tbl ON col.attrelid = tbl.oid\n"
    + "\t\t\t\tJOIN pg_type ty ON col.atttypid = ty.oid\n"
    + "\t\t\t\tLEFT JOIN pg_description des ON col.attrelid = des.objoid \n" + "\t\t\t\tAND col.attnum = des.objsubid\n"
    + "\t\t\t\tJOIN pg_namespace spa ON spa.oid = ty.typnamespace\n"
    + "\t\t\t\tJOIN pg_namespace nsp ON nsp.oid = tbl.relnamespace \n" + "\t\t\tWHERE\n" + "\t\t\t\tcol.attnum > 0 \n"
    + "\t\t\t\tAND tbl.relkind IN ( 'r', 'v' ) \n" + "\t\t\t\tAND NOT col.attisdropped \n"
    + "\t\t\t\tAND ( pg_has_role ( tbl.relowner, 'usage' ) OR has_table_privilege ( tbl.oid, 'select' ) OR has_any_column_privilege ( tbl.oid, 'select' ) ) \n"
    + "\t\t\t\tAND tableSchema = '%s' \n" + "\t\t\t\tAND tablename = '%s' \n" + "\t\tORDER BY\n"
    + "ordinalPosition";
private static String Gauss_Table_COLUMNS = "SELECT \n"
    + "table_catalog tableCatalog,\ttable_schema tableSchema,\ttable_name tableName,\tcolumn_name columnName,\tordinal_position ordinalPosition,\tcolumn_default columnDefault,\tis_nullable isNullable,\tdata_type dataType,\tcharacter_maximum_length characterMaximumLength,\tcharacter_octet_length characterOctetLength,\tnumeric_precision numericPrecision,\tnumeric_precision_radix numericPrecisionRadix,\tnumeric_scale numericScale,\tdatetime_precision datetimePrecision,\tinterval_type intervalType,\tinterval_precision intervalPrecision,\tcharacter_set_catalog characterSetCatalog,\tcharacter_set_schema characterSetSchema,\tcharacter_set_name characterSetName,\tcollation_catalog collationCatalog,\tcollation_schema collationSchema,\tcollation_name collationName,\tdomain_catalog domainCatalog,\tdomain_schema domainSchema,\tdomain_name domainName,\tudt_catalog udtCatalog,\tudt_schema udtSchema,\tudt_name udtName,\tscope_catalog scopeCatalog,\tscope_schema scopeSchema,\tscope_name scopeName,\tmaximum_cardinality maximumCardinality,\tdtd_identifier dtdIdentifier,\tis_self_referencing isSelfReferencing,\tis_identity isIdentity,\tidentity_generation identityGeneration,\tidentity_start identityStart,\tidentity_increment identityIncrement,\tidentity_maximum identityMaximum,\tidentity_minimum identityMinimum,\tidentity_cycle identityCycle,\tis_generated isGenerated,\tgeneration_expression generationExpression,\tis_updatable isUpdatable\n"
    + "FROM information_schema.COLUMNS WHERE table_catalog = '%s' AND table_schema = '%s' AND table_name = '%s'";

    private static String SQL_SELECT_COLUMNS = "SELECT table_catalog tableCat,table_schema tableSchema,table_name tableName,column_name columnName,ordinal_position ordinalPosition,column_default columnDef,is_nullable isNullable,data_type typeName,character_maximum_length characterMaximumLength,character_octet_length charOctetLength,numeric_precision columnSize,numeric_precision_radix numPrecRadix,numeric_scale numericScale,character_set_name characterSetName,collation_name collationName,udt_name udtName,scope_catalog scopeCatlog,scope_schema scopeSchema,scope_name scopeName,is_self_referencing isSelfReferencing,is_identity isIdentity FROM information_schema.COLUMNS WHERE table_catalog = '%s' AND table_name in ('%s') ORDER BY TABLE_NAME,ORDINAL_POSITION;";

    //获取Gauss表详细信息

    public List<DataTableDO> getTableInfo(DatasourceInfo info,List<GaussTable> tableList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (GaussTable table:tableList) {
            stringBuilder.append("'").append(table.getTableName()).append("',");
        }
        String tableNames = StringUtils.left(stringBuilder.toString(), stringBuilder.length() - 1);
        List<DataTableDO> tables = selectTables(DatasourcePool.getHikariDataSource(info), DataTableDO.class, info.getDbName(),tableNames);

        log.info("获取Gauss表元数据成功");

        if (CollectionUtils.isEmpty(tables)) {

            return new ArrayList();

        }

        return tables;



    }



    //获取Gauss表结构（字段）详细信息

    public List<DataColumnDO> getColumnInfo(DatasourceInfo info, GaussTable table) {

        boolean tabAuth = checkTableAuth(DatasourcePool.getHikariDataSource(info), table);

        if (!tabAuth) {

            throw new RuntimeException(table.getTableName() + "无查询权限/网络不通");

        }

        List<DataColumnDO> gaussColumns = selectTableColumns(DatasourcePool.getHikariDataSource(info), DataColumnDO.class,info.getDbName(), table);

        /*for (GaussColumn gaussColumn : gaussColumns) {

            gaussColumn.calcRole();

        }*/

        return gaussColumns;

    }



    //自由sql查询

    public SqlRowSetMetaData querySql(DatasourceInfo info, String sql) {

        SqlRowSet set = JDBCUtil.queryRowSet(DatasourcePool.getHikariDataSource(info), sql);

        return set.getMetaData();

    }



    private boolean checkTableAuth(DataSource dataSource, GaussTable table) {

        try {

            JDBCUtil.queryRowSet(dataSource, String.format("select 1 from \"%s\".\"%s\"", table.getTableSchema(), table.getTableName()));

        } catch (Exception e) {

            return false;

        }

        return true;

    }



    private <T> List<T> selectTables(DataSource dataSource, Class<T> clazz, String userName,String tableNames) {

        return JDBCUtil.selectList(dataSource, clazz, String.format(Gauss_Tables, userName,tableNames));

    }



    private <T> List<T> selectTableColumns(DataSource dataSource, Class<T> clazz,String catalog, GaussTable table) {

        return JDBCUtil.selectList(dataSource, clazz, String.format(SQL_SELECT_COLUMNS,catalog, table.getTableName()));

    }
}