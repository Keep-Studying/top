/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.hash;

/**
 * 位图BitMap，利用int来实现
 * @author study
 * @version : BitMapByInt.java, v 0.1 2020年07月18日 15:00 study Exp $
 */
public class BitMapByInt {
    //1 int = 32 bit
    int[] bits;
    int max;

    public BitMapByInt(int max) {
        this.max = max;
        //即 max/32+1
        bits = new int[max>>5+1];
    }

    /**
     * 插入
     * */
    public void add(int n){
        // 不能添加比最大值大的数值
        if(n > max){
            return;
        }
        // 除以32 就可以知道在那个byte
        int index = n >> 5;
        //这里其实还可以用&运算
        //%运算转为 &运算的 前提 是取余的数为2^n幂，n & (2^n-1) == n % 2^n
        //int loc = n % 32;
        int loc = n & 31;
        //接下来就是要把bit数组里面的 bisIndex这个下标的byte里面的 第loc 个bit位置为1
        //位或|：同位上的两个数只要有一个为1，则位是1，否则为0
        bits[index] |= 1 << loc;
    }
    /**
     * 查找
     * */
    public boolean find(int n){
        // 除以32 就可以知道在那个byte
        int index = n >> 5;
        //这里其实还可以用&运算
        //int loc = n % 32;
        int loc = n & 31;
        //如果原来的那个位置是0 那肯定就是0 只有那个位置是1 才行
        //位于&：同位上的两个数都是1，则位是1，否则为0
        int flag = bits[index] & (1 << loc);
        if(flag == 0){
            return false;
        }
        return true;
    }

    /**
     * 删除
     * */
    public boolean remove(int n){
        // 除以32 就可以知道在那个byte
        int index = n >> 5;
        //这里其实还可以用&运算
        //int loc = n % 32;
        int loc = n & 31;
        //要删除的数字存在时，才能进行删除
        if(find(n)){
            //位异^: 同位上的两个数只要不相同时，则位是1，否则为0
            bits[index] ^= 1 << loc;
            return true;
        }
        return false;
    }

    /**
     * 输出结果
     * 3125000
     * true
     * false
     * true
     * true
     * false
     * */
    public static void main(String[] args) {
        BitMapByInt bitMapByInt = new BitMapByInt(200000001);  //10亿
        System.out.println(bitMapByInt.bits.length);

        bitMapByInt.add(2);
        bitMapByInt.add(3);
        bitMapByInt.add(65);
        bitMapByInt.add(66);

        System.out.println(bitMapByInt.find(3));
        System.out.println(bitMapByInt.find(64));
        System.out.println(bitMapByInt.find(65));
        System.out.println(bitMapByInt.remove(65));
        System.out.println(bitMapByInt.find(65));
    }
}