package model.user;

public class Employee extends User {
    protected double salary;

    public Employee(int id, String name, String login, String password, double salary) {
        super(id, name, login, password);
        this.salary = salary;
    }
}