/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.api;

import com.study.spring.sample.model.CommonRequest;
import com.study.spring.sample.model.Result;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean 接口的使用
 * @author study
 * @version : CommonService.java, v 0.1 2020年09月23日 23:57 study Exp $
 */
public interface CommonService extends InitializingBean {

    Result<?> execute(CommonRequest request);

    //ServiceEnum getType();
}