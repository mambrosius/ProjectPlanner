package dtu.planner.app;

import org.junit.*;

import static org.junit.Assert.*;

public class ManagerTest {

    ProjectPlanner app = new ProjectPlanner();
    Administrator admin = new Administrator();
    Project pro = new Project("pro1");
    Project pro2 = new Project("pro2");
    Activity act1 = new Activity("act1","pro1");
    Activity act2 = new Activity("act2","pro1");
    Activity act3 = new Activity("act2","pro2");
    Developer bran = new Developer("bran");
    Developer moaa = new Developer("moaa");
    Developer bamo = new Developer("bamo");
    Developer moba = new Developer("moba");
    Manager Man = new Manager("bran");

/*
    //Manager assigner udviklere til activitet
    @Test
    public void assignAct() throws Exception {
        Man.assignActivity("bran","act1","pro1");




    }

*/


}