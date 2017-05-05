package dtu.planner.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Developer {

    private String initials;

    private Map<String, Activity> activityMap = new HashMap<>();
    // private Map<String, Absence> absences;

    private static final String[] columnNames = new String[]{"initials", "activities", "status"};

    //private Double checkIn;
    //private Double checkOut;
    //private Double unregisteredHours;

    public Developer(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    /*

    void assignActivity(Activity activity) {
        activities.add(activity);
    }

    void unassignActivity(Activity activity) {
        activities.remove(activity);
    }

    void logActivity(String activity, Double hours) {
        getActivity(activity).setHoursUsed(hours);
    }

    /*
    Boolean isAbsent(Calendar date) {

    }

    void registerAbsence(AbsenceType type, Calendar date) {

    }

    void seekAssistance(Activity activity, Developer initial ) {

    }

    Boolean assistanceAccept(Activity activity) {

    }
    */

    public static Object[][] getData(Map<String, Developer> developerMap) {

        List<Developer> developers = new ArrayList<>(developerMap.values());
        Object[][] developerData = new Object[developers.size()][columnNames.length];

        for (int i = 0; i < developers.size(); i++) {
            developerData[i][0] = developers.get(i).getInitials();
            developerData[i][1] = developers.get(i).getActivityMap().size();
            //developerData[i][2] = developers.get(i).isManager();
        }

        return developerData;
    }

    public Map<String, Activity> getActivityMap() {
        return activityMap;
    }
}
