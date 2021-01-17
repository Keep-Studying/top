/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author study
 * @version : ChatServerHandler.java, v 0.1 2021年01月17日 23:20 study Exp $
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**GlobalEventExecutor.INSTANCE是全局的事件执行器，是一个单例*/
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**时间格式化*/
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**表示channel处于就绪状态，提示上线*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户端假如聊天的信息推送给其他在线的客户端
        //该方法会将 channelGroup 中所有的 channel 遍历，并发送消息
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"上线了"+sdf.format(new Date())+"\n");
        //将当前 channel 加入到当前 channelGroup
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress() + "上线了\n");
    }

    /**表示 channel 处于不活动状态，就提示离线了*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"下线了"+sdf.format(new Date())+"\n");
        System.out.println(channel.remoteAddress() + "下线了\n");
        System.out.println("channelGroup size = " + channelGroup.size());
    }

    /**读取数据*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取当前 channel
        Channel channel = ctx.channel();
        channelGroup.forEach(ch->{
            if(channel != ch){
                ch.writeAndFlush("[客户端]"+channel.remoteAddress()+"发送了消息："+msg+"\n");
            }else {
                ch.writeAndFlush("[自己]发送了消息："+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}