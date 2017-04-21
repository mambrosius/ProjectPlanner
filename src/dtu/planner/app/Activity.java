package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

class Activity {

    private String name;
    private List<String> participants = new ArrayList<>();
    //private startWeek;
    //private endWeek;
    //private workHoursEstimated;
    //private workHoursRemaining;

    Activity(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<String> getParticipants() {
        return participants;
    }

    void addParticipant(String initials) {
        participants.add(initials);
    }

    void removeParticipant(String initials) {
        participants.remove();
    }

    /*
    Integer getWeeksToDeadline() {

    }

    void editWorkHours(Double hours) {

    }
    */
}
