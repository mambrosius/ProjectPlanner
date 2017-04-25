package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

class Activity {


    private String name;
    private String projectName;

    private Double estimatedWorkHours;
    private List<String> participants = new ArrayList<>();

    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    Activity(String activityName, String projectName) {
        this.projectName = projectName;
        this.name = activityName;
    }

    public String getProjectName() {
        return projectName;
    }

    List<String> getParticipants() {
        return participants;
    }

    void addParticipant(String initials) {
        participants.add(initials);
    }

    void removeParticipant(String initials) {
        participants.remove(initials);
    }

    public String getName() {
        return name;
    }

    public Double getEstimatedWorkHours() {
        return estimatedWorkHours;
    }

    public void setEstimatedWorkHours(Double estimatedWorkHours) {
        this.estimatedWorkHours = estimatedWorkHours;
    }



    /*
    Integer getWeeksToDeadline() {

    }

    void editWorkHours(Double hours) {

    }
    */
}
