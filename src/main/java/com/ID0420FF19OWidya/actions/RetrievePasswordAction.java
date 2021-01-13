package com.ID0420FF19OWidya.actions;

import com.ID0420FF19OWidya.util.regexInput;
import com.ID0420FF19OWidya.util.sendEmail;
import com.ID0420FF19OWidya.dao.Dao;
import com.opensymphony.xwork2.ActionSupport;

public class RetrievePasswordAction extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    private String email;
    Dao dao;
    private String result;
    
    public RetrievePasswordAction() {
        this.dao = new Dao();
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setResult(final String result) {
        this.result = result;
    }
    
    public String execute() {
        System.out.println("email" + this.email);
        Boolean isValid = regexInput.checkRetrievePasswordInput(this.email);
        if (isValid) {
	        if (this.dao.checkUser(this.email, "email") == 0) {
	            this.result = "email not Exist";
	        }
	        else {
	            final String to = this.email;
	            System.out.println(to);
	            final String subject = "Password Retrieval";
	            final String body = "Your Password : " + this.dao.checkUserPassword(to);
	            this.result = sendEmail.sendemail(to, subject, body);
	        }
        }else {
        	this.result = "invalid Email";
        }
        return "success";
    }
}
