/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.study.utils.domain.KylinHttpConfig;
import com.study.utils.domain.KylinTableMeta;
import com.study.utils.json.JacksonUtils;
import com.study.utils.json.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author boyan
 * @version : ServiceClient.java, v 0.1 2021年07月11日 12:50 上午 boyan Exp $
 */
public class ServiceClient {

    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5 * 1000)
                .build();
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
    private final Properties connProps;

    public ServiceClient(Properties connProps) {
        this.connProps = connProps;
    }

    /*public static <T> T sendGet(String url, TypeReference<T> reference) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        addAuthHeader(httpGet, config);
        addHttpHeaders(httpGet);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            assertResponse(httpPost, response);
            String json = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            EntityUtils.consume(response.getEntity());
            return (T)JSON.parseObject(json, reference);
        }
    }*/

    /*public static BaseResp<List<List<String>>> asyncMeta(KylinExecConfig config, String queryID) throws IOException {
        String uri = config.getBaseUri() + "/kylin/api/async_query/" + queryID + "/metadata";
        HttpGet get = new HttpGet(uri);
        addDefaultHeader(get);
        addAuthHeader(get, config);
        try (CloseableHttpResponse response = config.getHttpClient().execute(get)) {
            assertResponse(get, response);
            String json = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            EntityUtils.consume(response.getEntity());
            return JsonUtils.toObject(json, new com.fasterxml.jackson.core.type.TypeReference<BaseResp<List<List<String>>>>() {
            });
        }
    }*/

    public static List<KylinTableMeta> retrieveMetaData(KylinHttpConfig config, String project) throws IOException {

        String url = baseUrl(config) + "/kylin/api/tables_and_columns?project=" + project;
        HttpGet get = new HttpGet(url);
        addHttpHeaders(get,config);

        HttpResponse response = httpClient.execute(get);
        try {
            if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 201) {
                throw asIOException(get, response);
            }

            String json = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            EntityUtils.consume(response.getEntity());
            List<KylinTableMeta> listBaseHttpResponse = JacksonUtils.readValue(json,
                    new TypeReference<List<KylinTableMeta>>() {});
            System.out.println(JsonUtil.toJSONString(listBaseHttpResponse));

            return listBaseHttpResponse;
        } finally {
            get.releaseConnection();
        }
    }

   /* private static void addAuthHeader(HttpRequestBase method, KylinExecConfig config) {
        String basicAuth = DatatypeConverter
                .printBase64Binary((config.getUsername() + ":" + config.getPassword()).getBytes(StandardCharsets.UTF_8));
        method.addHeader("Authorization", "Basic " + basicAuth);
    }*/

    private static void assertResponse(HttpRequestBase request, HttpResponse response) throws IOException {
        int code = response.getStatusLine().getStatusCode();
        if (code != 200 && code != 201) {
            throw new IOException(request.getMethod() + " failed, error code " + code
                    + " and response: " + EntityUtils.toString(response.getEntity()));
        }
    }


    private static void addHttpHeaders(HttpRequestBase method,KylinHttpConfig config) {
        //method.addHeader("Accept", "application/json, text/plain, */*");
        //method.addHeader("Content-Type", "application/json");
        //method.addHeader("User-Agent", "KylinJDBCDriver");

        //method.addHeader("Accept", "application/vnd.apache.kylin-v2+json");
        method.addHeader("Accept", " application/json, text/plain, */*");
        method.addHeader("Accept-Language", "en");
        method.addHeader("Content-Type", "application/json;charset=utf-8");

        String basicAuth = DatatypeConverter
                .printBase64Binary((config.getUserName() + ":" + config.getPassword()).getBytes(StandardCharsets.UTF_8));
        method.addHeader("Authorization", "Basic " + basicAuth);
    }

    private static IOException asIOException(HttpRequestBase request, HttpResponse response) throws IOException {
        return new IOException(request.getMethod() + " failed, error code " + response.getStatusLine().getStatusCode()
                + " and response: " + EntityUtils.toString(response.getEntity()));
    }

    private static String baseUrl(KylinHttpConfig config) {
        return (config.isSsl() ? "https://" : "http://") + config.getBaseUrl();
    }
}