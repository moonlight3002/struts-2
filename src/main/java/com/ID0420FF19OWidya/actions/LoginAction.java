package com.ID0420FF19OWidya.actions;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.ID0420FF19OWidya.dao.Dao;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.ID0420FF19OWidya.models.User;
import com.ID0420FF19OWidya.util.regexInput;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ModelDriven<User>, SessionAware
{
    private static final long serialVersionUID = 1L;
    private User user;
    private Map<String, Object> userSession;
    
    public void validate() {
        System.out.println("-in login validate");
        final Dao dao = new Dao();
        System.out.println(this.user);
        if (StringUtils.isEmpty((CharSequence)this.user.getEmail())) {
            System.out.println(this.getText("email.required"));
            this.addFieldError("emailError", this.getText("email.required"));
        }
        else if (dao.checkUser(this.user.getEmail(), "email") == 0) {
            System.out.println("user is not exist");
            this.addFieldError("userNotExist", this.getText("user.notexist"));
        }
        if (StringUtils.isEmpty((CharSequence)this.user.getPassword())) {
            System.out.println(this.getText("password.required"));
            this.addFieldError("passwordError", this.getText("password.required"));
        }

    }
    
    public String execute() {
        System.out.println("-in login execute");
        System.out.println(this.user);
        Boolean isValid = regexInput.checkLoginInput(this.user);
        if (isValid) {
        	final Dao dao = new Dao();
            this.user = dao.loginUser(this.user);
            System.out.println(this.user);
            if (this.user.getUserID() > 0) {
                this.userSession.put("userData", this.user);
                this.userSession.put("role", this.user.getRole());
                return SUCCESS;
            }else {
            	System.out.println(this.getText("password.invalid"));
                this.addFieldError("invalidPassword", this.getText("password.invalid"));
                return INPUT;
            }
        }else {
        	return INPUT;
        }
        
        
    }
    
    public User getModel() {
        System.out.println("-login get Model");
        return this.user = new User();
    }
    
    public void setSession(final Map<String, Object> session) {
        System.out.println("-login set session");
        this.userSession = session;
    }
}