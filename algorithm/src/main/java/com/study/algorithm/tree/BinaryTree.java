/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.tree;

/**
 * 二叉树
 * 二叉树（binary tree）是指树中节点的度不大于2的有序树，它是一种最简单且最
 * 重要的树。二叉树的递归定义为：二叉树是一棵空树，或者是一棵由一个根节点和两
 * 棵互不相交的，分别称作根的左子树和右子树组成的非空树；左子树和右子树又同样
 * 都是二叉树
 *
 * 重要口诀：根节点输出；子树
 *
 * @author study
 * @version : BinaryTree.java, v 0.1 2020年07月05日 13:27 study Exp $
 */
public class BinaryTree {

    /**
     * 打印节点值
     * */
    public void print(TreeNode treeNode){
        System.out.print(treeNode.getData());
    }

    /**
     * 先序遍历（前序遍历）
     * 顺序：根 左 右
     * */
    public void preOrder(TreeNode treeNode){
        print(treeNode);
        if(treeNode.getLeft() != null){
            preOrder(treeNode.getLeft());
        }
        if(treeNode.getRight() != null){
            preOrder(treeNode.getRight());
        }

    }

    /**
     * 中序遍历
     * 顺序：左 根 右
     * */
    public void inOrder(TreeNode treeNode){
        if(treeNode.getLeft() != null){
            inOrder(treeNode.getLeft());
        }
        print(treeNode);
        if(treeNode.getRight() != null){
            inOrder(treeNode.getRight());
        }
    }

    /**
     * 后序遍历
     * 顺序：左 右 根
     * */
    public void postOrder(TreeNode treeNode){
        if(treeNode.getLeft() != null){
            postOrder(treeNode.getLeft());
        }
        if(treeNode.getRight() != null){
            postOrder(treeNode.getRight());
        }
        print(treeNode);
    }

    /**
     * 二叉树构造时，从叶子开始
     * 代码中测试使用的二叉树如下：
     *              A
     *            /  \
     *           B    E
     *           \     \
     *            C     F
     *           /     /
     *          D     G
     *               / \
     *              H   K
     * 输出结果：
     * 二叉树遍历
     * 先序遍历
     * ABCDEFGHK
     * 中序遍历
     * BDCAEHGKF
     * 后序遍历
     * DCBHKGFEA
     * */
    public static void main(String[] args) {
        TreeNode D = new TreeNode('D', null, null);
        TreeNode H = new TreeNode('H', null, null);
        TreeNode K = new TreeNode('K', null, null);
        TreeNode C = new TreeNode('C', D, null);
        TreeNode G = new TreeNode('G', H, K);
        TreeNode B = new TreeNode('B', null, C);
        TreeNode F = new TreeNode('F', G, null);
        TreeNode E = new TreeNode('E', null, F);
        TreeNode A = new TreeNode('A', B, E);

        System.out.println("二叉树遍历");
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("先序遍历");
        binaryTree.preOrder(A);
        System.out.println();
        System.out.println("中序遍历");
        binaryTree.inOrder(A);
        System.out.println();
        System.out.println("后序遍历");
        binaryTree.postOrder(A);
        System.out.println();
    }

}

/**
 * 树节点
 * */
class TreeNode{
    /**节点值*/
    private char data;
    /**左孩子*/
    private TreeNode left;
    /**右孩子*/
    private TreeNode right;

    public TreeNode(char data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public char getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>left</tt>.
     *
     * @return property value of left
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Setter method for property <tt>left</tt>.
     *
     * @param left value to be assigned to property left
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Getter method for property <tt>right</tt>.
     *
     * @return property value of right
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Setter method for property <tt>right</tt>.
     *
     * @param right value to be assigned to property right
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }
}