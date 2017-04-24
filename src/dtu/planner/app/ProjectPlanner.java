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
        new LoginGUI();
    }

    private ProjectPlanner() {
        this.admin = new Administrator();
        this.projects = new ArrayList<>();
        this.developers = new ArrayList<>();
    }

    List<String> getNamesOfProjects() {
        List<String> namesOfProjects = new ArrayList<>();
        for (Project project : projects) {
            namesOfProjects.add(project.getName());
        }
        return namesOfProjects;
    }

    Project getProjectBy(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    List<Developer> getDevelopers() {
        return developers;
    }

    List<String> getInitialsOfDevelopers() {
        List<String> initialsOfDevelopers = new ArrayList<>();
        for (Developer developer : developers) {
            initialsOfDevelopers.add(developer.getInitials());
        }
        return initialsOfDevelopers;
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
