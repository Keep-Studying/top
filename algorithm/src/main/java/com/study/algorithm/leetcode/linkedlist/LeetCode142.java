/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.alibaba.fastjson.JSON;
import com.study.algorithm.leetcode.lists.ListNode;
import org.junit.Test;

/**
 * LeetCode142
 * 环形链表
 * 给定一个链表，返回链表开始进入环的第一个节点，否则返回null
 * @author boyan
 * @version : LeetCode142.java, v 0.1 2022-12-27 14:04 boyan
 */
public class LeetCode142 {

    @Test
    public void test001(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        node5.next = node3;
        ListNode listNode = hasCircle(node1);
        System.out.println(JSON.toJSONString(listNode));
    }

    /**
     * 使用快慢指针来解决
     *
     * @param head
     * @return
     */
    public ListNode hasCircle(ListNode head){
        if (head == null){
            return null;
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
        // 如果环存在
        if (loopExists){
            slowPtr = head;
            while (slowPtr != fastPtr){
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }
            //返回环的开始结点
            return slowPtr;
        }

        return null;
    }

    public ListNode hasCircle1(ListNode head){
        if (head == null){
            return null;
        }
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        boolean loopExists = false;
        while (slowPtr.next != null && fastPtr.next.next != null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
            if (slowPtr == fastPtr){
                loopExists = true;
                break;
            }
        }
        if(loopExists){
            slowPtr = head;
            while (slowPtr != fastPtr){
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }
            return slowPtr;
        }
        return null;
    }
}