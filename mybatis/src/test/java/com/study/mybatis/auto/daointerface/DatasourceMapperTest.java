package com.study.mybatis.auto.daointerface;

import com.alibaba.fastjson.JSON;
import com.study.mybatis.MybatisApplicationTests;
import com.study.mybatis.auto.dataobject.DatasourceDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author boyan
 * @version : DatasourceMapperTest, v0.1 2021年07月18日 11:50 上午 boyan Exp $
 */
public class DatasourceMapperTest extends MybatisApplicationTests {

    @Resource
    private DatasourceMapper datasourceMapper;

    @Test
    public void testSelect(){
        List<DatasourceDO> datasourceDOS = datasourceMapper.selectList(null);
        System.out.println(datasourceDOS);
        System.out.println("------");
        System.out.println(JSON.toJSONString(datasourceDOS));
    }

}