package com.ID0420FF19OWidya.models;

import java.util.List;

public class Job
{
    private int JobID;
    private int userID;
    private String companyName;
    private String companyInfo;
    private String position;
    private String place;
    private String hours;
    private String jobDescription;
    private String jobRequirements;
    private String deadlineSubmission;
    private String createdDate;
    private String modifyDate;
    private String notes;
    private boolean show;
    private boolean deleted;
    private City city;
    private Country country;
    private List<JobSkill> jobSkills;
    private List<JobQuestion> jobQuestions;
    private String requiredSkill;
    private Boolean hasApply;
    
    
    public Job() {
        this.companyName = "";
        this.position = "";
        this.show = true;
        this.deleted = false;
    }
    
    
    
    public Boolean getHasApply() {
		return hasApply;
	}



	public void setHasApply(Boolean hasApply) {
		this.hasApply = hasApply;
	}



	public String getRequiredSkill() {
        return this.requiredSkill;
    }
    
    public void setRequiredSkill(final String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }
    
    public City getCity() {
        return this.city;
    }
    
    public void setCity(final City city) {
        this.city = city;
    }
    
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(final Country country) {
        this.country = country;
    }
    
    public int getJobID() {
        return this.JobID;
    }
    
    public void setJobID(final int jobID) {
        this.JobID = jobID;
    }
    
    public int getUserID() {
        return this.userID;
    }
    
    public void setUserID(final int userID) {
        this.userID = userID;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }
    
    public String getCompanyInfo() {
        return this.companyInfo;
    }
    
    public void setCompanyInfo(final String companyInfo) {
        this.companyInfo = companyInfo;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }
    
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(final String place) {
        this.place = place;
    }
    
    public String getHours() {
        return this.hours;
    }
    
    public void setHours(final String hours) {
        this.hours = hours;
    }
    
    public String getJobDescription() {
        return this.jobDescription;
    }
    
    public void setJobDescription(final String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
    public String getJobRequirements() {
        return this.jobRequirements;
    }
    
    public void setJobRequirements(final String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }
    
    public String getDeadlineSubmission() {
        return this.deadlineSubmission;
    }
    
    public void setDeadlineSubmission(final String deadlineSubmission) {
        this.deadlineSubmission = deadlineSubmission;
    }
    
    public String getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(final String modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(final String notes) {
        this.notes = notes;
    }
    
    public boolean isShow() {
        return this.show;
    }
    
    public void setShow(final boolean show) {
        this.show = show;
    }
    
    public boolean isDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }
    
    public List<JobSkill> getJobSkills() {
        return this.jobSkills;
    }
    
    public void setJobSkills(final List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }
    
    public List<JobQuestion> getJobQuestions() {
        return this.jobQuestions;
    }
    
    public void setJobQuestions(final List<JobQuestion> jobQuestions) {
        this.jobQuestions = jobQuestions;
    }



	@Override
	public String toString() {
		return "Job [JobID=" + JobID + ", userID=" + userID + ", companyName=" + companyName + ", companyInfo="
				+ companyInfo + ", position=" + position + ", place=" + place + ", hours=" + hours + ", jobDescription="
				+ jobDescription + ", jobRequirements=" + jobRequirements + ", deadlineSubmission=" + deadlineSubmission
				+ ", createdDate=" + createdDate + ", modifyDate=" + modifyDate + ", notes=" + notes + ", show=" + show
				+ ", deleted=" + deleted + ", city=" + city + ", country=" + country + ", jobSkills=" + jobSkills
				+ ", jobQuestions=" + jobQuestions + ", requiredSkill=" + requiredSkill + ", hasApply=" + hasApply
				+ "]";
	}
    
    
}