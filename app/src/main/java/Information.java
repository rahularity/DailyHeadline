/**
 * Created by Rahul on 1/28/2018.
 */

public class Information {

    private String teacherName;
    private String timeStamp;
    private String topics;

    public Information(){}

    public Information(String teacherName, String timeStamp, String topics) {
        this.teacherName = teacherName;
        this.timeStamp = timeStamp;
        this.topics = topics;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }
}
