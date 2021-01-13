package com.ID0420FF19OWidya.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ID0420FF19OWidya.models.Feedback;

public class validateInput
{
    public static boolean validateFeedbackForm(Feedback feedback) {
        boolean isValid = true;
        if (feedback.getFeedbackCategory().isEmpty() || feedback.getFeedbackMessage().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }
    
    public static boolean validateJobPostDeadline(String date) {
    	boolean isValid = true;
    	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    	Calendar current = Calendar.getInstance();
    	Calendar maxDate = Calendar.getInstance();
    	maxDate.add(Calendar.DATE, 31);
    	try {
			Date deadline = sdf.parse(date);
			if(deadline.getTime()< current.getTimeInMillis() || deadline.getTime() > maxDate.getTimeInMillis()) {
				isValid = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return isValid;
    }
}