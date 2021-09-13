/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * LoadData
 *
 * @author boyan
 * @version : LoadData.java, v 0.1 2021-09-01 20:53 boyan
 */
public class LoadData {


    public void load(DbConf dbConf,String filePath,String table) {

        try {
            Class.forName(dbConf.getDriver());
//            String url = "jdbc:mysql://" + ip +":" + port + "/mydb";
            Connection conn =
                DriverManager.getConnection(dbConf.getUrl(), dbConf.getUser(), dbConf.getPassword());
            String sql = "LOAD DATA LOCAL INFILE '"+filePath+"' INTO TABLE " +table+ " FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\r\\n' ;";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void loadMysql(){
        DbConf dbConf = new DbConf();
        dbConf.setDriver("com.mysql.cj.jdbc.Driver");
        dbConf.setUrl(String.format("jdbc:mysql://%s:%s/%s","rm-bp13v78q00709p391co.mysql.rds.aliyuncs.com","3306","test"));
        dbConf.setUser("root");
        dbConf.setPassword("Aloudata20210520");
        load(dbConf,"/Users/boyan/Downloads/DingWorks/TestV1/Calcs.csv","Calcs");
    }

    @AllArgsConstructor
    @Data
    class DbConf{
        private String driver;
        private String url;
        private String user;
        private String password;

        public DbConf() {
        }
    }
}