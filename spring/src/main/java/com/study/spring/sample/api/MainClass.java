/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.api;

import com.alibaba.fastjson.JSON;
import com.study.spring.sample.model.CommonRequest;
import com.study.spring.sample.model.ServiceEnum;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年09月24日 0:39 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        ServiceFactory serviceFactory = (ServiceFactory)ctx.getBean("serviceFactory");
        CommonService service = serviceFactory.query(ServiceEnum.TR);
        System.out.println(JSON.toJSONString(service.execute(new CommonRequest())));


        RestService restService = (RestService)ctx.getBean("restService");
        System.out.println(JSON.toJSONString(restService.execute(new CommonRequest())));
    }
}