package dtu.planner.app;

import static dtu.planner.app.ProjectPlanner.app;

class Administrator {

    Boolean registerProject(String name) {
        if (app.getProject(name) == null)
            return app.getProjects().add(new Project(name));
        return false;
    }

    void unregisterProject(String name) {
        app.getProjects().remove(app.getProject(name));
    }

    Boolean registerDeveloper(String initials) {
        if (app.getDeveloper(initials) == null)
            return app.getDevelopers().add(new Developer(initials));
        return false;
    }

    void unregisterDeveloper(String initials) {
        app.getDevelopers().remove(app.getDeveloper(initials));
    }
}
