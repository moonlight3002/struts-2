package com.ID0420FF19OWidya.models;

import java.util.Date;

public class Activity
{
    private Date createdDate;
    private int userID;
    private String action;
    private String entity;
    private String activityNote;
    
    public Activity() {
        this.activityNote = "";
    }
    
    public Activity(final int userID, final String action, final String entity, final String activityNote) {
        this.activityNote = "";
        this.userID = userID;
        this.action = action;
        this.entity = entity;
        this.activityNote = activityNote;
    }
    
    public Activity(final int userID, final String action, final String entity) {
        this.activityNote = "";
        this.userID = userID;
        this.action = action;
        this.entity = entity;
    }
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void setAction(final String action) {
        this.action = action;
    }
    
    public String getEntity() {
        return this.entity;
    }
    
    public void setEntity(final String entity) {
        this.entity = entity;
    }
    
    public String getActivityNote() {
        return this.activityNote;
    }
    
    public void setActivityNote(final String activityNote) {
        this.activityNote = activityNote;
    }
    
    @Override
    public String toString() {
        return "Activity [createdDate=" + this.createdDate + ", userID=" + this.userID + ", action=" + this.action + ", entity=" + this.entity + ", activityNote=" + this.activityNote + "]";
    }
}