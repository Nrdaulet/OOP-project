package model.academic;
import java.io.Serializable;
import model.enums.LessonType;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;
    private String topic;
    private LessonType lessonType;

    public Lesson(String topic, LessonType lessonType) {
        this.topic = topic;
        this.lessonType = lessonType;
    }

    public String getTopic() {
        return topic;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    @Override
    public String toString() {
        return lessonType + ": " + topic;
    }
}