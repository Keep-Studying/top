/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.tree;

import com.study.algorithm.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCodeOffer32
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCodeOffer32.java, v 0.1 2023-01-11 21:58 boyan
 */
public class LeetCodeOffer32 {

    @Test
    public void test001(){
        TreeNode node5 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(15, null, null);
        TreeNode node3 = new TreeNode(20, node4, node5);
        TreeNode node2 = new TreeNode(9, null, null);
        TreeNode root = new TreeNode(3, node2, node3);
        List<List<Integer>> levelOrder = levelOrder(root);
        System.out.println(levelOrder.toString());

        List<List<Integer>> zigzagLevelOrder = zigzagLevelOrder(root);
        System.out.println(zigzagLevelOrder.toString());
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()){
            List<Integer> levelList = new ArrayList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                levelList.add(curNode.data);
                if (curNode.left != null){
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null){
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()){
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft){
                    levelList.offerLast(curNode.data);
                }else {
                    levelList.offerFirst(curNode.data);
                }
                if (curNode.left != null){
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null){
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }
}