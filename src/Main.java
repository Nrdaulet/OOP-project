import model.academic.Course;
import model.academic.Lesson;
import model.academic.Mark;
import model.database.Database;
import model.enums.LessonType;
import model.enums.TeacherTitle;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.SortByCitations;
import model.user.*;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Database db = Database.getInstance();

    private static Student student;
    private static Teacher teacher;
    private static Manager manager;
    private static Admin admin;
    private static Course oopCourse;
    private static ResearchProject researchProject;

    public static void main(String[] args) {
        seedData();

        boolean running = true;

        while (running) {
            System.out.println("\n===== UNIVERSITY SYSTEM =====");
            System.out.println("1. Sign in as Student");
            System.out.println("2. Sign in as Teacher");
            System.out.println("3. Sign in as Manager");
            System.out.println("4. Sign in as Admin");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    teacherMenu();
                    break;
                case 3:
                    managerMenu();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("System closed.");
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    private static void seedData() {
        student = new Student(1, "student01", "12345", "Ali", 4);

        teacher = new Teacher(
                2,
                "teacher01",
                "12345",
                "Dr. Smith",
                500000,
                TeacherTitle.PROFESSOR
        );

        manager = new Manager(
                3,
                "manager01",
                "12345",
                "Mr. Manager",
                600000
        );

        admin = new Admin(
                4,
                "admin01",
                "12345",
                "System Admin",
                700000
        );

        oopCourse = new Course("OOP101", "Object-Oriented Programming", 5);
        oopCourse.addInstructor(teacher);
        oopCourse.addLesson(new Lesson("Introduction to OOP", LessonType.LECTURE));
        oopCourse.addLesson(new Lesson("Inheritance Practice", LessonType.PRACTICE));

        researchProject = new ResearchProject("AI University System");

        db.addUser(student);
        db.addUser(teacher);
        db.addUser(manager);
        db.addUser(admin);
        db.addCourse(oopCourse);
        db.addResearchProject(researchProject);
    }

    private static boolean login(User user) {
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (user.signIn(login, password)) {
            System.out.println("Signed in successfully as " + user.getName());
            return true;
        }

        System.out.println("Wrong login or password.");
        return false;
    }

    private static void studentMenu() {
        if (!login(student)) return;

        boolean back = false;

        while (!back) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. View profile");
            System.out.println("2. Register for course");
            System.out.println("3. View courses");
            System.out.println("4. View transcript");
            System.out.println("5. View GPA");
            System.out.println("6. Join research project");
            System.out.println("7. Change password");
            System.out.println("0. Log out");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    student.viewProfile();
                    break;
                case 2:
                    student.registerCourse(oopCourse);
                    break;
                case 3:
                    System.out.println(student.getCourses());
                    break;
                case 4:
                    if (!student.getCourses().contains(oopCourse)) {
                        System.out.println("Student is not registered for this course. Register first.");
                        break;
                    }

                    Mark mark = new Mark(25, 25, 35);
                    teacher.putMark(student, oopCourse, mark);

                    System.out.println("Mark was successfully added:");
                    System.out.println(oopCourse.getName() + " -> " + mark);
                    break;
                case 5:
                    System.out.println("GPA: " + student.calculateGPA());
                    break;
                case 6:
                    researchProject.addParticipant(student);
                    break;
                case 7:
                    changePassword(student);
                    break;
                case 0:
                    student.logOut();
                    back = true;
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    private static void teacherMenu() {
        if (!login(teacher)) return;

        boolean back = false;

        while (!back) {
            System.out.println("\n===== TEACHER MENU =====");
            System.out.println("1. View profile");
            System.out.println("2. View class info");
            System.out.println("3. View students list");
            System.out.println("4. Put mark");
            System.out.println("5. Publish research paper");
            System.out.println("6. Print papers sorted by citations");
            System.out.println("0. Log out");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    teacher.viewProfile();
                    break;
                case 2:
                    teacher.viewClassInfo(oopCourse);
                    break;
                case 3:
                    teacher.viewStudentsList(oopCourse);
                    break;
                case 4:
                    teacher.putMark(student, oopCourse, new Mark(25, 25, 35));
                    System.out.println("Mark was added.");
                    break;
                case 5:
                    ResearchPaper paper = new ResearchPaper("AI in Education", 45);
                    teacher.addPaper(paper);
                    researchProject.addPaper(paper);
                    System.out.println("Research paper published.");
                    break;
                case 6:
                    teacher.printPapers(new SortByCitations());
                    break;
                case 0:
                    teacher.logOut();
                    back = true;
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    private static void managerMenu() {
        if (!login(manager)) return;

        boolean back = false;

        while (!back) {
            System.out.println("\n===== MANAGER MENU =====");
            System.out.println("1. View profile");
            System.out.println("2. Approve course registration");
            System.out.println("3. Assign supervisor");
            System.out.println("4. View course info");
            System.out.println("0. Log out");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.viewProfile();
                    break;
                case 2:
                    manager.approveRegistration(student, oopCourse);
                    break;
                case 3:
                    manager.assignSupervisor(student, teacher);
                    break;
                case 4:
                    System.out.println(oopCourse);
                    break;
                case 0:
                    manager.logOut();
                    back = true;
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    private static void adminMenu() {
        if (!login(admin)) return;

        boolean back = false;

        while (!back) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View profile");
            System.out.println("2. Add user");
            System.out.println("3. Remove user");
            System.out.println("4. View users");
            System.out.println("5. View research projects");
            System.out.println("0. Log out");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewProfile();
                    break;
                case 2:
                    Student newStudent = new Student(5, "newstudent", "123", "New Student", 1);
                    admin.addUser(newStudent);
                    db.addUser(newStudent);
                    break;
                case 3:
                    admin.removeUser(student);
                    break;
                case 4:
                    admin.viewUsers();
                    break;
                case 5:
                    System.out.println(db.getResearchProjects());
                    break;
                case 0:
                    admin.logOut();
                    back = true;
                    break;
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }

    private static void changePassword(User user) {
        System.out.print("Old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("New password: ");
        String newPassword = scanner.nextLine();

        user.changePassword(oldPassword, newPassword);
    }
}