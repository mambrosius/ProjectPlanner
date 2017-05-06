package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class AdministratorTest {

    private ProjectPlanner pp = new ProjectPlanner();
    private Administrator admin = new Administrator(pp);
    private String projectName = "project1";
    private String devInitials = "dev1";

    @Test
    public void registerProject() throws Exception {
        try {
            assertTrue(admin.getProjects().isEmpty());
            admin.registerProject(projectName);
            assertEquals(1, admin.getProjects().size());
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.registerProject(projectName);
            fail();
        } catch (CustomException ex) {
            assertEquals(projectName + " already exists", ex.getMessage());
        }
    }

    @Test
    public void unregisterProject() throws Exception {
        registerProject();

        admin.unregisterProject(projectName, JOptionPane.CLOSED_OPTION);
        assertEquals(1, admin.getProjects().size());

        admin.unregisterProject(projectName, JOptionPane.NO_OPTION);
        assertEquals(1, admin.getProjects().size());

        admin.unregisterProject(projectName, JOptionPane.YES_OPTION);
        assertTrue(admin.getProjects().isEmpty());
    }

    @Test
    public void registerDeveloper() throws Exception {
        try {
            assertTrue(admin.getDevelopers().isEmpty());
            admin.registerDeveloper(devInitials);
            assertEquals(1, admin.getDevelopers().size());
        } catch (CustomException ex) {
            fail();
        }

        try {
            admin.registerDeveloper(devInitials);
            fail();
        } catch (CustomException ex) {
            assertEquals(devInitials + " already exists", ex.getMessage());
        }
    }

    @Test
    public void unregisterDeveloper() throws Exception {
        registerDeveloper();

        admin.unregisterDeveloper(devInitials, JOptionPane.CLOSED_OPTION);
        assertEquals(1, admin.getDevelopers().size());

        admin.unregisterDeveloper(devInitials, JOptionPane.NO_OPTION);
        assertEquals(1, admin.getDevelopers().size());

        admin.unregisterDeveloper(devInitials, JOptionPane.YES_OPTION);
        assertTrue(admin.getDevelopers().isEmpty());
    }


    /*
    @Test
    public void assignDeveloper() throws Exception {
        registerProject();
        registerDeveloper();

        admin.assignDeveloper(projectName);
    }*/

    @Test
    public void unassignDeveloper() throws Exception {
    }

    @Test
    public void assignManager() throws Exception {
    }

    @Test
    public void unassignManager() throws Exception {
    }

    @Test
    public void getDateServer() throws Exception {
    }

    @Test
    public void updateProjectTable() throws Exception {
    }

    @Test
    public void updateDeveloperTable() throws Exception {
    }

    @Test
    public void getProjects() throws Exception {
    }

    @Test
    public void getDevelopers() throws Exception {
    }

}