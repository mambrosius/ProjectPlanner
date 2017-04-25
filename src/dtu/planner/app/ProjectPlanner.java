package dtu.planner.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ProjectPlanner {

    static ProjectPlanner app = new ProjectPlanner();

    Administrator admin;
    DateServer dateServer;
    List<Project> projects;
    List<Developer> developers;

    final String[] projectColumnNames = new String[] {"name", "manager", "participants", "activities"};
    final String[] developerColumnNames = new String[]{"initials", "activities"};

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

    List getDevelopers() {
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

    Object[][] getProjectDataOf(List<Project> projects) {
        Object[][] projectData = new Object[projects.size()][projectColumnNames.length];
        for (int i = 0; i < projects.size(); i++) {
            projectData[i][0] = projects.get(i).getName();
            projectData[i][1] = projects.get(i).getManager();
            projectData[i][2] = projects.get(i).getParticipants().size();
            projectData[i][3] = projects.get(i).getActivities().size();
        }
        return projectData;
    }

    Object[][] getDeveloperDataOf(List<Developer> developers) {
        Object[][] developerData = new Object[developers.size()][developerColumnNames.length];
        for (int i = 0; i < developers.size(); i++) {
            developerData[i][0] = developers.get(i).getInitials();
            developerData[i][1] = developers.get(i).getActivities().size();
        }
        return developerData;
    }

    List getProjects() {
        return projects;
    }

    /*
    Vector projectData = new Vector();

    for (Project project : projects) {
        Vector newRow = new Vector<>(Arrays.asList(
                project.getName(),
                project.getActivities().size(),
                project.getManager(),
                project.getActivities().size()));
        projectData.add(newRow);
    }
    return projectData;
    */
}
