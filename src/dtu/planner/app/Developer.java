package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Developer {

    Manager asManager;

    private String initials;
    //private Double checkIn;
    //private Double checkOut;
    //private Double unregisteredHours;
    //private List<Absence> absences;
    private List<String> activities = new ArrayList<>();

    Developer(String initials) {
        this.initials = initials;
    }


    String getInitials() {
        return initials;
    }

    void appointManager(String projectName) {
        asManager = new Manager(projectName);
        app.getProjectBy(projectName).setManager(this.getInitials());
    }

    void reassignManagerTitle() {
        app.getProjectBy(asManager.getProjectName()).setManager(null);
        asManager = null;
    }

    Boolean isManager() {
        return asManager != null;
    }

    Object[][] getActivityData() {

        Object[][] data = new Object[activities.size()][1];

        for (int i = 0; i < activities.size(); i++) {
            data[i][0] = activities.get(i);
        }
        return data;
    }

    void addActivity(String activityName) {
        this.activities.add(activityName);
    }

    void removeActivity(String activityName) {
        this.activities.remove(activityName);
    }

    public List<String> getActivities() {
        return activities;
    }


    /*
    Boolean isAbsent(Calendar date) {

    }

    void registerAbsence(AbsenceType type, Calendar date) {

    }

    void logActivity(Activity activity, Double hours) {

    }

    void seekAssistance(Activity activity, Developer initial ) {

    }

    Boolean assistanceAccept(Activity activity) {

    }
    */
}
