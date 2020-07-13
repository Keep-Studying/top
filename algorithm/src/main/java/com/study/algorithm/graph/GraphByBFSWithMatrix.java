/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 广度优先遍历（BFS）：类似于树结构的层次遍历，先找到一个点，然后把该点加入队列，
 * 依次找出该点的关联边加入队列，循环操作，一直到队列为空。
 * 一开始就把所有的路都给走了
 *
 * 两个关键点：
 * 1.队列
 * 2.标记数组，加过的点不能再加
 *
 * 应用：启发式搜索：A*
 *
 * 有一天，小美和你去玩迷宫。但是方向感不好的小美很快就迷路了，你得知后便去
 * 解救无助的小美，你已经弄清楚了迷宫的地图，现在你要知道从你当前位置出发你
 * 是否能够达到小美的位置？
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
 * @version : GraphByBFSWithMatrix.java, v 0.1 2020年07月13日 22:24 study Exp $
 */
public class GraphByBFSWithMatrix implements Graph{
    int n; // 地图的行
    int m; // 地图的列
    int dx; // 安琪的位置x
    int dy; // 安琪的位置y
    int data[][]; // 邻接矩阵
    boolean mark[][]; // 标记数据 走过的位置
    public void bfs(int x, int y) { // x 和 y表示你当前的位置,就是求（x，y）->(dx,dy)能不能到
        if(x < 1 || x > n || y < 1 || y > m) return ;
        if(x == dx && y == dy) {
            System.out.println("true");
            return ;
        }
        mark[x][y] = true;

        Queue<Point> queue = new ArrayBlockingQueue<>(n * m); // 因为最多也就是n*m个点
        Point start = new Point();
        start.x = x;
        start.y = y;
        queue.add(start);
        /**这行代码的设计思路很棒，非常巧妙地表示了上下左右4个方向*/
        int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };	//ACM想到的

        while (!queue.isEmpty()) {		//O(n)
            Point point = queue.poll(); // 拿出队列的第一个点
            for(int i = 0 ; i < 4; i++) {
                int nextx = point.x + next[i][0];
                int nexty = point.y + next[i][1];
                if(nextx < 1 || nextx > n || nexty < 1 || nexty > m) continue;
                if(data[nextx][nexty] == 0 && !mark[nextx][nexty]) {		//表示可以走
                    if(nextx == dx && nexty == dy) {		//表示可以到了 就不走了
                        System.out.println("true");
                        return ;
                    }
                    Point newPoint = new Point();
                    newPoint.x = nextx;
                    newPoint.y = nexty;
                    mark[nextx][nexty] = true;
                    queue.add(newPoint);
                }
            }

        }
        System.out.println("false");
        return ;

    }
}
class Point{
    int x;
    int y;
}