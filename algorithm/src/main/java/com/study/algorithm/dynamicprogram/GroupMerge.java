/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

import java.util.LinkedList;
import java.util.Queue;

/**
 * GroupMerge
 * 有n个城市，其中一些彼此相连接，另一些没有相连接。如果a与b相连接，且b与c相连接，
 * 那么a与c间接相连接
 * 省份是一组直接或者间接相连接的城市，组内不包含其他没有相连接的城市
 * 给你一个 n * n 的矩阵 isConnected，其中 isConnected[i][j]=1 表示第i个城市和第j个城市
 * 直接相连接，而 isConnected[i][j]=0 则表示二者不直接相连接
 * 请返回矩阵中省份的数量
 *
 * 扩展：朋友圈问题，亲戚问题
 * @author boyan
 * @version : GroupMerge.java, v 0.1 2022-12-07 23:38 boyan
 */
public class GroupMerge {

    public static void main(String[] args) {
        // dfs遍历
        System.out.println("dfs遍历：");
        // 2
        System.out.println(dfs(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        // 3
        System.out.println(dfs(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));

        // bfs遍历
        System.out.println("bfs遍历：");
        // 2
        System.out.println(bfs(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        // 3
        System.out.println(bfs(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));

        // 并查集
        System.out.println("并查集遍历：");
        // 2
        System.out.println(bfs(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        // 3
        System.out.println(bfs(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));
    }

    /**
     * 深度优先遍历
     *
     * 时间复杂度：T(n) = O(n^2)
     * 空间复杂度：S(n) = O(2n)即 O(n)
     * @param citiesConnected
     * @return
     */
    private static int dfs(int[][] citiesConnected){
        int cities = citiesConnected.length;
        int province = 0;
        boolean[] visited = new boolean[cities];
        for (int i = 0; i< cities;i++){
            if(!visited[i]){
                // 深度优先遍历
                dfs(i,cities,visited,citiesConnected);
                province++;
            }
        }
        return province;
    }

    private static void dfs(int i, int cities, boolean[] visited, int[][] citiesConnected) {
        for (int j = 0; j < cities; j++) {
            // visited[j] 为true时表示已经访问过，则不需要再次访问了
            if (citiesConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(j,cities,visited,citiesConnected);
            }
        }
    }

    /**
     * 广度优先遍历
     *
     * 时间复杂度：T(n) = O(n^2)
     * 空间复杂度：S(n) = O(2n)即 O(n)
     * @param citiesConnected
     * @return
     */
    private static int bfs(int[][] citiesConnected){
        int cities = citiesConnected.length;
        int province = 0;
        boolean[] visited = new boolean[cities];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i< cities;i++){
            if(!visited[i]){
                queue.offer(i);
                // 找到一个城市直接关联的所有城市，每一个while找一圈
                while (!queue.isEmpty()){
                    int k = queue.poll();
                    visited[k] = true;
                    for (int j = 0; j < cities; j++) {
                        // visited[j] 为true时表示已经访问过，则不需要再次访问了
                        if (citiesConnected[i][j] == 1 && !visited[j]){
                            queue.offer(j);
                        }
                    }
                }
                province++;
            }
        }
        return province;
    }

    /**
     * 并查集
     *
     * 时间复杂度：T(n) = O(n^2)
     * 空间复杂度：S(n) = O(2n)即 O(n)
     * @param citiesConnected
     * @return
     */
    private static int bfs1(int[][] citiesConnected){
        int cities = citiesConnected.length;
        int province = 0;
        boolean[] visited = new boolean[cities];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i< cities;i++){
            if(!visited[i]){
                queue.offer(i);
                // 找到一个城市直接关联的所有城市，每一个while找一圈
                while (!queue.isEmpty()){
                    int k = queue.poll();
                    visited[k] = true;
                    for (int j = 0; j < cities; j++) {
                        // visited[j] 为true时表示已经访问过，则不需要再次访问了
                        if (citiesConnected[i][j] == 1 && !visited[j]){
                            queue.offer(j);
                        }
                    }
                }
                province++;
            }
        }
        return province;
    }
}