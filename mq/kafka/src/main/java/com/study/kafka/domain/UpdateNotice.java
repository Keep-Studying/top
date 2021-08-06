/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;


/**
 * 更新通知
 * @author boyan
 * @version : UpdateNotice.java, v 0.1 2021年07月05日 12:07 上午 boyan Exp $
 */

public class UpdateNotice extends ToString {
    private static final long serialVersionUID = -6709456557917914965L;
    /**数据源id*/
    private String dataSourceId;
    /**
     * 表名
     * KYLIN时，填cube名：ACAM_C01_SANA_PFM_CUST_BRN_MON_SR_202012_KC
     * */
    private String tableName;
    /**事件的作业id*/
    private String jobName;
    /**事件的更新类型：FULL（全量）/INCREMENT（增量）*/
    private String updateType;
    /**事件的完成事件：'YYYY-MM-dd HH:mm:ss'*/
    private String endTime;
    /**业务日期(分区字段）：'YYYYMMDD'*/
    private String bizDate;

    /**
     * Getter method for property <tt>dataSourceId</tt>.
     *
     * @return property value of dataSourceId
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * Setter method for property <tt>dataSourceId</tt>.
     *
     * @param dataSourceId value to be assigned to property dataSourceId
     */
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * Getter method for property <tt>tableName</tt>.
     *
     * @return property value of tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Setter method for property <tt>tableName</tt>.
     *
     * @param tableName value to be assigned to property tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Getter method for property <tt>jobName</tt>.
     *
     * @return property value of jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Setter method for property <tt>jobName</tt>.
     *
     * @param jobName value to be assigned to property jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Getter method for property <tt>updateType</tt>.
     *
     * @return property value of updateType
     */
    public String getUpdateType() {
        return updateType;
    }

    /**
     * Setter method for property <tt>updateType</tt>.
     *
     * @param updateType value to be assigned to property updateType
     */
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>bizDate</tt>.
     *
     * @return property value of bizDate
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * Setter method for property <tt>bizDate</tt>.
     *
     * @param bizDate value to be assigned to property bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }
}