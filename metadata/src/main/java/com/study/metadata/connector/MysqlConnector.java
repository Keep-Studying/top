/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.connector;

import com.study.metadata.domain.DbConnConf;
import com.study.metadata.util.ConnectionUtil;

import java.sql.*;

/**
 * MysqlConnector
 *
 * @author boyan
 * @version : MysqlConnector.java, v 0.1 2021-08-12 12:47 boyan
 */
public class MysqlConnector {

    private static String Mysql_Table_COLUMNS =
        "SELECT table_schema                                                                                      schemaName,\n"
            +

            "       table_name                                                                                        tableName,\n"
            +

            "       column_name                                                                                       columnName,\n"
            +

            "       data_type                                                                                         columnType,\n"
            +

            "       column_key                                                                                        columnIndex,\n"
            +

            "       column_comment                                                                                    alias,\n"
            +

            "       case is_nullable when 'NO' then false else true end                                               nullAble,\n"
            +

            "       case when character_maximum_length > 2147483647 then 2147483647 else character_maximum_length end width,\n"
            +

            "       case when numeric_precision > 2147483647 then 2147483647 else numeric_precision end               'precision',\n"
            +

            "       numeric_scale                                                                                     scale,\n"
            +

            "       ordinal_position                                                                                  position\n"
            +

            "FROM information_schema.columns\n" +

            "WHERE table_schema = '%s'\n" +

            "  AND table_name = '%s'\n" +

            "order by table_name, ordinal_position";

    private static String Mysql_Tables =
        "SELECT table_schema schemaName, table_name simpleTableName, table_comment alias FROM information_schema.tables WHERE table_schema = '%s'";

    //获得驱动
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    //获得url
    private static String URL = "jdbc:mysql://localhost:3306/mbp?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    //获得连接数据库的用户名
    private static String USER = "mbp";
    //获得连接数据库的密码
    private static String PASS = "mbp1234";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            //连接数据库
            DbConnConf dbConnConf = new DbConnConf().setUrl(URL).setDriver(DRIVER).setUser(USER).setPassword(PASS);

