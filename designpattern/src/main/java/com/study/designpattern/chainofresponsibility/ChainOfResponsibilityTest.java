/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.designpattern.chainofresponsibility;

/**
 * 责任链模式
 * 定义：Wie请求创建了一个接受对象的链
 * 应用场景：一个请求的处理需要多个对象当中的一个或几个协作处理
 *
 * 优点：
 * 1.请求的发送者和接受者解耦
 * 2.可以控制执行顺序
 * 3.符合开闭原则和单一职责原则
 *
 * 经典案例：
 * javax.servlet.Filter
 * javax.servlet.FilterChain
 * @author study
 * @version : ChainOfResponsibility.java, v 0.1 2020年06月15日 23:26 study Exp $
 */
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        Request request = new Request.RequestBuilder().frequentOk(true).loggedOn(true).build();

        RequestFrequentHandler requestFrequentHandler = new RequestFrequentHandler(new RequestLoggingHandler(null));
        if(requestFrequentHandler.process(request)){
            System.out.println("正常业务处理");
        }else{
            System.out.println("访问异常");
        }
    }
}

class Request{
    /**是否登录*/
    private boolean loggedOn;
    /**访问频率是否ok*/
    private boolean frequentOk;
    /**权限是否允许*/
    private boolean isPermits;
    /**是否含有敏感词*/
    private boolean containsSensitiveWords;
    /**请求主体*/
    private String requestBody;

    public Request(){

    }
    public Request(boolean loggedOn, boolean frequentOk, boolean isPermits, boolean containsSensitiveWords) {
        this.loggedOn = loggedOn;
        this.frequentOk = frequentOk;
        this.isPermits = isPermits;
        this.containsSensitiveWords = containsSensitiveWords;
    }
    static class RequestBuilder{
        private boolean loggedOn;
        private boolean frequentOk;
        private boolean isPermits;
        private boolean containsSensitiveWords;
        
        RequestBuilder loggedOn(boolean loggedOn){
            this.loggedOn = loggedOn;
            return this;
        }
        RequestBuilder frequentOk(boolean frequentOk){
            this.frequentOk = frequentOk;
            return this;
        }
        RequestBuilder isPermits(boolean isPermits){
            this.isPermits = isPermits;
            return this;
        }
        RequestBuilder containsSensitiveWords(boolean containsSensitiveWords){
            this.containsSensitiveWords = containsSensitiveWords;
            return this;
        }
        public Request build(){
            Request request = new Request(loggedOn, frequentOk, isPermits, containsSensitiveWords);
            return request;
        }
    }
    public boolean isloggedOn() {
        return loggedOn;
    }

    public boolean isFrequentOk() {
        return frequentOk;
    }

    public boolean isPermits() {
        return isPermits;
    }

    public boolean isContainsSensitiveWords() {
        return containsSensitiveWords;
    }
}

abstract class Handler{
    Handler next;
    abstract boolean process(Request request);

    public Handler(Handler next) {
        this.next = next;
    }

    /**
     * Getter method for property <tt>next</tt>.
     *
     * @return property value of next
     */
    public Handler getNext() {
        return next;
    }

    /**
     * Setter method for property <tt>next</tt>.
     *
     * @param next value to be assigned to property next
     */
    public void setNext(Handler next) {
        this.next = next;
    }
}

class RequestFrequentHandler extends Handler{
    public RequestFrequentHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("访问频率控制");
        if(request.isFrequentOk()){
            Handler next = getNext();
            if(next == null){
                return true;
            }
            if(!next.process(request)){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}

class RequestLoggingHandler extends Handler{
    public RequestLoggingHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("登录验证");
        if (request.isloggedOn()){
            Handler next=getNext();
            if (null==next){
                return true;
            }
            if (!next.process( request )) {
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}