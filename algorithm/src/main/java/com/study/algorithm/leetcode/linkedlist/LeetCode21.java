/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.study.algorithm.leetcode.lists.ListNode;
import org.junit.Test;

/**
 * LeetCode21
 * 合并两个有序链表
 * @author boyan
 * @version : LeetCode21.java, v 0.1 2022-12-27 12:40 boyan
 */
public class LeetCode21 {


    @Test
    public void test001(){
        ListNode node5 = new ListNode(9, null);
        ListNode node4 = new ListNode(7, node5);
        ListNode node3 = new ListNode(5, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode node22 = new ListNode(4, null);
        ListNode node11 = new ListNode(2, node22);
        ListNode node = mergeLists(node1,node11);
        System.out.println(node);
    }

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