package dtu.planner.app;

import dtu.planner.app.exceptions.NotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectTest extends ProjectTestSetup {

    ProjectPlanner app = new ProjectPlanner();
    Administrator admin = new Administrator();
    Project pro = new Project("pro1");


    // Tester om 2 udviklere er assignet til et projekt
   @Test
    public void AssignDev(){
       //De 2 udvikler registreres, hvorefter de assignes et projekt
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");

        pro.assignDeveloper("bran");
        pro.assignDeveloper("moaa");
        List<Developer> test = pro.getDevelopers();


        assertEquals(test.get(0).getInitials(),"bran");
        assertEquals(test.get(1).getInitials(),"moaa");
   }

    // Tester hvor en udvikler ud af 4 bliver unassignet af et projekt
    @Test
    public void UnassignDev(){
        //De 4 udvikler registreres, hvorefter de assignes et projekt
        admin.registerDeveloper("bran");
        pro.assignDeveloper("bran");

        admin.registerDeveloper("moaa");
        pro.assignDeveloper("moaa");

        admin.registerDeveloper("bamo");
        pro.assignDeveloper("bamo");

        admin.registerDeveloper("moba");
        pro.assignDeveloper("moba");
        List<Developer> test = pro.getDevelopers();

        //Tjekker om de alle er assignet projektet
        assertEquals(4,test.size());

        //Vi unassigner nu "bran" af projeket
        pro.unassignDeveloper("bran");

        //Tjekker om "bran" er unassignet af projektet ved at se om der er 3 personer assignet til projektet
        assertEquals(3,test.size());
        assertEquals(test.get(0).getInitials(),"moaa");
        assertEquals(test.get(1).getInitials(),"bamo");
        assertEquals(test.get(2).getInitials(),"moba");
    }

    /*
    @Test
    public void
    */




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