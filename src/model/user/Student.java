package model.user;

import model.academic.Course;
import model.academic.Mark;
import model.exceptions.LowHIndexException;
import model.research.Researcher;
import model.research.ResearchPaper;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User implements Researcher {
    private int year;
    private double gpa;
    private int credits;
    private Researcher supervisor;

    private List<Course> courses = new ArrayList<>();
    private Map<Course, Mark> marks = new HashMap<>();


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

    public void dropCourse(Course course) {

        if (!courses.contains(course)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        courses.remove(course);
        credits -= course.getCredits();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addMark(Course course, Mark mark) {
        if (!courses.contains(course)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        marks.put(course, mark);
        System.out.println("Mark added for course: " + course.getName());
    }

    public void viewTranscript() {
        System.out.println("=== TRANSCRIPT ===");

        for (Map.Entry<Course, Mark> entry : marks.entrySet()) {
            System.out.println(
                    entry.getKey().getCode() + " | " +
                            entry.getKey().getName() + " | " +
                            entry.getValue()
            );
        }

        System.out.println("GPA: " + calculateGPA());
    }

    public double calculateGPA() {
        if(marks.isEmpty()) return 0;

        double sum = 0;

        for(Mark mark : marks.values()) {
            sum += mark.getTotal();
        }

        return sum / marks.size();
    }

    public void assignSupervisor(Researcher researcher)
            throws LowHIndexException {

        if(researcher.getHIndex() < 3) {
            throw new LowHIndexException(
                    "Researcher's h-index is lower than 3"
            );
        }

        this.supervisor = researcher;

        System.out.println("Supervisor assigned successfully");
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    @Override
    public int getHIndex() {
        return 0;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        System.out.println("Student has no papers.");
    }
}