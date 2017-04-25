package dtu.planner.app;

import java.util.*;

import static dtu.planner.app.ProjectPlanner.app;

class Manager {

    private String initials;

    Manager(String initials) {
        this.initials = initials;
    }

    String getInitials() {
        return initials;
    }

    void assignActivity(String initials, String activity, String project) {
        if (app.getProject(project).getActivity(activity) != null) {
            app.getProject(project).getActivity(activity).assignDeveloper(initials);
        }
    }

    void unassignActivity(String initials, String activity, String project) {
        app.getProject(project).getActivity(activity).unassignDeveloper(initials);
    }

    void setStart(String activity, Calendar date) {

    }

    void setEnd(String activity, Calendar data) {

    }

    void setWorkHours(String activity, Double hours) {

    }

    void generateRapport(String project) {

    }


    /*
    public Developer getDeveloper() {
        return developer;
    }
    */
}
