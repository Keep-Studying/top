/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * NowCoderHj43
 * 迷宫问题
 * 描述
 * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
 *
 *
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 *
 *
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
 *
 *
 * 数据范围： 2 \le n,m \le 10 \2≤n,m≤10  ， 输入的内容只包含 0 \le val \le 1 \0≤val≤1
 *
 * 输入描述：
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * 输出描述：
 * 左上角到右下角的最短路径，格式如样例所示。
 * @author boyan
 * @version : NowCoderHj43.java, v 0.1 2023-01-12 10:16 boyan
 */
public class NowCoderHj43 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int rows = in.nextInt();
            int columns = in.nextInt();
            // 构造迷宫
            int[][] nums = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    nums[i][j] = in.nextInt();
                }
            }
            // 路径存储的数组
            List<Position> path = new ArrayList<>();
            dfs(nums,0,0,path);
            for (Position pos:path) {
                System.out.println("(" + pos.x + "," + pos.y + ")");
            }
        }
    }

    /**
     *
     * @param nums
     * @param x
     * @param y
     * @param path
     * @return 标记是否找到可通行的路劲
     */
    public static boolean dfs(int[][] nums, int x, int y, List<Position> path) {
        // 添加路径并标记已走
        path.add(new Position(x,y));
        nums[x][y] = 1;
        if (x == nums.length -1 && y == nums[0].length -1){
            return true;
        }
        // 向下能走
        if (x +1 < nums.length && nums[x+1][y] == 0){
            if (dfs(nums,x+1,y,path)){
                return true;
            }
        }
        // 向右能走
        if (y +1 < nums[0].length && nums[x][y+1] == 0){
            if (dfs(nums,x,y+1,path)){
                return true;
            }
        }
        // 向上能走
        if (x -1 > -1 && nums[x-1][y] == 0){
            if (dfs(nums,x-1,y,path)){
                return true;
            }
        }
        // 向左能走
        if (y -1 > -1 && nums[x][y-1] == 0){
            if (dfs(nums,x,y-1,path)){
                return true;
            }
        }
        // 回溯
        path.remove(path.size()-1);
        nums[x][y] = 0;
        return false;
    }


    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}