package com.ID0420FF19OWidya.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userID;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String dob;
	private String bio;
	private String profilePic;
	private String profilePicBase64;
	private String headerPic;
	private String headerPicBase64;
	private Boolean deleted;
	private String datecreated;
	private String facebook;
	private String twitter;
	private String instagram;
	private String youtube;
	private String github;
	private String role;
	private String lastLogin;
	private List<Skill> skills;
	private List<Experience> experiences;
	private List<Education> educations;
	private int followerCount;
	private int followingCount;

	public User() {
		this.skills = new ArrayList<Skill>();
		this.experiences = new ArrayList<Experience>();
		this.educations = new ArrayList<Education>();
	}

	public String getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(final String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public int getFollowerCount() {
		return this.followerCount;
	}

	public void setFollowerCount(final int followerCount) {
		this.followerCount = followerCount;
	}

	public int getFollowingCount() {
		return this.followingCount;
	}

	public void setFollowingCount(final int followingCount) {
		this.followingCount = followingCount;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(final int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(final String dob) {
		this.dob = dob;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(final String bio) {
		this.bio = bio;
	}

	public String getProfilePic() {
		return this.profilePic;
	}

	public void setProfilePic(final String profilePic) {
		this.profilePic = profilePic;
	}

	public String getProfilePicBase64() {
		return this.profilePicBase64;
	}

	public void setProfilePicBase64(final String profilePicBase64) {
		this.profilePicBase64 = profilePicBase64;
	}

	public String getHeaderPic() {
		return this.headerPic;
	}

	public void setHeaderPic(final String headerPic) {
		this.headerPic = headerPic;
	}

	public String getHeaderPicBase64() {
		return this.headerPicBase64;
	}

	public void setHeaderPicBase64(final String headerPicBase64) {
		this.headerPicBase64 = headerPicBase64;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(final Boolean deleted) {
		this.deleted = deleted;
	}

	public String getDatecreated() {
		return this.datecreated;
	}

	public void setDatecreated(final String datecreated) {
		this.datecreated = datecreated;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(final String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(final String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return this.instagram;
	}

	public void setInstagram(final String instagram) {
		this.instagram = instagram;
	}

	public String getYoutube() {
		return this.youtube;
	}

	public void setYoutube(final String youtube) {
		this.youtube = youtube;
	}

	public String getGithub() {
		return this.github;
	}

	public void setGithub(final String github) {
		this.github = github;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}

	public List<Experience> getExperiences() {
		return this.experiences;
	}

	public void setExperiences(final List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(final List<Education> educations) {
		this.educations = educations;
	}

	@Override
	public String toString() {
		return "User [userID=" + this.userID + ", userName=" + this.userName + ", firstName=" + this.firstName
				+ ", lastName=" + this.lastName + ", password=" + this.password + ", email=" + this.email + ", dob="
				+ this.dob + ", bio=" + this.bio + ", profilePic=" + this.profilePic + ", profilePicBase64="
				+ this.profilePicBase64 + ", headerPic=" + this.headerPic + ", headerPicBase64=" + this.headerPicBase64
				+ ", deleted=" + this.deleted + ", datecreated=" + this.datecreated + ", facebook=" + this.facebook
				+ ", twitter=" + this.twitter + ", instagram=" + this.instagram + ", youtube=" + this.youtube
				+ ", github=" + this.github + ", role=" + this.role + ", lastLogin=" + this.lastLogin + ", skills="
				+ this.skills + ", experiences=" + this.experiences + ", educations=" + this.educations
				+ ", followerCount=" + this.followerCount + ", followingCount=" + this.followingCount + "]";
	}
}