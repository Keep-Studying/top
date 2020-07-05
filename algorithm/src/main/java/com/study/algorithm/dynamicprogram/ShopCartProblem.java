/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

/**
 *
 * 双十一马上就要来了，小C心目中的女神在购物车加了N个东西，突然她中了一个奖就
 * 可以清空购物车500元的东西（不能找零），每个东西只能买一件，那么她应该如何
 * 选择物品使之中奖的额度能最大利用呢？如果存在多种最优组合，你只需要给出一种
 * 即可。现在女神来问你，你该怎么办？
 *
 * @author study
 * @version : ShopCartProblem.java, v 0.1 2020年07月04日 19:19 study Exp $
 */
public class ShopCartProblem {
    /**
     * 输出结果：
     * 490
     * 5 : 210
     * 3 : 120
     * 2 : 100
     * 1 : 60
     * */
    public static void main(String[] args) {
        //价格数组
        int value[] = {60, 100, 120,180,210,250};

        //v表示总价,n表示是物品
        int v = 500;
        int n = value.length;
        //初始化矩阵时元素全是0
        int dp[][] = new int[n + 1][v + 1];
        for (int i = 1; i <= n; i++) {//每次加的物品
            for (int cw = 1; cw <= v; cw++) {//分割的购物车
                if (value[i - 1] <= cw) {//表示这个物品可以装进去
                    dp[i][cw] = Math.max(value[i - 1] + dp[i - 1][cw - value[i - 1]], dp[i - 1][cw]);
                } else {
                    dp[i][cw] = dp[i - 1][cw];
                }
            }
        }
        v = dp[n][v];
        System.out.println(dp[n][v]);
        //输出添加的物品
        for (int i = n; i > 1; i--) {
            if(dp[i][v] == dp[i-1][v]){
                //不用加该物品
            }else {
                System.out.println(i+" : "+value[i-1]);
                v = v - value[i-1];
            }
        }
        if(v != 0){
            //表示最后一个物品时加进来的
            System.out.println(1+" : "+value[0]);
        }
    }
}