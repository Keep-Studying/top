/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.list;

/**
 * 双向链表
 * @author study
 * @version : DoubleLinkList.java, v 0.1 2020年06月27日 16:20 study Exp $
 */
public class DoubleLinkList {
    /**头*/
    private DNode head;
    /**尾*/
    private DNode tail;

    public DoubleLinkList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * 插入头结点元素
     * */
    public void insertHead(int data){
        DNode newNode = new DNode(data);
        if(head == null){
            tail = newNode;
        }else {
            head.pre = newNode;
            newNode.next = head;
        }
        head = newNode;
    }

    /**
     * 删除头结点元素
     * */
    public void deleteHead(){
        //没有数据
        if(head == null){
            return;
        }
        //就一个元素
        if(head.next == null){
            tail = null;
        }else {
            head.next.pre = null;
        }
        head = head.next;
    }

    public void deleteKey(int data){
        DNode cur = this.head;
        //找到对应元素
        while (cur.value != data){
            if(cur.next == null){
                System.out.println("没找到对应结点");
                return;
            }
            cur = cur.next;
        }
        //指向下一个就表示删除第一个
        if(cur == head){
            deleteHead();
        }else {
            cur.pre.next = cur.next;
            //删除的是尾部元素
            if(cur ==tail){
                cur.pre = null;
            }else {
                cur.next.pre = cur.pre;
            }
        }
    }
}

class DNode{
    /**值*/
    int value;
    /**前驱指针*/
    DNode pre;
    /**后继指针*/
    DNode next;

    public DNode(int value) {
        this.value = value;
        this.pre = null;
        this.next = null;
    }
}