/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DBUtils
 *
 * @author boyan
 * @version : DBUtils.java, v 0.1 2021-08-17 00:21 boyan
 */
public class DBUtils {


    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /**
     * Closes an <code>ResultSet</code> unconditionally.
     * <p>
     * Equivalent to {@link ResultSet#close()}, except any exceptions will be ignored.
     * This is typically used in finally blocks.
     * <p>
     *
     * @param output the ResultSet to close, may be null or already closed
     */
    public static void closeQuietly(final ResultSet rs) {
        closeQuietly((AutoCloseable) rs);
    }

    /**
     * Closes an <code>Statement</code> unconditionally.
     * <p>
     * Equivalent to {@link Statement#close()}, except any exceptions will be ignored.
     * This is typically used in finally blocks.
     * <p>
     *
     * @param output the ResultSet to close, may be null or already closed
     */
    public static void closeQuietly(final Statement stmt) {
        closeQuietly((AutoCloseable) stmt);
    }

    /**
     * Closes an <code>Connection</code> unconditionally.
     * <p>
     * Equivalent to {@link Connection#close()}, except any exceptions will be ignored.
     * This is typically used in finally blocks.
     * <p>
     *
     * @param output the ResultSet to close, may be null or already closed
     */
    public static void closeQuietly(final Connection conn) {
        closeQuietly((AutoCloseable) conn);
    }

    /**
     * Closes a <code>AutoCloseable</code> unconditionally.
     * <p>
     * Equivalent to {@link AutoCloseable#close()}, except any exceptions will be ignored. This is typically used in
     * finally blocks.
     * <p>
     *
     * @param closeable the objects to close, may be null or already closed
     */
    public static void closeQuietly(final AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final Exception ioe) {
            logger.debug("", ioe);
        }
    }
}