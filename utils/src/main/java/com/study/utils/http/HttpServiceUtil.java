/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.study.utils.domain.KylinTableMeta;
import com.study.utils.json.JacksonUtils;
import com.study.utils.json.JsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author boyan
 * @version : HttpServiceUtil.java, v 0.1 2021年07月10日 10:59 下午 boyan Exp $
 */
public class HttpServiceUtil extends BaseHttp{

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServiceUtil.class);

    /**
     * 发送http post请求
     * 超时时间2秒，socket时间6秒
     *
     * @param uri，地址
     * @param paramJSON，组装成的JSON参数
     * @return
     */
    public static String sendHttpPost(PostMethod ethod,String uri, String paramJSON) throws RuntimeException {

        String response = "";
        HttpClient client = getHttpClient();

        PostMethod postMethod = new PostMethod(uri);
        try {
            StringRequestEntity entity = new StringRequestEntity(paramJSON, "application/json",
                    "UTF-8");
            postMethod.setRequestEntity(entity);

            int status = client.executeMethod(postMethod);
            //校验返回状态码
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {

                response = new String(postMethod.getResponseBody(), "UTF-8");
                LOGGER.info( "[sendHttpPost]URL="+uri+",result=success"+paramJSON+",response==>>", response);
            } else {

                LOGGER.warn("[sendHttpPost]result=false,URL=", uri,
                        ",status=" + status + ",errorMsg=" + postMethod.getResponseBodyAsString());
            }
        } catch (Throwable t) {

            LOGGER.error("[sendHttpPost]请求失败",t);
            throw new RuntimeException("[sendHttpPost]请求异常", t);
        } finally {

            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

        return response;
    }

    /**
     * 发送http get请求
     * 超时时间2秒，socket时间6秒
     * @param url，地址
     * @return
     */
    public static String sendHttpGet(String url) throws RuntimeException {

        String response = "";
        HttpClient client = getHttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Accept", "application/json, text/plain, */*");
        getMethod.addRequestHeader("Accept-Language", "en");
        getMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");

        String basicAuth = DatatypeConverter
                .printBase64Binary(("ADMIN" + ":" + "KYLIN").getBytes(StandardCharsets.UTF_8));
        getMethod.addRequestHeader("Authorization", "Basic " + basicAuth);
        try {
            int status = client.executeMethod(getMethod);
            //校验返回状态码
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {

                response = new String(getMethod.getResponseBody(), "UTF-8");
                List<KylinTableMeta> listBaseHttpResponse = JacksonUtils.readValue(response,
                        new TypeReference<List<KylinTableMeta>>() {});
                System.out.println("kylin response is == "+JsonUtil.toJSONString(listBaseHttpResponse));
                LOGGER.warn( "[httpGet]URL="+url+"result=success,response==>>", response);
            } else {

                LOGGER.warn( "[httpGet]result=false,URL=", url,
                        ",status=" + status + ",errorMsg=" + getMethod.getResponseBodyAsString());
            }
        } catch (Throwable t) {

            LOGGER.error("[sendHttpGet]请求失败",t);
            throw new RuntimeException("[sendHttpGet]请求异常", t);
        } finally {

            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }

        return response;
    }
}