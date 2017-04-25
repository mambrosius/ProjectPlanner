package dtu.planner.app;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Activity {
    private String name;
    private Project project;

    private Double estimatedWorkHours;
    private List<Developer> developers = new ArrayList<>();

    static final String[] columnNames = new String[]{"name", "developers", "estimated work hours"};

    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    Activity(String name, Project project) {
        this.name = name;
        this.project = project;
    }

    public String getName() {
        return name;
    }

    Project getProject() {
        return project;
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

    void assignDeveloper(String initials) {
        if (app.getDeveloper(initials) != null) {
            developers.add(app.getDeveloper(initials));
            getDeveloper(initials).assignActivity(this);
        }
    }

    void unassignDeveloper(String initials) {
        if (getDeveloper(initials) != null)
            developers.remove(getDeveloper(initials));
    }

    public void setEstimatedWorkHours(Double estimatedWorkHours) {
        this.estimatedWorkHours = estimatedWorkHours;
    }

    Double getEstimatedWorkHours() {
        return estimatedWorkHours;
    }

    static Object[][] getData(List<Activity> activities) {
        Object[][] activityData = new Object[activities.size()][columnNames.length];
        for (int i = 0; i < activities.size(); i++) {
            activityData[i][0] = activities.get(i).getName();
            activityData[i][1] = activities.get(i).getDevelopers().size();
            activityData[i][2] = activities.get(i).getEstimatedWorkHours();
        }
        return activityData;
    }

    /*
    Integer getWeeksToDeadline() {
    }

    void editWorkHours(Double hours) {
    }
    */
}
