package com.ID0420FF19OWidya.util;

import java.sql.Timestamp;
import com.ID0420FF19OWidya.models.User;
import org.apache.commons.lang3.RandomStringUtils;

public class generateRandom
{
    public static String getVerificationID() {
        final int randomnumber = (int)Math.random() * 1000000;
        final String randomString = RandomStringUtils.randomAlphabetic(50);
        System.out.println(randomString);
        return String.valueOf(randomString) + Integer.toString(randomnumber);
    }
    
    public static String getUserName(final User user) {
        final int randomnumber = (int)Math.random() * 1000000;
        final long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        System.out.println(String.valueOf(user.getFirstName()) + user.getLastName() + timestamp + randomnumber);
        return String.valueOf(user.getFirstName()) + user.getLastName() + timestamp + randomnumber;
    }
}