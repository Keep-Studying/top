/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode234
 * 回文链表
 * 给定一个单链表的头节点head，请判断该链表是否为回文链表，
 * 如果是，则返回true，否则，返回false
 * @author boyan
 * @version : LeetCode234.java, v 0.1 2022-12-27 14:36 boyan
 */
public class LeetCode234 {
    
    @Test
    public void test001(){
        ListNode node5 = new ListNode(1, null);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        boolean palindrome = isPalindrome(node1);
        System.out.println(palindrome);
    }

    public boolean isPalindrome(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //将快指针移动到链表的尾部
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果链表的节点个数为奇数，则将正中间的节点归到左边
        if(fast != null){
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow!=null){
            if(fast.value != slow.value){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode current = head;
        while (current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current =next;
        }
        return prev;
    }
}