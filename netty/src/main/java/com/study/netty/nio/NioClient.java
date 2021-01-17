/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author study
 * @version : NioClient.java, v 0.1 2021年01月17日 15:39 study Exp $
 */
public class NioClient {
    /**通道管理器*/
    private Selector selector;

    /**
     * 启动客户端程序测试
     * @throws IOException
     * */
    public static void main(String[] args)throws IOException{
        NioClient client = new NioClient();
        client.initClient("127.0.0.1",9000);
        client.connect();
    }

    /**
     * 获得一个Socket通道，并对该通道做一些初始化的工作
     * @param ip 连接的服务器的ip
     * @param port 连接的服务器的端口号
     * @throws IOException
     * */
    public void  initClient(String ip,int port) throws IOException {
        //获取一个Socket通道
        SocketChannel channel = SocketChannel.open();
        //设置通道为非阻塞
        channel.configureBlocking(false);
        //获得一个通道管理器
        this.selector = Selector.open();
        //客户端连接服务器，其实方法执行并没有实现连接，需要在listen()方法
        // 中调用channel.finishConnect()方法，才能完成连接
        channel.connect(new InetSocketAddress(ip,port));
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     * */
    public void connect() throws IOException{
        //轮询方式访问selector
        while (true){
            /**
             * 选择一组可以进行IO操作的事件，放在selector中，客户端的该方法不会阻塞
             * 这里和服务端的方法不一样，查看API注释可以知道，当至少需要一个通道被选
             * 中时*/
            selector.select();
            //获得selector中选中的迭代器
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //删除已选key，以防重复处理
                iterator.remove();
                //连接事件发生
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    //设置成非阻塞
                    channel.configureBlocking(false);
                    //在这里可以给服务器发送消息
                    ByteBuffer buffer = ByteBuffer.wrap("HelloServer".getBytes());
                    channel.write(buffer);
                    //在和服务器连接成功之后，为了可以接收到服务器的信息，需要给通道设置读的权限
                    //获得了可读的事件
                    channel.register(this.selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    /**
     * 处理读取服务端发来的额信息的事件
     * @throws IOException
     * */
    public void read(SelectionKey key)throws IOException {
        //和服务端的read方法一样
        //服务端可读取消息：得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        int len = channel.read(buffer);
        if(len != -1){
            System.out.println("客户端收到信息：" + new String(buffer.array(), 0, len));
        }
    }
}