/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testcompentscan.config;

import com.study.spring.annotations.testcompentscan.filtertype.MyFilterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月12日 0:09 study Exp $
 */
@Configuration
/*@ComponentScan(basePackages = {"com.study.spring.annotations.testcompentscan"},excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {MyService.class})
})*/
/*@ComponentScan(basePackages = {"com.study.spring.annotations.testcompentscan"},excludeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class, Service.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM,value = MyFilterType.class)
},includeFilters = {`
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Repository.class)
})*/
@ComponentScan(basePackages = {"com.study.spring.annotations.testcompentscan"},includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,value = MyFilterType.class)
},useDefaultFilters = false)
//@ComponentScan(basePackages ={"com.study.spring.annotations.testcompentscan"} )
public class MainConfig {
}