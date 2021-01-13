package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.User;
import com.ID0420FF19OWidya.util.regexInput;
import com.ID0420FF19OWidya.util.validateInput;
import com.ID0420FF19OWidya.models.Technology;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ID0420FF19OWidya.models.Job;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class HiringAction extends ActionSupport implements Preparable, ModelDriven<Job> {
	private Job jobPost;
	private List<Technology> technologies;
	private User sessionUser;

	public Job getJobPost() {
		return this.jobPost;
	}

	public void setJobPost(final Job jobPost) {
		this.jobPost = jobPost;
	}

	public List<Technology> getTechnologies() {
		return this.technologies;
	}

	public void setTechnologies(final List<Technology> technologies) {
		this.technologies = technologies;
	}

	public void validate() {
		System.out.println("-in HiringAction validate");
		final Dao dao = new Dao();
		System.out.println(jobPost);
		if (StringUtils.isEmpty(jobPost.getCompanyName())) {
			System.out.println(this.getText("companyName.required"));
			this.addFieldError("companyName", "required");
		}
		if (jobPost.getCity() == null || jobPost.getCity().getCityID() <= 0) {
			System.out.println(this.getText("cityID.required"));
			this.addFieldError("city.cityID", "required");
		}
		if (jobPost.getCountry() == null || jobPost.getCountry().getCountryID() <= 0) {
			System.out.println(this.getText("countryID.required"));
			this.addFieldError("country.countryID", "required");
		}
		if (StringUtils.isEmpty(jobPost.getCompanyInfo())) {
			System.out.println(this.getText("companyInfo.required"));
			this.addFieldError("companyInfo", "required");
		}
		if (StringUtils.isEmpty(jobPost.getPosition())) {
			System.out.println(this.getText("position.required"));
			this.addFieldError("position", "required");
		}
		if (StringUtils.isEmpty(jobPost.getPlace())) {
			System.out.println(this.getText("place.required"));
			this.addFieldError("place", "required");
		}
		if (StringUtils.isEmpty(jobPost.getJobDescription())) {
			System.out.println(this.getText("jobDescription.required"));
			this.addFieldError("jobDescription", "required");
		}
		if (StringUtils.isEmpty(jobPost.getJobRequirements())) {
			System.out.println(this.getText("jobRequirements.required"));
			this.addFieldError("jobRequirements", "required");
		}
		if (StringUtils.isEmpty(jobPost.getDeadlineSubmission())) {
			System.out.println(this.getText("deadlineSubmission.required"));
			this.addFieldError("deadlineSubmission", "required");
		} else {
			Boolean isValidDate = validateInput.validateJobPostDeadline(jobPost.getDeadlineSubmission());
			if (isValidDate == false) {
				System.out.println(this.getText("date not valid"));
				this.addFieldError("deadlineSubmission", "not valid date");
			}
		}

	}

//    
	@org.apache.struts2.interceptor.validation.SkipValidation
	public String execute() {
		System.out.println("in hireAction Execute");
		return "success";
	}

	public String postJob() {
		System.out.println("in hireAction postJob");
		System.out.println("user" + this.sessionUser);
		System.out.println("jobPost" + this.jobPost);
		Boolean isValid = regexInput.checkHiringInput(jobPost);
		if (isValid) {
			final Dao dao = new Dao();
			final int rowsAffected = dao.insertJobPost(this.sessionUser, this.jobPost);
			if (rowsAffected > 0) {
				return "success";
			} else {
				return INPUT;
			}
		} else {
			return INPUT;
		}
	}

	public Job getModel() {
		System.out.println("in getmodel");
		return this.jobPost;
	}

	public void prepare() throws Exception {
		System.out.println("in preparable");
		this.jobPost = new Job();
		final Dao dao = new Dao();
		this.technologies = (List<Technology>) dao.getTechnologyLists();
		this.sessionUser = (User) ActionContext.getContext().getSession().get("userData");
	}
}