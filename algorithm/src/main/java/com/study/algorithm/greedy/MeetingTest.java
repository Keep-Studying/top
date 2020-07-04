/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 开会问题
 *
 * 某天早上公司领导找你解决一个问题，明天公司有N个同等级的会议需要使用同一个会议室，
 * 现在给你这个N个会议的开始和结束时间，你怎么样安排才能使会议室最大利用？
 * 即安排最多场次的会议？
 *
 * 可以使用贪心算法来解决
 * 概念：贪心算法又称贪婪算法，它在求解某个问题时，总是做出眼前最大利益。
 * 也就是说只顾眼前不顾大局，所以它是局部最优解。核心点：通过局部最优推出全局最优。
 *
 * 贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，选择的贪心策略
 * 必须具备无后续性，即某个状态以前的过程不会影响以后的状态，只与当前状态有关
 *
 * 贪心算法使用场景
 * 一般通过以下问题就可以通过贪心算法解决：
 * 1. 针对某个问题有限制值，以及有一个期望的最好结果，通常是从某些数据中选出其中一些，
 *    达到最好的结果
 * 2. 一般会有一个排序，找出贡献最大的
 * 3. 举例看贪心是否可以解决
 * @author study
 * @version : MeetingTest.java, v 0.1 2020年07月04日 17:10 study Exp $
 */
public class MeetingTest {

    /**
     * 输出结果示例：
     * 3
     * 0
     * 3
     * 1
     * 2
     * 3
     * 5
     * Meeting [        meetingNum=1,        startTime=0,        endTime=3]
     * Meeting [        meetingNum=3,        startTime=3,        endTime=5]
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Meeting> meetingList = new ArrayList<>();
        //n个会议
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();
            Meeting meeting = new Meeting(i + 1, startTime, endTime);
            meetingList.add(meeting);
        }
        meetingList.sort(null);
        //当前的时间,从一天的0点开始,如果领导要求从8点开始 那curTime=8
        int currentTime = 0;
        for (Meeting meeting:meetingList) {
            if(meeting.getStartTime() >= currentTime){
                System.out.println(meeting.toString());
                currentTime =meeting.getEndTime();
            }
        }

    }
}

class Meeting implements Comparable<Meeting>{

    /**会议编号*/
    private int meetingNum;
    /**开始时间*/
    private int startTime;
    /**结束时间*/
    private int endTime;

    public Meeting(int meetingNum, int startTime, int endTime) {
        super();
        this.meetingNum = meetingNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting o) {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Meeting [");
        builder
                .append("        meetingNum=").append(meetingNum)
                .append(",        startTime=").append(startTime)
                .append(",        endTime=").append(endTime)
                .append(']');
        return builder.toString();
    }

    /**
     * Getter method for property <tt>meetingNum</tt>.
     *
     * @return property value of meetingNum
     */
    public int getMeetingNum() {
        return meetingNum;
    }

    /**
     * Setter method for property <tt>meetingNum</tt>.
     *
     * @param meetingNum value to be assigned to property meetingNum
     */
    public void setMeetingNum(int meetingNum) {
        this.meetingNum = meetingNum;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}