/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCodeJz61
 * 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCodeJz61.java, v 0.1 2023-01-12 10:05 boyan
 */
public class LeetCodeJz61 {

    @Test
    public void test001(){
        int[] nums = new int[]{1,2,3,4,5};
        boolean straight = isStraight(nums);
        System.out.println(straight);
    }

    public boolean isStraight(int[] nums){
        Set<Integer> repeat = new HashSet<>();
        int max = 0,min = 14;
        for (int num:nums) {
            if (num == 0){
                //跳过大小王
                continue;
            }
            max = Math.max(max,num);
            min = Math.min(min,num);
            if (repeat.contains(num)){
                // 若有重复，提前返回 false
                return false;
            }
            repeat.add(num);
        }
        // 最大牌 - 最小牌 < 5 则可构成顺子
        return max - min < 5;
    }
}