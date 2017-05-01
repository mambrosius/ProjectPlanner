package dtu.planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Project {

    //private deadline;
    //private startDate;

    private String projectNo;
    private String name;

    private Manager manager;

    private List<Activity> activities = new ArrayList<>();
    private List<Developer> developers = new ArrayList<>();

    static final String[] columnNames = new String[] {"project number", "name", "manager", "participants", "activities"};

    Project(String projectNo, String name) {
        this.projectNo = projectNo;
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setManager(String initials) {
        this.manager = new Manager(initials);
    }

    Manager getManager() {
        return manager;
    }

    String getManagerInitials() {
        if (manager != null)
            return manager.getInitials();
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

    Boolean assignDeveloper(String initials) {
        if (initials != null && app.getDeveloper(initials) != null)
            return developers.add(app.getDeveloper(initials));
        return false;
    }

    Boolean unassignDeveloper(String initials) {
        if (getDeveloper(initials) != null)
            return developers.remove(getDeveloper(initials));
        return false;
    }

    List<Activity> getActivities() {
        return activities;
    }

    Activity getActivity(String name) {
        for (Activity activity : activities)
            if (activity.getName().equals(name))
                return activity;
        return null;
    }

    boolean addActivity(String activityName) {
        if (getActivity(activityName) == null)
            return activities.add(new Activity(activityName, name));
        return false;
    }

    void removeActivity(String activity) {
        activities.remove(getActivity(activity));
    }

    List<String> getNamesOfActivities() {
        List<String> namesOfActivities = new ArrayList<>();
        for (Activity activity : activities) {
            namesOfActivities.add(activity.getName());
        }
        return namesOfActivities;
    }

    static Object[][] getData(List<Project> projects) {
        Object[][] projectData = new Object[projects.size()][columnNames.length];
        for (int i = 0; i < projects.size(); i++) {
            projectData[i][0] = projects.get(i).getProjectNo();
            projectData[i][1] = projects.get(i).getName();
            projectData[i][2] = projects.get(i).getManagerInitials();
            projectData[i][3] = projects.get(i).getDevelopers().size();
            projectData[i][4] = projects.get(i).getActivities().size();
        }
        return projectData;
    }

    public String getProjectNo() {
        return projectNo;
    }
}
