package com.ID0420FF19OWidya.models;

public class Skill
{
    private int userID;
    private int technologyID;
    private String technologyName;
    private String skillLevel;
    private boolean deleted;
    private boolean show;
    private String skillID;
    
    public Skill() {
        this.skillLevel = "not specified";
        this.deleted = false;
        this.show = true;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public int getTechnologyID() {
        return this.technologyID;
    }
    
    public void setTechnologyID(final int technologyID) {
        this.technologyID = technologyID;
    }
    
    public String getTechnologyName() {
        return this.technologyName;
    }
    
    public void setTechnologyName(final String technologyName) {
        this.technologyName = technologyName;
    }
    
    public String getSkillLevel() {
        return this.skillLevel;
    }
    
    public void setSkillLevel(final String skillLevel) {
        this.skillLevel = skillLevel;
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
    
    public String getSkillID() {
        return this.skillID;
    }
    
    public void setSkillID(final String skillID) {
        this.skillID = skillID;
    }
    
    @Override
    public String toString() {
        return "Skill [userID=" + this.userID + ", technologyID=" + this.technologyID + ", technologyName=" + this.technologyName + ", skillLevel=" + this.skillLevel + ", deleted=" + this.deleted + ", show=" + this.show + ", skillID=" + this.skillID + "]";
    }
}