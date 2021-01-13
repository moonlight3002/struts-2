package com.ID0420FF19OWidya.models;

public class JobQuestion
{
    private int jobQuestionID;
    private int jobID;
    private String question;
    
    public int getJobQuestionID() {
        return this.jobQuestionID;
    }
    
    public void setJobQuestionID(final int jobQuestionID) {
        this.jobQuestionID = jobQuestionID;
    }
    
    public int getJobID() {
        return this.jobID;
    }
    
    public void setJobID(final int jobID) {
        this.jobID = jobID;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final String question) {
        this.question = question;
    }
    
    @Override
    public String toString() {
        return "JobQuestion [jobQuestionID=" + this.jobQuestionID + ", jobID=" + this.jobID + ", question=" + this.question + "]";
    }
}