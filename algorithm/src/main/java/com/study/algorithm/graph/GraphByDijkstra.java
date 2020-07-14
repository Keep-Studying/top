/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 迪杰斯特拉（Dijkstra）算法，即单源最短路径算法，它是所有最短路径算法的基础，
 * 我们的地图软件最终使用的算法也是以他为基础进行的优化
 *
 * 地图计算最短路径问题
 *
 * 核心思想分析：贪心、排序，贪心策略，1-3我我们认为是10，局部
 * 1. 我们开一个dis数组，用来表示起点到每个顶点的距离，最开始时我们赋值为无穷大
 * 2. 加变量loc，初始赋值为起始点
 * 贪心的策略：在dis数组里面找距离初始点最近的那个点
 * 3. 通过loc更新ids数组，因为加入一个点后我们就可以更新路径
 * 4. 在dis数组里面找离初始点最近的那个点，排除已经选择的点，将之赋值为loc
 * 5. 重复执行3、4操作，直到所有点加完
 *
 * @author study
 * @version : GraphByDijkstra.java, v 0.1 2020年07月14日 20:34 study Exp $
 */
public class GraphByDijkstra implements Graph{

    static Map<String, String> path = new HashMap<>();
    /**
     * @param startPoint 起点
     * @param dis dis数组
     * @param value 邻接矩阵表示的地图
     * @param points 点的个数
     * */
    public static void search(int startPoint,int dis[],int value[][],int points){
        boolean mark[] = new boolean[points + 1];
        //标记已经选择过了
        mark[startPoint] = true;
        dis[startPoint] = 0;
        int count = 1;
        while (count <= points){ //时间复杂度为:O(n^2)
            //表示新加的点
            int loc = 0;
            int min = Integer.MAX_VALUE;
            /**优化点：求dis里面的最小值,O(n),可以优化为使用优先队列,堆，O(logn)*/
            for (int i = 1; i <= points; i++) {
                if(!mark[i] && dis[i] < min){
                    min = dis[i];
                    loc = i;
                }
            }
            if(loc == 0){
                //表示没有可以加的点了
                break;
            }
            mark[loc] = true;
            if (path.get(String.valueOf(loc)) == null) {
                path.put(String.valueOf(loc), startPoint + " -> " + loc);
            }
            //只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis
            //最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
            for (int j = 1; j <= points; j++) {
                //如果loc到item可达，且距离小于上一次判断的距离
                //查找从新加的点出发可以达到的点
                if(value[loc][j] != -1 && (dis[loc]+value[loc][j] < dis[j])){
                    dis[j] = dis[loc] + value[loc][j];
                    // 更新路径
                    path.put(String.valueOf(j), path.get(String.valueOf(loc)) + " -> " + j);
                }
            }
            count++;
        }
        for (int i = 1; i <= points; i++) {
            System.out.println(startPoint+"到"+i+"的最短距离为："+dis[i]);
            if(dis[i] == 0 && path.get(String.valueOf(i)) == null){
                System.out.println(startPoint + "到 " + i + "的最短路径为 ：" + i);
            }else if(dis[i] == Integer.MAX_VALUE && path.get(String.valueOf(i)) == null){
                System.out.println(startPoint + "无法到达 " + i);
            }else {
                System.out.println(startPoint + "到 " + i + "的最短路径为 ：" + path.get(String.valueOf(i)));
            }
        }
    }

    public static void main(String[] args) {
        int points, sides, startPoint; // points表示点数，sides表示边数，startPoint表示我们要的起点
        Scanner cin = new Scanner(System.in);

        points = cin.nextInt();
        sides = cin.nextInt();
        startPoint = cin.nextInt();

        int value[][] = new int[points + 1][points + 1]; // 表示点到点的邻接矩阵
        int dis[] = new int[points + 1]; // 存最短路径的
        for (int i = 1; i <= points; i++) {
            dis[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= points; j++) {
                // 初始化我们的地图
                value[i][j] = -1; // 表示没有路的
                if (i == j) {
                    value[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < sides; i++) { // 表示要输入m个边
            int xx = cin.nextInt();
            int yy = cin.nextInt();
            int v = cin.nextInt(); // xx yy v表示从xx到yy有一条路 长度是v
            value[xx][yy] = v;
            // dis其实在第一个点时候可以在这里计算
            if (xx == startPoint) {
                dis[yy] = v;
            }
        }
        search(startPoint, dis, value, points);
    }

/**
 * 测试数据及结果如下
 6
 8
 1
 1	3	10
 1	5	30
 1	6	100
 2	3	5
 3	4	50
 4	6	10
 5	4	20
 5	6	60
 1到1的最短距离为：0
 1到 1的最短路径为 ：1
 1到2的最短距离为：2147483647
 1无法到达 2
 1到3的最短距离为：10
 1到 3的最短路径为 ：1 -> 3
 1到4的最短距离为：50
 1到 4的最短路径为 ：1 -> 5 -> 4
 1到5的最短距离为：30
 1到 5的最短路径为 ：1 -> 5
 1到6的最短距离为：60
 1到 6的最短路径为 ：1 -> 5 -> 4 -> 6
 * */
}