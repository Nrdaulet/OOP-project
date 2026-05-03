import model.academic.Course;
import model.academic.Lesson;
import model.academic.Mark;
import model.enums.LessonType;
import model.enums.ManagerType;
import model.enums.TeacherTitle;
import model.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<User> allUsers = new ArrayList<>();
    static List<Course> allCourses = new ArrayList<>();
    static Admin admin;
    static Manager manager;

    public static void main(String[] args) {
        initData();

        System.out.println("==========================================");
        System.out.println("   RESEARCH-ORIENTED UNIVERSITY SYSTEM");
        System.out.println("==========================================");

        while (true) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Login: ");
            String login = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            User user = authenticate(login, password);
            if (user == null) {
                System.out.println("Wrong login or password! Try again.");
                continue;
            }

            System.out.println("\nWelcome, " + user.getName() + "!");

            if (user instanceof Admin) {
                adminMenu((Admin) user);
            } else if (user instanceof Manager) {
                managerMenu((Manager) user);
            } else if (user instanceof Teacher) {
                teacherMenu((Teacher) user);
            } else if (user instanceof Student) {
                studentMenu((Student) user);
            }
        }
    }

    static User authenticate(String login, String password) {
        for (User u : allUsers) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // ==================== STUDENT MENU ====================

    static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n=== STUDENT MENU (" + student.getName() + ") ===");
            System.out.println("1. View my courses");
            System.out.println("2. Register for a course");
            System.out.println("3. View my marks");
            System.out.println("4. View transcript");
            System.out.println("5. View all available courses");
            System.out.println("6. View teacher info");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    List<Course> myCourses = student.getCourses();
                    if (myCourses.isEmpty()) {
                        System.out.println("You have no courses.");
                    } else {
                        System.out.println("Your courses:");
                        for (int i = 0; i < myCourses.size(); i++) {
                            System.out.println("  " + (i + 1) + ". " + myCourses.get(i));
                        }
                    }
                    break;

                case "2":
                    System.out.println("Available courses:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Enter course number: ");
                    int cNum = readInt();
                    if (cNum >= 1 && cNum <= allCourses.size()) {
                        student.registerCourse(allCourses.get(cNum - 1));
                    } else {
                        System.out.println("Invalid number.");
                    }
                    break;

                case "3":
                    student.viewMarks();
                    break;

                case "4":
                    student.getTranscript();
                    break;

                case "5":
                    System.out.println("All courses:");
                    for (Course c : allCourses) {
                        System.out.println("  " + c + " | Instructors: " + c.getInstructors().size()
                                + " | Students: " + c.getStudents().size());
                    }
                    break;

                case "6":
                    System.out.println("Select course:");
                    List<Course> sc = student.getCourses();
                    for (int i = 0; i < sc.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + sc.get(i));
                    }
                    System.out.print("Enter number: ");
                    int tNum = readInt();
                    if (tNum >= 1 && tNum <= sc.size()) {
                        Course selected = sc.get(tNum - 1);
                        System.out.println("Instructors of " + selected.getName() + ":");
                        for (Teacher t : selected.getInstructors()) {
                            System.out.println("  " + t);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== TEACHER MENU ====================

    static void teacherMenu(Teacher teacher) {
        while (true) {
            System.out.println("\n=== TEACHER MENU (" + teacher.getName() + ", " + teacher.getTitle() + ") ===");
            System.out.println("1. View my courses");
            System.out.println("2. View students of a course");
            System.out.println("3. Put mark");
            System.out.println("4. Send message");
            System.out.println("5. View messages");
            System.out.println("6. Send complaint");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    teacher.viewCourses();
                    break;

                case "2":
                    List<Course> tc = teacher.getCourses();
                    if (tc.isEmpty()) {
                        System.out.println("No courses assigned.");
                        break;
                    }
                    System.out.println("Select course:");
                    for (int i = 0; i < tc.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + tc.get(i));
                    }
                    System.out.print("Enter number: ");
                    int cn = readInt();
                    if (cn >= 1 && cn <= tc.size()) {
                        teacher.viewStudents(tc.get(cn - 1));
                    }
                    break;

                case "3":
                    tc = teacher.getCourses();
                    if (tc.isEmpty()) {
                        System.out.println("No courses assigned.");
                        break;
                    }
                    System.out.println("Select course:");
                    for (int i = 0; i < tc.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + tc.get(i));
                    }
                    System.out.print("Course number: ");
                    int courseIdx = readInt();
                    if (courseIdx < 1 || courseIdx > tc.size()) {
                        System.out.println("Invalid.");
                        break;
                    }
                    Course selectedCourse = tc.get(courseIdx - 1);
                    List<Student> studs = selectedCourse.getStudents();
                    if (studs.isEmpty()) {
                        System.out.println("No students in this course.");
                        break;
                    }
                    System.out.println("Select student:");
                    for (int i = 0; i < studs.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + studs.get(i));
                    }
                    System.out.print("Student number: ");
                    int sIdx = readInt();
                    if (sIdx < 1 || sIdx > studs.size()) {
                        System.out.println("Invalid.");
                        break;
                    }
                    System.out.print("ATT1 (0-30): ");
                    double a1 = readDouble();
                    System.out.print("ATT2 (0-30): ");
                    double a2 = readDouble();
                    System.out.print("Final (0-40): ");
                    double fin = readDouble();
                    teacher.putMark(studs.get(sIdx - 1), selectedCourse, a1, a2, fin);
                    break;

                case "4":
                    System.out.println("Employees:");
                    List<Employee> employees = getEmployees();
                    for (int i = 0; i < employees.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + employees.get(i));
                    }
                    System.out.print("Send to (number): ");
                    int eIdx = readInt();
                    if (eIdx >= 1 && eIdx <= employees.size()) {
                        System.out.print("Message: ");
                        String msg = scanner.nextLine().trim();
                        teacher.sendMessage(employees.get(eIdx - 1), msg);
                    }
                    break;

                case "5":
                    teacher.viewMessages();
                    break;

                case "6":
                    System.out.print("Complaint text: ");
                    String complaint = scanner.nextLine().trim();
                    teacher.sendComplaint(complaint);
                    break;

                case "0":
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== MANAGER MENU ====================

    static void managerMenu(Manager mgr) {
        while (true) {
            System.out.println("\n=== MANAGER MENU (" + mgr.getName() + ", " + mgr.getManagerType() + ") ===");
            System.out.println("1. Assign teacher to course");
            System.out.println("2. Approve student registration");
            System.out.println("3. View course report");
            System.out.println("4. View students by GPA");
            System.out.println("5. View students alphabetically");
            System.out.println("6. View teachers of a course");
            System.out.println("7. View all courses");
            System.out.println("8. Send message");
            System.out.println("9. View messages");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    List<Teacher> teachers = getTeachers();
                    System.out.println("Select teacher:");
                    for (int i = 0; i < teachers.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + teachers.get(i));
                    }
                    System.out.print("Teacher number: ");
                    int tIdx = readInt();
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int cIdx = readInt();
                    if (tIdx >= 1 && tIdx <= teachers.size() && cIdx >= 1 && cIdx <= allCourses.size()) {
                        mgr.assignTeacherToCourse(teachers.get(tIdx - 1), allCourses.get(cIdx - 1));
                    }
                    break;

                case "2":
                    List<Student> students = getStudents();
                    System.out.println("Select student:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + students.get(i));
                    }
                    System.out.print("Student number: ");
                    int sIdx = readInt();
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int cIdx2 = readInt();
                    if (sIdx >= 1 && sIdx <= students.size() && cIdx2 >= 1 && cIdx2 <= allCourses.size()) {
                        mgr.approveStudentRegistration(students.get(sIdx - 1), allCourses.get(cIdx2 - 1));
                    }
                    break;

                case "3":
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int rIdx = readInt();
                    if (rIdx >= 1 && rIdx <= allCourses.size()) {
                        mgr.createStatisticalReport(allCourses.get(rIdx - 1));
                    }
                    break;

                case "4":
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int g = readInt();
                    if (g >= 1 && g <= allCourses.size()) {
                        mgr.viewStudentsByGPA(allCourses.get(g - 1));
                    }
                    break;

                case "5":
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int a = readInt();
                    if (a >= 1 && a <= allCourses.size()) {
                        mgr.viewStudentsAlphabetically(allCourses.get(a - 1));
                    }
                    break;

                case "6":
                    System.out.println("Select course:");
                    for (int i = 0; i < allCourses.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allCourses.get(i));
                    }
                    System.out.print("Course number: ");
                    int vt = readInt();
                    if (vt >= 1 && vt <= allCourses.size()) {
                        mgr.viewTeachers(allCourses.get(vt - 1));
                    }
                    break;

                case "7":
                    System.out.println("All courses:");
                    for (Course c : allCourses) {
                        System.out.println("  " + c);
                    }
                    break;

                case "8":
                    List<Employee> emps = getEmployees();
                    System.out.println("Select employee:");
                    for (int i = 0; i < emps.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + emps.get(i));
                    }
                    System.out.print("Send to (number): ");
                    int eIdx = readInt();
                    if (eIdx >= 1 && eIdx <= emps.size()) {
                        System.out.print("Message: ");
                        String msg = scanner.nextLine().trim();
                        mgr.sendMessage(emps.get(eIdx - 1), msg);
                    }
                    break;

                case "9":
                    mgr.viewMessages();
                    break;

                case "0":
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== ADMIN MENU ====================

    static void adminMenu(Admin adm) {
        while (true) {
            System.out.println("\n=== ADMIN MENU (" + adm.getName() + ") ===");
            System.out.println("1. View all users");
            System.out.println("2. Add new student");
            System.out.println("3. Add new teacher");
            System.out.println("4. Remove user");
            System.out.println("5. View logs");
            System.out.println("6. Add new course");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    adm.viewAllUsers();
                    break;

                case "2":
                    System.out.print("Name: ");
                    String sName = scanner.nextLine().trim();
                    System.out.print("Login: ");
                    String sLogin = scanner.nextLine().trim();
                    System.out.print("Password: ");
                    String sPass = scanner.nextLine().trim();
                    System.out.print("Year (1-4): ");
                    int sYear = readInt();
                    Student newStudent = new Student(allUsers.size() + 1, sLogin, sPass, sName, sYear);
                    allUsers.add(newStudent);
                    adm.addUser(newStudent);
                    break;

                case "3":
                    System.out.print("Name: ");
                    String tName = scanner.nextLine().trim();
                    System.out.print("Login: ");
                    String tLogin = scanner.nextLine().trim();
                    System.out.print("Password: ");
                    String tPass = scanner.nextLine().trim();
                    System.out.print("Salary: ");
                    double tSalary = readDouble();
                    System.out.println("Title: 1-TUTOR, 2-LECTOR, 3-SENIOR_LECTOR, 4-PROFESSOR");
                    System.out.print("Choose: ");
                    int titleChoice = readInt();
                    TeacherTitle[] titles = TeacherTitle.values();
                    TeacherTitle title = (titleChoice >= 1 && titleChoice <= 4) ? titles[titleChoice - 1] : TeacherTitle.TUTOR;
                    Teacher newTeacher = new Teacher(allUsers.size() + 1, tName, tLogin, tPass, tSalary, title);
                    allUsers.add(newTeacher);
                    adm.addUser(newTeacher);
                    break;

                case "4":
                    System.out.println("Select user to remove:");
                    for (int i = 0; i < allUsers.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + allUsers.get(i));
                    }
                    System.out.print("Number: ");
                    int rIdx = readInt();
                    if (rIdx >= 1 && rIdx <= allUsers.size()) {
                        User removed = allUsers.remove(rIdx - 1);
                        adm.removeUser(removed);
                    }
                    break;

                case "5":
                    adm.viewLogFiles();
                    break;

                case "6":
                    System.out.print("Course code (e.g. CS101): ");
                    String code = scanner.nextLine().trim();
                    System.out.print("Course name: ");
                    String cName = scanner.nextLine().trim();
                    System.out.print("Credits: ");
                    int creds = readInt();
                    Course newCourse = new Course(code, cName, creds);
                    allCourses.add(newCourse);
                    System.out.println("Course added: " + newCourse);
                    break;

                case "0":
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== HELPERS ====================

    static List<Teacher> getTeachers() {
        List<Teacher> list = new ArrayList<>();
        for (User u : allUsers) {
            if (u instanceof Teacher) list.add((Teacher) u);
        }
        return list;
    }

    static List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        for (User u : allUsers) {
            if (u instanceof Student) list.add((Student) u);
        }
        return list;
    }

    static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        for (User u : allUsers) {
            if (u instanceof Employee) list.add((Employee) u);
        }
        return list;
    }

    static int readInt() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double readDouble() {
        try {
            double val = Double.parseDouble(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // ==================== INIT DATA ====================

    static void initData() {
        admin = new Admin(1, "SuperAdmin", "admin", "admin", 600000);
        manager = new Manager(2, "Dana", "dana", "dana", 400000, ManagerType.OR);
        Teacher aset = new Teacher(3, "Aset", "aset", "aset", 500000, TeacherTitle.LECTOR);
        Teacher bolat = new Teacher(4, "Bolat", "bolat", "bolat", 550000, TeacherTitle.PROFESSOR);
        Student nurs = new Student(5, "nurs", "nurs", "Nurs", 2);
        Student aigerim = new Student(6, "aigerim", "aigerim", "Aigerim", 2);
        Student marat = new Student(7, "marat", "marat", "Marat", 3);

        allUsers.add(admin);
        allUsers.add(manager);
        allUsers.add(aset);
        allUsers.add(bolat);
        allUsers.add(nurs);
        allUsers.add(aigerim);
        allUsers.add(marat);

        Course cs101 = new Course("CS101", "Programming", 3);
        Course math201 = new Course("MATH201", "Linear Algebra", 4);
        Course phys101 = new Course("PHYS101", "Physics", 3);
        allCourses.add(cs101);
        allCourses.add(math201);
        allCourses.add(phys101);

        manager.assignTeacherToCourse(aset, cs101);
        manager.assignTeacherToCourse(bolat, math201);

        nurs.registerCourse(cs101);
        nurs.registerCourse(math201);
        aigerim.registerCourse(cs101);
        marat.registerCourse(cs101);
        marat.registerCourse(math201);

        manager.approveStudentRegistration(nurs, cs101);
        manager.approveStudentRegistration(nurs, math201);
        manager.approveStudentRegistration(aigerim, cs101);
        manager.approveStudentRegistration(marat, cs101);
        manager.approveStudentRegistration(marat, math201);

        aset.putMark(nurs, cs101, 25, 28, 37);
        aset.putMark(aigerim, cs101, 20, 22, 30);
        aset.putMark(marat, cs101, 28, 30, 38);
        bolat.putMark(nurs, math201, 18, 20, 25);
        bolat.putMark(marat, math201, 25, 27, 35);
    }
}