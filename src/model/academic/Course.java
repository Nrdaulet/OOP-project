package model.academic;

import model.user.Student;
import model.user.Teacher;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private int credits;

    private List<Student> students = new ArrayList<>();
    private List<Teacher> instructors = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public void addStudent(Student student) {
        if(!students.contains(student)) {
            students.add(student);
        }
    }

    public void addInstructor(Teacher teacher) {
        if(!instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    public int getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getInstructors() {
        return instructors;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + credits + " credits)";
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}