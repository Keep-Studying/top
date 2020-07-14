/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;
import java.util.Stack;

/**
 * 优先遍历（DFS）：大家可以想象玩迷宫，是不是选择一个方向走到底，直到不能
 * 走了你返回一步继续试其他的方向，没错这其实就是深度优先遍历。
 *
 * 一条路走到底，递归，有回溯。也要标记走过的点。
 *
 * 关键的优化：剪枝
 * 有一天，小美去玩迷宫。但是方向感不好的小美很快就迷路了，你得知后便去
 * 解救无助的小美，你已经弄清楚了迷宫的地图，现在要你以最快的速度去解救小美，
 * 你能计算出最快需要几步吗？最快的路径。
 *
 * 邻接矩阵如下：
 * 0(你) 0 1 0
 * 0 0 0 0
 * 0 0 1 0
 * 0 1 0(小美) 0
 * 0 0 0 1
 *
 * 利用邻接矩阵实现
 *
 * @author study
 * @version : GraphByDFSWithMatrix.java, v 0.1 2020年07月13日 22:39 study Exp $
 */
public class GraphByDFSWithMatrix implements Graph{

    int n; // 地图的行
    int m; // 地图的列
    int dx; // 安琪的位置x
    int dy; // 安琪的位置y
    int data[][]; // 邻接矩阵

    int minStep = Integer.MAX_VALUE; // 要走的最小步数,求最小的数 你最开始是不是要赋值一个最大的数

    boolean mark[][]; // 标记数据 走过的位置
    int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public GraphByDFSWithMatrix(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }

    /**
     * 每条路径都可能走2n次，则m*2n，则m指的是路径数
     * 时间复杂度和路径数有关
     * 时间复杂度为：T(n) = O(n^2)
     * @param x
     * @param y
     * @param step
     * @param stack 标记走过的路径位置
     * */
    public void dfs(int x, int y, int step, Stack<String> stack) { // x,y表示我的位置，step,当前走过的路径长度
        //记录已经走过的位置
        stack.push(x+","+y);
        if (x == dx && y == dy) {		//枚举了所有的路径
            if (step < minStep){
                minStep = step;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextx = x + next[i][0];
            int nexty = y + next[i][1];
            if (nextx < 1 || nextx > n || nexty < 1 || nexty > m)
                continue;
            if (data[nextx][nexty] == 0 && !mark[nextx][nexty]) { // 表示可以走 每个点都有4个方向，
                // 这里有三行代码
                mark[nextx][nexty] = true;
                System.out.println("step:"+step+",point now x:"+x+",y:"+y+",point next n:"+nextx+",m:"+nexty);
                dfs(nextx, nexty, ++step,stack);
                // 回溯
                mark[nextx][nexty] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = 5;
        int m = 4;

        int data[][] = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                data[i][j] = cin.nextInt();
            }
        }
        int dx = cin.nextInt();
        int dy = cin.nextInt();

        boolean mark[][] = new boolean[n+1][m+1];
        int x = cin.nextInt();
        int y = cin.nextInt();

        mark[x][y] = true;		//我的起始位置
        GraphByDFSWithMatrix dfs = new GraphByDFSWithMatrix(n, m, dx, dy, data, mark);
        Stack<String> stack = new Stack<>();
        dfs.dfs(x, y, 0,stack);
        System.out.println(JSON.toJSONString(stack));
        System.out.println(dfs.minStep);
    }
}
/*
测试数据输入：
0 0 1 0
0 0 0 0
0 0 1 0
0 1 0 0
0 0 0 1
4 3
1 1
 */