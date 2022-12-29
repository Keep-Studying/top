/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import com.google.common.base.MoreObjects;

/**
 * ListNode
 *
 * @author boyan
 * @version : ListNode.java, v 0.1 2022-12-27 13:45 boyan
 */
public class ListNode {

    public int value;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    @Override public String toString() {
        return MoreObjects.toStringHelper(this).add("value", value).add("next", next).toString();
    }
}