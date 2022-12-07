/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.tree;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 * 二叉树（binary tree）是指树中节点的度不大于2的有序树，它是一种最简单且最
 * 重要的树。二叉树的递归定义为：二叉树是一棵空树，或者是一棵由一个根节点和两
 * 棵互不相交的，分别称作根的左子树和右子树组成的非空树；左子树和右子树又同样
 * 都是二叉树
 *
 * 重要口诀：根节点输出；子树
 *
 * 前序遍历：根左右
 * 中序遍历：左根右
 * 后序遍历：左右根
 * 层序遍历：从上往下，从左到右
 *
 * 递归遍历：使用递归方法遍历
 * 迭代遍历：使用迭代方法实现递归函数，与递归等价
 * morris遍历：时间复杂度是O(n)，但是空间复杂度是O(1)，
 *          建立中序线索二叉树，线索二叉树会破坏二叉树的结构，如果发现前驱结点的右指针指向自己，
 *          就把这个指针删除掉
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
     * 先序遍历（前序遍历）
     * 顺序：根 左 右
     * */
    public void preOrderIterator(TreeNode treeNode){
        if(treeNode != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(treeNode);
            while (!stack.isEmpty()){
                treeNode = stack.pop();
                if (treeNode != null){
                    print(treeNode);
                    stack.push(treeNode.right);
                    stack.push(treeNode.left);
                }
            }
        }
    }


    /**
     * 先序遍历（前序遍历）
     * 顺序：根 左 右
     * */
    public void preOrderMorris(TreeNode current){
        if (current == null){
            return;
        }
        // 左子树的最右边结点
        TreeNode mostRight = null;
        while (current != null){
            mostRight = current.left;
            if (mostRight != null){
                // 找到左子树的最右边结点
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    // 建立线索指针
                    mostRight.right = current;
                    print(current);
                    current = current.left;
                    continue;
                }else {
                    // mostRight.right == current 时，需要删除线索指针
                    mostRight.right = null;
                }
            } else {
                print(current);
            }
            current = current.right;
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
     * 中序遍历
     * 顺序：左 根 右
     * */
    public void inOrderIterator(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null){
                if (root != null){
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    print(root);
                    root = root.right;
                }
            }
        }
    }


    /**
     * 先序遍历（前序遍历）
     * 顺序：根 左 右
     * */
    public void inOrderMorris(TreeNode current){
        if (current == null){
            return;
        }
        // 左子树的最右边结点
        TreeNode mostRight = null;
        while (current != null){
            mostRight = current.left;
            if (mostRight != null){
                // 找到左子树的最右边结点
                while (mostRight.right != null && mostRight.right != current) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    // 建立线索指针
                    mostRight.right = current;
//                    print(current);
                    current = current.left;
                    continue;
                }else {
                    // mostRight.right == current 时，需要删除线索指针
                    mostRight.right = null;
                }
            } else {
//                print(current);
            }
            print(current);
            current = current.right;
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
     * 后序遍历
     * 顺序：左 右 根
     * */
    public void postOrderIterator(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            //prev用来记录
            TreeNode prev = null;
            while (!stack.isEmpty() || root != null){
                // 找到最左边的node
                while (root != null){
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 最左边的子节点的有右子节点为null，或者已经打印过了，则可以打印该左结点
                if(root.right == null || root.right == prev) {
                    // prev用来记录上一步该node是否打印过
                    print(root);
                    prev = root;
                    // 防止再次进入while循环，导致死循环
                    root = null;
                } else {
                    stack.push(root);
                    root= root.right;
                }
            }
        }
    }


    /**
     * 层次遍历
     * 层次遍历时，数组中元素的下标是由数学规律的
     * 左子结点 = 2 * i
     * 右子结点 = 2 * i + 1
     * */
    /**
     *
     * @param root
     * @param i  下标,下标要从1开始
     * @param list
     */
    public void levelOrder(TreeNode root, int i, ArrayList list) {
        if (root == null) {
            return;
        }
        // 防止数组下标越界，需要对数组进行填充
        int length = list.size();
        if (length <= i) {
            for (int j = 0; j <= i - length; j++) {
                list.add(length + j, null);
            }
        }
        list.set(i, root.data);
        levelOrder(root.left, 2 * i, list);
        levelOrder(root.right, 2 * i + 1, list);
    }

    /**
     * 层次遍历
     * 利用队列来实现，如果是完全二叉树时，利用数组来实现最高效
     *
     * */
    public void levelOrderIterator(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        //首先将根节点加入栈中
        queue.add(root);
        //遍历二叉树
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            print(temp);
            if(temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
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
        binaryTree.preOrderIterator(A);
        System.out.println();
        binaryTree.preOrderMorris(A);
        System.out.println();
        System.out.println("中序遍历");
        binaryTree.inOrder(A);
        System.out.println();
        binaryTree.inOrderIterator(A);
        System.out.println();
        binaryTree.inOrderMorris(A);
        System.out.println();
        System.out.println("后序遍历");
        binaryTree.postOrder(A);
        System.out.println();
        binaryTree.postOrderIterator(A);
        System.out.println();
        System.out.println("层次遍历");
        ArrayList arrayList = new ArrayList<>();
        binaryTree.levelOrder(A,1,arrayList);
        System.out.println(JSON.toJSONString(arrayList));

        binaryTree.levelOrderIterator(A);
        System.out.println();

        System.out.println(String.format(" minDepth is %s",minDepth(A)));
    }

    /**
     *           1
     *        2     3
     *      4  5   6
     *            7
     *
     *
     */
    @Test
    public void testMinDepth(){
        TreeNode node7 = new TreeNode('7', null, null);
        TreeNode node6 = new TreeNode('6', node7, null);
        TreeNode node5 = new TreeNode('5', null, null);
        TreeNode node4 = new TreeNode('4', null, null);
        TreeNode node3 = new TreeNode('3', node6, null);
        TreeNode node2 = new TreeNode('2', node4, node5);
        TreeNode node1 = new TreeNode('1', node2, node3);
        System.out.println(String.format(" minDepth is %s",minDepth(node1)));
    }

    public static int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null){
            return 1;
        }

        int min = Integer.MAX_VALUE;
        if (root.getLeft() != null){
            return Math.min(minDepth(root.getLeft()),min);
        }
        if (root.getRight() != null){
            return Math.min(minDepth(root.getRight()),min);
        }
        return min +1;
    }

    /**
     * 树节点
     * */
    static class TreeNode{
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

}
