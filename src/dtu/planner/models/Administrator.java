package dtu.planner.models;

import dtu.planner.exceptions.CustomException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Administrator {

    private ProjectPlanner model;

    public Administrator(ProjectPlanner model) {
        this.model = model;
    }

    public void registerProject(String name) throws CustomException {
        if (name != null)
            if (name.equals(""))
                throw new CustomException("Missing input");
            else if (model.getProjectMap().putIfAbsent(name, new Project("1", name)) != null)
                throw new CustomException(name + " already exists");
    }

    public void unregisterProject(String name, int option) {
        if (option == JOptionPane.YES_OPTION)
            model.getProjectMap().remove(name);
    }

    public void registerDeveloper(String initials) throws CustomException {
        if (initials != null)
            if (initials.equals(""))
                throw new CustomException("Missing input");
            else if (model.getDeveloperMap().putIfAbsent(initials, new Developer(initials)) != null)
                throw new CustomException(initials + " already exists");
    }

    public void unregisterDeveloper(String initials, int option) {
        if (option == JOptionPane.YES_OPTION)
            model.getDeveloperMap().remove(initials);
    }

    public Boolean assignDeveloper(String initials, String project) {
        if (initials == null)
            return false;
        getProject(project).addDeveloper(getDeveloper(initials));
        return (getProject(project).getDeveloperMap().size() == 1);
    }

    public void unassignDeveloper(String initials, String project, int option) throws CustomException {
        if (option == JOptionPane.YES_OPTION)
            if (initials != null) {
                if (hasManager(project) && getProject(project).getManager().equals(initials))
                    throw new CustomException("cannot unassign " + initials + " when manager");
                getProject(project).removeDeveloper(initials);
            }
    }

    public void assignManager(String initials, String project, int option) {
        if (option == JOptionPane.YES_OPTION)
            if (!hasManager(project)) {
                model.getManagerMap().putIfAbsent(initials, new Manager(initials));
                getManager(initials).addResp(getProject(project));
            }
    }

    public void unassignManager(String project, int option) throws CustomException {
        if (option == JOptionPane.YES_OPTION) {
            if (!hasManager(project))
                throw new CustomException("project has no manager");
            String initials = getProject(project).getManager();
            model.getManagerMap().get(initials).removeResp(project);
            if (getManager(initials).getRespMap().isEmpty())
                model.getManagerMap().remove(initials);
        }
    }

    public Object[] getAvailableDevelopers(String project) throws CustomException {
        List<String> availableDevelopers = new ArrayList<>(model.getDeveloperMap().keySet());
        availableDevelopers.removeAll(getProject(project).getDeveloperMap().keySet());
        if (availableDevelopers.size() == 0)
            throw new CustomException("could not find any developers");
        return  availableDevelopers.toArray();
    }

    /*
    private String generateProjectNumber() {

        String year = Integer.toString(app.date.get().get(Calendar.YEAR));
        String numberAsString = String.valueOf(++projectCount);
        StringBuilder sb = new StringBuilder();

        while (sb.length() + numberAsString.length() < 6)
            sb.append('0');
        sb.append(projectCount);

        return year + "-" + sb;
    }
    }*/

    public boolean haveProjects() {
        return !model.getProjectMap().isEmpty();
    }

    public boolean haveDevelopers() {
        return !model.getDeveloperMap().isEmpty();
    }

    private Project getProject(String name) {
        return model.getProjectMap().get(name);
    }

    private Developer getDeveloper(String initials) {
        return model.getDeveloperMap().get(initials);
    }

    private Manager getManager(String initials) {
        return model.getManagerMap().get(initials);
    }

    public Object[][] getProjectData() {
        return Project.getData(model.getProjectMap());
    }

    public Object[] getDevelopers(String project) throws CustomException {
        if (model.getProjectMap().get(project).getDeveloperMap().isEmpty())
            throw new CustomException("could not find any developers");
        return model.getProjectMap().get(project).getDeveloperMap().keySet().toArray();
    }

    public Object[][] getDeveloperData() {
        return Developer.getData(model.getDeveloperMap());
    }


    public String getManagerName(String project) {
        return model.getProjectMap().get(project).getManager();
    }

    public Boolean hasManager(String project) {
        return getProject(project).hasManager();
    }
}
