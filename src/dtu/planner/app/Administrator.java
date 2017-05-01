package dtu.planner.app;

import java.util.Calendar;

import static dtu.planner.app.ProjectPlanner.app;

class Administrator {

    private int projectCount;

    public Administrator() {
        this.projectCount = 0;
    }

    Boolean registerProject(String name) {

        if (app.getProject(name) == null) {
            String projectNo = generateProjectNumber();
            return app.getProjects().add(new Project(projectNo, name));
        }

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

    private String generateProjectNumber() {

        String year = Integer.toString(app.date.get().get(Calendar.YEAR));
        String numberAsString = String.valueOf(++projectCount);
        StringBuilder sb = new StringBuilder();

        while (sb.length() + numberAsString.length() < 6)
            sb.append('0');
        sb.append(projectCount);

        return year + "-" + sb;
    }
}
