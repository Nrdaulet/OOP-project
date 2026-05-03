package model.user;

import model.academic.Course;
import model.academic.Mark;
import model.enums.ManagerType;

import java.util.Comparator;
import java.util.List;

public class Manager extends Employee {
    private ManagerType managerType;

    public Manager(int id, String name, String login, String password, double salary, ManagerType type) {
        super(id, name, login, password, salary);
        this.managerType = type;
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        course.addInstructor(teacher);
        teacher.addCourse(course);
        System.out.println("Manager " + name + " assigned " + teacher.getName()
                + " to course " + course.getName());
    }

    public void approveStudentRegistration(Student student, Course course) {
        course.addStudent(student);
        System.out.println("Manager " + name + " approved " + student.getName()
                + " for course " + course.getName());
    }

    public void createStatisticalReport(Course course) {
        List<Student> students = course.getStudents();

        System.out.println("==============================================");
        System.out.println("  STATISTICAL REPORT: " + course);
        System.out.println("==============================================");

        if (students.isEmpty()) {
            System.out.println("  No students registered.");
            System.out.println("==============================================");
            return;
        }

        double totalScore = 0;
        double minScore = Double.MAX_VALUE;
        double maxScore = Double.MIN_VALUE;
        int passCount = 0;
        int failCount = 0;
        int gradedCount = 0;

        for (Student student : students) {
            for (Mark mark : student.getMarks()) {
                if (mark.getCourse().equals(course)) {
                    double score = mark.getTotal();
                    totalScore += score;
                    gradedCount++;

                    if (score < minScore) minScore = score;
                    if (score > maxScore) maxScore = score;

                    if (score >= 50) {
                        passCount++;
                    } else {
                        failCount++;
                    }
                }
            }
        }

        if (gradedCount == 0) {
            System.out.println("  No grades submitted yet.");
        } else {
            double avg = totalScore / gradedCount;
            System.out.println("  Students enrolled:  " + students.size());
            System.out.println("  Students graded:    " + gradedCount);
            System.out.println("  Average score:      " + String.format("%.1f", avg));
            System.out.println("  Highest score:      " + String.format("%.1f", maxScore));
            System.out.println("  Lowest score:       " + String.format("%.1f", minScore));
            System.out.println("  Passed (>=50):      " + passCount);
            System.out.println("  Failed (<50):       " + failCount);
        }
        System.out.println("==============================================");
    }

    public void viewStudentsByGPA(Course course) {
        List<Student> students = course.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students in " + course.getName());
            return;
        }
        System.out.println("=== Students in " + course.getName() + " (sorted by GPA) ===");
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(s -> System.out.println("  " + s));
    }

    public void viewStudentsAlphabetically(Course course) {
        List<Student> students = course.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students in " + course.getName());
            return;
        }
        System.out.println("=== Students in " + course.getName() + " (alphabetical) ===");
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(s -> System.out.println("  " + s));
    }

    public void viewTeachers(Course course) {
        List<Teacher> teachers = course.getInstructors();
        if (teachers.isEmpty()) {
            System.out.println("No instructors for " + course.getName());
            return;
        }
        System.out.println("=== Instructors of " + course.getName() + " ===");
        for (Teacher t : teachers) {
            System.out.println("  " + t);
        }
    }

    public ManagerType getManagerType() { return managerType; }

    @Override
    public String toString() {
        return "Manager: " + name + " (" + managerType + ", salary=" + salary + ")";
    }
}