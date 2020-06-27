/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.list;

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
}

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}