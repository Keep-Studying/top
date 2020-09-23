/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.api;

import com.study.spring.sample.model.CommonRequest;
import com.study.spring.sample.model.Result;
import com.study.spring.sample.model.ServiceEnum;
import org.springframework.stereotype.Component;

/**
 * @author study
 * @version : TrService.java, v 0.1 2020年09月24日 0:37 study Exp $
 */
@Component
public class TrService extends BaseService{
    @Override
    Result<?> doExecute(CommonRequest request) {
        Result<Object> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setMessage(getType().getCode());
        result.setData(getType());

        return result;
    }

    @Override
    ServiceEnum getType() {
        return ServiceEnum.TR;
    }
}