package com.ID0420FF19OWidya.actions;

import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.Technology;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

public class AdminModifyTechnologyAction extends ActionSupport {
	private List<Technology> technologies;
	private Technology tech;
	private int updateResult;
	private int deleteResult;
	private int addResult;

	public int getAddResult() {
		return this.addResult;
	}

	public void setAddResult(final int addResult) {
		this.addResult = addResult;
	}

	public int getUpdateResult() {
		return this.updateResult;
	}

	public void setUpdateResult(final int updateResult) {
		this.updateResult = updateResult;
	}

	public int getDeleteResult() {
		return this.deleteResult;
	}

	public void setDeleteResult(final int deleteResult) {
		this.deleteResult = deleteResult;
	}

	public List<Technology> getTechnologies() {
		return this.technologies;
	}

	public void setTechnologies(final List<Technology> technologies) {
		this.technologies = technologies;
	}

	public Technology getTech() {
		return this.tech;
	}

	public void setTech(final Technology tech) {
		this.tech = tech;
	}

	public String adminGetTechnologyList() {
		System.out.println("--in AdminModifyTechnology adminGetTechnologyList");
		final Dao dao = new Dao();
		this.technologies = (List<Technology>) dao.getTechnologyLists();
		return "success";
	}

	public String adminUpdateTechnology() {
		System.out.println("--in AdminModifyTechnology adminUpdateTechnology");
		final Dao dao = new Dao();
		this.updateResult = dao.updateTechnology(this.tech);
		return "success";
	}

	public String adminDeleteTechnology() {
		System.out.println("--in AdminModifyTechnology adminDeleteTechnology");
		final Dao dao = new Dao();
		this.deleteResult = dao.deleteTechnology((List) this.technologies);
		return "success";
	}

	public String adminAddTechnology() {
		System.out.println("--in AdminModifyTechnology adminAddTechnology");
		final Dao dao = new Dao();
		this.addResult = dao.addTechnology(this.tech);
		return "success";
	}
}