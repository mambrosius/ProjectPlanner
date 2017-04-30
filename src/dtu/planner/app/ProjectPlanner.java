package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlanner {

    Date date;
    static ProjectPlanner app = new ProjectPlanner();

    private Administrator getAdmin;
    private List<Project> projects;
    private List<Developer> developers;

    public static void main(String[] args) {
        new LoginGUI();
    }

    private ProjectPlanner() {
        this.date = new Date();
        this.getAdmin = new Administrator();
        this.projects = new ArrayList<>();
        this.developers = new ArrayList<>();
    }

    Administrator getAdmin() {
        return getAdmin;
    }

    List<Project> getProjects() {
        return projects;
    }

    Project getProject(String name) {
        for (Project project : projects)
            if (project.getName().equals(name))
                return project;
        return null;
    }

    List<Developer> getDevelopers() {
        return developers;
    }

    Developer getDeveloper(String initials) {
        for (Developer developer : developers)
            if (developer.getInitials().equals(initials))
                return developer;
        return null;
    }

    List<String> getInitialsOfDevelopers(List<Developer> developers) {
        List<String> initialsOfDevelopers = new ArrayList<>();
        for (Developer developer : developers)
            initialsOfDevelopers.add(developer.getInitials());
        return initialsOfDevelopers;
    }
}
