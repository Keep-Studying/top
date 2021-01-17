/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * BIO 客户端
 * @author study
 * @version : SocketClient.java, v 0.1 2021年01月17日 10:42 study Exp $
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket  = new Socket("localhost", 9000);
        //向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes));
        socket.close();
    }
}