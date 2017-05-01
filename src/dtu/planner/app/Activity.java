package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Activity {
    private String name;
    private String project;

    private Double estimatedHours;
    private Double hoursUsed;
    private Double remainingHours;

    private List<Developer> developers = new ArrayList<>();

    static final String[] columnNames = new String[]{"name", "developers", "estimated hours", "remaining hours"};

    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    Activity(String name, String project) {
        this.name = name;
        this.project = project;
        this.estimatedHours = 0.0;
        this.hoursUsed = 0.0;
        this.remainingHours = 0.0;
    }

    public String getName() {
        return name;
    }

    String getProjectName() {
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
            app.getDeveloper(initials).unassignActivity(this);
            developers.remove(getDeveloper(initials));
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

    static Object[][] getData(List<Activity> activities) {
        Object[][] activityData = new Object[activities.size()][columnNames.length];
        for (int i = 0; i < activities.size(); i++) {
            activityData[i][0] = activities.get(i).getName();
            activityData[i][1] = activities.get(i).getDevelopers().size();
            activityData[i][2] = activities.get(i).getEstimatedHours();
            activityData[i][3] = activities.get(i).getRemainingHours();
        }
        return activityData;
    }
    /*
    void updateRemainingHours() {
        remainingHours = estimatedHours - hoursUsed;
    }*/

    public Double getRemainingHours() {
        return remainingHours;
    }



    /*
    Integer getWeeksToDeadline() {
    }

    void editWorkHours(Double hours) {
    }
    */
}
