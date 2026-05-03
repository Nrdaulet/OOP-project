import model.academic.Course;
import model.user.Student;

public class Main {
    public static void main(String[] args) {
        Student s = new Student(1, "Nurs", "login", "123", 1);

        Course c1 = new Course("CS101", "Programming", 3);
        Course c2 = new Course("CS102", "Math", 4);

        s.registerCourse(c1);
        s.registerCourse(c2);

        System.out.println("Courses count: " + s.getCourses().size());

        Course big = new Course("CS999", "BigCourse", 30);
        s.registerCourse(big);
    }
}