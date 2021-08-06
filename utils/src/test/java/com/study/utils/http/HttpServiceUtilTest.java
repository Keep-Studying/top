package com.study.utils.http;

import org.junit.jupiter.api.Test;

/**
 * @author boyan
 * @version : HttpServiceUtilTest, v0.1 2021年07月10日 11:54 下午 boyan Exp $
 */
class HttpServiceUtilTest {



    @Test
    void sendHttpPost() {
        String url = "http://172.16.0.57:7070/kylin/api/tables_and_columns?project=learn_kylin";
        String s = HttpServiceUtil.sendHttpGet(url);
        System.out.println(s);
    }

    @Test
    void sendHttpGet() {
    }


}