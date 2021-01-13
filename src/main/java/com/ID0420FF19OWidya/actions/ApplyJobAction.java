package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ID0420FF19OWidya.util.uploadFile;
import com.ID0420FF19OWidya.dao.Dao;
import java.io.File;
import com.ID0420FF19OWidya.models.User;
import com.ID0420FF19OWidya.models.Country;
import java.util.List;
import com.ID0420FF19OWidya.models.JobApplication;
import com.ID0420FF19OWidya.models.Job;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class ApplyJobAction extends ActionSupport implements Preparable
{
    private Job jobDetails;
    private JobApplication jobApplicationData;
    private int jobID;
    private List<Country> countries;
    private User sessionuser;
    private File applicationAttachment;
    private String applicationAttachmentFileName;
    private String applicationAttachmentContentType;
    private String directory;
    
    public File getApplicationAttachment() {
        return this.applicationAttachment;
    }
    
    public void setApplicationAttachment(final File applicationAttachment) {
        this.applicationAttachment = applicationAttachment;
    }
    
    public String getApplicationAttachmentFileName() {
        return this.applicationAttachmentFileName;
    }
    
    public void setApplicationAttachmentFileName(final String applicationAttachmentFileName) {
        this.applicationAttachmentFileName = applicationAttachmentFileName;
    }
    
    public String getApplicationAttachmentContentType() {
        return this.applicationAttachmentContentType;
    }
    
    public void setApplicationAttachmentContentType(final String applicationAttachmentContentType) {
        this.applicationAttachmentContentType = applicationAttachmentContentType;
    }
    
    public List<Country> getCountries() {
        return this.countries;
    }
    
    public void setCountries(final List<Country> countries) {
        this.countries = countries;
    }
    
    public Job getJobDetails() {
        return this.jobDetails;
    }
    
    public void setJobDetails(final Job jobDetails) {
        this.jobDetails = jobDetails;
    }
    
    public JobApplication getJobApplicationData() {
        return this.jobApplicationData;
    }
    
    public void setJobApplicationData(final JobApplication jobApplicationData) {
        this.jobApplicationData = jobApplicationData;
    }
    
    public int getJobID() {
        return this.jobID;
    }
    
    public void setJobID(final int jobID) {
        this.jobID = jobID;
    }
    
    @org.apache.struts2.interceptor.validation.SkipValidation
    public String viewJobApplicationForm() {
        System.out.println("--in ApplyJobAction viewJobApplicationForm");
        Dao dao = new Dao();
        Boolean isValidJobPost = dao.checkJobPostStatus(jobID);
        Boolean isApplicationExist = dao.checkApplicantStatus(jobID, sessionuser.getUserID());
        if(isValidJobPost) {
        	if(!(isApplicationExist)) {
                (this.jobDetails = dao.viewJobPostPublic(this.jobID, Boolean.valueOf(true), Boolean.valueOf(false))).setJobQuestions(dao.getQuestion(this.jobID));
                System.out.println("jobdetails:" + this.jobDetails);
                this.countries = (List<Country>)dao.getCountries();
                return "success";
        	}else {
        		return "applicationexist";
        	}

        }else {
        	return "invalidJobPost";
        }

    }
    
    public String submitApplication() {
    	System.out.println("--in ApplyJobAction submitApplication");
        System.out.println("jobApplicationData" + this.jobApplicationData);
        System.out.println("File name" + this.applicationAttachmentFileName);
        Dao dao = new Dao();
        Boolean isValidJobPost = dao.checkJobPostStatus(jobApplicationData.getJobPostID());
        if(isValidJobPost) {
		        if (this.applicationAttachment != null) {
		            final String filename = "application_" + this.sessionuser.getUserID() + "_" + System.currentTimeMillis() + ".pdf";
		            final String path = String.valueOf(this.directory) + this.sessionuser.getUserID();
		            final boolean isUploadSuccess = uploadFile.upload(this.applicationAttachment, filename, path);
		            if (isUploadSuccess) {
		                this.jobApplicationData.setAttachment(filename);
		            }
		        }
		        this.jobApplicationData.setJobApplicationUser(this.sessionuser);
		        final int rowsAffected = dao.insertJobApplication(this.jobApplicationData, this.jobDetails.getUserID());
		        System.out.println(rowsAffected);
		        if(rowsAffected>0) {
			        return "success";
		        }else {
		        	return "error";
		        }
        }else {
        	return "invalidJobPost";
        }
    }
    
    public void prepare() throws Exception {
        this.jobApplicationData = new JobApplication();
        this.sessionuser = (User) ActionContext.getContext().getSession().get("userData");
        this.directory = this.getText("path.directory");
    }
}



