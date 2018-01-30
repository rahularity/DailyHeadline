package com.dailyheadline.msit.rahul.dailyheadline;

/**
 * Created by Rahul on 1/29/2018.
 */

public class PostDisplayModel {

    public String teacherName;
    public long timeStamp;
    public String topic;

    PostDisplayModel(){}

    public PostDisplayModel(String teacherName, long timeStamp, String topic) {
        this.teacherName = teacherName;
        this.timeStamp = timeStamp;
        this.topic = topic;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

