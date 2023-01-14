/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode200
 * 岛屿数量 / 省份数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCode200.java, v 0.1 2023-01-12 14:07 boyan
 */
public class LeetCode200 {


    @Test
    public void test001(){
        // dfs遍历
        System.out.println("dfs遍历：");
        // 2
        System.out.println(dfs(new int[][]{{1,1,1,1,0},{1,1,0,1,0},{0,0,0,0,0}}));
        // 3
        System.out.println(dfs(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));

        // bfs遍历
        System.out.println("bfs遍历：");
        // 2
        System.out.println(bfs(new int[][]{{1,1,1,1,0},{1,1,0,1,0},{0,0,0,0,0}}));
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
        for (int i = 0; i < cities; i++) {
            if (!visited[i]){
                dfs(i,cities,visited,citiesConnected);
                province++;
            }
        }
        return province;
    }

    private static void dfs(int i,int cities,boolean[] visited,int[][] citiesConnected){
        for (int j = 0; j < cities; j++) {
            if (citiesConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(j,cities,visited,citiesConnected);
            }
        }
    }



    /**
     * 深度优先遍历
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
        for (int i = 0; i < cities; i++) {
            if (!visited[i]){
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