/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.kafka.daointerface.DataSetMapper;
import com.study.kafka.dataobject.DataSetDO;
import com.study.kafka.manager.DataSetManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * DataSetManagerImpl
 *
 * @author boyan
 * @version : DataSetManagerImpl.java, v 0.1 2021-09-01 00:09 boyan
 */
@Service
public class DataSetManagerImpl implements DataSetManager {

    @Resource
    private DataSetMapper dataSetMapper;

    @Override public DataSetDO selectById(Long id) {
        return dataSetMapper.selectById(id);
    }

    @Override public DataSetDO selectByUk(String dataSetUk) {
        return dataSetMapper.
            selectOne(new LambdaQueryWrapper<DataSetDO>().eq(DataSetDO::getDatasetUk, dataSetUk));
    }

    @Override public List<DataSetDO> selectByUks(List<String> dataSetUks) {
        return dataSetMapper.
            selectList(new LambdaQueryWrapper<DataSetDO>().in(DataSetDO::getDatasetUk, dataSetUks));
    }

    @Override public IPage<DataSetDO> selectPage(Integer pageNumber, Integer pageSize, String keyword) {
        return dataSetMapper.selectPage(new Page<>(pageNumber, pageSize),
            new LambdaQueryWrapper<DataSetDO>()
                .like(StringUtils.isNotBlank(keyword), DataSetDO::getName, keyword)
                .or()
                .like(StringUtils.isNotBlank(keyword), DataSetDO::getDatasetUk, keyword)
                .orderByDesc(DataSetDO::getId));
    }

    @Override public List<DataSetDO> selectList() {
        return dataSetMapper.selectList(null);
    }
}