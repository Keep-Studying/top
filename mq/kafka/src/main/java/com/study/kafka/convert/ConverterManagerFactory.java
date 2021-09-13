/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.convert;

import com.study.kafka.dataobject.*;
import com.study.kafka.domain.*;

/**
 * ConverterManagerFactory
 *
 * @author boyan
 * @version : ConverterManagerFactory.java, v 0.1 2021-08-31 14:35 boyan
 */
public class ConverterManagerFactory {
    public static BaseConverterManager<DataSetDO, DataSet>            dataSetDODomainConverter    = new DataSetDoDomainBaseConverter();
    public static BaseConverterManager<DataTableDO, DataTableSub>     dataTableDODomainConverter  = new DataTableDoDomainBaseConverter();
    public static BaseConverterManager<DataColumnDO, DataColumnSub>   dataColumnDODomainConverter = new DataColumnDoDomainBaseConverter();

    public static BaseConverterManager<DataSetDO, DataSet> getDataSetConverter() {
        return dataSetDODomainConverter;
    }

    public static BaseConverterManager<DataTableDO, DataTableSub> getDataTableSubConverter() {
        return dataTableDODomainConverter;
    }

    public static BaseConverterManager<DataColumnDO, DataColumnSub> getDataColumnSubConverter() {
        return dataColumnDODomainConverter;
    }
}