/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.study.algorithm.leetcode.lists.ListNode;
import org.junit.Test;

import java.util.HashSet;

/**
 * LeetCode141
 * 环形链表
 * 给定一个链表，判断链表中是否存在环，存在，则返回true，否则，返回false
 * @author boyan
 * @version : LeetCode141.java, v 0.1 2022-12-27 13:53 boyan
 */
public class LeetCode141 {

    @Test
    public void test001(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        node5.next = node3;

        System.out.println(hasCircle2(node1));
        System.out.println(hasCircle(node1));
    }

    /**
     * 使用HashSet来解决
     *
     * @param head
     * @return
     */
    public boolean hasCircle2(ListNode head){
        HashSet<ListNode> listNodes = new HashSet<>();
        while (head != null){
            if (!listNodes.add(head)) {
                return true;
            }
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
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        boolean loopExists = false;
        while (slowPtr.next != null && fastPtr.next.next != null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
            if(slowPtr == fastPtr){
                loopExists = true;
                break;
            }
        }
        return loopExists;
    }
}