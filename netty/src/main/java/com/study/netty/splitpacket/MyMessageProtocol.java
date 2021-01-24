/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.netty.splitpacket;

/**
 * 自定义协议包
 * @author study
 * @version : MyMessageProtocol.java, v 0.1 2021年01月24日 15:57 study Exp $
 */
public class MyMessageProtocol {
    //定义一次发送包体长度
    private int len;
    //一次发送包体内容
    private byte[] content;

    /**
     * Getter method for property <tt>len</tt>.
     *
     * @return property value of len
     */
    public int getLen() {
        return len;
    }

    /**
     * Setter method for property <tt>len</tt>.
     *
     * @param len value to be assigned to property len
     */
    public void setLen(int len) {
        this.len = len;
    }

    /**
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content value to be assigned to property content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}