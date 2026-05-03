package model.user;

import model.enums.ManagerType;

public class Manager extends Employee {
    private ManagerType managerType;

    public Manager(int id, String name, String login, String password, double salary, ManagerType type) {
        super(id, name, login, password, salary);
        this.managerType = type;
    }
}