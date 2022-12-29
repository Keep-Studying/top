/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

/**
 * LeetCode21
 * 合并两个有序链表
 * @author boyan
 * @version : LeetCode21.java, v 0.1 2022-12-27 12:40 boyan
 */
public class LeetCode21 {

    /**
     * 循环 + 双指针解决
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeLists(ListNode l1,ListNode l2){
        if( l1 == null){
            return l2;
        }
        if( l2 == null){
            return l1;
        }
        ListNode resultNode = new ListNode(0);
        ListNode p = resultNode;
        while (l1 != null && l2 != null){
            if(l1.value < l2.value){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if( l1 != null){
            p.next = l1;
        }
        if( l2 != null){
            p.next = l2;
        }
        return resultNode.next;
    }
}