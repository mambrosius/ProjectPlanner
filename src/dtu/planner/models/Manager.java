package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import dtu.planner.ui.AdministratorUi;
import dtu.planner.ui.DeveloperUi;
import dtu.planner.ui.ManagerUi;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Manager {

    private DeveloperUi devUi;
    private AdministratorUi adminUi;

    private ProjectPlanner model;
    private String initials;

    private Map<String, Project> respMap = new HashMap<>();

    public Manager(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public void setProjectPlanner(ProjectPlanner model) {
        this.model = model;
    }

    public void addActivity(String name, String project) throws CustomException {
        if (name != null)
            if (name.equals(""))
                throw new CustomException("Missing input");
            else if (getProject(project).addActivity(name) != null)
                throw new CustomException(name + " already exists");
    }

    public void removeActivity(String name, String project, int option) throws CustomException {
        if (option == JOptionPane.YES_OPTION)
            getProject(project).removeActivity(name);
    }

    public void assignActivity(String initials, String activity, String project) {
        if (initials != null)
            getActivity(activity, project).addDeveloper(getDeveloper(initials, project));
    }

    public void unassignDeveloper(String initials, String activity, String project) throws CustomException {
        if (initials != null)
            getActivity(activity, project).removeDeveloper(initials);
    }

    public void setEstimatedHours(Double hours, String name, String project) {
        getActivity(name, project).setEstimatedHours(hours);
    }

    /*
    void setStart(String activity, Calendar date) {

    }

    void setEnd(String activity, Calendar data) {

    }

    void generateRapport(String project) {

    }
    */

    public Map<String, Project> getRespMap() {
        return respMap;
    }

    public void addResp(Project project) {
        respMap.put(project.getName(), project);
        respMap.get(project.getName()).setManager(initials);
    }

    public void removeResp(String project) {
        getProject(project).setManager(null);
        respMap.remove(project);
    }



    private Developer getDeveloper(String initials, String project) {
        return getProject(project).getDeveloperMap().get(initials);
    }

    public Object[] getDevelopers(String project) throws CustomException {
        if (getProject(project).getDeveloperMap().isEmpty())
            throw new CustomException("could not find any developers");
        return getProject(project).getDeveloperMap().keySet().toArray();
    }

    public Object[] getAvailableDevelopers(String activity, String project) throws CustomException {

        List<String> availableDevelopers = new ArrayList<>(getProject(project).getDeveloperMap().keySet());
        availableDevelopers.removeAll(getActivity(activity, project).getDeveloperMap().keySet());
        if (availableDevelopers.size() == 0)
            throw new CustomException("could not find any developers");
        return  availableDevelopers.toArray();
    }

    private Activity getActivity(String activity, String project) {
        return getProject(project).getActivityMap().get(activity);
    }

    private Project getProject(String project) {
        return respMap.get(project);
    }

    public boolean hasDeveloper(String project) {
        return !getProject(project).getDeveloperMap().isEmpty();
    }

    public boolean hasActivity(String project) {
        return !getProject(project).getActivityMap().isEmpty();
    }


    public Object[][] getActivityData(String project) {
        return Activity.getData(respMap.get(project).getActivityMap());
    }

    public Object[][] getDeveloperData(String project) {
        return Developer.getData(respMap.get(project).getDeveloperMap());
    }

    public Component setupDeveloperTap() {
        devUi = new DeveloperUi(model.getDeveloper(initials), model);
        return devUi.getContentPane();
    }

    public Component setupAdminTap() {
        adminUi = new AdministratorUi(model);
        return adminUi.getContentPane();
    }

    public void dispose() {
        adminUi.dispose();
        devUi.dispose();
    }

    public Object[] getActivityDevelopers(String activity, String project) throws CustomException {
        Object[] developers = getActivity(activity, project).getDeveloperMap().keySet().toArray();
        if (developers.length == 0)
            throw new CustomException("could not find any developers");
        return developers;
    }

    public void refreshTab(int index, ManagerUi manUi) {
        if (index == 0)
            manUi.update();
        else if (index == 1)
            devUi.update();
        else
            adminUi.update();
    }
}
