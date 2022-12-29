/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode018
 * 链表中倒数第k个节点
 * @author boyan
 * @version : LeetCode018.java, v 0.1 2022-12-27 15:18 boyan
 */
public class LeetCode018 {


    @Test
    public void test001(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = kthNodeFromEnd(node1,3);
        System.out.println(node);
    }

    public ListNode kthNodeFromEnd(ListNode head ,int kthNode){
        if(kthNode <= 0 || head == null){
            return null;
        }
        ListNode pTemp = head;
        ListNode pKthNode = null;
        // pTemp沿着链表移动了k-1次
        for (int i = 1; i < kthNode; i++) {
            if(pTemp != null){
                pTemp = pTemp.next;
            }
        }
        //将快指针移动到链表的尾部
        while (pTemp != null ){
            if(pKthNode == null){
                pKthNode = head;
            }else {
                pKthNode = pKthNode.next;
            }
            pTemp = pTemp.next;
        }
        if(pKthNode != null){
            return pKthNode;
        }
        return null;
    }
}