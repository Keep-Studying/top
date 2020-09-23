/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.model;

/**
 * @author study
 * @version : CommonRequest.java, v 0.1 2020年09月24日 0:07 study Exp $
 */
public class CommonRequest extends ToString {
    private static final long serialVersionUID = 9153163814356647971L;

    /**服务key*/
    private String serviceKey;

    /**请求参数，以JSON格式提供*/
    private String requestData;

    /**
     * Getter method for property <tt>serviceKey</tt>.
     *
     * @return property value of serviceKey
     */
    public String getServiceKey() {
        return serviceKey;
    }

    /**
     * Setter method for property <tt>serviceKey</tt>.
     *
     * @param serviceKey value to be assigned to property serviceKey
     */
    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    /**
     * Getter method for property <tt>requestData</tt>.
     *
     * @return property value of requestData
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * Setter method for property <tt>requestData</tt>.
     *
     * @param requestData value to be assigned to property requestData
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}