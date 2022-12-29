/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.HashSet;

/**
 * LeetCode141
 * 环形链表
 * 给定一个链表，判断链表中是否存在环，存在，则返回true，否则，返回false
 * @author boyan
 * @version : LeetCode141.java, v 0.1 2022-12-27 13:53 boyan
 */
public class LeetCode141 {

    /**
     * 使用HashSet来解决
     *
     * @param head
     * @return
     */
    public boolean hasCircle2(ListNode head){
        if (head == null){
            return false;
        }
        HashSet<ListNode> listNodes = new HashSet<>();
        while (head.next != null){
            if(listNodes.contains(head)){
                return true;
            }
            listNodes.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 使用快慢指针来解决
     *
     * @param head
     * @return
     */
    public boolean hasCircle(ListNode head){
        if (head == null){
            return false;
        }
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (slowPtr.next != null && fastPtr.next.next != null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
            if(slowPtr == fastPtr){
                return true;
            }
        }
        return false;
    }
}