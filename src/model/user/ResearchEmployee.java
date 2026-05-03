package model.user;

import model.research.ResearchPaper;
import model.research.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchEmployee extends Employee implements Researcher {
    private int hIndex;
    private List<ResearchPaper> papers = new ArrayList<>();

    public ResearchEmployee(int id, String name, String login, String password, double salary) {
        super(id, name, login, password, salary);
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
