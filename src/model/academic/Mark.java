package model.academic;

import model.user.Student;

public class Mark {
    private double att1;
    private double att2;
    private double finalExam;
    private Student student;
    private Course course;

    public Mark(Student student, Course course, double att1, double att2, double finalExam) {
        this.student = student;
        this.course = course;
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
    }

    public double getTotal() {
        return att1 + att2 + finalExam;
    }

    public double getGPA() {
        double total = getTotal();
        if (total >= 95) return 4.0;
        if (total >= 90) return 3.67;
        if (total >= 85) return 3.33;
        if (total >= 80) return 3.0;
        if (total >= 75) return 2.67;
        if (total >= 70) return 2.33;
        if (total >= 65) return 2.0;
        if (total >= 60) return 1.67;
        if (total >= 55) return 1.33;
        if (total >= 50) return 1.0;
        return 0.0;
    }

    public String getLetterGrade() {
        double total = getTotal();
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    public double getAtt1() { return att1; }
    public double getAtt2() { return att2; }
    public double getFinalExam() { return finalExam; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    public void setAtt1(double att1) { this.att1 = att1; }
    public void setAtt2(double att2) { this.att2 = att2; }
    public void setFinalExam(double finalExam) { this.finalExam = finalExam; }

    @Override
    public String toString() {
        return course.getName() + " | ATT1=" + att1 + " ATT2=" + att2
                + " Final=" + finalExam + " | Total=" + getTotal()
                + " (" + getLetterGrade() + ", GPA=" + getGPA() + ")";
    }
}