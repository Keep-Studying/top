/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author study
 * @version : ByteToLongDecoder.java, v 0.1 2021年01月17日 23:27 study Exp $
 */
public class ByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("ByteToLongDecoder decode 被调用");
        //因为 long 8个字节, 需要判断有8个字节，才能读取一个long
        if(byteBuf.readableBytes() >= 8) {
            list.add(byteBuf.readLong());
        }
    }
}