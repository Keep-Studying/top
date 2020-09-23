/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.api;

import com.study.spring.sample.model.ServiceEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author study
 * @version : ServiceFactory.java, v 0.1 2020年09月24日 0:11 study Exp $
 */
@Component
public class ServiceFactory {

    private static Map<ServiceEnum,CommonService> serviceMap = new HashMap<>();

    public static CommonService query(ServiceEnum serviceEnum){
        if(serviceEnum == null){
            throw new RuntimeException("serviceEnum invalid");
        }
        return serviceMap.get(serviceEnum);
    }

    public static void register(ServiceEnum serviceEnum,CommonService service){
        if(serviceEnum == null){
            throw new RuntimeException("serviceEnum invalid");
        }
        serviceMap.put(serviceEnum,service);
    }
}