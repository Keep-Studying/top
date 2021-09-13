/**
 * Aloudata.com Inc. Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * JDBCUtil
 *
 * @author boyan
 * @version : JDBCUtil.java, v 0.1 2021-08-12 16:56 boyan
 */
public class JDBCUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUtil.class);

    private JDBCUtil() {
        super();
    }

    public static <T> List<T> selectList(DataSource dataSource, Class<T> clazz, String sql) {
        return selectList(dataSource, clazz, sql, null);
    }

    public static <T> List<T> selectList(DataSource dataSource, Class<T> clazz, String sql, Integer limit) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if (limit != null) {
            jdbcTemplate.setMaxRows(limit);
        }
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clazz);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public static SqlRowSet queryRowSet(DataSource dataSource, String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setMaxRows(1);
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql);
        return rowset;

    }

    public static List<Map<String, Object>> queryForList(DataSource dataSource, String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForList(sql);
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (conn != null) {
                    try {
                        if (!conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
}
