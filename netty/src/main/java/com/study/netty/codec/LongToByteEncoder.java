/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author study
 * @version : LongToByteEncoder.java, v 0.1 2021年01月17日 23:28 study Exp $
 */
public class LongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long msg, ByteBuf byteBuf) throws Exception {
        System.out.println("LongToByteEncoder encode被调用");
        System.out.println("msg=" + msg);
        byteBuf.writeLong(msg);
    }
}