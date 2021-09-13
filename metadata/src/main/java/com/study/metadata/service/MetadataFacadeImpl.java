/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import com.study.metadata.domain.BaseMeta;
import com.study.metadata.domain.MysqlTable;
import com.study.metadata.domain.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * MetadataFacadeImpl
 *
 * @author boyan
 * @version : MetadataFacadeImpl.java, v 0.1 2021-08-18 14:05 boyan
 */
public class MetadataFacadeImpl implements MetadataFacade{
    @Override public BaseMeta getMeta(String datasourceid) {
        return new MysqlTable();
    }

    @Override public List<? extends BaseMeta> getMetas(String datasourceid) {
        List<MysqlTable> objects = new ArrayList<>();
        return objects;
    }

    @Override public Result<? extends BaseMeta> getMeta1(String datasourceid) {
        Result<BaseMeta> result = new Result<>();
        MysqlTable table = new MysqlTable();
        result.setData(table);
        return result;
    }

    @Override public Result<List<? extends BaseMeta>> getMeta2(String datasourceid) {
        Result<List<? extends BaseMeta>> result = new Result<>();
        List<BaseMeta> lists = new ArrayList<>();
        MysqlTable table = new MysqlTable();
        lists.add(table);
        result.setData(lists);
        return result;
    }
}