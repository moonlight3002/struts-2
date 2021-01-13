package com.ID0420FF19OWidya.models;

public class Technology {
	private int technologyID;
	private String technologyName;

	public int getTechnologyID() {
		return this.technologyID;
	}

	public void setTechnologyID(final int technologyID) {
		this.technologyID = technologyID;
	}

	public String getTechnologyName() {
		return this.technologyName;
	}

	public void setTechnologyName(final String technologyName) {
		this.technologyName = technologyName;
	}

	@Override
	public String toString() {
		return "Technology [technologyID=" + this.technologyID + ", technologyName=" + this.technologyName + "]";
	}
}