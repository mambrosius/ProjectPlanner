package dtu.planner.models;

import dtu.planner.exceptions.CustomException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeveloperTest {

    ProjectPlanner model = new ProjectPlanner();
    private Developer sut = new Developer("adm");

    @Before
    public void setup() throws Exception {
        sut.setProjectPlanner(model);
        sut.addActivity(new Activity("debug", "rapport1"));

        assertEquals(0, model.getDeveloperMap().size());

        model.addDeveloperIfAbsent("dev1");
        assertEquals(1, model.getDeveloperMap().size());
    }

    @Test
    public void seekAssistance() throws Exception {

        try {
            assertTrue(sut.seekAssistance("debug", "dev1"));
        } catch (CustomException ex) {
            fail();
        }

        try {
            assertTrue(sut.seekAssistance("debug", "dev1"));
            fail();
        } catch (CustomException ex) {
            assertEquals("request already sent", ex.getMessage());
        }

        try {
            assertTrue(sut.seekAssistance("debug", "foo"));
            fail();
        } catch (CustomException ex) {
            assertEquals("developer does not exist", ex.getMessage());
        }

        try {
            assertTrue(sut.seekAssistance("debug", ""));
            fail();
        } catch (CustomException ex) {
            assertEquals("need initials", ex.getMessage());
        }
        /*
        try {
            assertTrue(sut.seekAssistance("", "dev1"));
            fail();
        } catch (CustomException ex) {
            assertEquals("select an activity", ex.getMessage());
        }*/
    }

    @Test
    public void logActivity() throws Exception {
        /*
        try {
            sut.logActivity("debug", "10.");
            assertTrue(sut.getActivityMap().get("debug").getHoursUsed() == 10);
        } catch (CustomException ex) {
            fail();
        }
        */
        /*
        public void logActivity(String activity, String input) throws CustomException {
            if (!input.matches("[0.-9.]+"))
                throw new CustomException("invalid input");
            activityMap.get(activity).setHoursUsed(Double.parseDouble(input));
        }
        */
    }

    @Test
    public void getInitials() throws Exception {
    }

    @Test
    public void getColumnNames() throws Exception {
    }

    @Test
    public void addActivity() throws Exception {
    }

    @Test
    public void removeActivity() throws Exception {
    }

    @Test
    public void addRequest() throws Exception {
    }

    @Test
    public void replyReq() throws Exception {

        /*
        public void replyReq(Activity activity, boolean accept) {
        if (activity != null) {
            if (accept)
                activity.addDeveloper(this);
            reqMap.remove(activity.getName());
        }
        */
    }

    @Test
    public void getData() throws Exception {
    }

    @Test
    public void getActivityMap() throws Exception {
    }

    @Test
    public void getReqMap() throws Exception {
    }

    @Test
    public void hasActivity() throws Exception {
    }

    @Test
    public void getActivityData() throws Exception {
    }

    @Test
    public void getActivities() throws Exception {
    }

    @Test
    public void getAvailableDevelopers() throws Exception {
    }

    @Test
    public void setProjectPlanner() throws Exception {
    }

}