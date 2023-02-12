/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.linkedlist;

import com.study.algorithm.leetcode.lists.ListNode;
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
        if (head == null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        //将快指针移动到链表的尾部
        while (fast.next != null && fast.next.next != null){
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

    /**
     *
     作者：LeetCode-Solution
     链接：https://leetcode.cn/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.value != p2.value) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}