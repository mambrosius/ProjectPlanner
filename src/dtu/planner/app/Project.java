package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Project {

    //private deadline;
    //private startDate;
    //private String projectNumber;

    private String manager;
    private String projectName;
    private List<String> participants = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();

    Project(String name) {
        this.projectName = name;
    }

    String getProjectName() {
        return projectName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    List<String> getParticipants() {
        return this.participants;
    }

    void assignParticipant(String initials) {
        if (app.developers.contains(app.getDeveloperBy(initials))) {
            participants.add(initials);
        }
    }

    void reassignParticipant(String initials) {
        participants.remove(initials);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    void addActivity(String activity) {
        activities.add(new Activity(activity, projectName));
    }

    void removeActivity(String activity) {
        activities.remove(getActivityBy(activity));
    }

    Activity getActivityBy(String name) {
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(name)) {
                return activity;
            }
        }
        return null;
    }

    /*
    public void generateProjectNumber() {
    }
    */
}
