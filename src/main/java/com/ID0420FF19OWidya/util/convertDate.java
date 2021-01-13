package com.ID0420FF19OWidya.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class convertDate
{
    public static String countCreatedDate(final Timestamp timestamp) {
        String createdDateString = "";
        final long diffinmiliseconds = System.currentTimeMillis() - timestamp.getTime();
        final long oneSecondinmili = 1000;
        final long oneMinuteinmili = oneSecondinmili * 60;
        final long oneHourinmili = oneMinuteinmili * 60;
        final long oneDayinmili = oneHourinmili * 24;
        if (diffinmiliseconds > oneDayinmili) {
            final SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy");
            final Date resultdate = new Date(timestamp.getTime());
            createdDateString = sdf.format(resultdate);
        }
        else if (diffinmiliseconds > oneHourinmili) {
            final long diffinhour = diffinmiliseconds / oneHourinmili;
            createdDateString = String.valueOf(diffinhour) + "H ago";
        }
        else if (diffinmiliseconds > oneMinuteinmili) {
            final long diffinminute = diffinmiliseconds / oneMinuteinmili;
            createdDateString = String.valueOf(diffinminute) + "m ago";
        }
        else if (diffinmiliseconds > oneSecondinmili) {
            final long diffinsecond = diffinmiliseconds / oneSecondinmili;
            createdDateString = String.valueOf(diffinsecond) + "seconds ago";
        }
        System.out.println(createdDateString);
        return createdDateString;
    }
    
    
    public static String countCreatedDateDay(Timestamp timestamp) {
        String createdDateString = "";
        long diffinmiliseconds = System.currentTimeMillis() - timestamp.getTime();
        long oneSecondinmili = 1000;
        long oneMinuteinmili = oneSecondinmili * 60;
        long oneHourinmili = oneMinuteinmili * 60;
        long oneDayinmili = oneHourinmili * 24;
        long twoDayinmili = oneDayinmili * 2;
        if (diffinmiliseconds > twoDayinmili) {
            SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy");
            Date resultdate = new Date(timestamp.getTime());
            createdDateString = sdf.format(resultdate);
        }
        else if (diffinmiliseconds > oneDayinmili) {
            long diffinhour = diffinmiliseconds / oneHourinmili;
            createdDateString = "Yesterday";
        }
        else if (diffinmiliseconds < oneDayinmili) {
            long diffinminute = diffinmiliseconds / oneMinuteinmili;
            createdDateString = "Today";
        }
        System.out.println(createdDateString);
        return createdDateString;
    }
}