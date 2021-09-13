package com.study.kafka.daointerface;

import com.study.kafka.dataobject.DataTableDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据表Mapper接口
 *
 * @author boyan
 * @version : DataTableMapper.java, v 0.1 2021年07月05日 9:08 下午 boyan Exp $
 */
public interface DataTableMapper extends DalBaseMapper<DataTableDO> {

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
//    DataTableDO selectById(@Param("id") Long id);

    /**
     * 根据数据源id和表名查询详情
     *
     * @param datasourceId
     * @param tableName
     * @return
     */
    DataTableDO selectByDatasourceIdTableName(@Param("datasourceId") String datasourceId,
                                              @Param("tableName") String tableName);

    /**
     * 根据数据源id查询详情集合
     *
     * @param datasourceId
     * @return
     */
    List<DataTableDO> selectByDatasourceId(@Param("datasourceId") String datasourceId);

    /**
     * 根据数据集id查询详情
     *
     * @param datasetId
     * @return
     */
    List<DataTableDO> selectByDatasetId(@Param("datasetId") Long datasetId);
}