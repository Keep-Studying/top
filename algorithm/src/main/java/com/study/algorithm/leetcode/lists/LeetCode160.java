/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

/**
 * LeetCode160
 * 相交链表
 * 给定两个单链表的头结点headA和headB，请找出两个单链表相交的起始位置，如果没有交点，返回null
 * 解决：
 * 穷举
 * hash表
 * 双指针：移动到尾部后，则交换到另一个链表上的head
 * 计算两个链表的长度差，并直接从节点数相等的位置开始
 * @author boyan
 * @version : LeetCode160.java, v 0.1 2022-12-27 14:11 boyan
 */
public class LeetCode160 {

    /**
     * 两个指针
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        if (headA == null || headB == null){
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}