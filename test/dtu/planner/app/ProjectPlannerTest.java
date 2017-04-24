package dtu.planner.app;

import dtu.planner.app.exceptions.NotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectPlannerTest extends ProjectPlannerTestSetup {
/*
    @Test
    public void getProjectName() throws Exception {
        assertEquals("Software Huset", ppApp.getProjectName());
    }

    // <editor-fold desc = "developer related tests">
    @Test
    public void registerDeveloper() throws Exception {
        assertTrue(ppApp.getInitialsOfParticipants().isEmpty());

        ppApp.registerDeveloper("MAA");
        assertEquals(1, ppApp.getInitialsOfParticipants().size());

        ppApp.registerDeveloper("BA");
        assertEquals(2, ppApp.getInitialsOfParticipants().size());
    }

    @Test
    public void unregisterDeveloper() throws Exception {

        registerDeveloper();
        assertEquals(2, ppApp.getInitialsOfParticipants().size());

        ppApp.unregisterDeveloper("MAA");
        assertEquals(1, ppApp.getInitialsOfParticipants().size());

        ppApp.unregisterDeveloper("BA");
        assertEquals(0, ppApp.getInitialsOfParticipants().size());
    }

    @Test
    public void getInitialsOfParticipants() throws Exception {
        assertTrue(ppApp.getInitialsOfParticipants().isEmpty());
    }

    @Test
    public void getDeveloperBy() throws Exception {
        ppApp.registerDeveloper("MAA");
        ppApp.registerDeveloper("BA");
        try {
            assertEquals("BA", ppApp.getDeveloperBy("BA").getInitials());
        } catch (NotFoundException e) {
            assertEquals("\"BA\" was not found.", e.getMessage());
            assertEquals("BA", e.getProjectName());
        }

        try {
            assertEquals("MAA", ppApp.getDeveloperBy("MA").getInitials());
            fail("A NotFoundException should have been thrown.");
        } catch (NotFoundException e) {
            assertEquals("\"MA\" was not found.", e.getMessage());
            assertEquals("MA", e.getProjectName());
        }
    }
    // </editor-fold>

    // <editor-fold desc = "project related tests">
    @Test
    public void registerProject() throws Exception {
        assertTrue(ppApp.getNamesOfProjects().isEmpty());

        ppApp.registerProject("Project Planner");
        assertEquals(1, ppApp.getNamesOfProjects().size());

        ppApp.registerProject("Bachelor Thesis");
        assertEquals(2, ppApp.getNamesOfProjects().size());
    }

    @Test
    public void unregisterProject() throws Exception {

        registerProject();
        assertEquals(2, ppApp.getNamesOfProjects().size());

        ppApp.unregisterProject("Bachelor Thesis");
        assertEquals(1, ppApp.getNamesOfProjects().size());

        ppApp.unregisterProject("Project Planner");
        assertEquals(0, ppApp.getNamesOfProjects().size());
    }

    @Test
    public void getNamesOfProjects() throws Exception {
        assertTrue(ppApp.getNamesOfProjects().isEmpty());
    }

    @Test
    public void getProjectBy() throws Exception {
        ppApp.registerProject("Project Planner");
        ppApp.registerProject("Bachelor Thesis");
        try {
            assertEquals("Bachelor Thesis", ppApp.getProjectBy("Bachelor Thesis").getProjectName());
        } catch(NotFoundException e) {
            assertEquals("\"Bachelor Thesis\" was not found.", e.getMessage());
            assertEquals("Bachelor Thesis", e.getProjectName());
        }

        try {
            assertEquals("Project Planner", ppApp.getProjectBy("Project lanner").getProjectName());
            fail("A NotFoundException should have been thrown.");
        } catch (NotFoundException e) {
            assertEquals("\"Project lanner\" was not found.", e.getMessage());
            assertEquals("Project lanner", e.getProjectName());
        }
    }
    // </editor-fold>

    */
}