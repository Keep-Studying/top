/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.list;

/**
 *
 * 约瑟夫问题：N个人围成一圈，从第一个开始报数，第M个将被杀掉，剩下最后一个，
 * 其余人都将被杀掉。假如N=6，M=5，被杀掉的顺序是：5,4,6,2,3,1
 * 现在问你最后留下的人是谁？
 * 比如N=6，M=5，留下的就是1
 * 1 2 3 4 5 6 =>6 1 2 3 4 => 6 1 2 3 => 1 2 3 =>1 3 => 1
 * @author study
 * @version : JosefCircle.java, v 0.1 2020年06月27日 23:22 study Exp $
 */
public class JosefCircle {

    private RoundNode head;

    /**
     * 插入链表的头部
     * O(1)
     * */
    public void insertHead(int data){
        RoundNode newNode = new RoundNode(data);
        //如果原来就有数据呢?
        newNode.next = head; //栈内存的使用
        head = newNode;
        //插入O(1)
    }

    int josefCircle(RoundNode node,int m){
        if(node.next == node){
            return node.data;
        }else {
            RoundNode roundNode = node;
            for (int i = 1; i < m-1; i++) {
                roundNode = node.next;
            }
            System.out.println("position "+roundNode.next.data+" is killed");
            print();
            return josefCircle(roundNode.next,m);
        }
    }

    public void print(){
        RoundNode cur = this.head;
        while (cur.next != head){
            System.out.println(cur.data+",");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}

class RoundNode{
    int data;
    RoundNode next;

    public RoundNode(int data) {
        this.data = data;
        this.next = null;
    }
}