/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.tree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

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
    public void insert(BinaryNode root, int data){
        //根节点值小于要插入的数据，则放到右边
        if(root.data < data){
            if(root.right == null){
                BinaryNode newNode = new BinaryNode(data);
                newNode.parent = root;
                root.right = newNode;
            }else {
                insert(root.right,data);
            }
        }else {
            //否则，根节点值大于等于要插入的数据，则放到左边
            if(root.left == null){
                BinaryNode newNode = new BinaryNode(data);
                newNode.parent = root;
                root.left = newNode;
            }else {
                insert(root.left,data);
            }
        }
    }

    /**
     * 查找(递归方式)
     * 时间复杂度：T(n) = O(logn)
     * @param root 根节点
     * @param data 要查找的数据
     * */
    public void findByRecursion(BinaryNode root, int data){
        if(root != null){
            if(root.data < data){
                findByRecursion(root.right,data);
            }else if(root.data > data){
                findByRecursion(root.left,data);
            }else {
                System.out.println("找到了");
                System.out.println(root.toString());
                return;
            }
        }
    }
    /**
     * 查找
     * 时间复杂度：T(n) = O(logn)
     * @param root 根节点
     * @param key 要查找的数据
     * */
    public BinaryNode find(BinaryNode root,int key){
        BinaryNode current = root;
        while (current != null){
            if(key < current.data){
                current = current.left;
            }else if(key > current.data){
                current = current.right;
            }else {
                return current;
            }
        }
        return null;
    }

    /**
     * 用于获得树的层数
     * */
    public int getTreeDepth(BinaryNode root){
        return root == null ? 0:(1+Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    public void writeArray(BinaryNode currNode,int rowIndex,int columnIndex,String[][] res, int treeDepth){
        //保证输入的树不为空
        if(currNode == null){
            return;
        }
        //先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.data);
        //计算当前位于树的第几层
        int currentLevel = ((rowIndex+1)/2);
        //如果到了最后一层，则返回
        if(currentLevel == treeDepth){
            return;
        }
        //计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currentLevel - 1;
        //对左孩子进行判断，若有左孩子，则记录相应的"/"与左孩子的值
        if(currNode.left != null){
            res[rowIndex+1][columnIndex-gap] = "/";
            writeArray(currNode.left,rowIndex+2,columnIndex-gap*2,res,treeDepth);
        }
        // 对右孩子进行判断，若有右孩子，则记录相应的"\"与右孩子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public void show(BinaryNode root){
        if (root == null) {
            System.out.println("The Tree is Empty !");
            return ;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 查找node的后继节点
     * */
    public BinaryNode findSuccessor(BinaryNode node){
        // 表示没有右边 那就没有后继
        if(node.right == null){
            return node;
        }

        BinaryNode cur = node.right;
        // 开一个额外的空间 用来返回后继节点，因为我们要找到为空的时候，那么其实返回的是上一个节点
        BinaryNode pre = node.right;
        while (cur != null){
            pre = cur;
            // 注意后继节点是要往左边找，因为右边的肯定比左边的大，
            // 我们要找的是第一个比根节点小的，所以只能往左边
            cur = cur.left;
        }
        // 因为cur会变成null，实际我们是要cur的上一个点，所以就是pre来代替
        return pre;
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
    public BinaryNode remove(BinaryNode root,int data){
        BinaryNode delNode = find(root, data);
        if(delNode == null){
            System.out.println("要删除的值不存在树中！");
            return root;
        }
        // 1.删除的点没有左右子树
        if (delNode.left == null && delNode.right == null) {
            if (delNode == root) {
                root = null;
            } else if (delNode.parent.data < delNode.data) { // 说明删除的点是右子节点
                delNode.parent.right = null;
            } else {
                delNode.parent.left = null;
            }
        } else if (delNode.left != null && delNode.right != null) { // 2.删除的节点有两颗子节点
            // 先找的后继节点
            BinaryNode successor = findSuccessor(delNode);
            // 后继节点和删除节点进行交换，首先后继节点的左节点是肯定为空的
            // 后继的左边变为删除的左边
            successor.left = delNode.left;
            // 删除点的左边parent指向后继节点
            successor.left.parent = successor;
            // 再来看后继节点的右边
            if (successor.right != null && successor.parent != delNode) { // 后继节点有右边,这其实就是下面情况3的第一种
                successor.right.parent = successor.parent;
                successor.parent.left = successor.right;
                successor.right = delNode.right;
                successor.right.parent = successor;
            }else if(successor.right == null) {	//如果后继节点没有右边,那其实就是情况1，没有左右子树
                if(successor.parent != delNode) { //如果后继节点的parent不等于删除的点 那么就需要把删除的右子树赋值给后继节点
                    successor.parent.left = null;//注意原来的后继节点上的引用要删掉,否则会死循环
                    successor.right = delNode.right;
                    successor.right.parent = successor;
                }
            }
            // 替换做完接下来就要删除节点了
            if (delNode == root) {
                successor.parent = null;
                root = successor;
                return root;
            }
            successor.parent = delNode.parent;
            if (delNode.data > delNode.parent.data) { // 删除的点在右边，关联右子树
                delNode.parent.right = successor;
            } else {
                delNode.parent.left = successor;
            }

        } else { // 3.删除点有一个节点
            if (delNode.right != null) { // 有右节点
                if (delNode == root) {
                    root = delNode.right;
                    return root;
                }
                delNode.right.parent = delNode.parent; // 把右节点的parent指向删除点的parent
                // 关联父节点的左右子树
                if (delNode.data < delNode.parent.data) { // 删除的点在左边
                    delNode.parent.left = delNode.right;
                } else {
                    delNode.parent.right = delNode.right;
                }
            } else {
                if (delNode == root) {
                    root = delNode.left;
                    return root;
                }
                delNode.left.parent = delNode.parent;
                if (delNode.data < delNode.parent.data) {
                    delNode.parent.left = delNode.left;
                } else {
                    delNode.parent.right = delNode.left;
                }
            }
        }
        return root;
    }

    /**
     * 中序遍历：左 根（输出） 右
     * */
    public void inOrder(BinaryNode root){
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
    public void level(BinaryNode root){
        Queue<BinaryNode> queue = new ArrayDeque<>(256);
        //首先将根节点加入栈中
        queue.add(root);
        //遍历二叉树
        while (!queue.isEmpty()){
            BinaryNode temp = queue.poll();
            System.out.print(temp.data +",");
            if(temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
    }

    /**
     *
     * 测试用例
     * 15
     * 10
     * 19
     * 8
     * 13
     * 16
     * 28
     * 5
     * 9
     * 12
     * 14
     * 20
     * 30
     * -1
     * 删除：15 8 5 10 12 19 16 14 30 9 13 20 28
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        BinaryNode root = null;
        Scanner cin = new Scanner(System.in);
        int t = 1;
        System.out.println("二叉搜索树假定不存重复的子节点,重复可用链表处理，请注意~~");
        System.out.println("请输入根节点:");
        int rootData = cin.nextInt();
        root = new BinaryNode(rootData);
        System.out.println("请输入第" + t + "个点:输入-1表示结束");
        while (true) { //
            int data = cin.nextInt();
            if (data == -1)
                break;
            binarySearchTree.insert(root, data);
            t++;
            System.out.println("请输入第" + t + "个点:输入-1表示结束");
        }
        binarySearchTree.show(root);
        System.out.println("删除测试:");
        while(true) {
            System.out.println("请输入要删除的点：-1表示结束");
            int key = cin.nextInt();
            if (key == -1)
                break;
            root = binarySearchTree.remove(root, key);
            binarySearchTree.show(root);
            if(root == null) {
                System.out.println("树已经没有数据了~~");
                break;
            }
        }
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
    @Test
    public void test(){
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
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        BinaryNode root = new BinaryNode(data[0]);
        System.out.println("根节点为:"+root.data);
        for (int i = 1; i < data.length; i++) {
            binarySearchTree.insert(root,data[i]);
        }
        System.out.println("中序遍历");
        binarySearchTree.inOrder(root);
        System.out.println();
        int key = 2;
        BinaryNode findNode = binarySearchTree.find(root, key);
        System.out.println("搜索key:"+key+"，找到的节点为："+findNode);
        System.out.println("层次遍历");
        binarySearchTree.level(root);
    }
}
class BinaryNode{
    /**数据*/
    int data;
    /**左孩子*/
    BinaryNode left;
    /**右孩子*/
    BinaryNode right;
    /**父节点*/
    BinaryNode parent;

    public BinaryNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        /*
        parent.parent;//祖父（爷爷）节点
        parent.parent.left;//左边的叔叔节点
        parent.left;//兄弟节点*/
    }

    @Override
    public String toString() {
        return "BinaryNode [data=" + data   + ", parent="+ parent + "]" + "\r\n";
        /*return  "BinaryNode [data=" + data + ", left=" + left  + ", right=" + right + ", parent="
                + parent + "]" + "\r\n";*/
    }
}