package model.research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<Researcher> participants = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    public ResearchProject(String topic) {
        this.topic = topic;
    }

    public void addParticipant(Researcher researcher) {
        participants.add(researcher);
        System.out.println("Researcher joined project: " + topic);
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void printParticipants() {
        System.out.println("Participants of project: " + topic);
        for (Researcher r : participants) {
            System.out.println(r);
        }
    }

    public void printPapers() {
        System.out.println("Papers of project: " + topic);
        for (ResearchPaper p : papers) {
            System.out.println(p);
        }
    }

    public String getTopic() {
        return topic;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    @Override
    public String toString() {
        return "ResearchProject: " + topic;
    }
}