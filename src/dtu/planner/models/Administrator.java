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
            else if (model.getProjectMap().putIfAbsent(name, new Project(generateProjectNumber(), name)) != null)
                throw new CustomException(name + " already exists");
    }

    public void unregisterProject(String name) {
        if (showConfirmDialog("unregister " + name + "?") == JOptionPane.YES_OPTION)
            model.getProjectMap().remove(name);
    }

    public void registerDeveloper(String initials) throws CustomException {
        if (initials != null)
            if (initials.equals(""))
                throw new CustomException("Missing input");
            else if (model.getDeveloperMap().putIfAbsent(initials, new Developer(initials)) != null)
                throw new CustomException(initials + " already exists");
    }

    public void unregisterDeveloper(String initials) {
        if (showConfirmDialog("unregister " + initials + "?") == JOptionPane.YES_OPTION)
            model.getDeveloperMap().remove(initials);
    }

    public void assignDeveloper(String project) throws CustomException {
        String initials = showInputBoxDialog("Select developer", getAvailableDevelopers(project));
        if (initials != null) {
            getProject(project).addDeveloper(getDeveloper(initials));
            if (getProject(project).getDeveloperMap().size() == 0)
                assignManager(project);
        }
    }

    public void unassignDeveloper(String project) throws CustomException {

        String initials = showInputBoxDialog("Select developer", getAssignedDevelopers(project));
        if (initials != null) {
            if (showConfirmDialog("unassign " + initials + "?") == JOptionPane.YES_OPTION) {
                if (getProject(project).getManager() != null & getProject(project).getManager().equals(initials))
                    throw new CustomException("cannot unassign " + initials + " when manager");
                getProject(project).removeDeveloper(initials);
            }
        }
    }

    public void assignManager(String project) throws CustomException {
        if (getProject(project).getManager() != null)
            throw new CustomException("manager is already assigned");
        String initials = showInputBoxDialog("Select developer", getAssignedDevelopers(project));
        if (showConfirmDialog("assign " + initials + " as manager?") == JOptionPane.YES_OPTION)
            model.addManager(initials, project);
    }

    public void unassignManager(String project) throws CustomException {
        String initials = getProject(project).getManager();
        if (initials == null)
            throw new CustomException("project has no manager");
        if (showConfirmDialog("unassign " + initials + " as manager?") == JOptionPane.YES_OPTION) {
            model.removeManager(initials, project);
        }
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
        return model.getProjectMap().get(name);
    }

    private Developer getDeveloper(String initials) {
        return model.getDeveloperMap().get(initials);
    }


    private Object[] getAvailableDevelopers(String project) throws CustomException {
        List<String> availableDevelopers = new ArrayList<>(model.getDeveloperMap().keySet());
        availableDevelopers.removeAll(getProject(project).getDeveloperMap().keySet());
        if (availableDevelopers.size() == 0)
            throw new CustomException("could not find any developers");
        return  availableDevelopers.toArray();
    }

    private Object[] getAssignedDevelopers(String project) throws CustomException {
        if (model.getProjectMap().get(project).getDeveloperMap().isEmpty())
            throw new CustomException("could not find any developers");
        return getProject(project).getDeveloperMap().keySet().toArray();
    }

    public DateServer getDateServer() {
        return model.getDateServer();
    }

    public void updateProjectTable(DefaultTableModel projectModel) {
        projectModel.setDataVector(Project.getData(model.getProjectMap()), Project.getColumnNames());
    }

    public void updateDeveloperTable(DefaultTableModel developerModel) {
        developerModel.setDataVector(Developer.getData(model.getDeveloperMap()), Developer.getColumnNames());
    }

    private int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
    }

    private String showInputBoxDialog(String message, Object[] keys) {
        return (String) JOptionPane.showInputDialog(null, message, "Select",
                JOptionPane.INFORMATION_MESSAGE, null, keys, "");
    }

    //public Map<String, Project> getProjects()
}
