package dtu.planner.models;

import dtu.planner.exceptions.CustomException;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Administrator {

    private ProjectPlanner model;

    public Administrator(ProjectPlanner model) {
        this.model = model;
    }

    public void register(Object selectedItem, String id) throws CustomException {
        if (id.equals(""))
            throw new CustomException("no input");
        if (selectedItem.equals("project")) {
            if (!model.addProjectIfAbsent(id))
                throw new CustomException(id + " already exists");
        } else {
            if (!model.addDeveloperIfAbsent(id))
                throw new CustomException(id + " already exists");
        }
    }

    public void unregister(Object selectedItem, Object id) throws CustomException{
        if (id == null)
            throw new CustomException("select a " + selectedItem);
        if (selectedItem.equals("project"))
            model.removeProject(id.toString());
        else
            model.removeDeveloper(id.toString());
    }

    public void assign(String project, String initials, boolean manager) throws CustomException {
        if (project == null || initials == null)
            throw new CustomException("select a project and a developer");
        getProject(project).addDeveloper(model.getDeveloper(initials));

        if (manager) {
            if (getProject(project).getManager() != null)
                throw new CustomException("manager is already assigned");
            model.addManager(initials, project);
        }
    }

    public void unassign(String project, String initials) {
        if (initials != null) {
            if (getProject(project).hasManager() && getProject(project).getManager().equals(initials))
                model.removeManager(initials, project);
            getProject(project).removeDeveloper(initials);
        }
    }

    public DateServer getDateServer() {
        return model.getDateServer();
    }

    public Project getProject(String name) {
        return model.getProjectMap().get(name);
    }

    public Object[] getProjects() {
        return model.getProjectMap().keySet().toArray();
    }


    public Object[] getDevelopers() {
        return model.getDeveloperMap().keySet().toArray();
    }

    public Object[] getAvailableDevelopers(String project) {
        List<String> availableDevelopers = new ArrayList<>(model.getDeveloperMap().keySet());
        if (project != null)
            availableDevelopers.removeAll(getProject(project).getDeveloperMap().keySet());
        return  availableDevelopers.toArray();
    }

    public Object[] getAssignedDevelopers(String project) {
        return getProject(project).getDeveloperMap().keySet().toArray();
    }

    public void updateProjectTable(DefaultTableModel projectModel) {
        projectModel.setDataVector(Project.getData(model.getProjectMap()), Project.getColumnNames());
    }

    public void updateDeveloperTable(DefaultTableModel developerModel) {
        developerModel.setDataVector(Developer.getData(model.getDeveloperMap()), Developer.getColumnNames());
    }
}
