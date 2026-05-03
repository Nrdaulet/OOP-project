package model.research;

import java.util.Comparator;

public interface Researcher {
    int getHIndex();
    void printPapers(Comparator<ResearchPaper> c);
}