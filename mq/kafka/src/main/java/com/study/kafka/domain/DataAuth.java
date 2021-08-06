/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

/**
 * 数据列和认证的关系
 * @author boyan
 * @version : DataAuth.java, v 0.1 2021年07月10日 11:23 上午 boyan Exp $
 */
@Data
public class DataAuth extends ToString {
    private static final long serialVersionUID = -5112602920301230096L;

    /**认证id：火眼+CORE_ORG_BBKNBR*/
    private String authId;
    /**认证列(即列id)：MRG_FRS_LVL_BBK_ID(V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR)*/
    private String authColumn;
}