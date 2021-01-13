package com.ID0420FF19OWidya.dao;

import java.util.Iterator;
import com.ID0420FF19OWidya.models.Activity;
import com.ID0420FF19OWidya.models.Follow;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ID0420FF19OWidya.models.Skill;
import com.ID0420FF19OWidya.models.Education;
import com.ID0420FF19OWidya.models.Experience;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.List;
import java.sql.Statement;
import com.ID0420FF19OWidya.models.Notification;
import com.ID0420FF19OWidya.models.User;

public class ProfileDao {
	/**
	 * @param sessionUser
	 * @param userName
	 * @param visibility
	 * @return
	 */
	public User showProfile(final User sessionUser, final String userName, final int visibility) {
		User user = new User();
		System.out.println("-user: " + user);
		user.setUserName(userName);
		System.out.println("-user: " + user);
		final Connection conn = DBConnection.getConnection();
		final PreparedStatement myPreStat = null;
		System.out.println("-user: " + user);
		List<Experience> ex = null;
		List<Education> ed = null;
		List<Skill> sk = null;
		System.out.println("user in Dao before: " + user);
		switch (visibility) {
		case 3: {
			user = this.showUserData(conn, myPreStat, user, false);
			ex = this.showExperience(conn, myPreStat, user.getUserID(), false, true);
			ed = this.showEducation(conn, myPreStat, user.getUserID(), false, true);
			sk = this.showSkill(conn, myPreStat, user.getUserID(), false, true);
			user.setFollowerCount(this.getTotalFollowerCount(conn, myPreStat, user.getUserID()));
			user.setFollowingCount(this.getTotalFollowingCount(conn, myPreStat, user.getUserID()));
			if (!sessionUser.getUserName().equals(user.getUserName())) {
				final Notification notif = new Notification(user.getUserID(), sessionUser.getUserID(),
						sessionUser.getUserName(), "viewprofile", "<b>" + String.valueOf(sessionUser.getFirstName())
								+ " " + sessionUser.getLastName() + "</b>" + " " + "view your profile");
				final Dao dao = new Dao();
				dao.updateNotification(conn, myPreStat, notif);
			}
			DBConnection.close(myPreStat, conn);
			break;
		}
		case 2: {
			user = this.showUserData(conn, myPreStat, user, false);
			ex = this.showExperience(conn, myPreStat, user.getUserID(), false);
			ed = this.showEducation(conn, myPreStat, user.getUserID(), false);
			sk = this.showSkill(conn, myPreStat, user.getUserID(), false);
			user.setFollowerCount(this.getTotalFollowerCount(conn, myPreStat, user.getUserID()));
			user.setFollowingCount(this.getTotalFollowingCount(conn, myPreStat, user.getUserID()));
			DBConnection.close(myPreStat, conn);
			break;
		}
		case 1: {
			user = this.showUserData(conn, myPreStat, user);
			ex = this.showExperience(conn, myPreStat, user.getUserID());
			ed = this.showEducation(conn, myPreStat, user.getUserID());
			sk = this.showSkill(conn, myPreStat, user.getUserID());
			user.setFollowerCount(this.getTotalFollowerCount(conn, myPreStat, user.getUserID()));
			user.setFollowingCount(this.getTotalFollowingCount(conn, myPreStat, user.getUserID()));
			DBConnection.close(myPreStat, conn);
			break;
		}
		}
		user.setExperiences((List) ex);
		user.setEducations((List) ed);
		user.setSkills((List) sk);
		System.out.println("user in dao" + user);
		return user;
	}

	public User getUserSkills(final User user, final int visibility) {
		final Connection conn = DBConnection.getConnection();
		final PreparedStatement myPreStat = null;
		System.out.println("-user: " + user);
		List<Skill> sk = null;
		System.out.println("user in Dao before: " + user);
		switch (visibility) {
		case 2: {
			sk = this.showSkill(conn, myPreStat, user.getUserID(), false);
			DBConnection.close(myPreStat, conn);
			break;
		}
		case 1: {
			sk = this.showSkill(conn, myPreStat, user.getUserID());
			DBConnection.close(myPreStat, conn);
			break;
		}
		}
		user.setSkills((List) sk);
		System.out.println("user in dao" + user);
		return user;
	}

