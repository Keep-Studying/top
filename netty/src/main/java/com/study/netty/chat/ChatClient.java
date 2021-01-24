/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author study
 * @version : ChatClient.java, v 0.1 2021年01月17日 23:18 study Exp $
 */
public class ChatClient {
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap，而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //向pipeline中添加编码器
                    pipeline.addLast("encoder",new StringEncoder());
                    //向pipeline中添加解码器
                    pipeline.addLast("decoder",new StringDecoder());
                    pipeline.addLast(new ChatClientHandler());
                }
            });
            System.out.println("netty client start...");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9009).sync();
            //得到 channel
            Channel channel = channelFuture.channel();
            System.out.println("=========" + channel.remoteAddress() + "=========");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String msg = scanner.nextLine();
                //通过 channel 发送到服务器端
                channel.writeAndFlush(msg);
            }
            //或者不间断的发送200条
            /*for (int i = 0; i < 200; i++) {
                channel.writeAndFlush("hello，诸葛!" + "_");
            }*/
        } finally {
            group.shutdownGracefully();
        }
    }
}