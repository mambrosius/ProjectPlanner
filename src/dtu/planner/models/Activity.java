package dtu.planner.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity {
    private String name;
    private String project;

    private Double estimatedHours;
    private Double hoursUsed;
    private Double remainingHours;

    private Map<String, Developer> developerMap = new HashMap<>();

    private static final String[] columnNames = new String[]{"name", "project", "developers", "estimated hours", "remaining hours"};

    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    public Activity(String name, String project) {
        this.name = name;
        this.project = project;
        this.hoursUsed = 0.0;
        this.remainingHours = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return project;
    }

    public Map<String, Developer> getDeveloperMap() {
        return developerMap;
    }

    void addDeveloper(Developer dev) {
        if (developerMap.putIfAbsent(dev.getInitials(), dev) == null)
            dev.addActivity(this);
    }

    public void removeDeveloper(String initials) {
        developerMap.get(initials).removeActivity(this.getName());
        developerMap.remove(initials);
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
        remainingHours = estimatedHours - hoursUsed;
    }

    Double getEstimatedHours() {
        return estimatedHours;
    }

    void setHoursUsed(Double hours) {
        this.hoursUsed += hours;
        remainingHours = estimatedHours - hoursUsed;
    }

    public Double getHoursUsed() {
        return hoursUsed;
    }

    public static Object[][] getData(Map<String, Activity> activityMap) {
        List<Activity> activities = new ArrayList<>(activityMap.values());
        Object[][] activityData = new Object[activities.size()][columnNames.length];
        for (int i = 0; i < activities.size(); i++) {
            activityData[i][0] = activities.get(i).getName();
            activityData[i][1] = activities.get(i).getProjectName();
            activityData[i][2] = activities.get(i).getDeveloperMap().size();
            activityData[i][3] = activities.get(i).getEstimatedHours();
            activityData[i][4] = activities.get(i).getRemainingHours();
        }
        return activityData;
    }

    private Double getRemainingHours() {
        if (estimatedHours == null)
            return null;
        return remainingHours;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    /*
    Integer getWeeksToDeadline() {
    }

    void editWorkHours(Double hours) {
    }
    */

    public String toString() {
        return getName();
    }
}
