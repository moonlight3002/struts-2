package com.ID0420FF19OWidya.models;

import java.util.List;

public class JobApplication
{
    private String jobApplicationID;
    private User jobApplicationUser;
    private int jobPostID;
    private String phoneNo;
    private int phoneCode;
    private String jobApplyDate;
    private String modifyDate;
    private String aboutApplicant;
    private String attachment;
    private boolean read;
    private List<JobAnswer> jobAnswers;
    private Job jobPost;
    
    public JobApplication() {
        this.read = false;
    }
    
    public Job getJobPost() {
        return this.jobPost;
    }
    
    public void setJobPost(final Job jobPost) {
        this.jobPost = jobPost;
    }
    
    public List<JobAnswer> getJobAnswers() {
        return this.jobAnswers;
    }
    
    public void setJobAnswers(final List<JobAnswer> jobAnswers) {
        this.jobAnswers = jobAnswers;
    }
    
    public String getJobApplicationID() {
        return this.jobApplicationID;
    }
    
    public void setJobApplicationID(final String jobApplicationID) {
        this.jobApplicationID = jobApplicationID;
    }
    
    public User getJobApplicationUser() {
        return this.jobApplicationUser;
    }
    
    public void setJobApplicationUser(final User jobApplicationUser) {
        this.jobApplicationUser = jobApplicationUser;
    }
    
    public int getJobPostID() {
        return this.jobPostID;
    }
    
    public void setJobPostID(final int jobPostID) {
        this.jobPostID = jobPostID;
    }
    
    public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public int getPhoneCode() {
        return this.phoneCode;
    }
    
    public void setPhoneCode(final int phoneCode) {
        this.phoneCode = phoneCode;
    }
    
    public String getJobApplyDate() {
        return this.jobApplyDate;
    }
    
    public void setJobApplyDate(final String jobApplyDate) {
        this.jobApplyDate = jobApplyDate;
    }
    
    public String getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(final String modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public String getAboutApplicant() {
        return this.aboutApplicant;
    }
    
    public void setAboutApplicant(final String aboutApplicant) {
        this.aboutApplicant = aboutApplicant;
    }
    
    public String getAttachment() {
        return this.attachment;
    }
    
    public void setAttachment(final String attachment) {
        this.attachment = attachment;
    }
    
    public boolean isRead() {
        return this.read;
    }
    
    public void setRead(final boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        return "JobApplication [jobApplicationID=" + this.jobApplicationID + ", jobApplicationUser=" + this.jobApplicationUser + ", jobPostID=" + this.jobPostID + ", phoneNo=" + this.phoneNo + ", phoneCode=" + this.phoneCode + ", jobApplyDate=" + this.jobApplyDate + ", modifyDate=" + this.modifyDate + ", aboutApplicant=" + this.aboutApplicant + ", attachment=" + this.attachment + ", read=" + this.read + ", jobAnswers=" + this.jobAnswers + ", jobPost=" + this.jobPost + "]";
    }
}