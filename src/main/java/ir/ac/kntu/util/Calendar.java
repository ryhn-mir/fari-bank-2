package ir.ac.kntu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Calendar {

    private Date date;

    public Calendar() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy / MM / dd hh : mm : ss");
        return simpleDateFormat.format(date);
    }
}