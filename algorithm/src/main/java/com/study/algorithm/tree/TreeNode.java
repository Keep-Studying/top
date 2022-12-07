/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.tree;

/**
 * TreeNode
 *
 * @author boyan
 * @version : TreeNode.java, v 0.1 2022-12-07 16:01 boyan
 */
public class TreeNode {

    /**节点值*/
    public int data;
    /**左孩子*/
    public TreeNode left;
    /**右孩子*/
    public TreeNode right;

    public int depth;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}