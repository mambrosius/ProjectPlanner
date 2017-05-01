package dtu.planner.app;

import org.junit.*;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;


public class ActivityTest {
    ProjectPlanner app = new ProjectPlanner();
    Administrator admin = new Administrator();
    Project pro = new Project("pro1");
    Activity act1 = new Activity("act1","pro1");
    Activity act2 = new Activity("act2","pro1");
    Developer bran = new Developer("bran");
    Developer moaa = new Developer("moaa");
    Developer bamo = new Developer("bamo");


    //Assigne udviklere til aktivitet
    @Test
    public void assignDev(){
        //Opretter først udviklerne
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");
        admin.registerDeveloper("bamo");

        //Tilføje udviklerne til projektet
        pro.assignDeveloper("bran");
        pro.assignDeveloper("moaa");
        pro.assignDeveloper("bamo");

        //Tilføjer nu "bran" og "bamo" til act1
        act1.assignDeveloper("bran");
        act1.assignDeveloper("bamo");
        List<Developer> test1 = act1.getDevelopers();

        //Ser om de er blevet tilføjet
        assertEquals("bran",test1.get(0).getInitials());
        assertEquals("bamo",test1.get(1).getInitials());


        //Tilføjer desuden alle 3 udviklere til act2
        act2.assignDeveloper("bran");
        act2.assignDeveloper("moaa");
        act2.assignDeveloper("bamo");
        List<Developer> test2 = act2.getDevelopers();

        //Ser om alle 3 udviklere er blevet tilføjet
        assertEquals("bran",test2.get(0).getInitials());
        assertEquals("moaa",test2.get(1).getInitials());
        assertEquals("bamo",test2.get(2).getInitials());

    }


    //Fjerne udviklere fra aktivitet
    @Test
    public void removeDev(){

        //Tilføje udviklerne til aktiviteterne
        act.assignDeveloper("bran");
        act.assignDeveloper("moaa");
        act.assignDeveloper("bamo");

        List<Developer> test = act.getDevelopers();

        //Se om udviklerne er assignet aktiviteten
        assertEquals(3,test.size());

        //Fjerner herefter en udvikler
        act.unassignDeveloper("bran");

        //Ser om "bran" er fjernet fra aktiviteten
        assertEquals("moaa",test.get(0).getInitials());
        assertEquals("bamo",test.get(1).getInitials());
    }


}