package dtu.planner.app;

import dtu.planner.app.exceptions.NotFoundException;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

class ProjectTestSetup {
    Administrator ppApp = new Administrator();
    Project bachelorProject = null;
    Project plannerProject = null;
    Developer dev1 = null;
    Developer dev2 = null;
    /*
    @Before
    public void setup() throws NotFoundException {

        ppApp.registerDeveloper("MAA");
        ppApp.registerDeveloper("BA");
        assertEquals(2, ppApp.getDevelopers().size());

        ppApp.registerProject("Project Planner");
        ppApp.registerProject("Bachelor Thesis");
        assertEquals(2, ppApp.getNamesOfProjects().size());

        try {
            bachelorProject = ppApp.getProjectBy("Bachelor Thesis");
        } catch (NotFoundException e) {
            e.getMessage();
        }

        try {
            plannerProject = ppApp.getProjectBy("Project Planner");
        } catch (NotFoundException e) {
            e.getMessage();
        }

        try {
            dev1 = ppApp.getDeveloperBy("MAA");
        } catch (NotFoundException e) {
            e.getMessage();
        }

        try {
            dev2 = ppApp.getDeveloperBy("BA");
        } catch (NotFoundException e) {
            e.getMessage();
        }
    }
    */
}
