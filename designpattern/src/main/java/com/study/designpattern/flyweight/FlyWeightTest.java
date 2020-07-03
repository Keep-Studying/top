/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * @author study
 * @version : FlyWeightTest.java, v 0.1 2020年07月03日 8:46 study Exp $
 */
public class FlyWeightTest {

    public static void main(String[] args) {
        TreeType treeTypeByName = TreeFactory.getTreeTypeByName("xxx","yyy" );
        TreeNode treeNode1 = new TreeNode(0, 0, treeTypeByName);
        TreeNode treeNode2 = new TreeNode(4, 6, treeTypeByName);
    }
}

class TreeNode{
    private int x;
    private int y;
    private TreeType treeType;

    public TreeNode(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }
}

class TreeType{
    private final String mame;
    private final String data;

    public TreeType(String mame, String data) {
        this.mame = mame;
        this.data = data;
    }

    /**
     * Getter method for property <tt>mame</tt>.
     *
     * @return property value of mame
     */
    public String getMame() {
        return mame;
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public String getData() {
        return data;
    }
}

class TreeFactory{
    static Map<String,TreeType> map = new HashMap<>();
    public static TreeType getTreeTypeByName(String name,String data){
        if(map.containsKey(name)){
            return map.get(name);
        }
        TreeType treeType = new TreeType(name,data);
        map.put(name,treeType);
        return treeType;
    }
}