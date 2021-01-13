package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import com.ID0420FF19OWidya.dao.Dao;
import java.io.InputStream;
import com.ID0420FF19OWidya.models.JobApplication;
import com.ID0420FF19OWidya.models.Job;
import java.util.List;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class ViewMyJobAction extends ActionSupport implements Preparable
{
    private User sessionUser;
    private List<Job> myJobLists;
    private List<JobApplication> applicantLists;
    private JobApplication jobApplication;
    private Job job;
    private String directory;
    private InputStream inputStream;
    
    public List<JobApplication> getApplicantLists() {
        return this.applicantLists;
    }
    
    public void setApplicantLists(final List<JobApplication> applicantLists) {
        this.applicantLists = applicantLists;
    }
    
    public JobApplication getJobApplication() {
        return this.jobApplication;
    }
    
    public void setJobApplication(final JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }
    
    public Job getJob() {
        return this.job;
    }
    
    public void setJob(final Job job) {
        this.job = job;
    }
    
    public InputStream getInputStream() {
        return this.inputStream;
    }
    
    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public List<Job> getMyJobLists() {
        return this.myJobLists;
    }
    
    public void setMyJobLists(final List<Job> myJobLists) {
        this.myJobLists = myJobLists;
    }
    
    public String execute() {
        System.out.println("in viewMyJobAction execute");
        final Dao dao = new Dao();
        this.myJobLists = (List<Job>)dao.getMyJobLists(this.sessionUser.getUserID(), Boolean.valueOf(false));
        System.out.println("myjobLists" + this.myJobLists);
        return "success";
    }
    
    public String viewApplicantLists() {
        System.out.println("in viewMyJobAction viewApplicantLists");
        System.out.println(this.job);
        final Dao dao = new Dao();
        this.applicantLists = (List<JobApplication>)dao.viewJobApplicants(this.sessionUser.getUserID(), this.job);
        System.out.println("applicantLists" + this.applicantLists);
        return "success";
    }
    
    public String viewApplication() {
        System.out.println("in viewMyJobAction viewApplication");
        final Dao dao = new Dao();
        this.jobApplication = dao.viewJobApplication(this.sessionUser.getUserID(), this.jobApplication);
        System.out.println("jobApplication" + this.jobApplication);
        return "success";
    }
    
    public String downloadAttachment() {
        System.out.println("in viewMyJobAction downloadAttachment");
        System.out.println("getAttachmentFile");
        System.out.println(this.jobApplication);
        File f = null;
        if (this.jobApplication.getAttachment() != null && !this.jobApplication.getAttachment().isEmpty()) {
            f = new File(String.valueOf(this.directory) + this.jobApplication.getJobApplicationUser().getUserID() + "/" + this.jobApplication.getAttachment());
        }
        if (f.exists() && f != null) {
            try {
                System.out.println(this.jobApplication.getAttachment());
                this.inputStream = new FileInputStream(f);
                if (this.inputStream != null) {
                    return "success";
                }
                return "none";
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                return "success";
            }
           
        }
        return "none";
    }
    
    public void prepare() throws Exception {
        this.directory = this.getText("path.directory");
        this.sessionUser = (User) ActionContext.getContext().getSession().get("userData");
    }
}