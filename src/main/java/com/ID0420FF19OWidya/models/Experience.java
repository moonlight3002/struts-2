package com.ID0420FF19OWidya.models;

public class Experience
{
    private int experienceID;
    private int userID;
    private String position;
    private String company;
    private int startMonth;
    private int startYear;
    private int endMonth;
    private int endYear;
    private String experienceDesc;
    private boolean deleted;
    private boolean show;
    
    public Experience() {
        this.endMonth = 0;
        this.endYear = 0;
        this.deleted = false;
        this.show = true;
    }
    
    public int getExperienceID() {
        return this.experienceID;
    }
    
    public void setExperienceID(final int experienceID) {
        this.experienceID = experienceID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }
    
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(final String company) {
        this.company = company;
    }
    
    public int getStartMonth() {
        return this.startMonth;
    }
    
    public void setStartMonth(final int startMonth) {
        this.startMonth = startMonth;
    }
    
    public int getStartYear() {
        return this.startYear;
    }
    
    public void setStartYear(final int startYear) {
        this.startYear = startYear;
    }
    
    public int getEndMonth() {
        return this.endMonth;
    }
    
    public void setEndMonth(final int endMonth) {
        this.endMonth = endMonth;
    }
    
    public int getEndYear() {
        return this.endYear;
    }
    
    public void setEndYear(final int endYear) {
        this.endYear = endYear;
    }
    
    public String getExperienceDesc() {
        return this.experienceDesc;
    }
    
    public void setExperienceDesc(final String experienceDesc) {
        this.experienceDesc = experienceDesc;
    }
    
    public boolean isDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }
    
    public boolean isShow() {
        return this.show;
    }
    
    public void setShow(final boolean show) {
        this.show = show;
    }
    
    @Override
    public String toString() {
        return "Experience [experienceID=" + this.experienceID + ", userID=" + this.userID + ", position=" + this.position + ", company=" + this.company + ", startMonth=" + this.startMonth + ", startYear=" + this.startYear + ", endMonth=" + this.endMonth + ", endYear=" + this.endYear + ", experienceDesc=" + this.experienceDesc + ", deleted=" + this.deleted + ", show=" + this.show + "]";
    }
}