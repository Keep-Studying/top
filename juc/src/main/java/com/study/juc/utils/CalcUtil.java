/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.utils;

import java.math.BigInteger;

/**
 * @author study
 * @version : CalcUtil.java, v 0.1 2020年09月05日 15:34 study Exp $
 */
public class CalcUtil {

    public static BigInteger calculateFactorial(BigInteger param) {

        return cal(param);
    }

    private static BigInteger cal(BigInteger param) {
        if (param.intValue() == 1) {
            return param;
        }

        BigInteger result = new BigInteger(param.toString());

        BigInteger subtract = result.subtract(new BigInteger("1"));

        result = result.multiply(cal(subtract));

        return result;
    }
}