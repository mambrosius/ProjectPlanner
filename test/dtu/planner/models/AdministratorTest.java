package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.*;

public class AdministratorTest {

    private ProjectPlanner pp = new ProjectPlanner();
    private Administrator admin = new Administrator(pp);

    private String PROJECT = "project";
    private String DEVELOPER = "developer";

    private String projectName = "project1";
    private String devInitials = "dev1";
    private String newComer = "roki";

    @Test
    public void register() throws Exception {
        try {
            assertTrue(admin.getProjects().length == 0);
            assertTrue(admin.getDevelopers().length == 0);

            admin.register(PROJECT, projectName);
            assertTrue(admin.getProjects().length == 1);
            assertTrue(admin.getDevelopers().length == 0);
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.register(DEVELOPER, devInitials);
            assertTrue(admin.getProjects().length == 1);
            assertTrue(admin.getDevelopers().length == 1);
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.register(PROJECT, projectName);
            fail();
        } catch (CustomException ex) {
            assertEquals(projectName + " already exists", ex.getMessage());
        }

        try {
            admin.register(DEVELOPER, devInitials);
            fail();
        } catch (CustomException ex) {
            assertEquals(devInitials + " already exists", ex.getMessage());
        }

        try {
            admin.register(PROJECT, "");
            fail();
        } catch (CustomException ex) {
            assertEquals("no input", ex.getMessage());
        }

        try {
            admin.register(DEVELOPER, "");
            fail();
        } catch (CustomException ex) {
            assertEquals("no input", ex.getMessage());
        }
    }

    @Test
    public void unregister() throws Exception {
        register();

        try {
            admin.unregister(PROJECT, projectName);
            assertTrue(admin.getProjects().length == 0);
            assertTrue(admin.getDevelopers().length == 1);
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.unregister(DEVELOPER, devInitials);
            assertTrue(admin.getProjects().length == 0);
            assertTrue(admin.getDevelopers().length == 0);
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.unregister(PROJECT, null);
            fail();
        } catch (CustomException ex) {
            assertEquals("select a " + PROJECT, ex.getMessage());
        }

        try {
            admin.unregister(DEVELOPER, null);
            fail();
        } catch (CustomException ex) {
            assertEquals("select a " + DEVELOPER, ex.getMessage());
        }
    }

    @Test
    public void assign() throws Exception {
        register();

        try {
            admin.register(DEVELOPER, newComer);
            assertEquals(2, admin.getDevelopers().length);
        } catch (CustomException ex) {
            fail();
        }

        assertEquals(0, admin.getAssignedDevelopers(projectName).length);
        assertFalse(admin.getProject(projectName).hasManager());

        try {
            admin.assign(projectName, newComer, false);
            assertEquals(1, admin.getAssignedDevelopers(projectName).length);
            assertFalse(admin.getProject(projectName).hasManager());
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.assign(projectName, devInitials, true);
            assertEquals(2, admin.getAssignedDevelopers(projectName).length);
            assertTrue(admin.getProject(projectName).hasManager());
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.assign(projectName, null, true);
            fail();
        } catch (CustomException ex) {
            assertEquals("select a project and a developer", ex.getMessage());
        }

        try {
            admin.assign(null, devInitials, true);
            fail();
        } catch (CustomException ex) {
            assertEquals("select a project and a developer", ex.getMessage());
        }

        try {
            admin.assign(projectName, newComer, true);
            fail();
        } catch (CustomException ex) {
            assertEquals("manager is already assigned", ex.getMessage());
        }
    }

    @Test
    public void unassign() throws Exception {
        assign();

        admin.unassign(projectName, newComer);
        assertEquals(1, admin.getAssignedDevelopers(projectName).length);
        assertTrue(admin.getProject(projectName).hasManager());

        admin.unassign(projectName, devInitials);
        assertEquals(0, admin.getAssignedDevelopers(projectName).length);
        assertFalse(admin.getProject(projectName).hasManager());
    }

    @Test
    public void getAvailableDevelopers() throws Exception {

        admin.register(PROJECT, projectName);

        for (int i = 0; i < 5; i++) {
            String initials = "dev" + i;
            admin.register(DEVELOPER, initials);
        }

        try {
            admin.assign(projectName, "dev1", true);
            assertEquals(4, admin.getAvailableDevelopers(projectName).length);

            admin.register(DEVELOPER, newComer);
            assertEquals(5, admin.getAvailableDevelopers(projectName).length);

            admin.assign(projectName, "dev3", false);
            admin.assign(projectName, "dev0", false);
            assertEquals(3, admin.getAvailableDevelopers(projectName).length);
        } catch (CustomException ex) {
            fail();
        }
    }

    @Test
    public void updateProjectTable() throws Exception {

    }

    @Test
    public void updateDeveloperTable() throws Exception {

    }

    /*
    public void updateProjectTable(DefaultTableModel projectModel) {
        projectModel.setDataVector(Project.getData(model.getProjectMap()), Project.getColumnNames());
    }

    public void updateDeveloperTable(DefaultTableModel developerModel) {
        developerModel.setDataVector(Developer.getData(model.getDeveloperMap()), Developer.getColumnNames());
    }
    */

    @Test
    public void getDateServer() throws Exception {
        // TODO: implement mock object
    }
}