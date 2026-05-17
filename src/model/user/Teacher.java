package model.user;

import model.academic.Course;
import model.academic.Mark;
import model.enums.TeacherTitle;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private TeacherTitle title;
    private int hIndex;

    private List<ResearchPaper> papers = new ArrayList<>();

    public Teacher(int id, String name, String login, String password, double salary, TeacherTitle title) {
        super(id, name, login, password, salary);
        this.title = title;
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream().sorted(c).forEach(System.out::println);
    }

    public void putMark(Student student, Course course, Mark mark) {
        student.addMark(course, mark);
    }
    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }
    public void viewStudentsList(Course course) {
        System.out.println("Students in course: " + course.getName());

        for(Student student : course.getStudents()) {
            System.out.println(student.getName());
        }
    }
    public void viewClassInfo(Course course) {
        System.out.println("Course: " + course.getCode());
        System.out.println("Name: " + course.getName());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("Students count: " + course.getStudents().size());
    }
}