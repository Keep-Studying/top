/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.designpattern.templatemethod;

/**
 * 模板方法模式
 * 定义：定义一个操作的算法骨架，而将一些步骤延迟到子类中。Template Method 使得子类可以
 * 不改边一个算法的结构即可重定义该算法的某些特定步骤
 *
 * 应用场景：
 * 1.当你想让客户端只扩展算法的特定步骤，而不是整个算法或其结构时，请使用Template Method模式
 * 2.当你有几个类包含几乎相同的算法，但有一些细微的差异时，请使用此模式
 *
 * 优点：
 * 1.你可以让客户端只覆盖大型算法的某些部分，从而减少算法其他部分发生的更改对它们的影响
 * 2.你可以将重复的代码拖到超类中
 *
 * 经典案例：
 * @see javax.servlet.http.HttpServlet
 * @see org.springframework.web.servlet.mvc.AbstractController
 *
 * @author study
 * @version : TemplateTest.java, v 0.1 2020年07月03日 8:49 study Exp $
 */
public class TemplateTest {

    public static void main(String[] args) {
        AbstractTemplate abstractTemplate = new SubTemplate();
        abstractTemplate.operation();
    }
}

abstract class AbstractTemplate{
    public void operation(){
        System.out.println("pre");
        System.out.println("step1");
        System.out.println("step2");
        templateMethod();
        System.out.println("end");
    }
    abstract protected void templateMethod();
}

class SubTemplate extends AbstractTemplate{

    @Override
    protected void templateMethod() {
        System.out.println("SubTemplate executed");
    }
}