package model.user;

import model.academic.Course;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int year;
    private double gpa;
    private int credits;

    private List<Course> courses = new ArrayList<>();

    public Student(int id, String login, String password, String name, int year) {
        super(id, login, password, name);
        this.year = year;
    }

    public void registerCourse(Course course) {
        if (credits + course.getCredits() > 21) {
            System.out.println("Credit limit exceeded!");
            return;
        }
        courses.add(course);
        credits += course.getCredits();
    }

    public List<Course> getCourses() {
        return courses;
    }
}