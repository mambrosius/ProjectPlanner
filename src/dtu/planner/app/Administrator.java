package dtu.planner.app;

import static dtu.planner.app.ProjectPlanner.app;

class Administrator {

    void registerProject(String name) {
        app.projects.add(new Project(name));
    }

    void unregisterProject(String name) {
        app.projects.remove(app.getProjectBy(name));
    }

    void registerDeveloper(String initials) {
        app.developers.add(new Developer(initials));
    }

    void unregisterDeveloper(String initials) {
        app.developers.remove(app.getDeveloperBy(initials));
    }
}
