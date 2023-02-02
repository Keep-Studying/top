/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * NowCoderTest3
 *
 * @author boyan
 * @version : NowCoderTest3.java, v 0.1 2023-01-14 17:58 boyan
 */
public class NowCoderTest3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int nums = Integer.parseInt(in.nextLine());
            int[][] array = new int[nums][2];
            for (int i = 0; i < nums; i++) {
                String str = in.nextLine();
                String[] split = str.split(",");
                array[i][0] = Integer.parseInt(split[0]);
                array[i][1] = Integer.parseInt(split[1]);
            }
//            Arrays.sort(array, new Comparator<int[]>() {
//                @Override public int compare(int[] o1, int[] o2) {
//                    if (o1[0] != o2[0]){
//                        return o1[0] - o2[0];
//                    }else {
//                        return o1[1] - o2[1];
//                    }
//                }
//            });
            int min = array[0][0],max = array[0][1];
            ArrayList<Position> positions = new ArrayList<>();
            Position position = new Position(array[0][0], array[0][1]);
            positions.add(position);
            HashMap<Integer, Position> positionHashMap = new HashMap<>();
            positionHashMap.put(position.start,position);
            positionHashMap.put(position.end,position);
            for (int i = 1; i < nums; i++) {
                boolean flag = false;
                if (array[i][0] < min){
                    Position position1 = positionHashMap.get(min);
                    positions.remove(position1);
                    min = array[i][0];
                    flag =true;
                }
                if (array[i][1] > max){
                    Position position2 = positionHashMap.get(min);
                    positions.remove(position2);
                    max = array[i][1];
                    flag =true;
                }
                if (flag){
                    Position position3 = new Position(array[0][0], array[0][1]);
                    positions.add(position3);
                }
            }
            System.out.println(positions.size());
        }
    }


    static class Position{
        int start;
        int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}