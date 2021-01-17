/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author study
 * @version : JsonUtils.java, v 0.1 2020年09月24日 23:26 study Exp $
 */
public class JsonUtils {

    @Test
    public void test01(){
        String str = "{\"result\":{\"success\":true,\"resultObject\":{\"name\":\"zhangsan\",\"age\":\"lisi\"},"
                + "\"errorContext\":{\"msg\":\"error\"}}}";

        JSONObject jsonObject = JSON.parseObject(str);
        JSONObject result = jsonObject.getJSONObject("result");
        String success = result.getString("success");
        System.out.println(success);

        JSONObject resultObject = result.getJSONObject("resultObject");
        System.out.println(resultObject);

        String name = resultObject.getString("name");
        System.out.println(name);

    }

    /**
     * JSON.toJSONString对String类型的value使用时，会有转义符 /" 存在，{"2":"\"2\""}
     */
    @Test
    public void test02(){
        String str = "zhangsan";
        System.out.println(JSON.toJSONString(str));

        HashMap<String, Object> map = new HashMap<>();
        map.put("name",str);

        String name = (String)map.get("name");
        System.out.println(name);
    }
}