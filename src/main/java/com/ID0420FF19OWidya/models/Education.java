package com.ID0420FF19OWidya.models;

public class Education
{
    private int educationID;
    private int userID;
    private String school;
    private String degree;
    private int startYear;
    private int endYear;
    private String educationDesc;
    private boolean deleted;
    private boolean show;
    
    public Education() {
        this.deleted = false;
        this.show = true;
    }
    
    public int getEducationID() {
        return this.educationID;
    }
    
    public void setEducationID(final int educationID) {
        this.educationID = educationID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(final String school) {
        this.school = school;
    }
    
    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(final String degree) {
        this.degree = degree;
    }
    
    public int getStartYear() {
        return this.startYear;
    }
    
    public void setStartYear(final int startYear) {
        this.startYear = startYear;
    }
    
    public int getEndYear() {
        return this.endYear;
    }
    
    public void setEndYear(final int endYear) {
        this.endYear = endYear;
    }
    
    public String getEducationDesc() {
        return this.educationDesc;
    }
    
    public void setEducationDesc(final String educationDesc) {
        this.educationDesc = educationDesc;
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
        return "Education [educationID=" + this.educationID + ", userID=" + this.userID + ", school=" + this.school + ", degree=" + this.degree + ", startYear=" + this.startYear + ", endYear=" + this.endYear + ", educationDesc=" + this.educationDesc + ", deleted=" + this.deleted + ", show=" + this.show + "]";
    }
}