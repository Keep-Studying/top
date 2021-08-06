/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.*;

/**
 *
 * @author boyan
 * @version : CodeGenerator.java, v 0.1 2021年07月17日 10:33 下午 boyan Exp $
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 生成文件:/Users/boyan/local/IdeaProjects/Owner/top/mybatis/src/main/java/com/study/mybatis/controller/MbpDatasourceController.java
        // projectPath（当前项目的路径）为/Users/boyan/local/IdeaProjects/Owner/top
        String projectPath = System.getProperty("user.dir");
        // setOutputDir的拼接的值，需要把目标module的路径带上，再加上/src/main/java
        gc.setOutputDir(projectPath + "/mybatis/src/main/java");
        gc.setAuthor("boyan");
        gc.setOpen(false);
        //设置实体类后缀
        gc.setEntityName("%sDO");
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mbp?serverTimezone=GMT&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("mbp");
        dsc.setPassword("mbp1234");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        //String scanner = scanner("请输入整体业务包名");
        //String modelName = StringUtils.isBlank(scanner) ? "" : "."+scanner;
        //moduleName是整体分模块

        pc.setParent("com.study.mybatis");
        pc.setMapper("auto.daointerface");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("auto.dataobject");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        String dtoPath = pc.getParent() + ".auto.dataobject";
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 不输出默认的XML 默认生成的xml在mapper层里面
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //配置自定义输出的文件   xml和dto
        //模板引擎是 velocity
        String xmlTemplatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/mybatis/src/main/resources/mapper/"
                        +tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        String dtoTemplatePath = "/dto.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(dtoTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //com.study.mybatis
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/mybatis/src/main/java/com/study/mybatis/auto/dataobject/" +
                        tableInfo.getEntityName() +StringPool.DOT_JAVA;
            }
        });

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {

            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("dtoPackage", dtoPath);
                this.setMap(map);
            }
        };
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置表名前缀
        strategy.setTablePrefix("mbp_");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //需要包含的表名，当enableSqlFilter为false时，允许正则表达式
        strategy.setEnableSqlFilter(false);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperMapperClass("com.study.mybatis.base.DalBaseMapper");
        strategy.setSuperEntityClass("com.study.mybatis.base.ToString");
        //表填充字段
        List<TableFill> tableFills = new ArrayList<>();
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //设置逻辑删除字段
        strategy.setLogicDeleteFieldName("status");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}