	public User getUserEducations(final User user, final int visibility) {
		final Connection conn = DBConnection.getConnection();
		final PreparedStatement myPreStat = null;
		System.out.println("-user: " + user);
		List<Education> ed = null;
		System.out.println("user in Dao before: " + user);
		switch (visibility) {
		case 2: {
			ed = this.showEducation(conn, myPreStat, user.getUserID(), false);
			DBConnection.close(myPreStat, conn);
			break;
		}
		case 1: {
			ed = this.showEducation(conn, myPreStat, user.getUserID());
			DBConnection.close(myPreStat, conn);
			break;
		}
		}
		user.setEducations((List) ed);
		System.out.println("user in dao" + user);
		return user;
	}

	public User getUserExperiences(final User user, final int visibility) {
		final Connection conn = DBConnection.getConnection();
		final PreparedStatement myPreStat = null;
		System.out.println("-user: " + user);
		List<Experience> ex = null;
		System.out.println("user in Dao before: " + user);
		switch (visibility) {
		case 2: {
			ex = this.showExperience(conn, myPreStat, user.getUserID(), false);
			DBConnection.close(myPreStat, conn);
			break;
		}
		case 1: {
			ex = this.showExperience(conn, myPreStat, user.getUserID());
			DBConnection.close(myPreStat, conn);
			break;
		}
		}
		user.setExperiences((List) ex);
		System.out.println("user in dao" + user);
		return user;
	}

	public User getUserAbout(User user) {
		final Connection conn = DBConnection.getConnection();
		final PreparedStatement myPreStat = null;
		System.out.println("-user: " + user);
		user = this.showUserData(conn, myPreStat, user);
		System.out.println("user in dao" + user);
		return user;
	}

