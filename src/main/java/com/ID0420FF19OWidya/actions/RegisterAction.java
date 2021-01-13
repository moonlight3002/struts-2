package com.ID0420FF19OWidya.actions;

import java.sql.Timestamp;
import com.ID0420FF19OWidya.util.sendEmail;
import com.ID0420FF19OWidya.util.generateRandom;
import com.ID0420FF19OWidya.util.regexInput;

import org.apache.commons.lang3.StringUtils;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements ModelDriven<User>
{
    private static final long serialVersionUID = 1L;
    private User user;
    private String category;
    private String vid;
    
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(final String category) {
        this.category = category;
    }
    
    public String getVid() {
        return this.vid;
    }
    
    public void setVid(final String vid) {
        this.vid = vid;
    }
    
    public void validate() {
        System.out.println("in register validate");
        final Dao dao = new Dao();
        System.out.println(this.user);
        if (this.category.equalsIgnoreCase("register")) {
            if (StringUtils.isEmpty((CharSequence)this.user.getFirstName())) {
                System.out.println(this.getText("firstName.required"));
                this.addFieldError("firstNameError", this.getText("firstName.required"));
            }
            if (StringUtils.isEmpty((CharSequence)this.user.getLastName())) {
                System.out.println(this.getText("lastName.required"));
                this.addFieldError("lastNameError", this.getText("lastName.required"));
            }
            if (StringUtils.isEmpty((CharSequence)this.user.getEmail())) {
                System.out.println(this.getText("email.required"));
                this.addFieldError("emailError", this.getText("email.required"));
            }
            else if (dao.checkUser(this.user.getEmail(), "email") != 0) {
                System.out.println("user is exist");
                this.addFieldError("userExist", this.getText("user.exist"));
            }
            if (this.user.getDob() == null) {
                System.out.println(this.getText("dob.required"));
                this.addFieldError("dobError", this.getText("dob.required"));
            }
            if (StringUtils.isEmpty((CharSequence)this.user.getPassword())) {
                System.out.println(this.getText("password.required"));
                this.addFieldError("passwordError", this.getText("password.required"));
            }
        }
        else if (this.category.equalsIgnoreCase("verification")) {
            if (StringUtils.isEmpty((CharSequence)this.user.getEmail())) {
                System.out.println(this.getText("email.required"));
                this.addFieldError("emailError", this.getText("email.required"));
            }
            else if (dao.checkUser(this.user.getEmail(), "email") != 0) {
                System.out.println("user is exist");
                this.addFieldError("userExist", this.getText("user.exist"));
            }
        }
    }
    
    public String execute() {
    	System.out.println("in register execute");
    	Boolean isValid = regexInput.checkRegistrationInput(this.user);
        if (isValid) {
	        String verificationID = generateRandom.getVerificationID();
	        String username = generateRandom.getUserName(this.user);
	        Dao dao = new Dao();
	        int registerRowsAffected = dao.registerUser(this.user, username, verificationID);
	        if (registerRowsAffected > 0) {
	            sendEmail.sendVerificationEmail(this.user.getEmail(), verificationID);
	            return SUCCESS;
	        }else {
		        return ERROR;
	        }
        }else {
        	return INPUT;
        }
    }
    
    public String verification() {
        System.out.println("in register verification");
    	Boolean isValid = regexInput.checkVerificationInput(this.user);
        if (isValid) {
	        Timestamp expiredDate = null;
	        final Dao dao = new Dao();
	        expiredDate = dao.verifyUser(this.user, this.vid);
	        System.out.println(expiredDate);
	        if (expiredDate != null) {
	            System.out.println("verification expired date" + expiredDate + " " + expiredDate.getTime() + " " + System.currentTimeMillis());
	            if (expiredDate.getTime() <= System.currentTimeMillis()) {
	                final String verificationID = generateRandom.getVerificationID();
	                final int updateVerificationRowsAffected = dao.updateVerification(this.user, verificationID);
	                if (updateVerificationRowsAffected > 0) {
	                    sendEmail.sendVerificationEmail(this.user.getEmail(), verificationID);
	                    return "invalid";
	                }else {
		                return ERROR;
	                }
	            }else if (expiredDate.getTime() > System.currentTimeMillis()) {
	                System.out.println(expiredDate);
	                final int insertUserRowsAffected = dao.insertUser(this.user, this.getText("welcome.message"));
	                System.out.println("insertUserRowsAffected" + insertUserRowsAffected);
	                if (insertUserRowsAffected == 0) {
	                    System.out.println("insert delete register user transaction failed");
	                    return ERROR;
	                }else {
		                return SUCCESS;
	                }
	            }
	        }else {
	            System.out.println("expired date is null");
	            return ERROR;
	        }
        }
        return INPUT;
    }
    
    public User getModel() {
        return this.user = new User();
    }
}