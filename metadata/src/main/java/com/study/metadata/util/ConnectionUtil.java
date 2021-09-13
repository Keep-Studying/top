/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import com.study.metadata.domain.DbConnConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

/**
 * ConnectionUtil
 *
 * @author boyan
 * @version : ConnectionUtil.java, v 0.1 2021-08-12 12:45 boyan
 */
public class ConnectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    private static final Random r = new Random();

    public static final int tryTimes = 5;
    public static final String DRIVER_MISS = "DRIVER_MISS";

    public static Connection getConnection(DbConnConf dbconf) {
        if (dbconf.getUrl() == null) {
            return null;
        }
        Connection con = null;
        try {
            Class.forName(dbconf.getDriver());
        } catch (Exception e) {
            logger.error("Miss Driver", e);
            throw new IllegalStateException(DRIVER_MISS);
        }
        boolean got = false;
        int times = 0;
        while (!got && times < tryTimes) {
            times++;
            try {
                con = DriverManager.getConnection(dbconf.getUrl(), dbconf.getUser(), dbconf.getPassword());
                got = true;
            } catch (Exception e) {
                logger.warn("while use:" + dbconf, e);
                try {
                    int rt = r.nextInt(10);
                    Thread.sleep(rt * 1000L);
                } catch (InterruptedException e1) {
                    Thread.interrupted();
                }
            }
        }
        if (null == con) {
            throw new IllegalStateException("Can not connect to the data source.");
        }
        return con;
    }
}