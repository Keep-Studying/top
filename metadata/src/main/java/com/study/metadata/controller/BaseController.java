/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.controller;

import com.study.metadata.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * BaseController
 *
 * @author boyan
 * @version : BaseController.java, v 0.1 2021-08-12 10:42 boyan
 */
@RestController
public class BaseController {
    
    @Resource
    private CreateCollectionService createCollectionService;

    @Resource
    private AggregateGroupService aggregateGroupService;

    @Resource
    private AggregatePipelineService aggregatePipelineService;

    @Resource
    private CreateIndexService createIndexService;

    @Resource
    private InsertService insertService;

    @Resource
    private QueryCollectionService queryCollectionService;

    @Resource
    private SaveService saveService;

    @GetMapping("testInsertService")
    public ModelMap testInsertService(){
        Object insert = insertService.insert();
        Object insertMany = insertService.insertMany();
        ModelMap modelMap = new ModelMap();
        modelMap.put("insert",insert);
        modelMap.put("insertMany",insertMany);
        return modelMap;
    }


    @GetMapping("testCreateCollectionService")
    public Object testCreateCollectionService(){
        Object collection = createCollectionService.createCollection();
        return collection;
    }

}