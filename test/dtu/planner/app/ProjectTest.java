package dtu.planner.app;

import dtu.planner.app.exceptions.NotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest extends ProjectTestSetup {
    /*
    @Test
    public void getInitialsOfParticipants() throws Exception {
        assertTrue(bachelorProject.getInitialsOfParticipants().isEmpty());

        bachelorProject.assignActivity(dev1);
        assertFalse(bachelorProject.getInitialsOfParticipants().isEmpty());
    }

    @Test
    public void assignActivity() throws Exception {
        assertTrue(bachelorProject.getInitialsOfParticipants().isEmpty());

        bachelorProject.assignActivity(dev1);
        assertEquals(1, bachelorProject.getInitialsOfParticipants().size());

        plannerProject.assignActivity(dev1);
        plannerProject.assignActivity(dev2);
        assertEquals(2, plannerProject.getInitialsOfParticipants().size());
    }

    @Test
    public void unassignActivity() throws Exception {
        assignActivity();
        assertEquals(1, bachelorProject.getInitialsOfParticipants().size());
        assertEquals(2, plannerProject.getInitialsOfParticipants().size());

        plannerProject.unassignActivity("MAA");
        assertEquals(1, plannerProject.getInitialsOfParticipants().size());

        plannerProject.unassignActivity("BA");
        assertEquals(0, plannerProject.getInitialsOfParticipants().size());
    }

    @Test
    public void getDeveloper() throws NotFoundException {
        ppApp.registerDeveloper("MAA");
        ppApp.registerDeveloper("BA");
        try {
            assertEquals("BA", ppApp.getDeveloper("BA").getInitials());
        } catch (NotFoundException e) {
            assertEquals("\"BA\" was not found.", e.getMessage());
            assertEquals("BA", e.getName());
        }

        try {
            assertEquals("MAA", ppApp.getDeveloper("MA").getInitials());
            fail("A NotFoundException should have been thrown.");
        } catch (NotFoundException e) {
            assertEquals("\"MA\" was not found.", e.getMessage());
            assertEquals("MA", e.getName());
        }
    }

    @Test
    public void assignManager() throws Exception {

        assignActivity();
        assertEquals(null, plannerProject.getManagerInitials());

        plannerProject.assignManager("MAA");
        assertEquals("MAA", plannerProject.getManagerInitials().getInitials());
    }

    @Test
    public void reassignManager() throws Exception {

        assignManager();

        plannerProject.reassignManager();
        assertEquals(null, plannerProject.getManagerInitials());
    }
    */
}