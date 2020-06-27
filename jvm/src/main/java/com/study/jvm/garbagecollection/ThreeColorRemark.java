/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.garbagecollection;

/**
 * 垃圾收集算法细节之三色标记
 * 代码仅供参考
 *
 * 在并发标记的过程中，因为标记期间应用线程还在继续跑，对象间的引用可能发生变化，多标和漏标的情况就有可能发生
 * 把GC Roots可达性分析遍历对象过程中遇到的对象，按照“是否访问过”这个条件标记成以下三种颜色：
 * • 黑色：表示对象已经被垃圾收集器访问过，且这个对象的索引引用都已经扫描过。
 *       黑色的对象代表已经扫描过，它是安全存活的，如果有其他对象引用指向了黑色对象，
 *       无需重新扫描一遍。黑色对象不可能直接（不经过灰色对象）指向某个白色对象
 * • 灰色：表示对象已经被垃圾收集器访问过，但这个对象上至少存在一个引用还没有被扫描过
 * • 白色：表示对象尚未被垃圾收集器访问过，显然在可达性分析刚刚开始的阶段，所有的对象都是白色的，
 *       若在分析结束的阶段，仍然是白色的对象，即代表不可达
 *
 *  多标-浮动垃圾：在并发标记过程中，如果由于方法运行结束导致部分局部变量(gcroot)被销毁，这个gcroot引用的
 *     对象之前又被扫描过(被标记为非垃圾对象)，那么本轮GC不会回收这部分内存。这部分本应该回收但是没有回收到
 *     的内存，被称之为“浮动垃圾”。
 *  漏标-读写屏障：漏标会导致被引用的对象被当成垃圾误删除，这是严重bug，必须解决，解决方案有两种：
 *  1.增量更新（Incremental Update）
 *  2.原始快照（Snapshot At The Beginning，SATB）
 * @author study
 * @version : ThreeColorRemark.java, v 0.1 2020年06月26日 23:57 study Exp $
 */
public class ThreeColorRemark {
    public static void main(String[] args) {
        A a = new A();
        //假设从该行开始做并发标记
        D d= a.b.d;         //1.读
        a.b.d = null;       //2.写
        a.d = d;            //3.写
    }


    /**
     * 写屏障
     * @param field 某对象的成员变量，如a.b.d
     * @param new_value 新值，如 null
     * */
/*    void oop_field_store(oop* field,oop new_value){
        pre_write_barrier(field);          // 写屏障-写前操作
        *field = new_value;
        post_write_barrier(field, value);  // 写屏障-写后操作
    }*/

    /**
     * 写屏障实现STAB
     * 当对象B的成员变量的引用发生变化时，比如引用消失（a.b.d = null），我们可以利用写屏障，将B原来成员变量的引用对象D记录下来
     *
     * 原始快照就是当灰色对象要删除指向白色对象的引用关系时， 就将这个要删除的引用记录下来， 在并发扫描结束之后，
     * 再将这些记录过的引用关系中的灰色对象为根， 重新扫描一次，这样就能扫描到白色的对象，将白色对象直接标记为
     * 黑色(目的就是让这种对象在本轮gc清理中能存活下来，待下一轮gc的时候重新扫描，这个对象也有可能是浮动垃圾)
     * */
/*    void pre_write_barrier(oop* field) {
        oop old_value = *field;    // 获取旧值
        remark_set.add(old_value); // 记录原来的引用对象
    }*/

    /**
     * 写屏障实现增量更新
     * 当对象A的成员变量的引用发生变化时，比如新增引用（a.d = d），我们可以利用写屏障，将A新的成员变量引用对象D记录下来
     *
     * 增量更新就是当黑色对象插入新的指向白色对象的引用关系时， 就将这个新插入的引用记录下来， 等并发扫描结束之后，
     * 再将这些记录过的引用关系中的黑色对象为根， 重新扫描一次。 这可以简化理解为， 黑色对象一旦新插入了指向白色对象的引用之后，
     * 它就变回灰色对象了
     * */
/*    void post_write_barrier(oop* field, oop new_value) {
        remark_set.add(new_value);  // 记录新引用的对象
    }*/


    /**
     * 读屏障
     * */
/*    oop oop_field_load(oop* field){
        pre_load_barrier(field); //读屏障-读取前操作
        return *field;
    }*/

    /**
     * 读屏障是直接针对第一步：D d = a.b.d，当读取成员变量时，一律记录下来
     * */
/*    void pre_load_barrier(oop* field) {
        oop old_value = *field;
        remark_set.add(old_value); // 记录读取到的对象
    }*/

}

class A{
    B b = new B();
    D d = null;
}

class B {
    C c = new C();
    D d = new D();
}

class C {
}

class D {
}