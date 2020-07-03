/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.designpattern.adapter;


/**
 * 适配器模式
 *
 * 定义：将一个类的接口转换为客户希望的另一个接口。Adapter模式使得原本由于接口不兼容
 * 而不能一起工作的那些类可以一起工作
 *
 * 优点：
 * 1.符合单一职责原则
 * 2.符合开闭原则
 *
 * 经典案例
 * jdk中
 * @see java.util.Arrays#asList(java.lang.Object[])
 * @see java.util.Collections#list(Enumeration)
 * spring中
 * @see org.springframework.context.event.GenericApplicationListenerAdapter
 *
 * @author study
 * @version : AdapterTest.java, v 0.1 2020年07月03日 8:37 study Exp $
 */
public class AdapterTest {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.output5v();

        //类适配器方法会暴露原始方法
        AdapterClass adapterClass = new AdapterClass();
        adapterClass.output5v();
    }
}

class Adaptee{
    public int output220v(){return 220;}
}

interface Target{
    int output5v();
}

/**
 * 对象适配器模式
 * */
class Adapter implements Target{

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public int output5v() {
        int i = adaptee.output220v();
        System.out.println(String.format("原始电压： %d v  - >  输出电压： %d  v  ",i,5 ));
        return 5;
    }
}

class AdapterClass extends Adaptee implements Target{

    @Override
    public int output5v() {
        int i = output220v();
        System.out.println(String.format("原始电压： %d v  - >  输出电压： %d  v  ",i,5 ));
        return 5;
    }
}