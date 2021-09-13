/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.kafka.dataobject.DataSetDO;

import java.util.List;

/**
 * DataSetManager
 *
 * @author boyan
 * @version : DataSetManager.java, v 0.1 2021-09-01 00:06 boyan
 */
public interface DataSetManager {


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    DataSetDO selectById(Long id);

    /**
     * 根据数据集ID获取数据集详情
     *
     * @param dataSetUk 唯一键
     * @return
     */
    DataSetDO selectByUk(String dataSetUk);

    /**
     * 根据uk集合搜索数据集集合
     *
     * @param dataSetUks uk集合
     * @return
     */
    List<DataSetDO> selectByUks(List<String> dataSetUks);

    /**
     * 根据关键词分页搜索数据集集合
     *
     * @param pageNumber 页码
     * @param pageSize   每页的数量
     * @param keyword    关键词
     * @return
     */
    IPage<DataSetDO> selectPage(Integer pageNumber, Integer pageSize, String keyword);

    /**
     * 根据关键词分页搜索数据集集合
     *
     * @return
     */
    List<DataSetDO> selectList();

}