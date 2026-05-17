package model.user;

import model.academic.Course;
import model.exceptions.LowHIndexException;
import model.research.Researcher;

public class Manager extends Employee {

    public Manager(int id, String login, String password, String name, double salary) {
        super(id, login, password, name, salary);
    }

    public void approveRegistration(Student student, Course course) {
        student.registerCourse(course);
        System.out.println("Registration approved by manager.");
    }

    public void assignSupervisor(Student student, Researcher researcher) {
        try {
            student.assignSupervisor(researcher);
        } catch (LowHIndexException e) {
            System.out.println("Cannot assign supervisor: " + e.getMessage());
        }
    }
}