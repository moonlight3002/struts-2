package com.ID0420FF19OWidya.actions;

import com.ID0420FF19OWidya.dao.Dao;
import java.util.List;
import com.ID0420FF19OWidya.models.Job;
import com.opensymphony.xwork2.ActionSupport;

public class GetJobAction extends ActionSupport
{
    private Job jobData;
    private List<Job> jobLists;
    private int offset;
    
    public Job getJobData() {
        return this.jobData;
    }
    
    public void setJobData(final Job jobData) {
        this.jobData = jobData;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public List<Job> getJobLists() {
        return this.jobLists;
    }
    
    public void setJobLists(final List<Job> jobLists) {
        this.jobLists = jobLists;
    }
    
    public String getJobList() {
        System.out.println("in getJobAction getJobList");
        System.out.println(this.jobData);
        final Dao dao = new Dao();
        this.jobLists = (List<Job>)dao.getJobList(this.jobData, this.offset);
        return "success";
    }
}