package com.ID0420FF19OWidya.models;

public class Feedback
{
    private int feedbackID;
    private int userID;
    private String userName;
    private String feedbackCategory;
    private String feedbackMessage;
    private String createdDate;
    private boolean solved;
    private boolean read;
    
    public int getFeedbackID() {
        return this.feedbackID;
    }
    
    public void setFeedbackID(final int feedbackID) {
        this.feedbackID = feedbackID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public String getFeedbackMessage() {
        return this.feedbackMessage;
    }
    
    public void setFeedbackMessage(final String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
    
    public String getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }
    
    public boolean isSolved() {
        return this.solved;
    }
    
    public void setSolved(final boolean solved) {
        this.solved = solved;
    }
    
    public boolean isRead() {
        return this.read;
    }
    
    public void setRead(final boolean read) {
        this.read = read;
    }
    
    public String getFeedbackCategory() {
        return this.feedbackCategory;
    }
    
    public void setFeedbackCategory(final String feedbackCategory) {
        this.feedbackCategory = feedbackCategory;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    @Override
    public String toString() {
        return "Feedback [feedbackID=" + this.feedbackID + ", userID=" + this.userID + ", userName=" + this.userName + ", feedbackCategory=" + this.feedbackCategory + ", feedbackMessage=" + this.feedbackMessage + ", createdDate=" + this.createdDate + ", solved=" + this.solved + ", read=" + this.read + "]";
    }
}