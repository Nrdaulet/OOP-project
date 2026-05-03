package model.user;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    private List<User> users = new ArrayList<>();
    private List<String> logFiles = new ArrayList<>();

    public Admin(int id, String name, String login, String password, double salary) {
        super(id, name, login, password, salary);
    }

    public void addUser(User user) {
        users.add(user);
        String log = "Admin " + name + " added user: " + user.getName();
        logFiles.add(log);
        System.out.println(log);
    }

    public void removeUser(User user) {
        if (users.remove(user)) {
            String log = "Admin " + name + " removed user: " + user.getName();
            logFiles.add(log);
            System.out.println(log);
        } else {
            System.out.println("User " + user.getName() + " not found in system.");
        }
    }

    public void updateUser(User oldUser, User newUser) {
        int index = users.indexOf(oldUser);
        if (index != -1) {
            users.set(index, newUser);
            String log = "Admin " + name + " updated user: " + oldUser.getName()
                    + " -> " + newUser.getName();
            logFiles.add(log);
            System.out.println(log);
        } else {
            System.out.println("User " + oldUser.getName() + " not found in system.");
        }
    }

    public void viewLogFiles() {
        if (logFiles.isEmpty()) {
            System.out.println("No log entries.");
            return;
        }
        System.out.println("=== System Logs ===");
        for (int i = 0; i < logFiles.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + logFiles.get(i));
        }
    }

    public void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in system.");
            return;
        }
        System.out.println("=== All Users (" + users.size() + ") ===");
        for (User user : users) {
            System.out.println("  " + user);
        }
    }

    public List<User> getUsers() { return users; }
    public List<String> getLogFiles() { return logFiles; }

    @Override
    public String toString() {
        return "Admin: " + name + " (salary=" + salary + ")";
    }
}