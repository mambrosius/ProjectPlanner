package dtu.planner.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Date {

    Calendar get() {
        return new GregorianCalendar();
    }

    public String toString() {
        Calendar date = new GregorianCalendar();
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.YEAR);
    }

    public String getWeek() {
        Calendar date = new GregorianCalendar();
        return Integer.toString(date.getWeekYear());
    }
}
