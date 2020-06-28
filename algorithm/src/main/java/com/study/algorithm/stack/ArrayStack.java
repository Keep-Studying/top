/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.stack;


/**
 * 数组方式实现的栈
 * @author study
 * @version : ArrayStack.java, v 0.1 2020年06月28日 0:09 study Exp $
 */
public class ArrayStack<E> implements MyStack<E> {

    /**使用数组来存储数据*/
    //最好开始就设置好容量大小
    private Object[] data  = new Object[1];
    /**大小，初始的元素个数*/
    private int                count = 0;

    public ArrayStack(int capacity) {
        data = new Object[capacity];
    }

    private void judgeSize(){
        if(count >= data.length){		//元素个数已经超出了数组的个数
            resize(2 * data.length);		//10*2*2=40个大小了，我出栈了20个了，只剩下20了吧。
        }else if(count > 0 && count <= data.length / 2){
            resize(data.length / 2);
        }
    }

    private void resize(int size){		//扩容O（n）
        Object[] temp = new Object[size];
        for(int i = 0 ; i < count; i ++){
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public MyStack<E> push(E e) {
        //入栈就完成了   时间复杂度 O(1)
        judgeSize();
        data[count++] = e;
        return null;
    }

    @Override
    public E pop() { //出栈 O(1)
        if(isEmpty()){
            return null;
        }
        //item[n--]
        //item[--n]
        //n不是已经--了么 --n和n-- --n是先把n减了在用，n--先用了在减
        E e = (E)data[--count];
        //为什么要执行这一步？释放空间
        data[count] = null;
        return e;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<>(10);
        arrayStack.push("a");
        arrayStack.push("b");
        System.out.println(arrayStack.size());
        System.out.println(arrayStack.isEmpty());
        arrayStack.pop();
        System.out.println(arrayStack.size());
        System.out.println(arrayStack.isEmpty());
    }
}