            connection = ConnectionUtil.getConnection(dbConnConf);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭连接
    public static void close(Object o){
        if (o == null){
            return;
        }
        if (o instanceof ResultSet){
            try {
                ((ResultSet)o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(o instanceof Statement){
            try {
                ((Statement)o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Connection){
            Connection c = (Connection)o;
            try {
                if (!c.isClosed()){
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void close(ResultSet rs, Statement stmt,
        Connection conn){
        close(rs);
        close(stmt);
        close(conn);
    }

    public static void close(ResultSet rs,
        Connection conn){
        close(rs);
        close(conn);
    }

    /**
     * @Description: 获取数据库相关信息
     * @author: boyan
     * @throws
     */
    public static void getDataBaseInfo() {
        Connection conn =  getConnection();
        ResultSet rs = null;
        try{
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("数据库已知的用户: "+ dbmd.getUserName());
            System.out.println("数据库的系统函数的逗号分隔列表: "+ dbmd.getSystemFunctions());
            System.out.println("数据库的时间和日期函数的逗号分隔列表: "+ dbmd.getTimeDateFunctions());
            System.out.println("数据库的字符串函数的逗号分隔列表: "+ dbmd.getStringFunctions());
            System.out.println("数据库供应商用于 'schema' 的首选术语: "+ dbmd.getSchemaTerm());
            System.out.println("数据库URL: " + dbmd.getURL());
            System.out.println("是否允许只读:" + dbmd.isReadOnly());
            System.out.println("数据库的产品名称:" + dbmd.getDatabaseProductName());
            System.out.println("数据库的版本:" + dbmd.getDatabaseProductVersion());
            System.out.println("驱动程序的名称:" + dbmd.getDriverName());
            System.out.println("驱动程序的版本:" + dbmd.getDriverVersion());

            System.out.println("数据库中使用的表类型");
            rs = dbmd.getTableTypes();
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_TYPE"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            MysqlConnector.close(rs,conn);
        }
    }

    /**
     * @Description:获得数据库中所有Schemas(对应于oracle中的Tablespace)
     * @author: boyan
     * @throws
     */
    public static void getSchemasInfo(){
        Connection conn =  getConnection();
        ResultSet rs = null;
        try{
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getSchemas();
            while (rs.next()){
                String tableSchem = rs.getString("TABLE_SCHEM");
                System.out.println(tableSchem);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            MysqlConnector.close(rs,conn);
        }
    }

    /**
     * @Description: 获取数据库中所有的表信息
     * @author: boyan
     * @throws
     */
    public static void getTablesList() {
        Connection conn =  getConnection();
        ResultSet rs = null;
        try {
            /**
             * 设置连接属性,使得可获取到表的REMARK(备注)
             */
            //            ((OracleConnection)conn).setRemarksReporting(true);
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = { "TABLE" };
            rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");  //表名
                String tableType = rs.getString("TABLE_TYPE");  //表类型
                String remarks = rs.getString("REMARKS");       //表备注
                System.out.println(tableName + " - " + tableType + " - " + remarks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            MysqlConnector.close(rs,conn);
        }
    }

    /**
     * @Description: 获取某表信息
     * @author: boyan
     * @throws
     */
    public static void getTablesInfo(){
        Connection conn =  getConnection();
        ResultSet rs = null;
        try {
            /**
             * 设置连接属性,使得可获取到表的REMARK(备注)
             */
            //            ((OracleConnection)conn).setRemarksReporting(true);
            DatabaseMetaData dbmd = conn.getMetaData();
            /**
             * 获取给定类别中使用的表的描述。
             * 方法原型:ResultSet getTables(String catalog,String schemaPattern,String tableNamePattern,String[] types);
             * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
             * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
             * tableNamePattern - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
             * types - 表类型数组; "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM";null表示包含所有的表类型;可包含单字符通配符("_"),或多字符通配符("%");
             */
            System.out.println("conn Schema is "+conn.getSchema());
            rs = dbmd.getTables(null, conn.getSchema(), null, new String[]{"TABLE","VIEW"});


            while(rs.next()){
                String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                String tableName = rs.getString("TABLE_NAME");  //表名
                String tableType = rs.getString("TABLE_TYPE");  //表类型,典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
                String remarks = rs.getString("REMARKS");       //表备注

                System.out.println(tableCat + " - " + tableSchemaName + " - " +tableName + " - " + tableType + " - "
                    + remarks);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            MysqlConnector.close(rs,conn);
        }
    }

    /**
     * 获取表的信息
     * */
    public void testGetTablesInfo(){
        getTablesInfo();
    }

    /**
     * @Description: 获取表主键信息
     */
    public static void getPrimaryKeysInfo() {
        Connection conn =  getConnection();
        ResultSet rs = null;
        try{
            DatabaseMetaData dbmd = conn.getMetaData();
            /**
             * 获取对给定表的主键列的描述
             * 方法原型:ResultSet getPrimaryKeys(String catalog,String schema,String table);
             * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
             * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
             * table - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
             */
            rs = dbmd.getPrimaryKeys(null, null, "CUST_INTER_TF_SERVICE_REQ");

            while (rs.next()){
                String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                String tableName = rs.getString("TABLE_NAME");  //表名
                String columnName = rs.getString("COLUMN_NAME");//列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                String pkName = rs.getString("PK_NAME"); //主键名称

                System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName + " - " + columnName + " - "
                    + keySeq + " - " + pkName);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            MysqlConnector.close(rs,conn);
        }
    }

    /**
     * @Description: 获取表索引信息
     * @author: boyan
     * @throws
     */
    public static void getIndexInfo() {
        Connection conn =  getConnection();
        ResultSet rs = null;
        try{
            DatabaseMetaData dbmd = conn.getMetaData();
            /**
             * 获取给定表的索引和统计信息的描述
             * 方法原型:ResultSet getIndexInfo(String catalog,String schema,String table,boolean unique,boolean approximate)
             * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
             * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
             * table - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
             * unique - 该参数为 true时,仅返回唯一值的索引; 该参数为 false时,返回所有索引;
             * approximate - 该参数为true时,允许结果是接近的数据值或这些数据值以外的值;该参数为 false时,要求结果是精确结果;
             */
            rs = dbmd.getIndexInfo(null, null, "CUST_INTER_TF_SERVICE_REQ", false, true);
            while (rs.next()){
                String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                String tableName = rs.getString("TABLE_NAME");  //表名
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 索引值是否可以不唯一,TYPE为 tableIndexStatistic时索引值为 false;
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//索引类别（可能为空）,TYPE为 tableIndexStatistic 时索引类别为 null;
                String indexName = rs.getString("INDEX_NAME");//索引的名称 ;TYPE为 tableIndexStatistic 时索引名称为 null;
                /**
                 * 索引类型：
                 *  tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
                 *  tableIndexClustered - 此为集群索引
                 *  tableIndexHashed - 此为散列索引
                 *  tableIndexOther - 此为某种其他样式的索引
                 */
                short type = rs.getShort("TYPE");//索引类型;
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//在索引列顺序号;TYPE为 tableIndexStatistic 时该序列号为零;
                String columnName = rs.getString("COLUMN_NAME");//列名;TYPE为 tableIndexStatistic时列名称为 null;
                String ascOrDesc = rs.getString("ASC_OR_DESC");//列排序顺序:升序还是降序[A:升序; B:降序];如果排序序列不受支持,可能为 null;TYPE为 tableIndexStatistic时排序序列为 null;
                int cardinality = rs.getInt("CARDINALITY");   //基数;TYPE为 tableIndexStatistic 时,它是表中的行数;否则,它是索引中唯一值的数量。
                int pages = rs.getInt("PAGES"); //TYPE为 tableIndexStatisic时,它是用于表的页数,否则它是用于当前索引的页数。
                String filterCondition = rs.getString("FILTER_CONDITION"); //过滤器条件,如果有的话(可能为 null)。

                System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName + " - " + nonUnique + " - "
                    + indexQualifier + " - " + indexName + " - " + type + " - " + ordinalPosition + " - " + columnName
                    + " - " + ascOrDesc + " - " + cardinality + " - " + pages + " - " + filterCondition);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            MysqlConnector.close(rs,conn);
        }
    }


    /**
     * @Description: 获取表中列值信息
     * @author: boyan
     * @throws
     */
    public static void getColumnsInfo(){
        Connection conn =  getConnection();
        ResultSet rs = null;

        try{
            /**
             * 设置连接属性,使得可获取到列的REMARK(备注)
             */
            //            ((OracleConnection)conn).setRemarksReporting(true);
            DatabaseMetaData dbmd = conn.getMetaData();
            /**
             * 获取可在指定类别中使用的表列的描述。
             * 方法原型:ResultSet getColumns(String catalog,String schemaPattern,String tableNamePattern,String columnNamePattern)
             * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
             * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
             * tableNamePattern - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
             * columnNamePattern - 列名称; ""表示获取列名为""的列(当然获取不到);null表示获取所有的列;可包含单字符通配符("_"),或多字符通配符("%");
             */
            rs =dbmd.getColumns(null, conn.getCatalog(), null, null);

            while(rs.next()){
                String tableCat = rs.getString("TABLE_CAT");  //表类别（可能为空）
                String tableSchemaName = rs.getString("TABLE_SCHEM");  //表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                String tableName_ = rs.getString("TABLE_NAME");  //表名
                String columnName = rs.getString("COLUMN_NAME");  //列名
                int dataType = rs.getInt("DATA_TYPE");     //对应的java.sql.Types的SQL类型(列类型ID)
                String dataTypeName = rs.getString("TYPE_NAME");  //java.sql.Types类型名称(列类型名称)
                int columnSize = rs.getInt("COLUMN_SIZE");  //列大小
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");  //小数位数
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");  //基数（通常是10或2） --未知
                /**
                 *  0 (columnNoNulls) - 该列不允许为空
                 *  1 (columnNullable) - 该列允许为空
                 *  2 (columnNullableUnknown) - 不确定该列是否为空
                 */
                int nullAble = rs.getInt("NULLABLE");  //是否允许为null
                String remarks = rs.getString("REMARKS");  //列描述
                String columnDef = rs.getString("COLUMN_DEF");  //默认值
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");    // 对于 char 类型，该长度是列中的最大字节数
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");   //表中列的索引（从1开始）
                /**
                 * ISO规则用来确定某一列的是否可为空(等同于NULLABLE的值:[ 0:'YES'; 1:'NO'; 2:''; ])
                 * YES -- 该列可以有空值;
                 * NO -- 该列不能为空;
                 * 空字符串--- 不知道该列是否可为空
                 */
                String isNullAble = rs.getString("IS_NULLABLE");

                /**
                 * 指示此列是否是自动递增
                 * YES -- 该列是自动递增的
                 * NO -- 该列不是自动递增
                 * 空字串--- 不能确定该列是否自动递增
                 */
                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");   //该参数测试报错

                System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName_ + " - " + columnName +
                    " - " + dataType + " - " + dataTypeName + " - " + columnSize + " - " + decimalDigits + " - "
                    + numPrecRadix + " - " + nullAble + " - " + remarks + " - " + columnDef + " - " + charOctetLength
                    + " - " + ordinalPosition + " - " + isNullAble+ " - " + isAutoincrement );

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            MysqlConnector.close(rs,conn);
        }
    }

    
    public void testGetColumnsInfo(){
        getColumnsInfo(); //获取表中列值信息
    }

    public static void main(String[] args) {
        getDataBaseInfo();  //获取数据库信息
        getSchemasInfo(); //获取数据库所有Schema
        getTablesList();  //获取某用户下所有的表
        getTablesInfo();  //获取表信息
        getPrimaryKeysInfo(); //获取表主键信息
        getIndexInfo();  //获取表索引信息
        getColumnsInfo(); //获取表中列值信息
    }
}