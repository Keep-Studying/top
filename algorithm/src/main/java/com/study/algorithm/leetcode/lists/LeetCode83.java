/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode83
 * 删除排序链表中重复元素
 * 存在一个升序排序的链表，给你这个链表的头结点，请删除所有重复的元素，
 * 使每个元素只出现一次
 * @author boyan
 * @version : LeetCode83.java, v 0.1 2022-12-27 13:47 boyan
 */
public class LeetCode83 {

    @Test
    public void test001(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = deleteDuplicates(node1);
        System.out.println(node);
    }

    /**
     * T(n) = O(n)
     * S(n) = O(1)
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head){
        if (head == null){
            return head;
        }
        ListNode currentNode = head;
        while (null != currentNode.next){
            if(currentNode.next.value == currentNode.value){
                currentNode.next = currentNode.next.next;
            }else {
                currentNode = currentNode.next;
            }
        }
        return head;
    }

}