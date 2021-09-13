/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.convert;

import org.springframework.util.CollectionUtils;

import com.study.kafka.dataobject.DataColumnDO;
import com.study.kafka.dataobject.DataTableDO;
import com.study.kafka.domain.DataColumnSub;
import com.study.kafka.domain.DataTableSub;

/**
 * DataTableDoDomainBaseConverter
 *
 * @author boyan
 * @version : DataTableDoDomainBaseConverter.java, v 0.1 2021-08-31 14:39 boyan
 */
public class DataTableDoDomainBaseConverter extends BaseConverterManager<DataTableDO, DataTableSub> {
    public DataTableDoDomainBaseConverter() {
        super(dataTableDO -> {
            if (dataTableDO == null) {
                return null;
            }
            DataTableSub dataTableSub = new DataTableSub();
            dataTableSub.setId(dataTableDO.getId());
            dataTableSub.setDataTableId(dataTableDO.getTableId());
            dataTableSub.setName(dataTableDO.getTableName());
            dataTableSub.setDataSourceId(dataTableDO.getDatasourceId());
            dataTableSub.setSchemaName(dataTableDO.getTableSchema());
            dataTableSub.setCatalogName(dataTableDO.getTableCat());
            BaseConverterManager<DataColumnDO, DataColumnSub> dataColumnSubConverter = ConverterManagerFactory.getDataColumnSubConverter();
            dataTableSub.setColumns(dataColumnSubConverter.createFromDtos(dataTableDO.getColumns()));
            return dataTableSub;
        }, dataTableSub -> {
            if (dataTableSub == null) {
                return null;
            }
            DataTableDO dataTableDO = new DataTableDO();
            dataTableDO.setTableId(dataTableSub.getDataTableId());
            dataTableDO.setTableName(dataTableSub.getName());
            dataTableDO.setDatasourceId(dataTableSub.getDataSourceId());
            dataTableDO.setTableSchema(dataTableSub.getSchemaName());
            dataTableDO.setTableCat(dataTableSub.getCatalogName());
            if (!CollectionUtils.isEmpty(dataTableSub.getColumns())) {
                BaseConverterManager<DataColumnDO, DataColumnSub> dataColumnSubConverter = ConverterManagerFactory
                    .getDataColumnSubConverter();
                dataTableDO.setColumns(dataColumnSubConverter.createFromEntities(dataTableSub.getColumns()));
            }
            dataTableDO.setDatasetId(0L);
            return dataTableDO;
        });
    }
}