package model.user;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    protected double salary;
    private List<String> messages = new ArrayList<>();
    private List<String> complaints = new ArrayList<>();

    public Employee() {}

    public Employee(int id, String name, String login, String password, double salary) {
        super(id, login, password, name);
        this.salary = salary;
    }

    public void sendMessage(Employee receiver, String text) {
        String message = "From " + this.name + ": " + text;
        receiver.receiveMessage(message);
        System.out.println("Message sent to " + receiver.getName());
    }

    public void receiveMessage(String message) {
        messages.add(message);
    }

    public void viewMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }
        System.out.println("=== Messages for " + name + " ===");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println((i + 1) + ". " + messages.get(i));
        }
    }

    public void sendComplaint(String complaintText) {
        String complaint = "Complaint from " + name + ": " + complaintText;
        complaints.add(complaint);
        System.out.println("Complaint submitted: " + complaintText);
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public List<String> getMessages() { return messages; }
    public List<String> getComplaints() { return complaints; }

    @Override
    public String toString() {
        return "Employee: " + name + " (salary=" + salary + ")";
    }
}