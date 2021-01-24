/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author study
 * @version : NettyClientHandler.java, v 0.1 2021年01月23日 0:13 study Exp $
 */
public class NettyClientHandler  extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到服务器消息:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        //ctx.writeAndFlush("测试String编解码");
        //测试对象编解码
        //ctx.writeAndFlush(new User(1,"zhuge"));
        //测试用protostuff对对象编解码
        ByteBuf buf = Unpooled.copiedBuffer(ProtostuffUtil.serializer(new User(1, "zhuge")));
        ctx.writeAndFlush(buf);

    }
}