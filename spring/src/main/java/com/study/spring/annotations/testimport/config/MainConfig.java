/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testimport.config;

import com.study.spring.annotations.testimport.component.Car;
import com.study.spring.annotations.testimport.component.Person;
import com.study.spring.annotations.testimport.importselect.BeanDefinitionRegister;
import com.study.spring.annotations.testimport.importselect.MyImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 往IOC容器中添加组件的方式：
 * 1. 通过@ComponentScan+ @Controller，@Service，@Repository，@Compent
 * 适用场景：针对我们自己写的组件可以通过该方式来进行加载到容器中
 * 2. 通过@Bean的方式来导入组件（适用于导入第三方组件的类）
 * 3. 通过@Import来导入组件（导入组件的id为全类名路径）【导入第三方组件】
 * 4. 通过FactoryBean接口来实现注册组件，如SqlSessionFactoryBean
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 23:09 study Exp $
 */
@Configuration
//@Import(value = {Person.class, Car.class})
//@Import(value = {Person.class, Car.class, MyImportSelector.class})
@Import(value = {Person.class, Car.class, MyImportSelector.class, BeanDefinitionRegister.class})
/**
 * 此注解对应MainCLass的输出结果
 * beanName:org.springframework.context.annotation.internalConfigurationAnnotationProcessor
 * beanName:org.springframework.context.annotation.internalAutowiredAnnotationProcessor
 * beanName:org.springframework.context.annotation.internalCommonAnnotationProcessor
 * beanName:org.springframework.context.event.internalEventListenerProcessor
 * beanName:org.springframework.context.event.internalEventListenerFactory
 * beanName:mainConfig
 * beanName:com.study.spring.annotations.testimport.component.Person
 * beanName:com.study.spring.annotations.testimport.component.Car
 * beanName:com.study.spring.annotations.testimport.component.Dog
 * beanName:cat
 * */
public class MainConfig {
}