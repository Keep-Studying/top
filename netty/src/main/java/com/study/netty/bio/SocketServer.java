/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO(Blocking IO) 服务端
 * 同步阻塞模型，一个客户端连接对应一个处理线程
 * @author study
 * @version : SocketServer.java, v 0.1 2021年01月17日 10:31 study Exp $
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            System.out.println("等待连接..");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            handler(clientSocket);

            //优化：每个连接起一个连接
            //但是这种方式会存在C10K问题，即1万个连接，服务端内存会爆掉
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();*/
        }

    }

    private static void handler(Socket clientSocket)throws IOException{
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
        }
        clientSocket.getOutputStream().write("HelloClient".getBytes());
        clientSocket.getOutputStream().flush();
    }
}