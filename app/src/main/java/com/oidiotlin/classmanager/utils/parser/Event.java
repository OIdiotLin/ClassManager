package com.oidiotlin.classmanager.utils.parser;

/**
 * Created by OIdiot on 2017/2/20.
 * Project: ClassManager
 */

public class Event {

    private String name;    // 活动名称
    private String date;    // 活动日期 'Y-M-D'
    private String time;    // 活动开始时间 'H:M:S'
    private String place;   // 活动地点
    private String content; // 活动内容
    private int participation;  // 活动参与分
    private int[] participator; // 活动参与人编号

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParticipation() {
        return participation;
    }

    public void setParticipation(int participation) {
        this.participation = participation;
    }

    public int[] getParticipator() {
        return participator;
    }

    public void setParticipator(int[] participator) {
        this.participator = participator;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
