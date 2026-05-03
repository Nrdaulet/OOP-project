package model.academic;

public class Mark {
    private double att1;
    private double att2;
    private double finalExam;

    public double getTotal() {
        return att1 + att2 + finalExam;
    }
}