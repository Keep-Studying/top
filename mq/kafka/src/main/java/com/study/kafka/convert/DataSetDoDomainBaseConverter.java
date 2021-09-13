/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.convert;

import com.alibaba.fastjson.TypeReference;
import com.study.kafka.dataobject.DataColumnDO;
import com.study.kafka.dataobject.DataSetDO;
import com.study.kafka.dataobject.DataTableDO;
import com.study.kafka.domain.*;
import com.study.kafka.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DataSetDoDomainBaseConverter
 *
 * @author boyan
 * @version : DataSetDoDomainBaseConverter.java, v 0.1 2021-08-31 14:35 boyan
 */
public class DataSetDoDomainBaseConverter extends BaseConverterManager<DataSetDO, DataSet> {
    public DataSetDoDomainBaseConverter() {
        super(dataSetDO -> {
            if (dataSetDO == null) {
                return null;
            }
            DataSet dataSet = new DataSet();
            dataSet.setId(dataSetDO.getId());
            dataSet.setDataSetId(dataSetDO.getDatasetId());
            dataSet.setUk(dataSetDO.getDatasetUk());
            dataSet.setName(dataSetDO.getName());
            dataSet.setVersion(dataSetDO.getVersion());
            dataSet.setPortal(dataSetDO.getPortal());
            if (StringUtils.isNoneBlank(dataSetDO.getConnections())) {
                dataSet.setConnections(JsonUtil.parseObject(dataSetDO.getConnections(), new TypeReference<List<Connection>>() {}));
            }
            if (StringUtils.isNoneBlank(dataSetDO.getDataAuths())) {
                dataSet.setDataAuths(JsonUtil.parseObject(dataSetDO.getDataAuths(), new TypeReference<List<DataAuth>>() {}));
            }
            if (!CollectionUtils.isEmpty(dataSetDO.getTables())) {
                BaseConverterManager<DataTableDO, DataTableSub> dataTableSubConverter = ConverterManagerFactory.getDataTableSubConverter();
                dataSet.setTables(dataTableSubConverter.createFromDtos(dataSetDO.getTables()));
            }
            if (!CollectionUtils.isEmpty(dataSetDO.getColumns())) {
                BaseConverterManager<DataColumnDO, DataColumnSub> dataColumnSubConverter = ConverterManagerFactory
                    .getDataColumnSubConverter();
                dataSet.setColumns(dataColumnSubConverter.createFromDtos(dataSetDO.getColumns()));
            }
            return dataSet;
        }, dataSet -> {
            if (dataSet == null) {
                return null;
            }
            DataSetDO dataSetDO = new DataSetDO();
            dataSetDO.setId(dataSet.getId());
            dataSetDO.setDatasetId(dataSet.getDataSetId());
            dataSetDO.setDatasetUk(
                StringUtils.isBlank(dataSet.getUk()) ? dataSet.getDataSetId() + "+" + dataSet.getVersion() : dataSet.getUk());
            dataSetDO.setName(dataSet.getName());
            dataSetDO.setVersion(dataSet.getVersion());
            dataSetDO.setPortal(dataSet.getPortal());
            if (!CollectionUtils.isEmpty(dataSet.getConnections())) {
                dataSetDO.setConnections(JsonUtil.toJsonString(dataSet.getConnections()));
            }
            if (!CollectionUtils.isEmpty(dataSet.getDataAuths())) {
                dataSetDO.setDataAuths(JsonUtil.toJsonString(dataSet.getDataAuths()));
            }
            if (!CollectionUtils.isEmpty(dataSet.getTables())) {
                BaseConverterManager<DataTableDO, DataTableSub> dataTableSubConverter = ConverterManagerFactory.getDataTableSubConverter();
                dataSetDO.setTables(dataTableSubConverter.createFromEntities(dataSet.getTables()));
            }
            if (CollectionUtils.isEmpty(dataSet.getColumns())) {
                BaseConverterManager<DataColumnDO, DataColumnSub> dataColumnSubConverter = ConverterManagerFactory
                    .getDataColumnSubConverter();
                dataSetDO.setColumns(dataColumnSubConverter.createFromEntities(dataSet.getColumns()));
            }
            return dataSetDO;
        });
    }
}