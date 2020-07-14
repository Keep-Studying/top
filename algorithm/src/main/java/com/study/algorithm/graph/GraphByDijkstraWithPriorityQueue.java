/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 该类不正确
 * @author study
 * @version : GraphByDijkstraWithPriorityQueue.java, v 0.1 2020年07月14日 23:48 study Exp $
 */
public class GraphByDijkstraWithPriorityQueue {
    public static int[][] data = {
            {0,-1,10,-1,30,100},
            {-1,0,5,-1,-1,-1},
            {-1,-1,0,50,-1,-1},
            {-1,-1,-1,0,-1,10},
            {-1,-1,-1,20,0,60},
            {-1,-1,-1,-1,-1,0}
    };
    private static class Distance implements Comparable<Distance>{

        int num;
        int distance = Integer.MAX_VALUE;
        List<Integer> path = new ArrayList<>();
        boolean mark = false;

        public Distance(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder("Distance [");
            builder
                    .append("        num=").append(num)
                    .append(",        distance=").append(distance)
                    .append(",        path=").append(path)
                    .append(",        mark=").append(mark)
                    .append(']');
            return builder.toString();
        }

        @Override
        public int compareTo(Distance o) {
            if(o == null){
                return 1;
            }
            return Integer.compare(distance,o.distance);
        }
    }
    private static List<Distance> dis = new ArrayList<>();
    private static PriorityQueue<Distance> queue = new PriorityQueue<>();
    public static void initDis(){
        for (int i = 0; i < 6; i++) {
            dis.add(new Distance(i));
        }
    }

    public static void dijkstra(int startIndex){
        if(startIndex < 0 || startIndex >= dis.size()){
            return;
        }
        Distance loc = dis.get(startIndex);
        loc.distance = 0;
        while (loc != null){
            loc.mark = true;
            loc.path.add(loc.num);
            System.out.println(loc.toString());
            for (Distance item : dis) {
                if(item.mark){
                    continue;
                }
                //如果loc到item可达，且距离小于上一次判断的距离
                if(data[loc.num][item.num] != -1 && ((data[loc.num][item.num]+loc.distance < item.distance))){
                    item.distance = loc.distance + data[loc.num][item.num];
                    item.path.clear();
                    item.path.addAll(loc.path);
                }
                queue.add(item);
            }
        }
        loc = queue.poll();
        queue.clear();
    }

    public static void main(String[] args) {
        initDis();
        dijkstra(1);
    }
}