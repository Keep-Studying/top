/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉搜索树又叫二叉查找树，二叉排序树
 * 它具有以下特点：
 * 1. 如果它的左子树不为空，则左子树上结点的值都小于根节点
 * 2. 如果它的右子树不为空，则右子树上结点的值都大于根节点
 * 3. 子树同样要遵循以上两点
 *
 * 只要一棵树是二叉搜索树，那么它的中序遍历一定是有序的
 *              5
 *            /  \
 *           3    6
 *         / \     \
 *        0   4     8
 * 中序遍历（左根【输出】右）：0 3 4 5 7 8
 *
 * @author study
 * @version : BinarySearchTree.java, v 0.1 2020年07月07日 0:38 study Exp $
 */
public class BinarySearchTree {
    /**数据*/
    private int              data;
    /**左孩子*/
    private BinarySearchTree left;
    /**右孩子*/
    private BinarySearchTree right;
    /**父节点*/
    private BinarySearchTree parent;

    public BinarySearchTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.right = null;
        /*
        parent.parent;//祖父（爷爷）节点
        parent.parent.left;//左边的叔叔节点
        parent.left;//兄弟节点*/
    }

    /**
     * 插入
     * 插入的时候每次都是和根节点比较，一直要找到它应该插入的位置
     * 肯定会插入在叶子节点，那么其实大家可以看到，插入其实就是查找。
     * 默认root节点不为空
     * 时间复杂度：T(n) = O(nlogn)，有n个数要插入，O(nlogn)，
     * 每一个都是要先查找到它的位置，即O(logn)，合起来就是O(nlogn)
     *
     * @param root 根节点，默认不为null
     * @param data 要插入的数据
     * */
    public void insert(BinarySearchTree root, int data){
        if(root.data < data){
            //根节点值小于要插入的数据，则放到右边
            if(root.right == null){
                root.right = new BinarySearchTree(data);
                System.out.println("插入右孩子:"+data);
            }else {
                insert(root.right,data);
            }
        }else {
            //否则，根节点值大于等于要插入的数据，则放到左边
            if(root.left == null){
                root.left = new BinarySearchTree(data);
                System.out.println("插入左孩子:"+data);
            }else {
                insert(root.left,data);
            }
        }
    }

    /**
     * 查找
     * 时间复杂度：T(n) = O(logn)
     * @param root 根节点
     * @param data 要查找的数据
     * */
    public void find(BinarySearchTree root, int data){
        if(root != null){
            if(root.data < data){
                find(root.right,data);
            }else if(root.data > data){
                find(root.left,data);
            }else {
                System.out.println("找到了");
                System.out.println(root.toString());
                return;
            }
        }
    }
    /**
     * 删除
     * 删除需要分3种情况
     * 1. 要删除的节点位置是叶子节点，直接删除即可，时间复杂度是O(1)
     * 2. 要删除的接点水只有一个子树（左或者右），时间复杂度是O(1)
     * 3. 要删除的节点有两颗子树，找后继结点，而且后继结点的左子树一定为空，时间复杂度为O(nlogn)
     * @param root 根节点
     * @param data 要删除的数据
     * */
    public void delete(BinarySearchTree root,int data){
        //todo
    }
    /**
     * 中序遍历：左 根（输出） 右
     * */
    public void inOrder(BinarySearchTree root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.data +",");
            inOrder(root.right);
        }
    }

    /**
     * 层次遍历
     * 利用队列来实现，如果是完全二叉树时，利用数组来实现最高效
     *
     * */
    public void level(BinarySearchTree root){
        Queue<BinarySearchTree> queue = new ArrayDeque<>(256);
        //首先将根节点加入栈中
        queue.add(root);
        //遍历二叉树
        while (!queue.isEmpty()){
            BinarySearchTree temp = queue.poll();
            System.out.print(temp.getData()+",");
            if(temp.getLeft() != null){
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null){
                queue.add(temp.getRight());
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("BinarySeachTree [");
        builder
                .append(",        data=").append(data)
                .append(",        left=").append(left)
                .append(",        right=").append(right)
                .append(",        parent=").append(parent)
                .append(']');
        return builder.toString();
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public int getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>left</tt>.
     *
     * @return property value of left
     */
    public BinarySearchTree getLeft() {
        return left;
    }

    /**
     * Setter method for property <tt>left</tt>.
     *
     * @param left value to be assigned to property left
     */
    public void setLeft(BinarySearchTree left) {
        this.left = left;
    }

    /**
     * Getter method for property <tt>right</tt>.
     *
     * @return property value of right
     */
    public BinarySearchTree getRight() {
        return right;
    }

    /**
     * Setter method for property <tt>right</tt>.
     *
     * @param right value to be assigned to property right
     */
    public void setRight(BinarySearchTree right) {
        this.right = right;
    }

    /**
     * Getter method for property <tt>parent</tt>.
     *
     * @return property value of parent
     */
    public BinarySearchTree getParent() {
        return parent;
    }

    /**
     * Setter method for property <tt>parent</tt>.
     *
     * @param parent value to be assigned to property parent
     */
    public void setParent(BinarySearchTree parent) {
        this.parent = parent;
    }

    /**
     * 输出结果：
     * 根节点为:5
     * 插入左孩子:0
     * 插入右孩子:9
     * 插入右孩子:1
     * 插入右孩子:2
     * 插入右孩子:3
     * 插入右孩子:10
     * 中序遍历
     * 0,1,2,3,5,9,10,
     * 找到了
     * BinarySeachTree [,        data=2,        left=null,        right=BinarySeachTree [,        data=3,        left=null,
     * right=null,        parent=null],        parent=null]
     * 层次遍历
     * 5,0,9,1,10,2,3,
     *
     * */
    public static void main(String[] args) {
        //快速排序，归并排序，二叉树排序
        /**
         *           5
         *          / \
         *         0  9
         *         \   \
         *          1  10
         *           \
         *            2
         *             \
         *              3
         *
         * */
        int data[] ={5,0,9,1,2,3,10};
        //数组第一个元素作为根节点
        BinarySearchTree root = new BinarySearchTree(data[0]);
        System.out.println("根节点为:"+root.data);
        for (int i = 1; i < data.length; i++) {
            root.insert(root,data[i]);
        }
        System.out.println("中序遍历");
        root.inOrder(root);
        System.out.println();
        root.find(root,2);
        System.out.println("层次遍历");
        root.level(root);
    }
}