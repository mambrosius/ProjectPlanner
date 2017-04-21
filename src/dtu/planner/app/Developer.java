package dtu.planner.app;

import java.util.ArrayList;
import java.util.List;

import static dtu.planner.app.ProjectPlanner.app;

class Developer {

    private String initials;
    Manager asManager;
    //private Double checkIn;
    //private Double checkOut;
    //private Double unregisteredHours;
    //private List<Absence> absences;
    private List<String> activityNames = new ArrayList<>();


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

    void assignActivity(String activity) {
        activityNames.add(activity);
    }

    void reassignActivity(String activity) {
        activityNames.remove(activity);
    }

    Object[][] getActivityData() {

        Object[][] data = new Object[activityNames.size()][1];

        for (int i = 0; i < activityNames.size(); i++) {
            data[i][0] = activityNames.get(i);
        }
        return data;
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
