/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

/**
 * LemonChange
 * 在柠檬水摊位上，每一杯柠檬水售价为5美元，顾客排队买产品，一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你支付5美元、10美元或者20美元，必须给每个顾客正确找零钱
 * 注意：一开始你手头是没有任何零钱的，
 * 如果你能够给每个顾客正确找零钱，则返回true，否则返回false
 * @author boyan
 * @version : LemonChange.java, v 0.1 2022-12-07 17:01 boyan
 */
public class LemonChange {

    public static void main(String[] args) {
        System.out.println(change(new int[]{5,5,20}));
        System.out.println(change(new int[]{5,5,10,20}));
    }

    public static boolean change(int[] bills){
        int five = 0,ten = 0;
        for (int bill : bills){
            if (bill == 5){
                five++;
            }else if (bill == 10){
                if (five == 0){
                    return false;
                }
                five--;
                ten++;
            }else {
                // 即bill == 20
                if(five >0 && ten > 0){
                    five--;
                    ten--;
                }else if (five >=3){
                    five -=3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
}