package model.user;

import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Comparable<User>  {
    private static final long serialVersionUID = 1L;
    protected int id;
    protected String login;
    protected String password;
    protected String name;

    public User(){}

    public User(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getName(){ return name; }
    public String getLogin(){ return login; }
    public String getPassword(){ return password; }

    @Override
    public String toString(){
        return "User{id=" + id + ", name='" + name + "', login='" + login + "'}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;

    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    public boolean signIn(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public void logOut() {
        System.out.println(name + " logged out.");
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (!this.password.equals(oldPassword)) {
            System.out.println("Wrong old password.");
            return false;
        }

        this.password = newPassword;
        System.out.println("Password changed successfully.");
        return true;
    }

    public void viewProfile() {
        System.out.println("=== PROFILE ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Login: " + login);
    }

    public void sendMessage(User receiver, String text) {
        System.out.println("Message from " + this.name + " to " + receiver.getName() + ": " + text);
    }

    public void sendComplaint(User receiver, String text) {
        System.out.println("Complaint from " + this.name + " to " + receiver.getName() + ": " + text);
    }
    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }
}