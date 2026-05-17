package model.academic;
import java.io.Serializable;

public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    private double att1;
    private double att2;
    private double finalExam;

    public double getTotal() {
        return att1 + att2 + finalExam;
    }

    public Mark(double att1, double att2, double finalExam) {
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
    }

    @Override
    public String toString() {
        return "Att1: " + att1 +
                ", Att2: " + att2 +
                ", Final: " + finalExam +
                ", Total: " + getTotal() +
                ", Grade: " + getLetterGrade();
    }

    public String getLetterGrade() {
        double total = getTotal();

        if(total >= 95) return "A";
        if(total >= 90) return "A-";
        if(total >= 85) return "B+";
        if(total >= 80) return "B";
        if(total >= 75) return "B-";
        if(total >= 70) return "C+";
        if(total >= 65) return "C";
        if(total >= 60) return "C-";
        if(total >= 50) return "D";
        return "F";
    }
}