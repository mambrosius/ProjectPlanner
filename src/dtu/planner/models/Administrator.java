package dtu.planner.models;

import dtu.planner.exceptions.CustomException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Administrator {

    private DateServer date = new DateServer();
    private ProjectPlanner model;

    public Administrator(ProjectPlanner model) {
        this.model = model;
    }

    public void registerProject(String name) throws CustomException {
        if (name != null)
            if (name.equals(""))
                throw new CustomException("Missing input");
            else if (getProjects().putIfAbsent(name, new Project(generateProjectNumber(), name)) != null)
                throw new CustomException(name + " already exists");
    }

    public void unregisterProject(String name, int option) {
        if (option == JOptionPane.YES_OPTION)
            getProjects().remove(name);
    }

    public void registerDeveloper(String initials) throws CustomException {
        if (initials != null)
            if (initials.equals(""))
                throw new CustomException("Missing input");
            else if (getDevelopers().putIfAbsent(initials, new Developer(initials)) != null)
                throw new CustomException(initials + " already exists");
    }

    public void unregisterDeveloper(String initials, int option) {
        if (option == JOptionPane.YES_OPTION)
            getDevelopers().remove(initials);
    }

    public void assignDeveloper(Object initials, Object project) throws CustomException {
        //String initials = showInputBoxDialog(getAvailableDevelopers(project));
        if (initials != null) {
            getProject(project.toString()).addDeveloper(getDeveloper(initials.toString()));
        }
    }

    public void assign(Object project, Object initials, boolean manager) throws CustomException {

        assignDeveloper(initials, project);
        if (manager) {
            assignManager(project.toString(), initials.toString());
        }

    }

    public void unassignDeveloper(String initials, String project) throws CustomException {
        if (initials != null) {
            if (getProject(project).hasManager() && getProject(project).getManager().equals(initials))
                model.removeManager(initials, project);
            getProject(project).removeDeveloper(initials);

            //if (showConfirmDialog("unassign " + initials + "?") == JOptionPane.YES_OPTION) {
                //if (getProject(project).getManager() != null & initials.equals(getProject(project).getManager()));//.equals(initials))
                  //  throw new CustomException("cannot unassign " + initials + " when manager");
            //}
        }
    }

    public void unassignManager(String project) throws CustomException {
        String initials = getProject(project).getManager();
        if (initials == null)
            throw new CustomException("project has no manager");
        if (showConfirmDialog("unassign " + initials + " as manager?") == JOptionPane.YES_OPTION) {
            model.removeManager(initials, project);
        }
    }

    public void assignManager(String project, String initials) throws CustomException {
        if (getProject(project).getManager() != null)
            throw new CustomException("manager is already assigned");

        //if (showConfirmDialog("assign " + initials + " as manager?") == JOptionPane.YES_OPTION)
        model.addManager(initials, project);
    }

    private String generateProjectNumber() {
        String year = Integer.toString(date.get().get(Calendar.YEAR));
        String numberAsString = String.valueOf(model.incrementProjectCount());
        StringBuilder sb = new StringBuilder();
        while (sb.length() + numberAsString.length() < 6)
            sb.append('0');
        sb.append(model.getProjectCount());
        return year + "." + sb;
    }

    private Project getProject(String name) {
        return getProjects().get(name);
    }

    private Developer getDeveloper(String initials) {
        return getDevelopers().get(initials);
    }

    public Object[] getAvailableDevelopers(Object project) { // throws CustomException
        List<String> availableDevelopers = new ArrayList<>(getDevelopers().keySet());
        if (project != null)
            availableDevelopers.removeAll(getProject(project.toString()).getDeveloperMap().keySet());
        //if (availableDevelopers.size() == 0)
            //throw new CustomException("could not find any developers");
        return  availableDevelopers.toArray();
    }

    public Object[] getAssignedDevelopers(String project) { //} throws CustomException {
        //if (getProject(project).getDeveloperMap().isEmpty())
          //  throw new CustomException("could not find any developers");
        return getProject(project).getDeveloperMap().keySet().toArray();
    }

    public DateServer getDateServer() {
        return model.getDateServer();
    }

    public void updateProjectTable(DefaultTableModel projectModel) {
        projectModel.setDataVector(Project.getData(getProjects()), Project.getColumnNames());
    }

    public void updateDeveloperTable(DefaultTableModel developerModel) {
        developerModel.setDataVector(Developer.getData(getDevelopers()), Developer.getColumnNames());
    }

    private int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
    }

    public Map<String, Project> getProjects() {
        return model.getProjectMap();
    }

    public Map<String, Developer> getDevelopers() {
        return model.getDeveloperMap();
    }

    public void register(Object selectedItem, String id) throws CustomException {
        if (selectedItem.equals("project"))
            registerProject(id);
        else
            registerDeveloper(id);
    }

    public void unregister(Object selectedItem, String id) throws CustomException{
        if (id == null)
            throw new CustomException("select a " + selectedItem);
        if (selectedItem.equals("project"))
            unregisterProject(id, 0);
        else
            unregisterDeveloper(id,0);
    }
}
