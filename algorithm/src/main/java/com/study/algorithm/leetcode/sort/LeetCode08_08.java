/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.sort;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode08_08
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * 示例1:
 *
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 *
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/permutation-ii-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCode08_08.java, v 0.1 2023-01-11 16:40 boyan
 */
public class LeetCode08_08 {

    List<String> permutations = new ArrayList<String>();
    StringBuffer temp = new StringBuffer();
    char[] arr;
    int n;
    boolean[] visited;

    @Test
    public void test001(){
        String str = "qqe";
        System.out.println(JSON.toJSONString(permutation(str)));
//        System.out.println(JSON.toJSONString(permutation1(str)));
    }

    public String[] permutation(String s) {
        arr = s.toCharArray();
        Arrays.sort(arr);
        this.n = s.length();
        this.visited = new boolean[n];
        backtrack(0);
        return permutations.toArray(new String[permutations.size()]);
    }

    public void backtrack(int index) {
        if (index == n) {
            permutations.add(temp.toString());
        } else {
            for (int i = 0; i < n; i++) {
                if (visited[i] || (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1])) {
                    continue;
                }
                temp.append(arr[i]);
                visited[i] = true;
                backtrack(index + 1);
                temp.deleteCharAt(index);
                visited[i] = false;
            }
        }
    }

    public String[] permutation1(String s) {
        arr = s.toCharArray();
        Arrays.sort(arr);
        n = arr.length;
        visited = new boolean[n];
        backtrack1(0);
        return permutations.toArray(new String[permutations.size()]);
    }

    public void backtrack1(int index) {
        if(index == n){
            permutations.add(temp.toString());
        }else {
            for (int i = 0; i < n; i++) {
                if(visited[i] || (i>0 && arr[i] == arr[i-1] && !visited[i-1])){
                    continue;
                }
                temp.append(arr[i]);
                visited[i] = true;
                backtrack1(index+1);
                temp.deleteCharAt(index);
                visited[i] = false;
            }
        }
    }
}