package dtu.planner.models;

import dtu.planner.ui.AdministratorUi;
import dtu.planner.ui.DeveloperUi;
import dtu.planner.ui.ManagerUi;
import dtu.planner.ui.ProjectPlannerUi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProjectPlanner {

    private int projectCount = 0;

    private Map<String, Project> projectMap = new HashMap<>();
    private Map<String, Manager> managerMap = new HashMap<>();
    private Map<String, Developer> developerMap = new HashMap<>();

    public Boolean adminLogin(char[] password) {
        if (Arrays.equals(password, "admin".toCharArray())) {
            AdministratorUi adminUi = new AdministratorUi(this);
            adminUi.setVisible(true);
            return true;
        } else
            return false;
    }

    public Boolean devLogin(String initials) {
        if (managerMap.containsKey(initials)) {
            ManagerUi manUi = new ManagerUi(managerMap.get(initials), this);
            manUi.setVisible(true);
            return true;
        } else if (developerMap.containsKey(initials)) {
            DeveloperUi devUi = new DeveloperUi(developerMap.get(initials));
            devUi.setVisible(true);
            return true;
        } else
            return false;
    }

    public Map<String, Project> getProjectMap() {
        return projectMap;
    }

    public Map<String, Manager> getManagerMap() {
        return managerMap;
    }

    public Map<String, Developer> getDeveloperMap() {
        return developerMap;
    }

    public Developer getDeveloper(String initials) {
        return developerMap.get(initials);
    }

    public int getProjectCount() {
        return projectCount;
    }

    public int incrementProjectCount() {
        return ++projectCount;
    }
}
