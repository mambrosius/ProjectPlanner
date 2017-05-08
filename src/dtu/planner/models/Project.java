package dtu.planner.models;

import dtu.planner.exceptions.CustomException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    private String name;
    private String projectNo;
    private String manager;
    //private deadline;
    //private startDate;

    private Map<String, Developer> developerMap = new HashMap<>();
    private Map<String, Activity> activityMap = new HashMap<>();

    private static final String[] columnNames =
            new String[] {"project number", "name", "manager", "participants", "activities"};

    public Project(String projectNo, String name) {
        this.projectNo = projectNo;
        this.name = name;
    }

    private String getProjectNo() {
        return projectNo;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public Boolean hasManager() {
        return manager != null;
    }

    public void addDeveloper(Developer dev) {
        developerMap.putIfAbsent(dev.getInitials(), dev);
    }

    void setManager(String initials) {
        manager = initials;
    }

    public void removeDeveloper(String initials) {
        developerMap.remove(initials);
    }

    Activity addActivity(String activityName) {
        return activityMap.putIfAbsent(activityName, new Activity(activityName, name));
    }

    public void removeActivity(String name) throws CustomException {
        if (activityMap.containsKey(name))
            activityMap.remove(name);
        else
            throw new CustomException("no such activity");
    }

    public Map<String, Developer> getDeveloperMap() {
        return developerMap;
    }

    public Map<String, Activity> getActivityMap() {
        return activityMap;
    }

    public static Object[] getColumnNames() {
        return columnNames;
    }

    public static Object[][] getData(Map<String, Project> projectMap) {

        List<Project> projects = new ArrayList<>(projectMap.values());
        Object[][] projectData = new Object[projects.size()][columnNames.length];

        for (int i = 0; i < projects.size(); i++) {
            projectData[i][0] = projects.get(i).getProjectNo();
            projectData[i][1] = projects.get(i).getName();
            projectData[i][2] = projects.get(i).getManager();
            projectData[i][3] = projects.get(i).getDeveloperMap().size();
            projectData[i][4] = projects.get(i).getActivityMap().size();
        }

        return projectData;
    }

    public Boolean addDeveloperIfAbsent(String initials) {
        return developerMap.putIfAbsent(initials, new Developer(initials)) == null;
    }

    public boolean addActivityIfAbsent(String activityName) {
        return activityMap.putIfAbsent(activityName, new Activity(activityName, name)) == null;
    }
}