/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.tree;

import java.util.Arrays;

/**
 * 红黑树
 *
 * 红黑树的性质（重点）:
 * 1. 每个节点不是红色就是黑色
 * 2. 不可能有连在一起的红色节点（即黑色的就可以），每个叶子节点都是黑色的空节点（NIL），也就是说叶子节点不存储数据
 * 3. 根节点（root）都是黑色
 * 4. 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点
 *
 * 那么红黑树有几种变换呢？有以下3种：
 * 1. 改变颜色：最简单，红变黑，黑变红
 * 2. 左旋：针对于点旋转，但是点上面的子树也要跟着旋转，有指针的变动
 * 3. 右旋：
 *
 * 应用：
 * 1.HashMap：jdk1.8开始
 * 2.TreeMap
 * 3.Windows底层：查找
 * 4.Linux进程调度，Nginx等
 * @author study
 * @version : RedBlackTree.java, v 0.1 2020年07月07日 0:41 study Exp $
 */
public class RedBlackTree {
    /**红色*/
    private final int R = 0;
    /**黑色*/
    private final int B = 1;

    private class Node{
        int key = -1;
        /**默认是黑色*/
        int color = B;
        /**左节点*/
        Node left = nil;
        Node right = nil;
        Node parent = nil;

        public Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Node [key=" + key + ", color=" + color + ", left=" + left.key + ", right=" + right.key + ", parent="
                    + parent.key + "]" + "\r\n";
        }
    }

    /**声明nil空节点*/
    private final Node nil = new Node(-1);
    /**声明根节点*/
    private Node root = nil;

    /**
     * 中序遍历打印：左 根（输出） 右
     * */
    public void printTree(Node node) {
        if(node == nil){
            return;
        }
        printTree(node.left);
        System.out.println(node.toString());
        printTree(node.right);
    }

    /**
     * 插入
     *
     * 插入的时候旋转和颜色变换规则：
     * 1. 变颜色的情况：当前节点的父亲是红色，且它的祖父节点的另一个子节点（叔叔）也是红色
     *    a. 把父亲设为黑色
     *    b. 把叔叔也设为黑色
     *    c. 把祖父（爷爷）也就是父亲的父亲设为红色
     *    d. 把指针定义到祖父节点（爷爷）设为当前要操作的
     * 2. 左旋：当前父节点是红色，叔叔是黑色的时候，且当前的节点是右子树，
     *    左旋以父节点作为左旋，指针变换到父亲节点
     * 3. 右旋：当前父节点是红色，叔叔是黑色的时候，且当前的节点是左子树，右旋
     *    a. 把父节点变为黑色
     *    b. 把祖父（爷爷）变为红色
     *    c. 以祖父（爷爷）节点旋转
     *
     * 时间复杂度：T(n) ≈ O(nlogn)
     * */
    private void insert(Node node){
        Node temp = root;
        if(root == nil){
            root = node;
            node.color = B;
            node.parent = nil;
        }else {
            node.color = R;
            while (true){
                if(node.key < temp.key){
                    if(temp.left == nil){
                        temp.left = node;
                        node.parent = temp;
                        break;
                    }else {
                        temp = temp.left;
                    }
                }else if(node.key >= temp.key){
                    if(temp.right == nil){
                        temp.right = node;
                        node.parent = temp;
                        break;
                    }else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    /**
     *
     * */
    private void fixTree(Node node){
        while (node.parent.color == R){
            Node y = nil;
            if(node.parent == node.parent.parent.left){
                y = node.parent.parent.right;
                if(y != nil && y.color == R){
                    node.parent.color = B;
                    y.color = B;
                    node.parent.parent.color = R;
                    node = node.parent.parent;
                }

                if(node == node.parent.right){
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = B;
                node.parent.parent.color = B;
                rotateRight(node.parent.parent);
            }else {
                y = node.parent.parent.left;
                if(y != nil && y.color == R){
                    node.parent.color = B;
                    y.color = B;
                    node.parent.parent.color = R;
                    node = node.parent.parent;
                    continue;
                }
                if(node == node.parent.left){
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = B;
                node.parent.parent.color = R;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = B;
    }
    /**
     * 左旋：当前父节点是红色，叔叔是黑色的时候，且当前的节点是右子树，
     *      左旋以父节点作为左旋，指针变换到父亲节点
     * */
    private void rotateLeft(Node node){
        if(node.parent != nil){
            if(node == node.parent.left){
                node.parent.left = node.right;
            }else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if(node.right.left != nil){
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }else {
            Node right = root.right;
            root.right = root.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    /**
     * 右旋：当前父节点是红色，叔叔是黑色的时候，且当前的节点是左子树，右旋
     *  a. 把父节点变为黑色
     *  b. 把祖父（爷爷）变为红色
     *  c. 以祖父（爷爷）节点旋转
     * */
    private void rotateRight(Node node){
        if(node.parent != nil){
            if(node == node.parent.left){
                node.parent.left = node.left;
            }else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if(node.left.right != nil){
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        }else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    /**
     * 构造一棵树
     * */
    public void createTree(){
        int data[]= {23,32,15,221,3};
        Node node;
        System.out.println(Arrays.toString(data));
        for (int i = 0; i < data.length; i++) {
            node = new Node(data[i]);
            insert(node);
        }
        printTree(root);
    }

    /**
     * 查找
     * 时间复杂度：T(n) = O(logn)
     * */
    public void find(Node root,int dada){
        //todo
    }

    /**
     * 删除
     * 时间复杂度：T(n) ≈ O(logn)
     * */
    public void delete(Node root,int dada){
        //todo 不要求掌握
    }

    /**
     * 输出结果：
     * [23, 32, 15, 221, 3]
     * Node [key=3, color=0, left=-1, right=-1, parent=15]
     *
     * Node [key=15, color=1, left=3, right=-1, parent=23]
     *
     * Node [key=23, color=1, left=15, right=32, parent=-1]
     *
     * Node [key=32, color=1, left=-1, right=221, parent=23]
     *
     * Node [key=221, color=0, left=-1, right=-1, parent=32]
     * */
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.createTree();
    }
}