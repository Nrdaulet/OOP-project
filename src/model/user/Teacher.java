package model.user;

import model.enums.TeacherTitle;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private TeacherTitle title;
    private int hIndex;

    private List<ResearchPaper> papers = new ArrayList<>();

    public Teacher(int id, String name, String login, String password, double salary, TeacherTitle title) {
        super(id, name, login, password, salary);
        this.title = title;
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream().sorted(c).forEach(System.out::println);
    }
}