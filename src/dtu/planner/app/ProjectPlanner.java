package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlanner {

    static ProjectPlanner app = new ProjectPlanner();

    Administrator admin;
    DateServer dateServer;
    List<Project> projects;
    List<Developer> developers;

    public static void main(String[] args) {
        new PositionSelectGUI();
    }

    private ProjectPlanner() {
        this.admin = new Administrator();
        this.projects = new ArrayList<>();
        this.developers = new ArrayList<>();
    }

    Project getProjectBy(String name) {
        for (Project project : projects) {
            if (project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    List<Developer> getDevelopers() {
        return developers;
    }

    String[] getInitialsOf(List<Developer> developers) {

        String[] initials = new String[developers.size()];

        for (int i = 0; i < developers.size(); i++) {
            initials[i] = developers.get(i).getInitials();
        }
        return initials;
    }

    Developer getDeveloperBy(String initials) {
        for (Developer developer : developers) {
            if (developer.getInitials().equals(initials)) {
                return developer;
            }
        }
        return null;
    }
}
