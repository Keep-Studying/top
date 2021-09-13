/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import com.study.metadata.domain.BaseMeta;
import com.study.metadata.domain.Result;

import java.util.List;

/**
 * MetadataFacade
 *
 * @author boyan
 * @version : MetadataFacade.java, v 0.1 2021-08-18 14:00 boyan
 */
public interface MetadataFacade {
    BaseMeta getMeta(String datasourceid);
    List<? extends BaseMeta> getMetas(String datasourceid);
    Result<? extends BaseMeta> getMeta1(String datasourceid);
    Result<List<? extends BaseMeta>> getMeta2(String datasourceid);
}