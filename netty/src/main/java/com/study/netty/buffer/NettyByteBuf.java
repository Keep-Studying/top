/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * ByteBuf 使用
 * @author study
 * @version : NettyByteBuf.java, v 0.1 2021年01月17日 23:25 study Exp $
 */
public class NettyByteBuf {
    public static void main(String[] args) {
        /**
         * 创建ByteBuf对象，该对象内部包含一个字节数据byte[10]
         * 通过readerIndex，writerIndex和capacity，将ByteBuf分成三个区域
         * 已读取的区域：[0,readerIndex)
         * 可读取的区域：[readerIndex,writerIndex)
         * 可写的区域：[writerIndex，capacity)
         * */
        ByteBuf byteBuf = Unpooled.buffer(10);
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 8; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.getByte(i));
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.readByte());
        }
        System.out.println("byteBuf=" + byteBuf);

        //用Unpooled工具类创建ByteBuf
        ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello,zhuge!", CharsetUtil.UTF_8);
        //使用相关的方法
        if(byteBuf2.hasArray()){
            byte[] content = byteBuf2.array();
            //将 content 转成字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf2=" + byteBuf2);

            //0
            System.out.println(byteBuf2.readerIndex());
            //12
            System.out.println(byteBuf2.writerIndex());
            //36
            System.out.println(byteBuf2.capacity());

            //获取数组0这个位置的字符h的ascii码，h=104
            System.out.println(byteBuf2.getByte(0));

            //可读的字节数12
            int len = byteBuf2.readableBytes();
            System.out.println("len="+len);

            for (int i = 0; i < len; i++) {
                System.out.println((char)byteBuf2.getByte(i));
            }

            //范围读取
            System.out.println(byteBuf2.getCharSequence(0, 6, CharsetUtil.UTF_8));
            System.out.println(byteBuf2.getCharSequence(6, 6, CharsetUtil.UTF_8));
        }
    }
}