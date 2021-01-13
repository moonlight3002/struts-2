package com.ID0420FF19OWidya.models;

public class JobAnswer
{
    private String jobAnswerID;
    private String jobApplicationID;
    private int jobQuestionID;
    private String answer;
    private String question;
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final String question) {
        this.question = question;
    }
    
    public String getJobAnswerID() {
        return this.jobAnswerID;
    }
    
    public void setJobAnswerID(final String jobAnswerID) {
        this.jobAnswerID = jobAnswerID;
    }
    
    public String getJobApplicationID() {
        return this.jobApplicationID;
    }
    
    public void setJobApplicationID(final String jobApplicationID) {
        this.jobApplicationID = jobApplicationID;
    }
    
    public int getJobQuestionID() {
        return this.jobQuestionID;
    }
    
    public void setJobQuestionID(final int jobQuestionID) {
        this.jobQuestionID = jobQuestionID;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(final String answer) {
        this.answer = answer;
    }
    
    @Override
    public String toString() {
        return "JobAnswer [jobAnswerID=" + this.jobAnswerID + ", jobApplicationID=" + this.jobApplicationID + ", jobQuestionID=" + this.jobQuestionID + ", answer=" + this.answer + ", question=" + this.question + "]";
    }
}