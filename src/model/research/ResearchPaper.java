package model.research;

public class ResearchPaper implements Comparable<ResearchPaper> {
    private String title;
    private int citations;

    @Override
    public int compareTo(ResearchPaper o) {
        return Integer.compare(this.citations, o.citations);
    }

    @Override
    public String toString() {
        return title + " (" + citations + ")";
    }
}