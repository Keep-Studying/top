/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */

package com.study.algorithm.leetcode.tree;

import com.alibaba.fastjson.JSON;
import com.study.algorithm.tree.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCodeOffer34
 * 二叉树中和为某一值的路径
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCodeOffer34.java, v 0.1 2023-01-19 15:58 boyan
 */
public class LeetCodeOffer34 {

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList();


    @Test
    public void test001(){
        TreeNode node1 = new TreeNode(5, null, null);
        TreeNode node2 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(8, null, null);
        TreeNode node4 = new TreeNode(11, null, null);
        TreeNode node5 = new TreeNode(13, null, null);
        TreeNode node6 = new TreeNode(4, null, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node8 = new TreeNode(2, null, null);
        TreeNode node9 = new TreeNode(5, null, null);
        TreeNode node10 = new TreeNode(1, null, null);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.left = node9;
        node6.right = node10;

        List<List<Integer>> lists = pathSum(node1, 22);
        System.out.println(JSON.toJSONString(lists));
        System.out.println(JSON.toJSONString(pathSum1(node1,22)));

    }

    public List<List<Integer>> pathSum(TreeNode root,int target){
        dfs(root,target);
        return ret;
    }

    public void dfs(TreeNode root,int target){
        if (root == null){
            return;
        }
        path.offerLast(root.data);
        target -= root.data;
        if (root.left == null && root.right == null && target == 0){
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left,target);
        dfs(root.right,target);
        path.pollLast();
    }



    public List<List<Integer>> pathSum1(TreeNode root,int target){
        List<List<Integer>> ret1 = new LinkedList<>();
        Deque<Integer> path1 = new LinkedList();
        dfs1(root,target,ret1,path1);
        return ret;
    }

    public void dfs1(TreeNode root,int target,List<List<Integer>> result,Deque<Integer> path){
        if (root == null){
            return;
        }
        path.offerLast(root.data);
        target -= root.data;
        if (root.left == null && root.right == null && target == 0){
            result.add(new LinkedList<>(path));
        }
        dfs1(root.left,target,result,path);
        dfs1(root.right,target,result,path);
        path.pollLast();
    }

    @Test
    public void test002(){
        Deque<Integer> path = new LinkedList();
        path.offerLast(2);
        path.offerLast(3);
        path.offerLast(4);
        LinkedList<Integer> integers = new LinkedList<>(path);
        System.out.println(JSON.toJSONString(integers));
        Integer integer = path.pollLast();
        System.out.println(integer);
    }

}
