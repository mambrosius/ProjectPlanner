package dtu.planner.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Date {

    private Calendar date;

    Calendar get() {
        /*date = new GregorianCalendar();
        return date;*/
        return new GregorianCalendar();
    }

    public String toString() {
        Calendar date = new GregorianCalendar();
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.YEAR);
    }
}
