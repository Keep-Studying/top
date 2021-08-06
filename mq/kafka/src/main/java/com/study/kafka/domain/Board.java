/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

import java.util.List;

/**
 * 看板
 * 
 * @author boyan
 * @version : Board.java, v 0.1 2021年07月02日 5:56 下午 boyan Exp $
 */
@Data
public class Board extends ToString {
    private static final long serialVersionUID = -1430029604348453294L;

    /** ID */
    private Long id;
    /** 看板id： */
    private String boardId;
    /** 看板名称： */
    private String name;
    /** 类型（区分报告/分析）：RPT（报告）/TWB（工作簿） */
    private String type;
    /** 工作簿名称： */
    private String twbName;
    /** 渠道：bix / tableau */
    private String channel;
    /** 门户：火眼 / 智策 */
    private String portal;
    /** 任务优先级（1～10）：默认1，数字越大，优先级越高 */
    private Integer priority;
    /** 数据集ID集合：字符串为dataSetId+version */
    private List<String> dataSetIds;
}