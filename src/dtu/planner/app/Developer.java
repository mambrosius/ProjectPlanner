package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Developer {

    private String initials;

    private List<Project> responsibilities = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();

    static final String[] columnNames = new String[]{"initials", "activities"};

    //private Double checkIn;
    //private Double checkOut;
    //private Double unregisteredHours;
    //private List<Absence> absences;

    Developer(String initials) {
        this.initials = initials;
    }

    String getInitials() {
        return initials;
    }

    void appointManager(Project project) {
        project.setManager(initials);
        responsibilities.add(project);
    }

    void stopManage(Project project) {
        project.setManager(null);
        responsibilities.remove(project);
    }

    List<Project> getResponsibilities() {
        return responsibilities;
    }

    Project getResponsibility(String projectName) {
        for (Project project : responsibilities)
            if (project.getName().equals(projectName))
                return project;
        return null;
    }

    List<Activity> getActivities() {
        return activities;
    }

    Activity getActivity(String activityName) {
        for (Activity activity : activities)
            if (activity.getName().equals(activityName))
                return activity;
        return null;
    }

    void assignActivity(Activity activity) {
        activities.add(activity);
    }

    void unassignActivity(Activity activity) {
        activities.remove(activity);
    }

    List<String> getNamesOfResp() {
        /*
        String[] namesOfProjects = new String[responsibilities.size()];
        for (int i = 0; i < responsibilities.size(); i++)
            namesOfProjects[i] = responsibilities.get(i).getName();
        return namesOfProjects;
        */

        List<String> namesOfProjects = new ArrayList<>();
        for (Project project : responsibilities) {
            namesOfProjects.add(project.getName());
        }
        return namesOfProjects;
    }

    static Object[][] getData(List<Developer> developers) {
        Object[][] developerData = new Object[developers.size()][columnNames.length];
        for (int i = 0; i < developers.size(); i++) {
            developerData[i][0] = developers.get(i).getInitials();
            developerData[i][1] = developers.get(i).getActivities().size();
        }
        return developerData;
    }

    /*
    Boolean isAbsent(Calendar date) {

    }

    void registerAbsence(AbsenceType type, Calendar date) {

    }

    void logActivity(Activity activity, Double hours) {

    }

    void seekAssistance(Activity activity, Developer initial ) {

    }

    Boolean assistanceAccept(Activity activity) {

    }
    */
}
