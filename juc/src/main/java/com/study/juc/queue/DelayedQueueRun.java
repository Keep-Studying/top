/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version : DelayedQueueRun.java, v 0.1 2020年08月17日 7:51 study Exp $
 */
public class DelayedQueueRun {

    public static void main(String[] args) {
        DelayQueue<MovieTicket> delayQueue = new DelayQueue<MovieTicket>();
        MovieTicket ticket = new MovieTicket("电影票0",10000);
        delayQueue.put(ticket);
        MovieTicket tiket1 = new MovieTicket("电影票1",5000);
        delayQueue.put(tiket1);
        MovieTicket tiket2 = new MovieTicket("电影票2",8000);
        delayQueue.put(tiket2);
        System.out.println("message:--->入队完毕");

        while( delayQueue.size() > 0 ){
            try {
                ticket = delayQueue.take();
                System.out.println("电影票出队:"+ticket.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class MovieTicket implements Delayed {
    //延迟时间
    private final long delay;
    //到期时间
    private final long expire;
    //数据
    private final String msg;
    //创建时间
    private final long now;

    public long getDelay() {
        return delay;
    }

    public long getExpire() {
        return expire;
    }

    public String getMsg() {
        return msg;
    }

    public long getNow() {
        return now;
    }

    /**
     * @param msg 消息
     * @param delay 延期时间
     */
    public MovieTicket(String msg , long delay) {
        this.delay = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    /**
     * @param msg
     */
    public MovieTicket(String msg){
        this(msg,1000);
    }

    public MovieTicket(){
        this(null,1000);
    }

    /**
     * 获得延迟时间   用过期时间-当前时间,时间单位毫秒
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire
                - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序  当前时间的延迟时间 - 比较对象的延迟时间
     * 越早过期的时间在队列中越靠前
     * @param delayed
     * @return
     */
    public int compareTo(Delayed delayed) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS)
                - delayed.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("MovieTicket [");
        builder
                .append("        delay=").append(delay)
                .append(",        expire=").append(expire)
                .append(",        msg=").append(msg)
                .append(",        now=").append(now)
                .append(']');
        return builder.toString();
    }
}