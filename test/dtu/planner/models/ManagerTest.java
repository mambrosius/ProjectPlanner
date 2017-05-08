package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerTest {

    private Manager man = new Manager("adm");
    private ProjectPlanner model = new ProjectPlanner();
    private Administrator admin = new Administrator(model);

    @Before
    public void setup() throws Exception {
        model.addProjectIfAbsent("project1");
        model.addProjectIfAbsent("project2");
        model.addDeveloperIfAbsent("dev1");
        model.addDeveloperIfAbsent("dev2");
        model.addDeveloperIfAbsent("dev3");

        admin.assign("project2", "dev1", false);
        admin.assign("project1", "dev2", false);
        admin.assign("project1", "dev3", false);
    }

    @Test
    public void getInitials() throws Exception {
        assertEquals("adm", man.getInitials());
    }

    @Test
    public void setProjectPlanner() throws Exception {
        assertEquals(null, man.getProjectPlanner());

        man.setProjectPlanner(model);
        assertEquals(model, man.getProjectPlanner());
    }

    @Test
    public void addResp() throws Exception {
        assertEquals(0, man.getResps().length);

        man.addResp(model.getProjectMap().get("project1"));
        assertEquals(1, man.getResps().length);

        man.addResp(model.getProjectMap().get("project2"));
        assertEquals(2, man.getResps().length);
    }

    @Test
    public void removeResp() throws Exception {
        addResp();

        man.removeResp("project2");
        assertEquals(1, man.getResps().length);

        man.removeResp("project1");
        assertEquals(0, man.getResps().length);
    }

    @Test
    public void register() throws Exception {
        addResp();

        assertEquals(0, man.getActivities("project1").length);
        assertEquals(0, man.getActivities("project2").length);

        try {
            man.register("project2", "activity1");
            assertEquals(0, man.getActivities("project1").length);
            assertEquals(1, man.getActivities("project2").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.register("project2", "activity5");
            assertEquals(0, man.getActivities("project1").length);
            assertEquals(2, man.getActivities("project2").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.register("project1", "chill");
            assertEquals(1, man.getActivities("project1").length);
            assertEquals(2, man.getActivities("project2").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.register("project1", "");
            fail();
        } catch (CustomException ex) {
            assertEquals("no input", ex.getMessage());
        }

        try {
            man.register("project1", "chill");
            fail();
        } catch (CustomException ex) {
            assertEquals("chill already exists", ex.getMessage());
        }
    }

    @Test
    public void unregister() throws Exception {
        register();

        try {
            man.unregister("project2", "activity5");
            assertEquals(1, man.getActivities("project1").length);
            assertEquals(1, man.getActivities("project2").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.unregister("project2", "budgeting");
            fail();
        } catch (CustomException ex) {
            assertEquals("no such activity", ex.getMessage());
        }

        try {
            man.unregister("project1", null);
        } catch (CustomException ex) {
            assertEquals("select an activity", ex.getMessage());
        }
    }

    @Test
    public void assign() throws Exception {
        register();

        assertEquals(0, man.getAssignedDevs("chill", "project1").length);

        try {
            man.assign("project1", "chill", "dev3");
            assertEquals(1, man.getAssignedDevs("chill", "project1").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.assign("project1", "chill", "dev2");
            assertEquals(2, man.getAssignedDevs("chill", "project1").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.assign("project2", "activity5", "dev1");
            assertEquals(1, man.getAssignedDevs("activity5", "project2").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.assign("project1", "chill", null);
            fail();
        } catch (CustomException ex) {
            assertEquals("select an activity and a developer", ex.getMessage());
        }

        try {
            man.assign("project1", null, "dev3");
            fail();
        } catch (CustomException ex) {
            assertEquals("select an activity and a developer", ex.getMessage());
        }
    }

    @Test
    public void unassign() throws Exception {
        assign();

        try {
            man.unassign("project1", "chill", "dev2");
            assertEquals(1, man.getAssignedDevs("chill", "project1").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.unassign("project1", "chill", "dev3");
            assertEquals(0, man.getAssignedDevs("chill", "project1").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.unassign("project2", "activity5", "dev1");
            assertEquals(0, man.getAssignedDevs("chill", "project1").length);
        } catch (CustomException ex) {
            fail();
        }

        try {
            man.unassign("project2", null, "dev1");
            fail();
        } catch (CustomException ex) {
            assertEquals("select a project and a developer", ex.getMessage());
        }
    }

    @Test
    public void setEstimatedHours() throws Exception {
        register();
        man.setEstimatedHours("12.5", "chill", "project1");
        assertEquals(12.5, man.getEstimatedHours("chill", "project1"), 0);
    }

    @Test
    public void getRespMap() throws Exception {
    }

    @Test
    public void hasActivity() throws Exception {
    }

    @Test
    public void getActivities() throws Exception {
    }

    @Test
    public void getActivityData() throws Exception {
    }

    @Test
    public void hasDeveloper() throws Exception {
    }

    @Test
    public void getDeveloperData() throws Exception {
    }

    @Test
    public void getAvailableDevs() throws Exception {
    }

    @Test
    public void getAssignedDevs() throws Exception {
    }

    @Test
    public void setupDeveloperTap() throws Exception {
    }

    @Test
    public void setupAdminTap() throws Exception {
    }

    @Test
    public void dispose() throws Exception {
    }

    @Test
    public void refreshTab() throws Exception {
    }
}