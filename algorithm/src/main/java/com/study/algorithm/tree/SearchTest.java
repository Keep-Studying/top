/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SearchTest
 *
 * @author boyan
 * @version : SearchTest.java, v 0.1 2022-12-07 16:04 boyan
 */
public class SearchTest {

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
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(String.format(" minDepth is %s",minDepth(node1)));
        int level = minLevel(node1);
        System.out.println();
        System.out.println(String.format(" level is : %s",level));
    }

    public static int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }

        int min = Integer.MAX_VALUE;
        if (root.left != null){
            min = Math.min(minDepth(root.left),min);
        }
        if (root.right != null){
            min = Math.min(minDepth(root.right),min);
        }
        return min +1;
    }

    /**
     * 层次遍历
     * 利用队列来实现，如果是完全二叉树时，利用数组来实现最高效
     *
     * */
    public int minLevel(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        root.depth = 1;
        //首先将根节点加入栈中
        queue.add(root);
        //遍历二叉树
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            System.out.print(temp.data +",");
            if (temp.left == null && temp.right == null){
                return temp.depth;
            }
            if(temp.left != null){
                temp.left.depth = temp.depth +1;
                queue.add(temp.left);
            }
            if (temp.right != null){
                temp.right.depth = temp.depth +1;
                queue.add(temp.right);
            }
        }
        return 0;
    }
}
