package com.ID0420FF19OWidya.util;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ID0420FF19OWidya.models.Job;
import com.ID0420FF19OWidya.models.JobQuestion;
import com.ID0420FF19OWidya.models.User;

public class regexInput {
	
	final static String regexFirstName = "^[A-Za-z ]{3,50}$";//min 3 char, max 50
	final static String regexLastName = "^[A-Za-z]{3,50}$";//min 3 char, max 50
	final static String regexEmail ="^[a-zA-z\\d\\.-]*@[\\w-]*\\.[a-zA-Z]{2,3}(\\.[a-zA-Z]{2,3})?$";
	final static String regexPassword = "^[\\w@%&=!]{5,20}$"; //password min 5 char, max 20 char, combination alphanumeric @%&=!
	final static String regexDOB = "^([0-2][0-9]|3[0-1])\\/(0[0-9]|1[0-2])\\/(19[3-9]\\d|20[01]\\d)$";
	final static String regexCompany = "^[a-zA-z .&:,\\-()\\/.\\'@]{3,100}$";
	final static String regexCityID = "^[\\d]*$";
	final static String regexCountryID = "^[\\d]*$";
	final static String regexCompanyInfo= "^[\\s\\S]{10,1000}$";
	final static String regexHours= "^parttime$|^fulltime$";
	final static String regexPlace= "^onsite$|^remote$";
	final static String regexPosition = "^[a-zA-z .&:,\\-()\\/.\\'@]{3,100}$";
	final static String regexDeadline = "^([0-2][0-9]|3[0-1])\\/(0[0-9]|1[0-2])\\/(202[0-1])$";
	final static String regexJobDesc = "^[\\s\\S]{10,2000}$";
	final static String regexJobRequirements = "^[\\s\\S]{10,2000}$";
	final static String regexTextarea = "^[\\s\\S]{0,2000}$";
	
	private static boolean regexTest(String patt, String input) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(patt);
		Matcher match = pattern.matcher(input);
		boolean ismatch = match.matches();
		return ismatch;
	}
	
	public static Boolean checkLoginInput(User user) {
		System.out.println("in check login Input");
		Boolean isValid = true;
		System.out.println(user);
		if(regexTest(regexEmail, user.getEmail())!= true) {
			isValid = false;
		}
		if(regexTest(regexPassword, user.getPassword())!= true) {
			isValid = false;
		}
		System.out.print("isValidInput:");
    	System.out.print(isValid);
		return isValid;
		
	}
	
	public static Boolean checkRegistrationInput(User user) {
		System.out.println("in check register Input");
		Boolean isValid = true;
		System.out.println(user);
		if(regexTest(regexEmail, user.getEmail())!= true) {
			isValid = false;
		}
		if(regexTest(regexPassword, user.getPassword())!= true) {
			isValid = false;
		}
		if(regexTest(regexFirstName, user.getFirstName())!= true) {
			isValid = false;
		}
		if(regexTest(regexLastName, user.getLastName())!= true) {
			isValid = false;
		}
		if(regexTest(regexDOB, user.getDob())!= true) {
			isValid = false;
		}
		System.out.print("isValidInput:");
    	System.out.print(isValid);
		return isValid;
	}
	
	public static Boolean checkVerificationInput(User user) {
		System.out.println("in check verification Input");
		Boolean isValid = true;
		System.out.println(user);
		isValid = regexTest(regexEmail, user.getEmail());
		System.out.print("isValidInput:");
    	System.out.print(isValid);
		return isValid;
	}
	
	public static Boolean checkRetrievePasswordInput(String email) {
		System.out.println("in check checkRetrievePasswordInput Input");
		Boolean isValid = true;
		isValid = regexTest(regexEmail, email);
		System.out.print("isValidInput:");
    	System.out.print(isValid);
		return isValid;
	}
	public static Boolean checkHiringInput(Job jobPost) {
		System.out.println("in check checkHiringInput Input");
		Boolean isValid = true;
		if(regexTest(regexCompany, jobPost.getCompanyName())!= true) {
			isValid = false;
		}
		
		if(regexTest(regexCityID, String.valueOf(jobPost.getCity().getCityID()))!= true) {
			isValid = false;
		}
		
		if(regexTest(regexCountryID, String.valueOf(jobPost.getCountry().getCountryID()))!= true) {
			isValid = false;
		}
		if(regexTest(regexCompanyInfo, jobPost.getCompanyInfo())!= true) {
			isValid = false;
		}
		if(regexTest(regexPosition, jobPost.getPosition())!= true) {
			isValid = false;
		}
		if(regexTest(regexPlace, jobPost.getPlace())!= true) {
			isValid = false;
		}
		if(regexTest(regexHours, jobPost.getHours())!= true) {
			isValid = false;
		}
		if(regexTest(regexJobDesc, jobPost.getJobDescription())!= true) {
			isValid = false;
		}
		if(regexTest(regexJobRequirements, jobPost.getJobRequirements())!= true) {
			isValid = false;
		}
		if(regexTest(regexDeadline, jobPost.getDeadlineSubmission())!= true) {
			isValid = false;
		}

		if(jobPost.getNotes()!=null) {
			if(jobPost.getNotes().isEmpty() == false) {
				if(regexTest(regexTextarea, jobPost.getNotes())!= true) {
					isValid = false;
				}
			}
		}
		
		if(jobPost.getJobQuestions() != null) {
			Iterator<JobQuestion> it = jobPost.getJobQuestions().iterator();
			while(it.hasNext()) {
				JobQuestion jq = it.next();
				if(regexTest(regexTextarea, jq.getQuestion())!= true) {
					isValid = false;
				}
			}
		}
		
		
		System.out.print("isValidInput:");
    	System.out.print(isValid);
		return isValid;
	}
}
