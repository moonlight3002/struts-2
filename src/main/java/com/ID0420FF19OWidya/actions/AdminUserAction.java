package com.ID0420FF19OWidya.actions;

import com.ID0420FF19OWidya.util.sendEmail;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.User;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

public class AdminUserAction extends ActionSupport {
	private List<User> users;
	private int fromID;
	private int toID;
	private String fromDateCreated;
	private String toDateCreated;
	private String firstName;
	private String lastName;
	private String email;
	private int offset;
	private int deleted;
	public String column;
	public String datatoUpdate;
	private User user;
	private int updateResult;
	private String recipientEmail;
	private String subject;
	private String message;
	private int emailResult;
	private String toUser;

	public AdminUserAction() {
		this.fromID = 0;
		this.toID = 0;
		this.offset = 0;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(final String toUser) {
		this.toUser = toUser;
	}

	public int getEmailResult() {
		return this.emailResult;
	}

	public void setEmailResult(final int emailResult) {
		this.emailResult = emailResult;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getRecipientEmail() {
		return this.recipientEmail;
	}

	public void setRecipientEmail(final String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public int getUpdateResult() {
		return this.updateResult;
	}

	public void setUpdateResult(final int updateResult) {
		this.updateResult = updateResult;
	}

	public String getDatatoUpdate() {
		return this.datatoUpdate;
	}

	public void setDatatoUpdate(final String datatoUpdate) {
		this.datatoUpdate = datatoUpdate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public String getColumn() {
		return this.column;
	}

	public void setColumn(final String column) {
		this.column = column;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(final int deleted) {
		this.deleted = deleted;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setOffset(final int offset) {
		this.offset = offset;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public int getFromID() {
		return this.fromID;
	}

	public void setFromID(final int fromID) {
		this.fromID = fromID;
	}

	public int getToID() {
		return this.toID;
	}

	public void setToID(final int toID) {
		this.toID = toID;
	}

	public String getFromDateCreated() {
		return this.fromDateCreated;
	}

	public void setFromDateCreated(final String fromDateCreated) {
		this.fromDateCreated = fromDateCreated;
	}

	public String getToDateCreated() {
		return this.toDateCreated;
	}

	public void setToDateCreated(final String toDateCreated) {
		this.toDateCreated = toDateCreated;
	}

	public String adminGetUserList() {
		final Dao dao = new Dao();
		System.out.println(String.valueOf(this.fromID + this.toID) + this.fromDateCreated + this.toDateCreated
				+ this.firstName + this.lastName + this.email + this.deleted + this.offset);
		this.users = (List<User>) dao.getUserList(this.fromID, this.toID, this.fromDateCreated, this.toDateCreated,
				this.firstName, this.lastName, this.email, this.deleted, this.offset);
		return "success";
	}

	public String adminUpdateUserColumn() {
		final Dao dao = new Dao();
		this.updateResult = dao.updateUserColumn(this.user.getUserID(), this.datatoUpdate, this.column);
		return "success";
	}

	public String adminDeactivateUsers() {
		final Dao dao = new Dao();
		this.updateResult = dao.DeactivateUser((List) this.users);
		return "success";
	}

	public String sendBulkMails() {
		String result = "";
		if (this.toUser.equals("all")) {
			final Dao dao = new Dao();
			this.recipientEmail = dao.getAllEmail();
			if (!this.recipientEmail.isEmpty()) {
				result = sendEmail.sendemail(this.recipientEmail, this.subject, this.message);
			}
		} else if (this.toUser.equals("selected")) {
			result = sendEmail.sendemail(this.recipientEmail, this.subject, this.message);
		}
		System.out.println(result);
		if (result.equals("success")) {
			this.emailResult = 1;
		} else {
			this.emailResult = 0;
		}
		System.out.println(this.emailResult);
		return result;
	}
}