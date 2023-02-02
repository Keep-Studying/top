/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode322
 * 零钱兑换，也可搜索动态规划相关题型（技术面试高频考点）
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCode322.java, v 0.1 2023-01-12 11:17 boyan
 */
public class LeetCode322 {

    @Test
    public void test001(){
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        int coinChange = coinChange(coins, amount);
        System.out.println(coinChange);
    }


    /**
     * 时间复杂度：O(Sn)O(Sn)，其中 SS 是金额，nn 是面额数。我们一共需要计算 O(S)O(S) 个状态，SS 为题目所给的总金额。对于每个状态，每次需要枚举 nn 个面额来转移状态，所以一共需要 O(Sn)O(Sn) 的时间复杂度。
     * 空间复杂度：O(S)O(S)。数组 \textit{dp}dp 需要开长度为总金额 SS 的空间。
     *
     * https://leetcode.cn/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount +1;
        int[] dp =new int[amount+1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i){
                    // 状态转移公式
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
//        System.out.println(JSON.toJSONString(dp));
        return dp[amount] > amount ? -1:dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        int max = amount +1;
        int[] dp =new int[max];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] < i){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1:dp[amount];
    }
}