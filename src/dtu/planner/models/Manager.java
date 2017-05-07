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

    public void register(String selectedResp, String activityName) throws CustomException {
        if (activityName.equals(""))
            throw new CustomException("no input");
        if (!respMap.get(selectedResp).addActivityIfAbsent(activityName))
            throw new CustomException(activityName + " already exists");
    }

    public void unregister(String selectedResp, String activityName) throws CustomException {
        if (activityName == null)
            throw new CustomException("select an activity");
        getProject(selectedResp).removeActivity(activityName);
    }

    public void assign(String resp, String activityName, String initials) throws CustomException {
        if (resp == null || activityName == null || initials == null)
            throw new CustomException("select an activity and a developer");
        getActivity(activityName, resp).addDeveloper(getDeveloper(initials, resp));
    }

    public void unassign(String resp, String activityName, String initials) throws CustomException {
        if (resp == null || activityName == null || initials == null)
            throw new CustomException("select a project and a developer");
        getActivity(activityName, resp).removeDeveloper(initials);
    }

    public void setEstimatedHours(Double hours, String name, String project) {
        getActivity(name, project).setEstimatedHours(hours);
    }

    // TODO: do cleanup below

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

    public void refreshTab(int index, ManagerUi manUi) {
        if (index == 0)
            manUi.updateView();
        else if (index == 1)
            devUi.update();
        else
            adminUi.updateView();
    }

    public Object[] getActivities(String selectedResp) {
        return respMap.get(selectedResp).getActivityMap().keySet().toArray();
    }

    public Object[] getAvailableDevs(String activity, String project) throws CustomException {
        List<String> availableDevelopers = new ArrayList<>(getProject(project).getDeveloperMap().keySet());
        if (activity != null)
            availableDevelopers.removeAll(getActivity(activity, project).getDeveloperMap().keySet());
        return  availableDevelopers.toArray();
    }

    public Object[] getAssignedDevs(String activityName, String resp) throws CustomException {
        return getProject(resp).getActivityMap().get(activityName).getDeveloperMap().keySet().toArray();
    }
}
