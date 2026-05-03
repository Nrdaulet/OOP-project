package model.research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;

    private List<Researcher> participants = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    public void addParticipant(Researcher r) {
        participants.add(r);
    }
}
