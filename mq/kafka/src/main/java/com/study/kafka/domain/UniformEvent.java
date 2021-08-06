/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息事件体
 * @author boyan
 * @version : UniformEvent.java, v 0.1 2021年07月04日 6:33 下午 boyan Exp $
 */
public class UniformEvent<T> extends ToString{
    private static final long serialVersionUID = -2580770531708083655L;

    /**主题*/
    private String topic;
    /**事件码*/
    private String eventCode;
    /**事件id（请保持唯一）：如业务流水号/UUID*/
    private String eventId;
    /**时间*/
    private Date   eventTime;
    /**负载*/
    private T      payload;
    /**租户的机构ID*/
    private String tntInstId;
    /**渠道*/
    private String channel;
    /**场景码*/
    private String sceneCode;
    /**系统名称*/
    private String invokeSystem;
    /**额外参数*/
    private Map<String,Object> extraParams=new HashMap<>();

    public UniformEvent() {
    }

    public UniformEvent(String topic, String eventCode, String eventId, T payload) {
        this.topic = topic;
        this.eventCode = eventCode;
        this.eventId = eventId;
        this.payload = payload;
    }

    public static <T> UniformEvent<T> build(String topic, String eventCode, String eventId, T payload){
        UniformEvent<T> uniformEvent = new UniformEvent<>();
        uniformEvent.setTopic(topic);
        uniformEvent.setEventCode(eventCode);
        uniformEvent.setEventId(eventId);
        uniformEvent.setPayload(payload);
        return uniformEvent;
    }

    /**
     * 根据key获取额外入参
     * @param key
     * @return
     */
    public Object fetchExtraParam(String key){
        if(key == null){
            return null;
        }
        return extraParams.get(key);
    }

    /**
     * 添加额外入参
     * @param key
     * @param value
     */
    public void addExtraParam(String key,Object value){
        if(key == null || value == null){
            return;
        }
        extraParams.put(key,value);
    }

    /**
     * Getter method for property <tt>topic</tt>.
     *
     * @return property value of topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method for property <tt>topic</tt>.
     *
     * @param topic value to be assigned to property topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Getter method for property <tt>eventCode</tt>.
     *
     * @return property value of eventCode
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * Setter method for property <tt>eventCode</tt>.
     *
     * @param eventCode value to be assigned to property eventCode
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * Getter method for property <tt>eventId</tt>.
     *
     * @return property value of eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Setter method for property <tt>eventId</tt>.
     *
     * @param eventId value to be assigned to property eventId
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter method for property <tt>eventTime</tt>.
     *
     * @return property value of eventTime
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * Setter method for property <tt>eventTime</tt>.
     *
     * @param eventTime value to be assigned to property eventTime
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Getter method for property <tt>payload</tt>.
     *
     * @return property value of payload
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Setter method for property <tt>payload</tt>.
     *
     * @param payload value to be assigned to property payload
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * Getter method for property <tt>tntInstId</tt>.
     *
     * @return property value of tntInstId
     */
    public String getTntInstId() {
        return tntInstId;
    }

    /**
     * Setter method for property <tt>tntInstId</tt>.
     *
     * @param tntInstId value to be assigned to property tntInstId
     */
    public void setTntInstId(String tntInstId) {
        this.tntInstId = tntInstId;
    }

    /**
     * Getter method for property <tt>channel</tt>.
     *
     * @return property value of channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Setter method for property <tt>channel</tt>.
     *
     * @param channel value to be assigned to property channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Getter method for property <tt>sceneCode</tt>.
     *
     * @return property value of sceneCode
     */
    public String getSceneCode() {
        return sceneCode;
    }

    /**
     * Setter method for property <tt>sceneCode</tt>.
     *
     * @param sceneCode value to be assigned to property sceneCode
     */
    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    /**
     * Getter method for property <tt>invokeSystem</tt>.
     *
     * @return property value of invokeSystem
     */
    public String getInvokeSystem() {
        return invokeSystem;
    }

    /**
     * Setter method for property <tt>invokeSystem</tt>.
     *
     * @param invokeSystem value to be assigned to property invokeSystem
     */
    public void setInvokeSystem(String invokeSystem) {
        this.invokeSystem = invokeSystem;
    }

    /**
     * Getter method for property <tt>extraParams</tt>.
     *
     * @return property value of extraParams
     */
    public Map<String, Object> getExtraParams() {
        return extraParams;
    }

    /**
     * Setter method for property <tt>extraParams</tt>.
     *
     * @param extraParams value to be assigned to property extraParams
     */
    public void setExtraParams(Map<String, Object> extraParams) {
        this.extraParams = extraParams;
    }
}