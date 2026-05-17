package model.research;
import java.io.Serializable;

public class ResearchPaper implements Comparable<ResearchPaper>,Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private int citations;

    public ResearchPaper(String title, int citations) {
        this.title = title;
        this.citations = citations;
    }

    @Override
    public int compareTo(ResearchPaper o) {
        return Integer.compare(this.citations, o.citations);
    }

    @Override
    public String toString() {
        return title + " (" + citations + " citations)";
    }

    public int getCitations() {
        return citations;
    }
}