	public User showUserData(final Connection conn, PreparedStatement myPreStat, final User user,
			final Boolean deleted) {
		final String query = "Select user_id, first_name, last_name, DATE_FORMAT(DOB, '%d/%m/%Y') as DOB, profile_picture, header_picture, bio, facebook, instagram, youtube, twitter, github from user where user_name = ? and deleted = ?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getUserName());
			myPreStat.setBoolean(2, deleted);
			final ResultSet myrs = myPreStat.executeQuery();
			this.setShowUsetData(myrs, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User showUserData(final Connection conn, PreparedStatement myPreStat, final User user) {
		final String query = "Select user_id, first_name, last_name, DATE_FORMAT(DOB, '%d/%m/%Y') as DOB, profile_picture, header_picture, bio, facebook, instagram, youtube, twitter, github from user where user_name = ?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getUserName());
			final ResultSet myrs = myPreStat.executeQuery();
			this.setShowUsetData(myrs, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void setShowUsetData(final ResultSet myrs, final User user) {
		try {
			while (myrs.next()) {
				user.setUserID(myrs.getInt("USER_ID"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setDob(myrs.getString("dob"));
				user.setProfilePic(myrs.getString("profile_picture"));
				user.setHeaderPic(myrs.getString("header_picture"));
				user.setBio(myrs.getString("bio"));
				user.setFacebook(myrs.getString("facebook"));
				user.setTwitter(myrs.getString("twitter"));
				user.setInstagram(myrs.getString("instagram"));
				user.setYoutube(myrs.getString("youtube"));
				user.setGithub(myrs.getString("github"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Skill> showSkill(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted, final boolean isShow) {
		final List<Skill> sk = new ArrayList<Skill>();
		final String query = "select s.*, t.technology_name from skill as s left join technology as t on s.skill_technology_ID = t.technology_ID where s.skill_user_id=? and s.is_show =? and s.deleted=?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, isShow);
			myPreStat.setBoolean(3, deleted);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getSkillData(sk, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sk;
	}

	public List<Skill> showSkill(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted) {
		final List<Skill> sk = new ArrayList<Skill>();
		final String query = "select s.*, t.technology_name from skill as s left join technology as t on s.skill_technology_ID = t.technology_ID where s.skill_user_id=? and s.deleted=? order by is_show;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, deleted);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getSkillData(sk, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sk;
	}

	public List<Skill> showSkill(final Connection conn, PreparedStatement myPreStat, final int userID) {
		final List<Skill> sk = new ArrayList<Skill>();
		final String query = "select s.*, t.technology_name from skill as s left join technology as t on s.skill_technology_ID = t.technology_ID where s.skill_user_id=? order by deleted, is_show;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getSkillData(sk, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sk;
	}

	private void getSkillData(final List<Skill> sk, final ResultSet myrs) {
		try {
			while (myrs.next()) {
				final Skill skill = new Skill();
				skill.setSkillID(myrs.getString("skill_ID"));
				skill.setUserID(myrs.getInt("skill_USER_ID"));
				skill.setTechnologyID(myrs.getInt("skill_TECHNOLOGY_ID"));
				skill.setTechnologyName(myrs.getString("technology_name"));
				skill.setSkillLevel(myrs.getString("skill_level"));
				skill.setShow(myrs.getBoolean("is_show"));
				skill.setDeleted(myrs.getBoolean("deleted"));
				System.out.println(skill);
				sk.add(skill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Education> showEducation(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted, final boolean isShow) {
		final List<Education> ed = new ArrayList<Education>();
		final String query = "select * from education where education_user_ID = ? and is_show = ? and deleted = ?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, isShow);
			myPreStat.setBoolean(3, deleted);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getEducationData(ed, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ed;
	}

	public List<Education> showEducation(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted) {
		final List<Education> ed = new ArrayList<Education>();
		final String query = "select * from education where education_user_id = ? and deleted = ? order by is_show";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, deleted);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getEducationData(ed, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ed;
	}

	public List<Education> showEducation(final Connection conn, PreparedStatement myPreStat, final int userID) {
		final List<Education> ed = new ArrayList<Education>();
		final String query = "select * from education where education_user_id = ? order by deleted, is_show;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getEducationData(ed, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ed;
	}

	private void getEducationData(final List<Education> ed, final ResultSet myrs) {
		try {
			while (myrs.next()) {
				final Education edu = new Education();
				edu.setEducationID(myrs.getInt("EDUCATION_ID"));
				edu.setUserID(myrs.getInt("education_USER_ID"));
				edu.setSchool(myrs.getString("school"));
				edu.setStartYear(myrs.getInt("start_year"));
				edu.setEndYear(myrs.getInt("end_year"));
				edu.setEducationDesc(myrs.getString("education_description"));
				edu.setShow(myrs.getBoolean("is_show"));
				edu.setDegree(myrs.getString("degree"));
				edu.setDeleted(myrs.getBoolean("deleted"));
				ed.add(edu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Experience> showExperience(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted, final boolean isShow) {
		final List<Experience> ex = new ArrayList<Experience>();
		final String query = "select * from experience where experience_user_id = ? and is_show = ? and deleted = ?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, isShow);
			myPreStat.setBoolean(3, deleted);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getExperienceData(ex, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ex;
	}

	public List<Experience> showExperience(final Connection conn, PreparedStatement myPreStat, final int userID,
			final boolean deleted) {
		final List<Experience> ex = new ArrayList<Experience>();
		final String query = "select * from experience where experience_user_id = ? and deleted = ? order by is_show";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setBoolean(2, deleted);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getExperienceData(ex, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ex;
	}

	public List<Experience> showExperience(final Connection conn, PreparedStatement myPreStat, final int userID) {
		final List<Experience> ex = new ArrayList<Experience>();
		final String query = "select * from experience where experience_user_id = ? order by deleted, is_show";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			this.getExperienceData(ex, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ex;
	}

	private void getExperienceData(final List<Experience> ex, final ResultSet myrs) {
		try {
			while (myrs.next()) {
				final Experience exp = new Experience();
				exp.setExperienceID(myrs.getInt("EXPERIENCE_ID"));
				exp.setUserID(myrs.getInt("experience_USER_ID"));
				exp.setPosition(myrs.getString("position"));
				exp.setCompany(myrs.getString("company"));
				exp.setStartYear(myrs.getInt("start_year"));
				exp.setStartMonth(myrs.getInt("start_month"));
				exp.setEndYear(myrs.getInt("end_year"));
				exp.setEndMonth(myrs.getInt("end_month"));
				exp.setExperienceDesc(myrs.getString("experience_description"));
				exp.setShow(myrs.getBoolean("is_show"));
				exp.setDeleted(myrs.getBoolean("deleted"));
				ex.add(exp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getTotalFollowerCount(final Connection conn, PreparedStatement myPreStat, final int userID) {
		int totalFollower = 0;
		final String query = "select count(*) as follower_count from follow where following_USER_ID = ? and follower_user_id>0";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			final ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				totalFollower = myrs.getInt("follower_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalFollower;
	}

	public int getTotalFollowingCount(final Connection conn, PreparedStatement myPreStat, final int userID) {
		int totalFollowing = 0;
		final String query = "select count(*) as following_count from follow where follower_USER_ID = ? and following_user_id>0;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			final ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				totalFollowing = myrs.getInt("following_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalFollowing;
	}

	public boolean isFollowingUserProfile(final int userID, final int sessionUserID) {
		final Connection conn = DBConnection.getConnection();
		boolean isFollowing = false;
		PreparedStatement myPreStat = null;
		final String query = "select * from follow where follower_user_id=? and following_user_id = ?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, sessionUserID);
			myPreStat.setInt(2, userID);
			final ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				isFollowing = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return isFollowing;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return isFollowing;
	}

	public List<User> getFollowLists(final int userID, final String inputValue, final int offset,
			final String directory, final String category) throws IOException {
		final List<User> followerUsers = new ArrayList<User>();
		PreparedStatement myPreStat = null;
		final Connection conn = DBConnection.getConnection();
		final String queryFollowing = "select u.user_name, u.user_id, c.count_follower, u.first_name, u.last_name, u.profile_picture from user as u \r\nright join follow on u.user_id = follow.following_USER_ID \r\nleft join (select following_USER_ID, count(follower_USER_ID) as count_follower from follow group by following_USER_ID) as c on u.USER_ID = c.following_USER_ID\r\nwhere follower_USER_ID=? \r\n and follower_USER_ID>0 and user_id>0\r\nand concat_ws(\" \", u.first_name, u.last_name) like ? limit ?,15;";
		final String queryFollower = "select u.user_name, u.user_id, c.count_follower, u.first_name, u.last_name, u.profile_picture from user as u \r\nright join follow as f on u.user_id = f.follower_USER_ID\r\nleft join (select following_USER_ID, count(follower_USER_ID) as count_follower from follow group by following_USER_ID) as c on u.USER_ID = c.following_USER_ID\r\nwhere f.following_USER_ID=? and f.following_USER_ID>0 and user_id>0\r\nand concat_ws(\" \", u.first_name, u.last_name) like ? limit ?,15;";
		String query = "";
		if (category.equals("follower")) {
			query = queryFollower;
		} else if (category.equals("following")) {
			query = queryFollowing;
		}
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setString(2, "%" + inputValue + "%");
			myPreStat.setInt(3, offset);
			System.out.println(myPreStat);
			final ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				final User user = new User();
				user.setUserID(myrs.getInt("USER_ID"));
				user.setUserName(myrs.getString("user_name"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setFollowerCount(myrs.getInt("count_follower"));
				user.setProfilePic(myrs.getString("profile_picture"));
				followerUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return followerUsers;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return followerUsers;
	}

	public int updateUserData(final String data, final String column, final User user) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "update user set " + column + " = ? where user_id=?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, data);
			myPreStat.setInt(2, user.getUserID());
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int insertFollow(final Follow follow, final User sessionUser) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "insert into follow (follower_USER_ID,following_USER_ID) values(?,?)";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, follow.getFollowerUserID());
			myPreStat.setInt(2, follow.getFollowingUserID());
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			final Notification notif = new Notification(follow.getFollowingUserID(), follow.getFollowerUserID(),
					sessionUser.getUserName(), "follow", "<b>" + String.valueOf(sessionUser.getFirstName()) + " "
							+ sessionUser.getLastName() + "</b>" + " " + "follow you");
			rowsAffected = dao.updateNotification(conn, myPreStat, notif);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteFollow(final Follow follow, final User sessionUser) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "delete from follow where follower_USER_ID = ? and following_USER_ID = ?";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, follow.getFollowerUserID());
			myPreStat.setInt(2, follow.getFollowingUserID());
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			final Notification notif = new Notification(follow.getFollowingUserID(), follow.getFollowerUserID(),
					sessionUser.getUserName(), "unfollow", "<b>" + String.valueOf(sessionUser.getFirstName()) + " "
							+ sessionUser.getLastName() + "</b>" + " " + "unfollow you");
			rowsAffected = dao.updateNotification(conn, myPreStat, notif);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int updateUserAbout(final int userID, final User user) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "update user set first_name=?, last_name=?, dob=STR_TO_DATE( ?, '%d/%m/%Y'), bio=? where user_id=?";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getFirstName());
			myPreStat.setString(2, user.getLastName());
			myPreStat.setString(3, user.getDob());
			myPreStat.setString(4, user.getBio());
			myPreStat.setInt(5, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			rowsAffected = dao.updateActivity(conn, myPreStat, new Activity(userID, "update", "user", "aboutme"));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int updateUserConnection(final int userID, final User user) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "update user set facebook=?, twitter=?, youtube=?, instagram=?, github=? where user_id=?";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getFacebook());
			myPreStat.setString(2, user.getTwitter());
			myPreStat.setString(3, user.getYoutube());
			myPreStat.setString(4, user.getInstagram());
			myPreStat.setString(5, user.getGithub());
			myPreStat.setInt(6, userID);
			rowsAffected = myPreStat.executeUpdate();
			System.out.println(myPreStat);
			rowsAffected = dao.updateActivity(conn, myPreStat, new Activity(userID, "update", "user", "connection"));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int insertExperience(final int userID, final Experience ex) {
		final Connection conn = DBConnection.getConnection();
		int generatedID = 0;
		PreparedStatement myPreStat = null;
		final String[] column = { "EXPERIENCE_ID" };
		final String query = "insert into experience (experience_USER_ID, company, position, start_month, start_year, end_month, end_year, experience_description, is_show) \r\nvalues (?,?,?,?,?,?,?,?,?);";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query, column);
			myPreStat.setInt(1, userID);
			myPreStat.setString(2, ex.getCompany());
			myPreStat.setString(3, ex.getPosition());
			myPreStat.setInt(4, ex.getStartMonth());
			myPreStat.setInt(5, ex.getStartYear());
			myPreStat.setInt(6, ex.getEndMonth());
			myPreStat.setInt(7, ex.getEndYear());
			myPreStat.setString(8, ex.getExperienceDesc());
			myPreStat.setBoolean(9, ex.isShow());
			System.out.println(myPreStat);
			myPreStat.executeUpdate();
			final ResultSet rs = myPreStat.getGeneratedKeys();
			if (rs.next()) {
				generatedID = rs.getInt(1);
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return generatedID;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return generatedID;
	}

	public int insertEducation(final int userID, final Education ed) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		int generatedID = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String[] column = { "EDUCATION_ID" };
		final String query = "insert into education (education_USER_ID, school, degree, start_year, end_year, education_description, is_show) values (?,?,?,?,?,?,?);";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query, column);
			myPreStat.setInt(1, userID);
			myPreStat.setString(2, ed.getSchool());
			myPreStat.setString(3, ed.getDegree());
			myPreStat.setInt(4, ed.getStartYear());
			myPreStat.setInt(5, ed.getEndYear());
			myPreStat.setString(6, ed.getEducationDesc());
			myPreStat.setBoolean(7, ed.isShow());
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			System.out.println("row affected" + rowsAffected);
			final ResultSet rs = myPreStat.getGeneratedKeys();
			if (rs.next()) {
				generatedID = rs.getInt(1);
			}
			rowsAffected = dao.updateActivity(conn, myPreStat,
					new Activity(userID, "create", "education", "education_ID:" + generatedID));
			System.out.println("row affected" + rowsAffected);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return generatedID;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return generatedID;
	}

	public String insertSkill(final int userID, final Skill sk) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		String generatedID = null;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "insert into skill (skill_user_id, skill_technology_id, skill_level, is_show, deleted) values(?,?,?,?,false) \r\non duplicate key update skill_user_id = values(skill_user_id), skill_technology_id = values(skill_technology_id), skill_level= values(skill_level), is_show= values(is_show), deleted= values(deleted);";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setInt(2, sk.getTechnologyID());
			myPreStat.setString(3, sk.getSkillLevel());
			myPreStat.setBoolean(4, sk.isShow());
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			if (rowsAffected > 0) {
				generatedID = "u" + userID + ":" + "t" + sk.getTechnologyID();
			}
			rowsAffected = dao.updateActivity(conn, myPreStat,
					new Activity(userID, "create", "skill", "skill_ID:" + generatedID));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return generatedID;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return generatedID;
	}

	public int updateSkill(final int userID, final List<Skill> skills, final int role) {
		final Connection conn = DBConnection.getConnection();
		Skill sk = new Skill();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "update skill set skill_level=?, is_show=?, deleted=? where skill_id=? and skill_user_id=?";
		System.out.println(query);
		final Iterator<Skill> it = skills.iterator();
		try {
			conn.setAutoCommit(false);
			while (it.hasNext()) {
				sk = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setString(1, sk.getSkillLevel());
				myPreStat.setBoolean(2, sk.isShow());
				myPreStat.setString(4, sk.getSkillID());
				myPreStat.setInt(5, userID);
				if (role == 2) {
					myPreStat.setBoolean(3, false);
				} else if (role == 1) {
					myPreStat.setBoolean(3, sk.isDeleted());
				}
				System.out.println(myPreStat);
				rowsAffected = myPreStat.executeUpdate();
				if (role == 2) {
					rowsAffected = dao.updateActivity(conn, myPreStat,
							new Activity(userID, "update", "skill", "skill_ID:" + sk.getSkillID()));
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int updateExperience(final int userID, final List<Experience> experiences, final int role) {
		final Connection conn = DBConnection.getConnection();
		Experience ex = new Experience();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final Dao dao = new Dao();
		final String query = "update experience set company=?, position=?, start_month=?, start_year=?, end_month=?, end_year=?, \r\nexperience_description=?, is_show=?, deleted = ? where experience_USER_ID=? and EXPERIENCE_ID=?";
		System.out.println(query);
		final Iterator<Experience> it = experiences.iterator();
		try {
			conn.setAutoCommit(false);
			while (it.hasNext()) {
				ex = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setString(1, ex.getCompany());
				myPreStat.setString(2, ex.getPosition());
				myPreStat.setInt(3, ex.getStartMonth());
				myPreStat.setInt(4, ex.getStartYear());
				myPreStat.setInt(5, ex.getEndMonth());
				myPreStat.setInt(6, ex.getEndYear());
				myPreStat.setString(7, ex.getExperienceDesc());
				myPreStat.setBoolean(8, ex.isShow());
				myPreStat.setInt(10, userID);
				myPreStat.setInt(11, ex.getExperienceID());
				if (role == 2) {
					myPreStat.setBoolean(9, false);
				} else if (role == 1) {
					myPreStat.setBoolean(9, ex.isDeleted());
				}
				System.out.println(myPreStat);
				rowsAffected = myPreStat.executeUpdate();
				if (role == 2) {
					rowsAffected = dao.updateActivity(conn, myPreStat,
							new Activity(userID, "update", "experience", "experience_ID:" + ex.getExperienceID()));
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int updateEducation(final int userID, final List<Education> educations, final int role) {
		final Connection conn = DBConnection.getConnection();
		Education ed = new Education();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "update education set school=?, degree=?, start_year=?, end_year=?, education_description=?, is_show=?, deleted = ? \r\nwhere EDUCATION_ID=? and education_USER_ID=?";
		System.out.println(query);
		final Iterator<Education> it = educations.iterator();
		final Dao dao = new Dao();
		try {
			conn.setAutoCommit(false);
			while (it.hasNext()) {
				ed = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setString(1, ed.getSchool());
				myPreStat.setString(2, ed.getDegree());
				myPreStat.setInt(3, ed.getStartYear());
				myPreStat.setInt(4, ed.getEndYear());
				myPreStat.setString(5, ed.getEducationDesc());
				myPreStat.setBoolean(6, ed.isShow());
				myPreStat.setInt(8, ed.getEducationID());
				myPreStat.setInt(9, userID);
				if (role == 2) {
					myPreStat.setBoolean(7, false);
				} else if (role == 1) {
					myPreStat.setBoolean(7, ed.isDeleted());
				}
				System.out.println(myPreStat);
				rowsAffected = myPreStat.executeUpdate();
				if (role == 2) {
					rowsAffected = dao.updateActivity(conn, myPreStat,
							new Activity(userID, "update", "education", "education_ID:" + ed.getEducationID()));
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteEducation(final int userID, final Education education) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "update education set deleted=?\r\nwhere EDUCATION_ID=? and education_USER_ID=?";
		System.out.println(query);
		final Dao dao = new Dao();
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, education.isDeleted());
			myPreStat.setInt(2, education.getEducationID());
			myPreStat.setInt(3, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			rowsAffected = dao.updateActivity(conn, myPreStat,
					new Activity(userID, "delete", "education", "education_ID:" + education.getEducationID()));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteExperience(final int userID, final Experience experience) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "update experience set deleted=?\r\nwhere EXPERIENCE_ID=? and experience_USER_ID=?";
		System.out.println(query);
		final Dao dao = new Dao();
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, experience.isDeleted());
			myPreStat.setInt(2, experience.getExperienceID());
			myPreStat.setInt(3, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			rowsAffected = dao.updateActivity(conn, myPreStat,
					new Activity(userID, "delete", "experience", "experience_ID:" + experience.getExperienceID()));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteSkill(final int userID, final Skill skill) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "update skill set deleted=?\r\nwhere SKILL_ID=? and skill_USER_ID=?";
		System.out.println(query);
		final Dao dao = new Dao();
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, skill.isDeleted());
			myPreStat.setString(2, skill.getSkillID());
			myPreStat.setInt(3, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
			rowsAffected = dao.updateActivity(conn, myPreStat,
					new Activity(userID, "delete", "skill", "skill_ID:" + skill.getSkillID()));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteEducationPermanently(final int userID, final Education education) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "delete from education where EDUCATION_ID=? and education_USER_ID=?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, education.getEducationID());
			myPreStat.setInt(2, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteExperiencePermanently(final int userID, final Experience experience) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "delete from experience where EXPERIENCE_ID=? and experience_USER_ID=?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, experience.getExperienceID());
			myPreStat.setInt(2, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}

	public int deleteSkillPermanently(final int userID, final Skill skill) {
		final Connection conn = DBConnection.getConnection();
		int rowsAffected = 0;
		PreparedStatement myPreStat = null;
		final String query = "delete from skill where SKILL_ID=? and skill_USER_ID=?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, skill.getSkillID());
			myPreStat.setInt(2, userID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		DBConnection.close(myPreStat, conn);
		return rowsAffected;
	}
}