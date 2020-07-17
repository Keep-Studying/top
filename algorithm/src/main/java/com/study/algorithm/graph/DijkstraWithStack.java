/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 迪杰斯特拉算法求路径，用栈来保存
 * @author study
 * @version : DijkstraWithStack.java, v 0.1 2020年07月17日 8:30 study Exp $
 */
public class DijkstraWithStack implements Graph{

    /**
     测试数据：
     6
     8
     1
     1 3 10
     1 5 30
     1 6 100
     2 3 5
     3 4 50
     4 6 10
     5 4 20
     5 6 60
     * */
    /**
     * 测试结果：
     * 1到 1的最短路径为 ：0
     * 1
     * 1到 3的最短路径为 ：10
     * 1
     * 3
     * 1到 4的最短路径为 ：50
     * 1
     * 5
     * 4
     * 1到 5的最短路径为 ：30
     * 1
     * 5
     * 1到 6的最短路径为 ：60
     * 1
     * 5
     * 4
     * 6
     * */
    public static void main(String[] args) {
        // n表示点数，即当前图中有多少个顶点
        int n;
        // m表示当前这个图有多少个边（为了方便我们一会构造图）
        int m;
        // begin表示我们的起点，即我们从哪一个点开始进行运算
        int begin;

        // 接受用户的输入
        Scanner cin = new Scanner(System.in);
        // 赋值点数，边数，起点
        n = cin.nextInt();
        m = cin.nextInt();
        begin = cin.nextInt();

        // 声明邻接矩阵, 因为我们的下标都是从0开始，所以长度都要加1，有几个点就是维度是几的二维数组
        int data[][] = new int[n + 1][n + 1];
        // 用来存取起点到下标的最短路径，因为下标还是从1开始的，所以长度还是需要加一，dis[5]就表示起点到顶点5的最短距离
        int dis[] = new int[n + 1];
        // 初始化一个Map，因为最后我们求出来的dis是起点到左右点的最短距离
        Map<Integer, Stack<Integer>> routeMap = new HashMap<>();
        // 初始化dis数组和邻接矩阵二维数组data[][]
        // 下标从1开始，所以有n个点，我们就要操作到第n个元素
        for (int i = 1; i <= n; i++) {
            // 初始化dis数组，因为求最短路径，所以dis中存的应该是最大值
            dis[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                // 赋值我们的二维数组，就是赋值邻接矩阵
                // 初始化邻接矩阵都是-1，-1表示的是顶点之间没有联系
                data[i][j] = -1;
                // 如果是自己和自己，则将其长度设置为0
                if (i == j) {
                    data[i][j] = 0;
                }
            }
        }

        // 赋值邻接矩阵，哪个点与哪个点有值，在此赋值
        for (int i = 0; i < m; i++) {
            // 横坐标
            int x = cin.nextInt();
            // 纵坐标
            int y = cin.nextInt();
            // 点与点之间的距离
            int value = cin.nextInt();
            // 表示的是xx到yy的距离是vv
            data[x][y] = value;

            // 如果当前的横坐标等于我们规定的起始位置下标
            if (x == begin) {
                // 直接赋值dis的第一次，即当loc为起点的时候，dis数组的值，dis[i]表示起点到i的值
                // 赋值起点到y的值为value，默认情况都是MAX
                dis[y] = value;
                // 赋值初始值
                Stack<Integer> stack = new Stack<>();
                // 将起点也就是路径走的第一步赋值进去
                stack.push(begin);
                // 赋值终点
                stack.push(y);
                routeMap.put(y, stack);
            }
        }
        // 开始搜索最短路径，起点begin，最短路径的dis数组（表示起点到其下标的点的最短路径），data表示的是邻接矩阵，n表示总共有多少个顶点
        search(begin, dis, data, n, routeMap);

        // 将起点加进去
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(begin);
        routeMap.put(begin, stack1);
        for (int i = 1; i <= n; i++) {
            Stack<Integer> stack = routeMap.getOrDefault(i, null);
            if (stack != null) {
                System.out.println(begin + "到 " + i + "的最短路径为 ：" + dis[i]);
                for (Integer s : stack){
                    System.out.println(s);
                }
            }

        }
    }

    /**
     * 开始搜索最短路径
     * @param begin 起始点
     * @param dis 最短路径数组
     * @param data 邻接矩阵
     * @param n 表示有多少个顶点
     * @param routeMap 走的最短路径的点
     */
    private static void search(int begin, int[] dis, int[][] data, int n, Map<Integer, Stack<Integer>> routeMap) {
        // 标记数组，用来表示dis中哪一个值已经被标记了，标记过的无需再次处理, 下标从1开始，所以需要长度是n+1
        boolean mark[] = new boolean[n + 1];
        // 起点已经赋值过了 所以将起点标注为true 表示已经赋值过了
        mark[begin] = true;
        // 当前节点到当前节点的最短路径是0
        dis[begin] = 0;
        // 循环遍历数组
        int index = 1;
        // 我们最多就是找节点的次数（n）
        while (index <= n) {
            // loc用来表示当前的节点
            int loc = 0;
            // 用于记录当前的dis中存储的最小值
            int min = Integer.MAX_VALUE;
            // 遍历dis数组，找到当前dis最小的
            for (int i = 1; i <= n; i++) {
                // 如果当前的dis中的点没有被标记，且小于我们的最小值
                if (!mark[i] && dis[i] < min) {
                    // 保存最小值和其下标
                    min = dis[i];
                    loc = i;
                }
            }
            // 表示没有找到最小的或者全都被标记了，此时我们退出循环
            if (loc == 0) {
                break;
            }
            // 将当前的loc点进行标记
            mark[loc] = true;
            // 拿到当前顶点对应的 最短路径的取法
            Stack<Integer> stack = routeMap.get(loc);
            // 更新dis数组，就是我们的dis[loc] + data[loc][X] 是否小于 dis[X] 若小于更新dis[X]的值
            for (int i = 1; i <= n; i++) {
                if (data[loc][i] != -1 && (dis[loc] + data[loc][i] < dis[i])) {
                    // 更新dis数组的值
                    dis[i] = dis[loc] + data[loc][i];
                    // 拷贝一份 因为直接改会影响
                    Stack<Integer> stackClone = (Stack<Integer>)stack.clone();
                    // 将当前的新的节点放进去
                    stackClone.push(i);
                    // 将其放入map中
                    routeMap.put(i, stackClone);
                }
            }
            // 下标加1
            index ++;
        }
    }
}