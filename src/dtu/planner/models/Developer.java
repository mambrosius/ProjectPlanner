package dtu.planner.models;

import dtu.planner.exceptions.CustomException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Developer {

    private String initials;
    private ProjectPlanner model;

    private Map<String, Activity> activityMap = new HashMap<>();
    private Map<String, Activity> reqMap = new HashMap<>();
    // private Map<String, Absence> absences;

    private static final String[] columnNames = new String[]{"initials", "activities", "status"};

    //private Double checkIn;
    //private Double checkOut;
    //private Double unregisteredHours;

    public Developer(String initials) {
        this.initials = initials;
    }

    public void setProjectPlanner(ProjectPlanner model) {
        this.model = model;
    }

    public String getInitials() {
        return initials;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    void addActivity(Activity activity) {
        activityMap.put(activity.getName(), activity);
    }

    void removeActivity(String activity) {
        activityMap.remove(activity);
    }

    public void logActivity(String activity, Double hours) {
        activityMap.get(activity).setHoursUsed(hours);
    }

    public void seekAssistance(String activity, Developer dev) throws CustomException {
        if (dev.getReqMap().containsKey(activity))
            throw new CustomException("Request already sent");
        dev.addRequest(activityMap.get(activity));
    }

    public void addRequest(Activity activity) {
        reqMap.put(activity.getName(), activity);
    }

    public void replyReq(Activity activity, boolean accept) {
        if (activity != null) {
            if (accept)
                activity.addDeveloper(this);
            reqMap.remove(activity.getName());
        }
    }

    /*
    Boolean isAbsent(Calendar date) {

    }

    void registerAbsence(AbsenceType type, Calendar date) {

    }
    */

    public static Object[][] getData(Map<String, Developer> developerMap) {

        List<Developer> developers = new ArrayList<>(developerMap.values());
        Object[][] developerData = new Object[developers.size()][columnNames.length];

        for (int i = 0; i < developers.size(); i++) {
            developerData[i][0] = developers.get(i).getInitials();
            developerData[i][1] = developers.get(i).getActivityMap().size();
            //developerData[i][2] = developers.getDate(i).isManager();
        }

        return developerData;
    }

    public Map<String, Activity> getActivityMap() {
        return activityMap;
    }

    public Map<String, Activity> getReqMap() {
        return reqMap;
    }

    public boolean hasActivity() {
        return !activityMap.isEmpty();
    }

    public Object[][] getActivityData() {
        return Activity.getData(activityMap);
    }

    public Object[] getActivities() {
        return activityMap.keySet().toArray();
    }

    public Object[] getAvailableDevelopers(String activity) {
        List<Developer> availableDevelopers = new ArrayList<>(model.getDeveloperMap().values());
        availableDevelopers.removeAll(activityMap.get(activity).getDeveloperMap().values());
        for (Developer developer : availableDevelopers)
            if (developer.getReqMap().containsKey(activity))
                availableDevelopers.remove(developer);
            else
                developer.toString();
        return  availableDevelopers.toArray();
    }

    public String toString() {
        return getInitials();
    }
}
