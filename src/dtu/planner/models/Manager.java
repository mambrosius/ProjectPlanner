package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import dtu.planner.ui.AdministratorUi;
import dtu.planner.ui.DeveloperUi;
import dtu.planner.ui.ManagerUi;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Manager {

    private DeveloperUi devUi;
    private AdministratorUi adminUi;

    private ProjectPlanner model;
    private String initials;

    private Map<String, Project> respMap = new HashMap<>();

    Manager(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public void setProjectPlanner(ProjectPlanner model) {
        this.model = model;
    }

    public ProjectPlanner getProjectPlanner() {
        return model;
    }

    private Project getResp(String project) {
        return respMap.get(project);
    }

    public Object[] getResps() {
        return respMap.keySet().toArray();
    }

    void addResp(Project project) {
        respMap.putIfAbsent(project.getName(), project);
        respMap.get(project.getName()).setManager(initials);
    }

    void removeResp(String project) {
        getResp(project).setManager(null);
        respMap.remove(project);
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
        getResp(selectedResp).removeActivity(activityName);
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

    public void setEstimatedHours(String input, String name, String project) throws CustomException {
        if (!input.matches("[0.-9.]+"))
            throw new CustomException("invalid input");
        getActivity(name, project).setEstimatedHours(Double.parseDouble(input));
    }

    public double getEstimatedHours(String name, String project) {
        return getActivity(name, project).getEstimatedHours();
    }

    // TODO: do cleanup below

    private Activity getActivity(String activity, String project) {
        return getResp(project).getActivityMap().get(activity);
    }

    public boolean hasActivity(String project) {
        return !getResp(project).getActivityMap().isEmpty();
    }

    public Object[] getActivities(String selectedResp) {
        return getResp(selectedResp).getActivityMap().keySet().toArray();
    }

    public Object[][] getActivityData(String project) {
        return Activity.getData(respMap.get(project).getActivityMap());
    }

    public boolean hasDeveloper(String project) {
        return !getResp(project).getDeveloperMap().isEmpty();
    }

    private Developer getDeveloper(String initials, String project) {
        return getResp(project).getDeveloperMap().get(initials);
    }

    public Object[][] getDeveloperData(String project) {
        return Developer.getData(respMap.get(project).getDeveloperMap());
    }

    public Object[] getAvailableDevs(String activity, String project) throws CustomException {
        List<String> availableDevelopers = new ArrayList<>(getResp(project).getDeveloperMap().keySet());
        if (activity != null)
            availableDevelopers.removeAll(getActivity(activity, project).getDeveloperMap().keySet());
        return  availableDevelopers.toArray();
    }

    public Object[] getAssignedDevs(String activityName, String resp) throws CustomException {
        return getActivity(activityName, resp).getDeveloperMap().keySet().toArray();
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

    // TODO: implement if time
    /*
    void setStart(String activity, Calendar date) {

    }

    void setEnd(String activity, Calendar data) {

    }

    void generateRapport(String project) {

    }
    */
}