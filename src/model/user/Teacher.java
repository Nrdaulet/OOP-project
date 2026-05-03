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
    private List<Course> courses = new ArrayList<>();

    public Teacher(int id, String name, String login, String password, double salary, TeacherTitle title) {
        super(id, name, login, password, salary);
        this.title = title;
    }

    public void putMark(Student student, Course course, double att1, double att2, double finalExam) {
        if (!courses.contains(course)) {
            System.out.println("Error: " + name + " is not an instructor of " + course.getName());
            return;
        }
        if (!course.getStudents().contains(student)) {
            System.out.println("Error: " + student.getName() + " is not registered for " + course.getName());
            return;
        }

        Mark mark = new Mark(student, course, att1, att2, finalExam);
        student.addMark(mark);
        System.out.println("Mark set: " + student.getName() + " got "
                + mark.getTotal() + " (" + mark.getLetterGrade() + ") in " + course.getName());
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println(name + " has no assigned courses.");
            return;
        }
        System.out.println("=== Courses for " + name + " (" + title + ") ===");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println("  " + (i + 1) + ". " + c + " | Students: " + c.getStudents().size());
        }
    }

    public void viewStudents(Course course) {
        if (!courses.contains(course)) {
            System.out.println("Error: " + name + " is not an instructor of " + course.getName());
            return;
        }
        List<Student> students = course.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered for " + course.getName());
            return;
        }
        System.out.println("=== Students in " + course.getName() + " ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + students.get(i));
        }
    }

    @Override
    public int getHIndex() { return hIndex; }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream().sorted(c).forEach(System.out::println);
    }

    public TeacherTitle getTitle() { return title; }
    public List<Course> getCourses() { return courses; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() {
        return "Teacher: " + name + " (" + title + ", salary=" + salary + ")";
    }
}