/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

/**
 * 动态规划-背包问题
 *
 * 背包问题(Knapsack problem)是一种组合优化的NP完全问题(Non-deterministic
 * Polynomial Complete)。
 * 问题可以描述为：给定一组物品，每种物品都有自己的重量和价格，在限定的总重量内，
 * 我们如何选择，才能使得物品的总价格最高
 *
 * 示例如下：
 * 小偷去某商店偷窃，背有一个背包，容量是50kg，现在有以下物品（物品不能切分，且只有一个），
 * 价值：60 100 120
 * 重量：10  20  40
 * 请问小偷应该怎么拿才能得到最大的价值?
 * @author study
 * @version : KnapsackProblem.java, v 0.1 2020年07月04日 18:44 study Exp $
 */
public class KnapsackProblem {

    /**
     * 假设袋子容量5kg
     * 物品信息如下：
     * 1. 价格：6  10  12
     * 2. kg：  1   2    4
     * 处理思路：
     * 把5kg的容量拆分为5份，拆分成1kg，1kg这样子计算，
     * 下面的表格元素就表示当前重量及物品下，能装的最多钱。表格的数列就表示是要装的物品
     *
     * 物品   1kg     1kg     1kg     1kg     1kg
     * 物品1  6       6       6       6       6
     * 物品2  6       10      16      16      16
     * 物品3  6       10      16      16      18
     *
     * 上面这一个递推过程总结起来就是一个东西——状态转移方程
     * 能装的时候，每次和上面的比较，大我就装，否则就不装
     * Max(money[i]+res[i-1][w-weight[i]],res[i-1][w]);
     * 其中：
     * money[i]+res[i-1][w-weight[i]]：装这个物品
     * w-weight[i]：表示装完还剩下的空间
     * res[i-1][w-weight[i]]：表示装完后还剩下的空间还能装的最大值，取上一次的结果
     * res[i-1][w]：表示不装这个物品的值
     *
     * 输出结果为：
     * 180
     * */
    public static void main(String[] args) {
        //价格数组
        int value[] = {60,100,120};
        //重量数组
        int weight[] = {10,20,40};

        //w表示重量,n表示是物品
        int w = 50;
        int n = 3;
        //初始化矩阵时元素全是0
        int dp[][] = new int [n+1][w+1];
        for (int i = 1; i <= n; i++) {//每次加的物品
            for (int cw = 1; cw <= w; cw++) {//分割的背包
                if(weight[i-1] <= cw){//表示这个物品可以装进去
                    dp[i][cw] = Math.max(value[i-1]+dp[i-1][cw-weight[i-1]],dp[i-1][cw]);
                }else {
                    dp[i][cw] = dp[i-1][cw];
                }
            }
        }
        System.out.println(dp[n][w]);
    }
}