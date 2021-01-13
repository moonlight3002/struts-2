package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport
{
    public String execute() {
        final SessionMap<String, Object> userSession = (SessionMap<String, Object>)ActionContext.getContext().getSession();
        userSession.remove((Object)"userData");
        userSession.clear();
        userSession.invalidate();
        return "success";
    }
}