package com.study.utils.http;

import com.study.utils.domain.KylinHttpConfig;
import com.study.utils.domain.KylinTableMeta;
import com.study.utils.json.JsonUtil;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author boyan
 * @version : ServiceClientTest, v0.1 2021年07月11日 10:05 上午 boyan Exp $
 */
class ServiceClientTest {

    @Test
    void retrieveMetaData() throws IOException {
        KylinHttpConfig kylinHttpConfig = new KylinHttpConfig();
        kylinHttpConfig.setBaseUrl("172.16.0.57:7070");
        kylinHttpConfig.setSsl(false);
        kylinHttpConfig.setUserName("ADMIN");
        kylinHttpConfig.setPassword("KYLIN");
        String project  = "learn_kylin";
        List<KylinTableMeta> kylinTableMetas = ServiceClient.retrieveMetaData(kylinHttpConfig, project);
        System.out.println(JsonUtil.toJSONString(kylinTableMetas));
    }

    @Test
    public void test01(){
        String basicAuth = DatatypeConverter
                .printBase64Binary(("ADMIN" + ":" + "KYLIN").getBytes(StandardCharsets.UTF_8));
        System.out.println("Basic " + basicAuth);
    }
}