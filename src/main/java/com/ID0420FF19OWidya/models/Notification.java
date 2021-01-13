package com.ID0420FF19OWidya.models;

public class Notification
{
    private int notificationID;
    private int userID;
    private int triggerUserID;
    private String link;
    private String notificationCategory;
    private String notificationMessage;
    private String createdDate;
    private boolean read;
    private String createdTime;
    public Notification() {
    }
    
    public Notification(final int userID, final int triggerUserID, final String link, final String notificationCategory, final String notificationMessage) {
        this.userID = userID;
        this.triggerUserID = triggerUserID;
        this.link = link;
        this.notificationCategory = notificationCategory;
        this.notificationMessage = notificationMessage;
    }
    
    public Notification(final int userID, final int triggerUserID, final String link, final String notificationCategory, final String notificationMessage, final boolean read) {
        this.userID = userID;
        this.triggerUserID = triggerUserID;
        this.link = link;
        this.notificationCategory = notificationCategory;
        this.notificationMessage = notificationMessage;
        this.read = read;
    }
    
    
    
    public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getNotificationID() {
        return this.notificationID;
    }
    
    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public int getTriggerUserID() {
        return this.triggerUserID;
    }
    
    public void setTriggerUserID(final int triggerUserID) {
        this.triggerUserID = triggerUserID;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setLink(final String link) {
        this.link = link;
    }
    
    public String getNotificationCategory() {
        return this.notificationCategory;
    }
    
    public void setNotificationCategory(final String notificationCategory) {
        this.notificationCategory = notificationCategory;
    }
    
    public String getNotificationMessage() {
        return this.notificationMessage;
    }
    
    public void setNotificationMessage(final String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
    
    public String getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }
    
    public boolean isRead() {
        return this.read;
    }
    
    public void setRead(final boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        return "Notification [notificationID=" + this.notificationID + ", userID=" + this.userID + ", triggerUserID=" + this.triggerUserID + ", link=" + this.link + ", notificationCategory=" + this.notificationCategory + ", notificationMessage=" + this.notificationMessage + ", createdDate=" + this.createdDate + ", read=" + this.read + "]";
    }
}