/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.list;

import org.junit.Test;

/**
 * 单链表
 * @author study
 * @version : MyLinkedList.java, v 0.1 2020年06月27日 15:58 study Exp $
 */
public class MyLinkedList {
    private ListNode head;
    private int size = 0;

    /**
     * 插入链表的头部
     * O(1)
     * */
    public void insertHead(int data){
        ListNode newNode = new ListNode(data);
        //如果原来就有数据呢?
        newNode.next = head; //栈内存的使用
        head = newNode;
        size++;
        //插入O(1)
    }

    /**
     * 插入中间，假设定义在第N个插入
     * O(n)
     * */
    public void insertNth(int data,int position){
        if(position == 0){
            insertHead(data);
        }else {
            ListNode cur = this.head;
            for (int i = 1; i < position; i++) {
                //一直往后遍历
                //p=p->next;  ->是c++里面的往后找指针
                cur = cur.next;
            }
            ListNode newNode = new ListNode(data);
            //新加的点指向后面，保证不断链
            newNode.next = cur.next;
            //把当前的点指向新加的点
            cur.next = newNode;
            size++;
        }
    }

    /**
     * 删除头部元素
     * O(1)
     * */
    public void deleteHead(){
        head = head.next;
        size--;
    }

    /**
     * 删除中间指定位置的元素
     * O(n)
     * */
    public void deleteNth(int position){
        if(position == 0){
            deleteHead();
        }else {
            ListNode cur = this.head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;
            }
            //cur.next 表示的是删除的点，后一个next就是我们要指向的
            cur.next = cur.next.next;
            size--;
        }
    }

    /**
     * 查找指定位置的元素
     * O(n)
     * */
    public void find(int data){
        ListNode cur = this.head;
        while (cur != null){
            if(cur.value == data){
                break;
            }
            cur = cur.next;
        }
    }

    public void print(){
        ListNode cur = this.head;
        while (cur != null){
            System.out.println(cur.value);
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insertHead(5);
        myLinkedList.insertHead(7);
        myLinkedList.insertHead(10);
        System.out.println("size:"+myLinkedList.size);
        myLinkedList.print();//10 -> 7 -> 5

        myLinkedList.deleteNth(0);
        System.out.println("size:"+myLinkedList.size);
        myLinkedList.print();//7 -> 5

        myLinkedList.deleteHead();
        System.out.println("size:"+myLinkedList.size);
        myLinkedList.print();//5

        myLinkedList.insertNth(11,1);
        System.out.println("size:"+myLinkedList.size);
        myLinkedList.print();//5 -> 11

        myLinkedList.deleteNth(1);
        System.out.println("size:"+myLinkedList.size);
        myLinkedList.print();//5
    }

    @Test
    public void test001(){
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode prev = recursion(node1);
        System.out.println(prev);
    }

    /**
     * 迭代器方式翻转链表
     * @param head
     * @return
     */
    public static ListNode iterate(ListNode head){
        ListNode prev = null;
        ListNode next ;
        ListNode current = head;
        while (current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current =next;
        }
        return prev;
    }

    public static ListNode recursion(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}