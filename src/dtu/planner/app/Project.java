package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Project {

    //private deadline;
    //private startDate;
    //private String projectNumber;

    private String manager;
    private String name;
    private List<String> participants = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    final String[] activityColumnNames = new String[]{"name", "participants", "estimated work hours"};

    Project(String name) {
        this.name = name;
    }

    String getName() {
        return name;
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

    List<Activity> getActivities() {
        return activities;
    }

    void addActivity(String activity) {
        activities.add(new Activity(activity, name));
    }

    void removeActivity(String activity) {
        activities.remove(getActivityBy(activity));
    }

    Activity getActivityBy(String name) {
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return activity;
            }
        }
        return null;
    }

    List<String> getNamesOfActivities() {
        List<String> namesOfActivities = new ArrayList<>();
        for (Activity activity : activities) {
            namesOfActivities.add(activity.getName());
        }
        return namesOfActivities;
    }

    Object[][] getDeveloperData() {
        Object[][] data = new Object[participants.size()][1];
        for (int i = 0; i < participants.size(); i++) {
            data[i][0] = participants.get(i);
        }
        return data;
    }
    /*
    Object[][] getActivityDataOf(List<Developer> developers) {
        Object[][] developerData = new Object[developers.size()][app.developerColumnNames.length];
        for (int i = 0; i < developers.size(); i++) {
            developerData[i][0] = developers.get(i).getInitials();
            developerData[i][1] = developers.get(i).getActivities().size();
        }
        return developerData;
    }
    */
    /*
    public void generateProjectNumber() {
    }
    */
}
