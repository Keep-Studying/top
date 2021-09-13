/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.daointerface;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * Dal基础Mapper接口
 *
 * @author boyan
 * @version : DalBaseMapper.java, v 0.1 2021年07月05日 9:08 下午 boyan Exp $
 */
public interface DalBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     *
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(Collection<T> entityList);
}