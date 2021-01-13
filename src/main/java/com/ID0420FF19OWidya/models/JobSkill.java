package com.ID0420FF19OWidya.models;

public class JobSkill {
	private String jobSkillID;
	private int jobID;
	private int technologyID;

	public String getJobSkillID() {
		return this.jobSkillID;
	}

	public int getJobID() {
		return this.jobID;
	}

	public void setJobID(final int jobID) {
		this.jobID = jobID;
	}

	public int getTechnologyID() {
		return this.technologyID;
	}

	public void setTechnologyID(final int technologyID) {
		this.technologyID = technologyID;
	}

	@Override
	public String toString() {
		return "JobSkill [jobSkillID=" + this.jobSkillID + ", jobID=" + this.jobID + ", technologyID="
				+ this.technologyID + "]";
	}
}