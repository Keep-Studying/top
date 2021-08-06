package com.study.mybatis;

import com.study.mybatis.config.MybatisPlusConfiguration;
import com.study.mybatis.config.TransactionConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisPlusConfiguration.class, TransactionConfiguration.class})
public class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

}
