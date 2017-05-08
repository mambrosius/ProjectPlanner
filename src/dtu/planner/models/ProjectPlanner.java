package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import dtu.planner.ui.AdministratorUi;
import dtu.planner.ui.DeveloperUi;
import dtu.planner.ui.ManagerUi;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProjectPlanner {

    private int projectCount = 0;

    private DateServer dateServer = new DateServer();

    private Map<String, Project> projectMap = new HashMap<>();
    private Map<String, Manager> managerMap = new HashMap<>();
    private Map<String, Developer> developerMap = new HashMap<>();

    public Boolean adminLogin(char[] password) {
        Boolean login = (Arrays.equals(password, "admin".toCharArray()));
        if (login) {
            AdministratorUi adminUi = new AdministratorUi(this);
            adminUi.setVisible(true);
        }
        return login;
    }

    public Boolean devLogin(String initials) {
        Boolean manLogin = managerMap.containsKey(initials);
        Boolean devLogin = developerMap.containsKey(initials);
        if (manLogin) {
            ManagerUi manUi = new ManagerUi(managerMap.get(initials), this);
            manUi.setVisible(true);
        } else if (devLogin) {
            DeveloperUi devUi = new DeveloperUi(developerMap.get(initials), this);
            devUi.setVisible(true);
        }
        return manLogin || devLogin;
    }

    public boolean addProjectIfAbsent(String name) {
        return projectMap.putIfAbsent(name, new Project(generateProjectNumber(), name)) == null;
    }

    public void removeProject(String projectName) {
        projectMap.remove(projectName);
    }

    public Boolean addDeveloperIfAbsent(String initials) {
        return developerMap.putIfAbsent(initials, new Developer(initials)) == null;
    }

    public void removeDeveloper(String initials) {
        developerMap.remove(initials);
    }

    public void addManager(String initials, String project) {
        managerMap.putIfAbsent(initials, new Manager(initials));
        managerMap.get(initials).addResp(projectMap.get(project));
    }

    public void removeManager(String initials, String project) {
        managerMap.get(initials).removeResp(project);
        if (managerMap.get(initials).getResps().length == 0)
            managerMap.remove(initials);
    }

    private String generateProjectNumber() {
        String year = Integer.toString(dateServer.get(Calendar.YEAR)).substring(2);
        String numberAsString = String.valueOf(++projectCount);
        StringBuilder sb = new StringBuilder();
        while (sb.length() + numberAsString.length() < 6)
            sb.append('0');
        sb.append(projectCount);
        return year.concat(sb.toString());
    }

    public DateServer getDateServer() {
        return dateServer;
    }

    public Developer getDeveloper(String initials) {
        return developerMap.get(initials);
    }

    public Map<String, Project> getProjectMap() {
        return projectMap;
    }

    public Map<String, Developer> getDeveloperMap() {
        return developerMap;
    }
}
