/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.api;

import com.study.spring.sample.model.CommonRequest;
import com.study.spring.sample.model.Result;
import com.study.spring.sample.model.ServiceEnum;

/**
 * @author study
 * @version : BaseService.java, v 0.1 2020年09月24日 0:20 study Exp $
 */
public abstract class BaseService implements CommonService{


    @Override
    public Result<?> execute(CommonRequest request) {
        //可以有preHandler和postHandler方法

        return doExecute(request);
    }

    abstract Result<?> doExecute(CommonRequest request);

    abstract ServiceEnum getType();

    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceFactory.register(getType(),this);
    }
}