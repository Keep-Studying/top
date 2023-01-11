/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.study.algorithm.leetcode.lists.ListNode;
import org.junit.Test;

/**
 * LeetCode876
 * 链表的中间节点
 * @author boyan
 * @version : LeetCode876.java, v 0.1 2022-12-27 15:08 boyan
 */
public class LeetCode876 {


    @Test
    public void test001(){
        ListNode node5 = new ListNode(1, null);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = middleNode(node1);
        System.out.println(node);
    }

    public ListNode middleNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //将快指针移动到链表的尾部
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}