package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static dtu.planner.app.ProjectPlanner.app;

class Project {

    private String name;
    private String manager;
    private List<String> participants = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();


    //private deadline;
    //private startDate;
    //private String projectNumber;

    /*
    public void generateProjectNumber() {
    }
    */

    Project(String name) {
        this.name = name;
    }

    String getName() {
        return name;
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

    Activity getActivityBy(String name) {
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return activity;
            }
        }
        return null;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    void addActivity(String activity) {
        activities.add(new Activity(activity));
    }

    void removeActivity(String activity) {
        activities.remove(getActivityBy(activity));
    }
}
