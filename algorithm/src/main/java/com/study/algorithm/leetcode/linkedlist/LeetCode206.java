/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.study.algorithm.leetcode.lists.ListNode;

/**
 * LeetCode206
 * 翻转链表
 * @author boyan
 * @version : LeetCode206.java, v 0.1 2022-12-27 14:25 boyan
 */
public class LeetCode206 {



    /**
     * 迭代器方式翻转链表
     * @param head
     * @return
     */
    public static ListNode iterate(ListNode head){
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

    public static ListNode iterate1(ListNode head){
        ListNode prev = null;
        ListNode current = head;
        while (current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

}