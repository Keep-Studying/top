/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.constantpool;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 字符串常量池
 * @author study
 * @version : StringConstantPool.java, v 0.1 2020年07月13日 21:44 study Exp $
 */
public class StringConstantPool {
    /**
     * 字符串常量池位置
     * VM args：-Xms10m -Xmx10m
     *
     * 输出结果：
     * JDK1.7及以上
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at com.study.jvm.constantpool.StringConstantPool.main(StringConstantPool.java:21)
     *
     * 	JDK1.6
     * 	Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
     * */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            for (int j = 0; j < 1000000; j++) {
                list.add(String.valueOf(i + j / 1000000).intern());
            }
        }
    }

    /**
     * 为什么输出会有这些变化呢？主要还是字符串常量池从永久代中脱离、
     * 加入堆区的原因，intern()方法也相应发生了变化：
     * 1、在JDK1.6中，调用intern()首先会在字符串中寻找equal()相等的字符串，
     *    假如字符串存在就返回该字符在字符串池中的引用；假如字符串不存在，虚拟
     *    机会重新在永久代上创建一个实例，将String Table的一个表项指向这个新
     *    创建的实例
     * 2、在JDK1.7（及以上版本）中，由于字符串池不在永久代了，intern()做了一
     *    些修改，更方便地利用堆中的对象。字符串存在时和JDK1.6一样，但是字符串
     *    不存在时不再需要重新创建实例，可以直接指向堆上的实例
     * */
    @Test
    public void test00(){
        String s1 = new String("he")+new String("llo");
        String s2 = s1.intern();
        // 在 JDK 1.6 下输出是 false，创建了 6 个对象
        // 在 JDK 1.7 及以上的版本输出是 true，创建了 5 个对象
        // 当然我们这里没有考虑GC，但这些对象确实存在或存在过
        System.out.println(s1 == s2);
    }
    /**
     * 因为例子中的 s0和s1中的”zhuge”都是字符串常量，它们在编译期就被确定了，
     * 所以s0==s1为true；而”zhu”和”ge”也都是字符串常量，当一个字 符串由多个
     * 字符串常量连接而成时，它自己肯定也是字符串常量，所以s2也同样在编译期就
     * 被优化为一个字符串常量"zhuge"，所以s2也是常量池中” zhuge”的一个引用。
     * 所以我们得出s0==s1==s2；
     * */
    @Test
    public void test01(){
        String s0="zhuge";
        String s1="zhuge";
        String s2="zhu" + "ge";
        System.out.println( s0==s1 ); //true
        System.out.println( s0==s2 ); //true
    }

    /**
     * 用new String() 创建的字符串不是常量，不能在编译期就确定，所以new String()
     * 创建的字符串不放入常量池中，它们有自己的地址空间。s0还是常量池"zhuge"的引用，
     * s1因为无法在编译期间确定，所以是运行时创建的新对象"zhuge"的引用，s2因为有后
     * 半部分new String("ge")，所以也无法在编译期间确定，所以也是一个新创建对象
     * "zhuge"的引用，明白了这些也就知道为何得出此结果了
     * */
    @Test
    public void test02(){
        String s0="zhuge";
        String s1=new String("zhuge");
        String s2="zhu" + new String("ge");
        System.out.println( s0==s1 ); // false
        System.out.println( s0==s2 ); // false
        System.out.println( s1==s2 ); // false
    }

    /**
     * JVM对于字符串常量的"+"号连接，将在程序编译期，JVM就将常量字符串的"+"连接优化
     * 为连接后的值，拿"a" +1来说，经编译器优化后在class中就已经是a1。在编译期其字
     * 符串常量的值就确定下来，故上面程序最终的结果都为true。
     * */
    @Test
    public void test03(){
        String a = "a1";
        String b = "a" + 1;
        System.out.println(a == b); //result = true

        String a1 = "atrue";
        String b1 = "a" + "true";
        System.out.println(a1 == b1); //result = true

        String a2 = "a3.4";
        String b2 = "a" + 3.4;
        System.out.println(a2 == b2); //result = true
    }

    /**
     * JVM对于字符串引用，由于在字符串的"+"连接中，有字符串引用存在，而引用的值在
     * 程序编译期是无法确定的，即"a" + bb无法被编译器优化，只有在程序运行期来动态
     * 分配并将连接后的新地址赋给b。所以上面程序的结果也就为false。
     * */
    @Test
    public void test04(){
        String a = "ab";
        String bb = "b";
        String b = "a" + bb;

        System.out.println(a == b); //result = false
    }

    /**
     * 和示例4中唯一不同的是bb字符串加了final修饰，对于final修饰的变量，它在编译
     * 时被解析为常量值的一个本地拷贝存储到自己的常量池中或嵌入到它的字节码流中。
     * 所以此时的"a" + bb和"a" + "b"效果是一样的。故上面程序的结果为true。
     * */
    @Test
    public void test05(){
        String a = "ab";
        final String bb = "b";
        String b = "a" + bb;

        System.out.println(a == b); //result = true
    }

    /**
     * JVM对于字符串引用bb，它的值在编译期无法确定，只有在程序运行期调用方法后，
     * 将方法的返回值和"a"来动态连接并分配地址为b，故上面 程序的结果为false。
     * */
    @Test
    public void test06(){
        String a = "ab";
        final String bb = getBB();
        String b = "a" + bb;

        System.out.println(a == b); //result = false
    }
    private static String getBB() {
        return "b";
    }

    /**
     * String是不可变的
     *
     * s1 这个就不一样了，可以通过观察其JVM指令码发现s1的"+"操作会变成如下操作
     * StringBuilder temp = new StringBuilder();
     * temp.append(a).append(b).append(c);
     * String s = temp.toString();
     * */
    @Test
    public void test07(){
        String s = "a" + "b" + "c"; //就等价于String s = "abc";
        String a = "a";
        String b = "b";
        String c = "c";
        String s1 = a + b + c;
        System.out.println(s == s1); //false
        //　s1 这个就不一样了，可以通过观察其JVM指令码发现s1的"+"操作会变成如下操作：
        /*StringBuilder temp = new StringBuilder();
        temp.append(a).append(b).append(c);
        String s = temp.toString();*/
    }

    @Test
    public void test08(){
        //字符串常量池："计算机"和"技术"     堆内存：str1引用的对象"计算机技术"
        //堆内存中还有个StringBuilder的对象，但是会被gc回收，StringBuilder的toString方法会new String()，这个String才是真正返回的对象引用
        String str2 = new StringBuilder("计算机").append("技术").toString();   //没有出现"计算机技术"字面量，所以不会在常量池里生成"计算机技术"对象
        System.out.println(str2 == str2.intern());  //true
        //"计算机技术" 在池中没有，但是在heap中存在，则intern时，会直接返回该heap中的引用

        //字符串常量池："ja"和"va"     堆内存：str1引用的对象"java"
        //堆内存中还有个StringBuilder的对象，但是会被gc回收，StringBuilder的toString方法会new String()，这个String才是真正返回的对象引用
        String str1 = new StringBuilder("ja").append("va").toString();    //没有出现"java"字面量，所以不会在常量池里生成"java"对象
        System.out.println(str1 == str1.intern());  //false
        //java是关键字，在JVM初始化的相关类里肯定早就放进字符串常量池了

        String s1=new String("test");
        System.out.println(s1==s1.intern());   //false
        //"test"作为字面量，放入了池中，而new时s1指向的是heap中新生成的string对象，s1.intern()指向的是"test"字面量之前在池中生成的字符串对象

        String s2=new StringBuilder("abc").toString();
        System.out.println(s2==s2.intern());  //false
        //同上
    }
}