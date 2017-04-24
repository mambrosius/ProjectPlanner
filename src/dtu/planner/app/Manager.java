package dtu.planner.app;

import java.util.*;

import static dtu.planner.app.ProjectPlanner.app;

class Manager {

    private String projectName;

    Manager(String project) {
        this.projectName = project;
    }

    String getProjectName() {
        return projectName;
    }

    void assignActivity(String activityName, String initials) {
        app.getDeveloperBy(initials).addActivity(activityName);
        app.getProjectBy(projectName).getActivityBy(activityName).addParticipant(initials);
    }

    void reassignActivity(String activityName, String initials) {
        app.getDeveloperBy(initials).removeActivity(activityName);
    }

    void setStart(String activity, Calendar date) {

    }

    void setEnd(String activity, Calendar data) {

    }

    void setWorkHours(String activity, Double hours) {

    }

    Object[][] getParticipantsData() {

        List<String> participants = app.getProjectBy(projectName).getParticipants();
        System.out.print(participants.size());
        Object[][] data = new Object[participants.size()][1];

        for (int i = 0; i < participants.size(); i++) {
            data[i][0] = participants.get(i);
        }
        return data;
    }

    void generateRapport(String project) {

    }

}
