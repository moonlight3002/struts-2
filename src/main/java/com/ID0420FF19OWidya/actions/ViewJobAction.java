package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.User;
import com.ID0420FF19OWidya.models.Job;
import com.ID0420FF19OWidya.models.Technology;
import java.util.List;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class ViewJobAction extends ActionSupport implements Preparable
{
    private List<Technology> technologyLists;
    private Job job;
    private User sessionUser;
    
    public Job getJob() {
        return this.job;
    }
    
    public void setJob(final Job job) {
        this.job = job;
    }
    
    public List<Technology> getTechnologyLists() {
        return this.technologyLists;
    }
    
    public void setTechnologyLists(final List<Technology> technologyLists) {
        this.technologyLists = technologyLists;
    }
    
    public String execute() {
        System.out.println("--in viewJobAction execute");
        return "success";
    }
    
    public String viewJob() {
        System.out.println("--in viewJobAction viewJob");
        final Dao dao = new Dao();
        this.sessionUser = (User) ActionContext.getContext().getSession().get("userData");
        if (this.job != null) {
            if (this.sessionUser != null) {
                if (this.sessionUser.getRole().equals("admin")) {
                    this.job = dao.viewJobPostAdmin(this.job.getJobID());
                }
                else if (this.sessionUser.getUserID() == this.job.getUserID()) {
                    this.job = dao.viewJobPostPrivate(this.job.getJobID(), Boolean.valueOf(false));
                }
                else {
                    this.job = dao.viewJobPostPublic(this.job.getJobID(), Boolean.valueOf(true), Boolean.valueOf(false));
                }
            	Boolean hasApply = dao.checkApplicantStatus(job.getJobID(), sessionUser.getUserID());
            	job.setHasApply(hasApply);
            	System.out.println("has apply?"+ hasApply);
            }
            else {
                this.job = dao.viewJobPostPublic(this.job.getJobID(), Boolean.valueOf(true), Boolean.valueOf(false));
                job.setHasApply(false);
            }
            System.out.println("jobdetails:" + this.job);
            return "success";
        }
        return "none";
    }
    
    public void prepare() throws Exception {
        final Dao dao = new Dao();
        this.technologyLists = (List<Technology>)dao.getTechnologyLists();
    }
}