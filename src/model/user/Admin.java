package model.user;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {

    private List<User> users = new ArrayList<>();

    public Admin(int id, String login, String password, String name, double salary) {
        super(id, login, password, name, salary);
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " added to system.");
    }

    public void removeUser(User user) {
        users.remove(user);
        System.out.println(user.getName() + " removed from system.");
    }

    public void viewUsers() {
        System.out.println("=== USERS LIST ===");
        for (User user : users) {
            System.out.println(user);
        }
    }
}