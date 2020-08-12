/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testconditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author study
 * @version : MyCondition.java, v 0.1 2020年08月11日 23:51 study Exp $
 */
@Configuration
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //判断容器中是否有aspect的组件
        if(conditionContext.getBeanFactory().containsBean("aspect")) {
            return true;
        }
        return false;
    }
}