package dtu.planner.app;

import dtu.planner.ui.ProjectPlannerUi;

public class ProjectPlannerApplication {

    public static void main(String[] args) {
        ProjectPlannerUi ui = new ProjectPlannerUi();
        ui.startApplication();
        ui.setVisible(true);
    }
}