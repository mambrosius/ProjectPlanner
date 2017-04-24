package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

class Activity {

    private String projectName;
    private String activityName;
    private List<String> participants = new ArrayList<>();

    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    Activity(String activityName, String projectName) {
        this.projectName = projectName;
        this.activityName = activityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getActivityName() {
        return activityName;
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



    /*
    Integer getWeeksToDeadline() {

    }

    void editWorkHours(Double hours) {

    }
    */
}
