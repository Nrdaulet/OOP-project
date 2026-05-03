package model.user;

import model.academic.Course;
import model.academic.Mark;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int year;
    private double gpa;
    private int credits;
    private int failCount;

    private List<Course> courses = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();

    public Student(int id, String login, String password, String name, int year) {
        super(id, login, password, name);
        this.year = year;
    }

    public void registerCourse(Course course) {
        if (failCount >= 3) {
            System.out.println("Cannot register: student has failed 3 or more courses!");
            return;
        }
        if (credits + course.getCredits() > 21) {
            System.out.println("Credit limit exceeded! Current: " + credits
                    + ", trying to add: " + course.getCredits() + ", max: 21");
            return;
        }
        courses.add(course);
        credits += course.getCredits();
        System.out.println(name + " registered for " + course);
    }

    public void addMark(Mark mark) {
        marks.add(mark);
        if (mark.getTotal() < 50) {
            failCount++;
            if (failCount >= 3) {
                System.out.println("WARNING: " + name + " has failed 3 courses!");
            }
        }
        recalculateGPA();
    }

    private void recalculateGPA() {
        if (marks.isEmpty()) {
            gpa = 0;
            return;
        }
        double totalGPA = 0;
        for (Mark mark : marks) {
            totalGPA += mark.getGPA();
        }
        gpa = totalGPA / marks.size();
    }

    public void viewMarks() {
        if (marks.isEmpty()) {
            System.out.println(name + " has no marks yet.");
            return;
        }
        System.out.println("=== Marks for " + name + " ===");
        for (Mark mark : marks) {
            System.out.println("  " + mark);
        }
        System.out.println("Overall GPA: " + String.format("%.2f", gpa));
    }

    public void getTranscript() {
        System.out.println("============================================");
        System.out.println("         TRANSCRIPT - " + name);
        System.out.println("         Year: " + year + " | GPA: " + String.format("%.2f", gpa));
        System.out.println("============================================");

        if (marks.isEmpty()) {
            System.out.println("  No completed courses.");
        } else {
            System.out.println(String.format("  %-10s %-20s %6s %6s %6s %6s %5s %4s",
                    "Code", "Course", "ATT1", "ATT2", "Final", "Total", "Grade", "GPA"));
            System.out.println("  " + "-".repeat(70));
            for (Mark mark : marks) {
                System.out.println(String.format("  %-10s %-20s %6.1f %6.1f %6.1f %6.1f %5s %4.2f",
                        mark.getCourse().getCode(),
                        mark.getCourse().getName(),
                        mark.getAtt1(),
                        mark.getAtt2(),
                        mark.getFinalExam(),
                        mark.getTotal(),
                        mark.getLetterGrade(),
                        mark.getGPA()));
            }
        }

        System.out.println("============================================");
        System.out.println("  Total Credits: " + credits);
        System.out.println("  Courses Registered: " + courses.size());
        System.out.println("  Courses Graded: " + marks.size());
        System.out.println("============================================");
    }

    public List<Course> getCourses() { return courses; }
    public List<Mark> getMarks() { return marks; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }
    public int getCredits() { return credits; }
    public int getFailCount() { return failCount; }

    @Override
    public String toString() {
        return "Student: " + name + " (Year " + year + ", GPA=" + String.format("%.2f", gpa) + ")";
    }
}