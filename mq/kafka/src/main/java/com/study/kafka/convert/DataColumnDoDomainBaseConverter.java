/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.convert;

import org.apache.commons.lang3.math.NumberUtils;

import com.study.kafka.dataobject.DataColumnDO;
import com.study.kafka.domain.DataColumnSub;

/**
 * DataColumnDoDomainBaseConverter
 *
 * @author boyan
 * @version : DataColumnDoDomainBaseConverter.java, v 0.1 2021-08-31 14:40 boyan
 */
public class DataColumnDoDomainBaseConverter extends BaseConverterManager<DataColumnDO, DataColumnSub> {
    public DataColumnDoDomainBaseConverter() {
        super(dataColumnDO -> {
            if (dataColumnDO == null) {
                return null;
            }
            DataColumnSub dataColumnSub = new DataColumnSub();
            dataColumnSub.setColumnId(dataColumnDO.getColumnId());
            dataColumnSub.setColumnIndex(dataColumnDO.getOrdinalPosition());
            dataColumnSub.setColumnName(dataColumnDO.getColumnName());
            dataColumnSub.setColumnType(dataColumnDO.getTypeName());
            dataColumnSub.setType(dataColumnDO.getType());
            dataColumnSub.setPrecision(dataColumnDO.getColumnSize().toString());
            dataColumnSub.setComment(dataColumnDO.getRemarks());
            return dataColumnSub;
        }, dataColumnSub -> {
            if (dataColumnSub == null) {
                return null;
            }
            DataColumnDO dataColumnDO = new DataColumnDO();
            dataColumnDO.setColumnId(dataColumnSub.getColumnId());
            dataColumnDO.setOrdinalPosition(dataColumnSub.getColumnIndex());
            dataColumnDO.setColumnName(dataColumnSub.getColumnName());
            dataColumnDO.setTypeName(dataColumnSub.getColumnType());
            dataColumnDO.setType(dataColumnSub.getType());
            dataColumnDO.setColumnSize(NumberUtils.toInt(dataColumnSub.getPrecision()));
            dataColumnDO.setRemarks(dataColumnSub.getComment());
            dataColumnDO.setDatasetId(0L);
            dataColumnDO.setDatatableId(0L);
            return dataColumnDO;
        });
    }
}