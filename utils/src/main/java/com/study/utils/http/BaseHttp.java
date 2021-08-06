/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author boyan
 * @version : BaseHttp.java, v 0.1 2021年07月10日 10:35 下午 boyan Exp $
 */
public class BaseHttp {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHttp.class);

    //线程安全的连接管理器,多线程连接时保证连接的线程安全
    protected static HttpConnectionManager       httpConnectionManager = new MultiThreadedHttpConnectionManager();
    private static   HttpConnectionManagerParams httpConnParams   = new HttpConnectionManagerParams();
    private static   HttpClientParams    httpClientParams = new HttpClientParams();
    protected static HttpClient httpClient       = null;

    /**
     *  默认构造函数
     */
    static {
        if (httpClient == null) {
            //每个主机地址最大连接数
            httpConnParams.setDefaultMaxConnectionsPerHost(50);
            //所有主机地址最大连接数,多主机地址的连接使限制所有主机地址总的连接数
            httpConnParams.setMaxTotalConnections(500);
            //连接超时时间
            httpConnParams.setConnectionTimeout(3000);
            httpConnParams.setSoTimeout(10000);

            //每次使用连接前检查连接状态是否可用
            httpConnParams.setStaleCheckingEnabled(true);
            //设置认证方式为抢占式。这种方式HttpClient会发送基本的认证Response,有些情况下,会在服务器返回一个未经授权的Response之前就发出去,从而使连接减少了开销。
            httpClientParams.setAuthenticationPreemptive(true);
            //通知服务器,在解析Cookie的时候采用尽可能兼容所有浏览器的方案
            httpClientParams.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            //发起请求时会将所有的Cookie合并成一条放入请求头信息中
            httpClientParams.setParameter("http.protocol.single-cookie-header", true);

            httpConnectionManager.setParams(httpConnParams);
            httpClient = new HttpClient(httpClientParams, httpConnectionManager);

            Runnable runnable = new Runnable() {
                /**
                 * 自己起一个异步线程来管理空闲连接
                 * @see Runnable#run()
                 */
                @Override
                public void run() {
                    while (true) {
                        try {
                            httpConnectionManager.closeIdleConnections(10000);
                            httpConnParams.setSoTimeout(3500);
                            Thread.sleep(5000);
                        } catch (Throwable e) {
                            LOGGER.error("关闭空闲http连接异常",e);
                        }
                    }
                }

            };
            new Thread(runnable).start();
        }
    }

    /**
     * Getter method for property   httpClient.
     *
     * @return property value of httpClient
     */
    public static HttpClient getHttpClient() {
        return httpClient;
    }
}