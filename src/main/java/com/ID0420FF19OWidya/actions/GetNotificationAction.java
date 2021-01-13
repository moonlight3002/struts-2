package com.ID0420FF19OWidya.actions;

import java.util.List;

import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.Notification;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class GetNotificationAction extends ActionSupport implements Preparable{
	private List<Notification> notifications;
	private User sessionUser;
	
	
	
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public String execute() {
		Dao dao = new Dao();
		System.out.println("--GetNotificationAction execute");
		notifications = dao.getNotificationList(sessionUser.getUserID());
		System.out.println("notification" + notifications);
		
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		sessionUser= (User) ActionContext.getContext().getSession().get("userData");
		
	}
}
