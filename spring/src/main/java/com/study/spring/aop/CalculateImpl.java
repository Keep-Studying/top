/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.spring.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author boyan
 * @version : CalculateImpl.java, v 0.1 2021年07月15日 12:09 上午 boyan Exp $
 */
@Service()
public class CalculateImpl implements Calculate {
    public int add(int numA, int numB) {
        System.out.println("执行目标方法:add");
        //System.out.println(1/0);
        return numA+numB;
    }

    public int reduce(int numA, int numB) {
        System.out.println("执行目标方法:reduce");
        return numA-numB;
    }

    public int div(int numA, int numB) {
        System.out.println("执行目标方法:div");

        return numA/numB;
    }

    public int multi(int numA, int numB) {
        System.out.println("执行目标方法:multi");

        return numA*numB;
    }

    public int mod(int numA,int numB){
        System.out.println("执行目标方法:mod");

        int retVal = ((Calculate) AopContext.currentProxy()).add(numA,numB);
        //int retVal = this.add(numA,numB);

        return retVal%numA;

        //return numA%numB;
    }
}