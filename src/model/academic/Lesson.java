package model.academic;

import model.enums.LessonType;

public class Lesson {
    private String topic;
    private LessonType lessonType;

    public Lesson(String topic, LessonType lessonType) {
        this.topic = topic;
        this.lessonType = lessonType;
    }
}