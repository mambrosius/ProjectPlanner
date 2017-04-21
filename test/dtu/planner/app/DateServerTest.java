package dtu.planner.app;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class DateServerTest {

    @Test
    public void getDate() throws Exception {
        DateServer dateServer = new DateServer();
        assertEquals(15, dateServer.getDate().get(Calendar.DATE));
        assertEquals(3, dateServer.getDate().get(Calendar.MONTH));
    }
}