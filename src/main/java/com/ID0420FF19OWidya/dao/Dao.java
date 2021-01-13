package com.ID0420FF19OWidya.dao;

import com.ID0420FF19OWidya.models.Technology;
import java.util.Map;
import java.util.HashMap;
import com.ID0420FF19OWidya.util.convertDate;
import java.util.Calendar;
import com.ID0420FF19OWidya.models.JobAnswer;
import com.ID0420FF19OWidya.models.JobApplication;
import com.ID0420FF19OWidya.models.Activity;
import com.ID0420FF19OWidya.models.JobSkill;
import com.ID0420FF19OWidya.models.JobQuestion;
import com.ID0420FF19OWidya.models.Job;
import com.ID0420FF19OWidya.models.City;
import com.ID0420FF19OWidya.models.Country;
import com.ID0420FF19OWidya.models.Feedback;
import java.util.Iterator;

import java.io.IOException;
import com.ID0420FF19OWidya.util.convertFiletoString;
import java.util.ArrayList;
import java.util.List;
import com.ID0420FF19OWidya.models.Notification;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import com.ID0420FF19OWidya.models.User;

public class Dao {
	public User loginUser(User user) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select user_id, user_name, first_name, last_name, profile_picture, role from user where email=? and password = ?;";
		String query2 = "insert into login(login_user_id) value(?);";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getEmail());
			myPreStat.setString(2, user.getPassword());
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				user.setUserID(myrs.getInt("user_id"));
				user.setUserName(myrs.getString("user_name"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setProfilePic(myrs.getString("profile_picture"));
				user.setRole(myrs.getString("role"));
			}
			myPreStat = conn.prepareStatement(query2);
			myPreStat.setInt(1, user.getUserID());
			myPreStat.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return user;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return user;
	}

	/**
	 * @param user
	 * @param username
	 * @param verificationID
	 * @return
	 */
	public int registerUser(User user, String username, String verificationID) {
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "Insert into register (user_name, first_name, last_name, email, password, dob, verification_ID) values (?,?, ?,?,?,STR_TO_DATE(?, '%d/%m/%Y'), ?)\r\non duplicate key update user_name = values(user_name), first_name = values(first_name), last_name = values(last_name), password = values(password), dob = values(dob),verification_ID = values(verification_ID),date_expired=(TIMESTAMPADD(HOUR, 3, CURRENT_TIMESTAMP));";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, username);
			myPreStat.setString(2, user.getFirstName());
			myPreStat.setString(3, user.getLastName());
			myPreStat.setString(4, user.getEmail());
			myPreStat.setString(5, user.getPassword());
			myPreStat.setString(6, user.getDob());
			myPreStat.setString(7, verificationID);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public int checkUser(String data, String column) {
		System.out.println(data);
		int userID = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "Select user_id from user where " + column + " = ?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, data);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				userID = myrs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return userID;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return userID;
	}

	public String checkUserPassword(String email) {
		String password = "";
		Connection conn = DBConnection.getConnection();
		String query = "Select password from user where email=?";
		System.out.println(query);
		try {
			PreparedStatement myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, email);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				password = myrs.getString("password");
				System.out.println(password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	public Timestamp verifyUser(User user, String vid) {
		Timestamp expiredDate = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "Select date_expired from register where email =? and verification_ID = ?";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, user.getEmail());
			myPreStat.setString(2, vid);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				expiredDate = myrs.getTimestamp("date_expired");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return expiredDate;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return expiredDate;
	}

	public int updateVerification(User user, String verificationID) {
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "update register set verification_ID=?, date_expired=(TIMESTAMPADD(HOUR, 3, CURRENT_TIMESTAMP)) where email=?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, verificationID);
			myPreStat.setString(2, user.getEmail());
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public int insertUser(User user, String notifMessage) {
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String[] column = { "USER_ID" };
		String query1 = "insert into user (user_name, first_name, last_name, email, password, dob) select user_name, first_name, last_name, email, password, dob from register where email=?;";
		System.out.println(query1);
		String query2 = "delete from register where email=?;";
		String query3= "select user_name from user where email=?";
		System.out.println(query2);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query1, column);
			myPreStat.setString(1, user.getEmail());
			myPreStat.executeUpdate();
			ResultSet rs = myPreStat.getGeneratedKeys();
			int generatedID = 0;
			if (rs.next()) {
				generatedID = rs.getInt(1);
			}
			myPreStat = conn.prepareStatement(query2);
			myPreStat.setString(1, user.getEmail());
			myPreStat.executeUpdate();
			myPreStat = conn.prepareStatement(query3);
			myPreStat.setString(1, user.getEmail());
			ResultSet myrs = myPreStat.executeQuery();
			while(myrs.next()) {
				user.setUserName(myrs.getString("user_name"));
			}
			rowsAffected = this.updateNotification(conn, myPreStat,
					new Notification(generatedID, generatedID, "", "welcome", notifMessage));
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
			DBConnection.close((Statement) myPreStat, conn);
		}
		return rowsAffected;
	}

	public List<User> searchUser(String inputValue, String directory) throws IOException {
		List<User> userCollections = new ArrayList<User>();
		PreparedStatement myPreStat = null;
		Connection conn = DBConnection.getConnection();
		String query = "select u.user_id, u.user_name, u.first_name, u.last_name, u.profile_picture, n.fullname from user as u \r\nleft join (select concat_ws(' ', first_name, last_name) as fullname, user_id FROM user) as n\r\non u.user_id=n.user_id\r\nwhere fullname like ? limit 6;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, "%" + inputValue + "%");
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				User user = new User();
				user.setUserID(myrs.getInt("USER_ID"));
				user.setUserName(myrs.getString("user_name"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setProfilePic(myrs.getString("profile_picture"));
				if (user.getProfilePic() != null && !user.getProfilePic().isEmpty()) {
					user.setProfilePicBase64(convertFiletoString.getBase64(user, directory));
				}
				System.out.println(user);
				userCollections.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return userCollections;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		return userCollections;
	}

	public List<User> searchMoreUser(String inputValue, int[] technologyids, int offset)
			throws IOException {
		List<User> userCollections = new ArrayList<User>();
		PreparedStatement myPreStat = null;
		Connection conn = DBConnection.getConnection();
		String query1 = "select u.user_id, u.user_name, u.first_name, u.last_name, u.profile_picture, n.fullname, u.bio, s.*, group_concat(skill_technology_id) as skill_technology_id_group from user as u \r\nleft join (select concat_ws(' ', first_name, last_name) as fullname, user_id FROM user) as n on u.user_id=n.user_id\r\nright join (select skill.skill_user_id, skill.skill_technology_id from skill) as s on u.user_id = s.skill_user_id\r\n";
		String query2 = "where fullname like ? \r\n";
		String query3 = "";
		if (technologyids != null && technologyids.length != 0) {
			query3 = String.valueOf(query3) + "and skill_technology_id in (";
			for (int i = 0; i < technologyids.length; ++i) {
				query3 = String.valueOf(query3) + technologyids[i];
				if (i != technologyids.length - 1) {
					query3 = String.valueOf(query3) + ",";
				}
			}
			query3 = String.valueOf(query3) + ") \r\n";
		}
		String query4 = "group by user_iD \r\n";
		String query5 = "";
		if (technologyids != null) {
			query5 = "having count(*) >=" + technologyids.length + "\r\n";
		}
		String query6 = "limit ?,15;";
		String query7 = String.valueOf(query1) + query2 + query3 + query4 + query5 + query6;
		System.out.println(query7);
		try {
			myPreStat = conn.prepareStatement(query7);
			myPreStat.setString(1, "%" + inputValue + "%");
			myPreStat.setInt(2, offset);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				User user = new User();
				user.setUserID(myrs.getInt("USER_ID"));
				user.setUserName(myrs.getString("user_name"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setProfilePic(myrs.getString("profile_picture"));
				user.setBio(myrs.getString("bio"));
				System.out.println(user);
				userCollections.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return userCollections;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return userCollections;
	}

	public int updateUserColumn(int userID, String data, String column) {
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "update user set " + column + "= ? where user_id = ?;";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, data);
			myPreStat.setInt(2, userID);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public int DeactivateUser(List<User> users) {
		int rowsAffected = 0;
		User user = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "update user set deleted = true, email = ?, user_id=? where user_ID =?;";
		Iterator<User> it = users.iterator();
		try {
			while (it.hasNext()) {
				user = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setString(1, ":" + user.getEmail());
				myPreStat.setInt(2, user.getUserID() * -1);
				myPreStat.setInt(3, user.getUserID());
				System.out.println(myPreStat);
				rowsAffected = myPreStat.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public List<User> getUserList(int fromID, int toID, String fromDateCreated,
			String toDateCreated, String firstName, String lastName, String email,
			int deleted, int offset) {
		List<User> users = new ArrayList<User>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "";
		String query2 = "select u.user_name, u.email, u.user_ID, u.first_name, u.last_name, u.profile_picture, u.date_created, u.deleted, l.max_date  from user as u \r\nleft join (select login_user_id, max(date_login) as max_date from login group by login_user_id) as l on u.user_id = l.login_user_id\r\n";
		String queryWhere = "where ";
		String query3 = "user_id>= ";
		String query4 = "user_id<= ";
		String query5 = "first_name like ";
		String query6 = "last_name like ";
		String query7 = "email like ";
		String query8 = "date_created >= STR_TO_DATE('";
		String query9 = "date_created <= str_to_date('";
		String query10 = "deleted = ";
		String query11 = "limit ?,15;";
		String queryFilter = "";
		int countFilter = 0;
		query = String.valueOf(query) + query2;
		if (fromID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query3 + fromID + "  ";
			++countFilter;
		}
		if (toID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query4 + toID + " ";
			++countFilter;
		}
		if (firstName != null) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query5 + "'%" + firstName + "%'" + " ";
			++countFilter;
		}
		if (lastName != null) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query6 + "'%" + lastName + "%'" + " ";
			++countFilter;
		}
		if (email != null) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query7 + "'%" + email + "%'" + " ";
			++countFilter;
		}
		if (fromDateCreated != null && !fromDateCreated.isEmpty()) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query8 + fromDateCreated + "', '%d/%m/%Y') ";
			++countFilter;
		}
		if (toDateCreated != null && !toDateCreated.isEmpty()) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query9 + toDateCreated + "', '%d/%m/%Y') ";
			++countFilter;
		}
		if (deleted != 2) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query10 + deleted + " ";
			++countFilter;
		}
		if (countFilter != 0) {
			query = String.valueOf(query) + queryWhere + queryFilter;
		}
		query = String.valueOf(query) + query11;
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, offset);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				User user = new User();
				user.setUserID(myrs.getInt("user_ID"));
				user.setUserName(myrs.getString("user_name"));
				user.setEmail(myrs.getString("email"));
				user.setFirstName(myrs.getString("first_name"));
				user.setLastName(myrs.getString("last_name"));
				user.setProfilePic(myrs.getString("profile_picture"));
				user.setDatecreated(myrs.getString("date_created"));
				user.setLastLogin(myrs.getString("max_date"));
				user.setDeleted(Boolean.valueOf(myrs.getBoolean("deleted")));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return users;
	}

	private String checkQuery(String queryFilter) {
		if (!queryFilter.isEmpty()) {
			queryFilter = String.valueOf(queryFilter) + "and ";
		}
		return queryFilter;
	}

	public int deleteFeedback(List<Feedback> feeds) {
		int rowsAffected = 0;
		Feedback feed = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "delete from feedback where feedback_ID =?;";
		try {
			Iterator<Feedback> it = feeds.iterator();
			while (it.hasNext()) {
				feed = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setInt(1, feed.getFeedbackID());
				System.out.println(myPreStat);
				rowsAffected = myPreStat.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public int updateFeedback(int feedbackID) {
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "update feedback set is_solved = ? where feedback_ID =?;";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, true);
			myPreStat.setInt(2, feedbackID);
			System.out.println(myPreStat);
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rowsAffected;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public Feedback seeFeedback(int feedbackID) {
		Feedback feed = new Feedback();
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select * from feedback where feedback_id = ?";
		String query2 = "update feedback set is_read = true where feedback_id=?;";
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, feedbackID);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				feed = new Feedback();
				feed.setFeedbackID(myrs.getInt("FEEDBACK_ID"));
				feed.setUserID(myrs.getInt("feedback_USER_ID"));
				feed.setFeedbackCategory(myrs.getString("category"));
				feed.setCreatedDate(myrs.getString("date_created"));
				feed.setRead(myrs.getBoolean("is_read"));
				feed.setSolved(myrs.getBoolean("is_solved"));
				feed.setFeedbackMessage(myrs.getString("feedback_message"));
			}
			if (!feed.isRead()) {
				myPreStat = conn.prepareStatement(query2);
				myPreStat.setInt(1, feedbackID);
				System.out.println(myPreStat);
				myPreStat.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return feed;
	}

	public List<Feedback> getFeedbackList(String category, int read, int solved, int fromUserID,
			int toUserID, int fromID, int toID, int offset, String fromDateCreated,
			String toDateCreated) {
		List<Feedback> feeds = new ArrayList<Feedback>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "";
		String query2 = "select FEEDBACK_ID, feedback_USER_ID, category, f.date_created, is_solved, is_read, u.user_name from feedback as f\r\nleft join user as u on f.feedback_USER_ID = u.USER_ID ";
		String queryWhere = "where ";
		String query3 = "category='";
		String query4 = "is_read =";
		String query5 = "is_solved =";
		String query6 = "feedback_user_id >=";
		String query7 = "feedback_user_id <=";
		String query8 = "feedback_id >=";
		String query9 = "feedback_id <=";
		String query10 = "f.date_created >= STR_TO_DATE('";
		String query11 = "f.date_created <= str_to_date('";
		String query12 = "order by is_read, is_solved limit ?,15;";
		String queryFilter = "";
		int countFilter = 0;
		query = String.valueOf(query) + query2;
		if (category != null && !category.isEmpty() && !category.equals("all")) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query3 + category + "' ";
			++countFilter;
		}
		if (read != 2) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query4 + read + " ";
			++countFilter;
		}
		if (solved != 2) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query5 + solved + "  ";
			++countFilter;
		}
		if (fromUserID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query6 + fromUserID + " ";
			++countFilter;
		}
		if (toUserID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query7 + toUserID + " ";
			++countFilter;
		}
		if (fromID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query8 + fromID + " ";
			++countFilter;
		}
		if (toID != 0) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query9 + toID + " ";
			++countFilter;
		}
		if (fromDateCreated != null && !fromDateCreated.isEmpty()) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query10 + fromDateCreated + "', '%d/%m/%Y') ";
			++countFilter;
		}
		if (toDateCreated != null && !toDateCreated.isEmpty()) {
			queryFilter = this.checkQuery(queryFilter);
			queryFilter = String.valueOf(queryFilter) + query11 + toDateCreated + "', '%d/%m/%Y') ";
			++countFilter;
		}
		if (countFilter != 0) {
			query = String.valueOf(query) + queryWhere + queryFilter;
		}
		query = String.valueOf(query) + query12;
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, offset);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				Feedback feed = new Feedback();
				feed.setFeedbackID(myrs.getInt("FEEDBACK_ID"));
				feed.setUserID(myrs.getInt("feedback_USER_ID"));
				feed.setUserName(myrs.getString("user_name"));
				feed.setFeedbackCategory(myrs.getString("category"));
				feed.setCreatedDate(myrs.getString("date_created"));
				feed.setRead(myrs.getBoolean("is_read"));
				feed.setSolved(myrs.getBoolean("is_solved"));
				feeds.add(feed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return feeds;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return feeds;
	}

	public List<Country> getCountries() {
		List<Country> countries = new ArrayList<Country>();
		Country country = null;
		Connection conn = DBConnection.getConnection();
		Statement myStat = null;
		String query = "Select * from country;";
		System.out.println(query);
		try {
			myStat = conn.createStatement();
			ResultSet myrs = myStat.executeQuery(query);
			while (myrs.next()) {
				country = new Country();
				country.setCountryID(myrs.getInt("COUNTRY_ID"));
				country.setSortName(myrs.getString("sort_name"));
				country.setCountryName(myrs.getString("country_name"));
				country.setPhoneCode(myrs.getInt("phone_code"));
				countries.add(country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return countries;
		} finally {
			DBConnection.close(myStat, conn);
		}
		DBConnection.close(myStat, conn);
		return countries;
	}

	public List<City> getCities(int countryID) {
		List<City> cities = new ArrayList<City>();
		City city = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select c.* from id0420ff19o.city as c join (Select state_id, country_id from state) as s on c.state_id = s.state_id where s.country_id=?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, countryID);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				city = new City();
				city.setCityID(myrs.getInt("CITY_ID"));
				city.setCityName(myrs.getString("city_name"));
				city.setStateID(myrs.getInt("state_id"));
				cities.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return cities;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return cities;
	}

	public int insertJobPost(User user, Job jobPost) {
		int rowsAffected = 0;
		int generatedID = 0;
		System.out.println("jobPost" + jobPost);
		String[] column = { "JOBPOST_ID" };
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query1 = "insert into jobpost(job_USER_ID,company,job_CITY_ID,job_COUNTRY_ID,company_info,position,place,hours,job_description,job_requirements,deadline_submission,notes)\r\nvalues (?,?,?,?,?,?,?,?,?,?,STR_TO_DATE(?, '%d/%m/%Y'),?);";
		String query2 = "insert into jobquestion(jobQuestion_JOBPOST_ID,question) values(?,?);";
		String query3 = "insert into jobskill(jobSkill_JOBPOST_ID,jobSkill_TECHNOLOGY_ID) values (?,?);";
		System.out.println(query1);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query1, column);
			myPreStat.setInt(1, user.getUserID());
			myPreStat.setString(2, jobPost.getCompanyName());
			myPreStat.setInt(3, jobPost.getCity().getCityID());
			myPreStat.setInt(4, jobPost.getCountry().getCountryID());
			myPreStat.setString(5, jobPost.getCompanyInfo());
			myPreStat.setString(6, jobPost.getPosition());
			myPreStat.setString(7, jobPost.getPlace());
			myPreStat.setString(8, jobPost.getHours());
			myPreStat.setString(9, jobPost.getJobDescription());
			myPreStat.setString(10, jobPost.getJobRequirements());
			myPreStat.setString(11, jobPost.getDeadlineSubmission());
			myPreStat.setString(12, jobPost.getNotes());
			System.out.println(myPreStat);
			myPreStat.executeUpdate();
			ResultSet rs = myPreStat.getGeneratedKeys();
			if (rs.next()) {
				generatedID = rs.getInt(1);
			}
			if (jobPost.getJobQuestions() != null) {
				for (JobQuestion jobquest : jobPost.getJobQuestions()) {
					myPreStat = conn.prepareStatement(query2);
					myPreStat.setInt(1, generatedID);
					myPreStat.setString(2, jobquest.getQuestion());
					myPreStat.executeUpdate();
					System.out.println(myPreStat);
				}
			}
			if (jobPost.getJobSkills() != null) {
				for (JobSkill jobsk : jobPost.getJobSkills()) {
					myPreStat = conn.prepareStatement(query3);
					myPreStat.setInt(1, generatedID);
					myPreStat.setInt(2, jobsk.getTechnologyID());
					myPreStat.executeUpdate();
					System.out.println(myPreStat);
				}
			}
			String notifMessage = "<b>"+String.valueOf(user.getFirstName()) + " " + user.getLastName() +"</b>"+ " "
					+ "is looking for " + "<b>"+jobPost.getPosition() + "</b>"+ ". " + "<br><u>"+"Apply now!"+"</u>";
			Notification notif = new Notification();
			notif.setNotificationMessage(notifMessage);
			notif.setLink(String.valueOf(generatedID));
			notif.setTriggerUserID(user.getUserID());
			notif.setNotificationCategory("hiring");
			String query4 = "select * from follow where following_user_id = ?";
			myPreStat = conn.prepareStatement(query4);
			myPreStat.setInt(1, user.getUserID());
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				notif.setUserID(myrs.getInt("follower_USER_ID"));
				this.updateNotification(conn, myPreStat, notif);
			}
			rowsAffected = this.updateActivity(conn, myPreStat,
					new Activity(user.getUserID(), "create", "jobpost", "JOBPOST_ID:" + generatedID));
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
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public Boolean checkJobPostStatus(int JobID) {
		Boolean isValid = false;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select * from jobpost where jobpost_id = ? and deadline_submission>current_timestamp;";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, JobID);
			ResultSet myrs = myPreStat.executeQuery();
			int count = 0;
			while (myrs.next()) {
				count += 1;
				System.out.println("count" + count);
			}
			if (count > 0) {
				isValid = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isValid;

	}

	public Boolean checkApplicantStatus(int JobID, int userID) {
		Boolean hasApply = false;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select * from jobapplication where jobApplication_USER_ID = ? and jobApplication_JOBPOST_ID = ?;";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			myPreStat.setInt(2, JobID);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			int count = 0;
			while (myrs.next()) {
				count += 1;
				System.out.println("count" + count);
			}
			if (count > 0) {
				hasApply = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hasApply;

	}

	public int insertJobApplication(JobApplication jobApplicationData, int jobUserID) {
		int rowsAffected = 0;
		System.out.println("jobApplicationData" + jobApplicationData);
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query1 = "insert into jobapplication(jobApplication_USER_ID,jobApplication_JOBPOST_ID,applicant_phone_no,applicant_phonecode,applicant_about,attachment_file,is_read)\r\nvalues (?,?,?,?,?,?,?);";
		String query2 = "insert into jobanswer(jobAnswer_JOBAPPLICATION_ID,jobAnswer_JOBQUESTION_ID,answer) values(?,?,?);";
		System.out.println(query1);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query1);
			myPreStat.setInt(1, jobApplicationData.getJobApplicationUser().getUserID());
			myPreStat.setInt(2, jobApplicationData.getJobPostID());
			myPreStat.setString(3, jobApplicationData.getPhoneNo());
			myPreStat.setInt(4, jobApplicationData.getPhoneCode());
			myPreStat.setString(5, jobApplicationData.getAboutApplicant());
			myPreStat.setString(6, jobApplicationData.getAttachment());
			myPreStat.setBoolean(7, false);
			System.out.println(myPreStat);
			myPreStat.executeUpdate();
			String generatedID = "u" + jobApplicationData.getJobApplicationUser().getUserID() + ":" + "j"
					+ jobApplicationData.getJobPostID();
			for (JobAnswer jobans : jobApplicationData.getJobAnswers()) {
				myPreStat = conn.prepareStatement(query2);
				myPreStat.setString(1, generatedID);
				myPreStat.setInt(2, jobans.getJobQuestionID());
				myPreStat.setString(3, jobans.getAnswer());
				myPreStat.executeUpdate();
				System.out.println(myPreStat);
			}
			String notifMessage = "You've got a new Applicant";
			this.updateNotification(conn, myPreStat, new Notification(jobUserID,
					jobApplicationData.getJobApplicationUser().getUserID(), "myJobPost", "newapplicant", notifMessage));
			rowsAffected = this.updateActivity(conn, myPreStat,
					new Activity(jobApplicationData.getJobApplicationUser().getUserID(), "create", "jobapplication",
							"JOBAPPLICATION_ID:" + generatedID));
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
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public Job viewJobPostPublic(int jobID, Boolean show, Boolean deleted) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		Job job = new Job();
		String query = "select group_concat(js.technology_name separator ', ') as required_skill, j.job_user_id, j.notes, j.jobpost_id, j.company, j.position, j.place, j.hours, j.job_description, j.job_requirements, j.company_info, DATE_FORMAT(j.deadline_submission, '%d/%m/%Y') as deadline_submission, j.deadline_submission as deadline_submission_timestamp, j.date_created, j.city_name, j.country_name \r\nfrom (select jobpost.*, c.city_name, co.country_name from jobpost \r\njoin city as c on jobpost.job_city_id = c.city_id \r\njoin country as co on jobpost.job_country_id = co.country_id) as j \r\nleft join (select jobskill.*, technology.* from jobskill\r\nleft join technology on jobSkill_TECHNOLOGY_ID = TECHNOLOGY_ID) as js on j.jobpost_id= js.jobskill_jobpost_id\r\nwhere deadline_submission > current_date() and is_show = ? and deleted = ? and JOBPOST_ID=?\r\ngroup by jobpost_id;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, show);
			myPreStat.setBoolean(2, deleted);
			myPreStat.setInt(3, jobID);
			ResultSet myrs = myPreStat.executeQuery();
			this.getJobData(job, myrs);
			System.out.println(myPreStat);
		} catch (SQLException e) {
			e.printStackTrace();
			return job;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return job;
	}

	public Job viewJobPostPrivate(int jobID, Boolean deleted) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		Job job = new Job();
		String query = "select group_concat(js.technology_name separator ', ') as required_skill, j.job_user_id, j.notes, j.jobpost_id, j.company, j.position, j.place, j.hours, j.job_description, j.job_requirements, j.company_info, DATE_FORMAT(j.deadline_submission, '%d/%m/%Y') as deadline_submission, j.deadline_submission as deadline_submission_timestamp, j.date_created, j.city_name, j.country_name \r\nfrom (select jobpost.*, c.city_name, co.country_name from jobpost \r\njoin city as c on jobpost.job_city_id = c.city_id \r\njoin country as co on jobpost.job_country_id = co.country_id) as j \r\nleft join (select jobskill.*, technology.* from jobskill\r\nleft join technology on jobSkill_TECHNOLOGY_ID = TECHNOLOGY_ID) as js on j.jobpost_id= js.jobskill_jobpost_id\r\nwhere deleted = ? and JOBPOST_ID=?\r\ngroup by jobpost_id;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, deleted);
			myPreStat.setInt(2, jobID);
			ResultSet myrs = myPreStat.executeQuery();
			System.out.println(myPreStat);
			this.getJobData(job, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
			return job;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return job;
	}

	public Job viewJobPostAdmin(int jobID) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		Job job = new Job();
		String query = "select group_concat(js.technology_name separator ', ') as required_skill, j.job_user_id, j.notes, j.jobpost_id, j.company, j.position, j.place, j.hours, j.job_description, j.job_requirements, j.company_info, DATE_FORMAT(j.deadline_submission, '%d/%m/%Y') as deadline_submission, j.deadline_submission as deadline_submission_timestamp, j.date_created, j.city_name, j.country_name \r\nfrom (select jobpost.*, c.city_name, co.country_name from jobpost \r\njoin city as c on jobpost.job_city_id = c.city_id \r\njoin country as co on jobpost.job_country_id = co.country_id) as j \r\nleft join (select jobskill.*, technology.* from jobskill\r\nleft join technology on jobSkill_TECHNOLOGY_ID = TECHNOLOGY_ID) as js on j.jobpost_id= js.jobskill_jobpost_id\r\nwhere JOBPOST_ID=?\r\ngroup by jobpost_id;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, jobID);
			ResultSet myrs = myPreStat.executeQuery();
			System.out.println(myPreStat);
			this.getJobData(job, myrs);
		} catch (SQLException e) {
			e.printStackTrace();
			return job;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return job;
	}

	private void getJobData(Job job, ResultSet myrs) {
		try {
			while (myrs.next()) {
				City city = new City();
				Country country = new Country();
				job.setPosition(myrs.getString("position"));
				job.setRequiredSkill(myrs.getString("required_skill"));
				job.setNotes(myrs.getString("notes"));
				job.setJobID(myrs.getInt("JOBPOST_ID"));
				job.setCompanyName(myrs.getString("company"));
				job.setCompanyInfo(myrs.getString("company_info"));
				job.setPlace(myrs.getString("place"));
				job.setHours(myrs.getString("hours"));
				job.setJobDescription(myrs.getString("job_description"));
				job.setJobRequirements(myrs.getString("job_requirements"));
				Calendar date = Calendar.getInstance();
				long millisecondsDate = date.getTimeInMillis();
				if (myrs.getTimestamp("deadline_submission_timestamp").getTime() > millisecondsDate) {
					job.setDeadlineSubmission(myrs.getString("deadline_submission"));
				} else {
					job.setDeadlineSubmission("Closed");
				}
				job.setCreatedDate(convertDate.countCreatedDate(myrs.getTimestamp("date_created")));
				job.setUserID(myrs.getInt("job_USER_ID"));
				city.setCityName(myrs.getString("city_name"));
				job.setCity(city);
				country.setCountryName(myrs.getString("country_name"));
				job.setCountry(country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<JobQuestion> getQuestion(int jobID) {
		List<JobQuestion> quests = new ArrayList<JobQuestion>();
		JobQuestion quest = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "SELECT * FROM id0420ff19o.jobquestion where jobQuestion_JOBPOST_ID = ?;";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, jobID);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				quest = new JobQuestion();
				quest.setJobQuestionID(myrs.getInt("JOBQUESTION_ID"));
				quest.setQuestion(myrs.getString("question"));
				quest.setJobID(myrs.getInt("jobQuestion_JOBPOST_ID"));
				quests.add(quest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return quests;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return quests;
	}

	public List<JobApplication> viewJobApplicants(int jobPostUserID, Job job) {
		List<JobApplication> applicantLists = new ArrayList<JobApplication>();
		JobApplication application = null;
		User applicant = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select j.job_user_id, j.JOBPOST_ID, uja.date_created, uja.profile_picture, uja.first_name, uja.last_name, uja.user_id, uja.user_name, uja.jobapplication_id, uja.is_read from jobpost as j \r\nright join (select u.first_name, u.last_name, u.user_name, u.profile_picture, u.user_id, ja.JOBAPPLICATION_ID, ja.jobApplication_JOBPOST_ID, ja.jobApplication_USER_ID, ja.is_read, ja.date_created from user as u \r\nright join jobapplication as ja on u.USER_ID = ja.jobApplication_USER_ID) as uja on j.JOBPOST_ID = uja.jobApplication_JOBPOST_ID\r\nwhere j.job_USER_ID = ? and j.JOBPOST_ID = ? order by is_read, date_created;";
		System.out.println("query before" + query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, jobPostUserID);
			myPreStat.setInt(2, job.getJobID());
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				application = new JobApplication();
				applicant = new User();
				applicant.setFirstName(myrs.getString("first_name"));
				applicant.setLastName(myrs.getString("last_name"));
				applicant.setUserID(myrs.getInt("user_id"));
				applicant.setUserName(myrs.getString("user_name"));
				applicant.setProfilePic(myrs.getString("profile_picture"));
				application.setJobApplicationID(myrs.getString("JOBAPPLICATION_ID"));
				application.setJobPostID(myrs.getInt("JOBPOST_ID"));
				application.setJobApplicationUser(applicant);
				application.setJobApplyDate(convertDate.countCreatedDate(myrs.getTimestamp("date_created")));
				application.setRead(myrs.getBoolean("is_read"));
				applicantLists.add(application);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return applicantLists;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return applicantLists;
	}

	public JobApplication viewJobApplication(int jobPostUserID, JobApplication jobApplication) {
		JobApplication application = new JobApplication();
		User applicant = new User();
		Job jobPost = new Job();
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select j.job_user_id, j.company, j.position, uja.* from jobpost as j \r\nright join (select u.first_name, u.last_name, u.user_name, u.profile_picture, u.user_id, ja.* from user as u \r\nleft join jobapplication as ja on u.USER_ID = ja.jobApplication_USER_ID) as uja on j.JOBPOST_ID = uja.jobApplication_JOBPOST_ID\r\nwhere j.job_USER_ID = ? and JOBAPPLICATION_ID = ?;";
		String query2 = "select jaa.*,jq.*  from jobanswer as jaa left join jobquestion as jq on jaa.jobAnswer_JOBQUESTION_ID = jq.JOBQUESTION_ID\r\nwhere jobAnswer_JOBAPPLICATION_ID = ?;";
		String query3 = "update jobapplication set is_read = true where JOBAPPLICATION_ID= ?;";
		System.out.println("query before" + query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, jobPostUserID);
			myPreStat.setString(2, jobApplication.getJobApplicationID());
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				applicant.setFirstName(myrs.getString("first_name"));
				applicant.setLastName(myrs.getString("last_name"));
				applicant.setUserID(myrs.getInt("user_id"));
				applicant.setUserName(myrs.getString("user_name"));
				applicant.setProfilePic(myrs.getString("profile_picture"));
				application.setJobApplicationUser(applicant);
				jobPost.setCompanyName(myrs.getString("company"));
				jobPost.setPosition(myrs.getString("position"));
				application.setJobPost(jobPost);
				application.setJobApplicationID(myrs.getString("JOBAPPLICATION_ID"));
				application.setPhoneNo(myrs.getString("applicant_phone_no"));
				application.setPhoneCode(myrs.getInt("applicant_phonecode"));
				application.setJobApplyDate(convertDate.countCreatedDate(myrs.getTimestamp("date_created")));
				application.setAboutApplicant(myrs.getString("applicant_about"));
				application.setAttachment(myrs.getString("attachment_file"));
				application.setJobPostID(myrs.getInt("jobApplication_JOBPOST_ID"));
				application.setRead(myrs.getBoolean("is_read"));
				System.out.println(application);
			}
			myPreStat = conn.prepareStatement(query2);
			myPreStat.setString(1, jobApplication.getJobApplicationID());
			System.out.println(myPreStat);
			ResultSet myrs2 = myPreStat.executeQuery();
			List<JobAnswer> jobanss = new ArrayList<JobAnswer>();
			while (myrs2.next()) {
				JobAnswer jobAns = new JobAnswer();
				jobAns.setJobAnswerID(myrs2.getString("JOBANSWER_ID"));
				jobAns.setJobApplicationID(myrs2.getString("jobAnswer_JOBAPPLICATION_ID"));
				jobAns.setAnswer(myrs2.getString("answer"));
				jobAns.setQuestion(myrs2.getString("question"));
				jobAns.setJobQuestionID(myrs2.getInt("JOBQUESTION_ID"));
				System.out.println(jobAns);
				jobanss.add(jobAns);
			}
			application.setJobAnswers((List) jobanss);
			if (!application.isRead()) {
				myPreStat = conn.prepareStatement(query3);
				myPreStat.setString(1, jobApplication.getJobApplicationID());
				System.out.println(myPreStat);
				myPreStat.executeUpdate();
				String notifMessage = "Your Application to" +" " + jobPost.getCompanyName() +" "+ "is being viewed";
				this.updateNotification(conn, myPreStat,
						new Notification(applicant.getUserID(), jobPostUserID, "", "viewapplication", notifMessage));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return application;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return application;
	}

	public List<Job> getMyJobLists(int userID, Boolean deleted) {
		List<Job> jobs = new ArrayList<Job>();
		Job job = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "select j.jobpost_id, j.job_USER_ID, j.company, j.position, j.place, j.hours, DATE_FORMAT(j.deadline_submission, '%d/%m/%Y') as deadline_submission, j.deadline_submission as deadline_submission_timestamp, j.date_created, j.city_name, j.country_name from (select jobpost.*, c.city_name, co.country_name from jobpost \r\njoin city as c on jobpost.job_city_id = c.city_id \r\njoin country as co on jobpost.job_country_id = co.country_id) as j \r\nwhere deleted = ? and job_user_id = ?\r\ngroup by jobpost_id \r\norder by deadline_submission_timestamp desc, date_created desc;";
		System.out.println("query before" + query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setBoolean(1, deleted);
			myPreStat.setInt(2, userID);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				job = new Job();
				City city = new City();
				Country country = new Country();
				job.setJobID(myrs.getInt("JOBPOST_ID"));
				job.setCompanyName(myrs.getString("company"));
				job.setPosition(myrs.getString("position"));
				job.setPlace(myrs.getString("place"));
				job.setHours(myrs.getString("hours"));
				job.setUserID(myrs.getInt("job_USER_ID"));
				Calendar date = Calendar.getInstance();
				long millisecondsDate = date.getTimeInMillis();
				if (myrs.getTimestamp("deadline_submission_timestamp").getTime() > millisecondsDate) {
					job.setDeadlineSubmission(myrs.getString("deadline_submission"));
				} else {
					job.setDeadlineSubmission("Closed");
				}
				job.setCreatedDate(convertDate.countCreatedDate(myrs.getTimestamp("date_created")));
				city.setCityName(myrs.getString("city_name"));
				job.setCity(city);
				country.setCountryName(myrs.getString("country_name"));
				job.setCountry(country);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return jobs;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return jobs;
	}

	public List<Job> getJobList(Job jobData, int offset) {
		System.out.println("offset" + offset);
		List<Job> jobs = new ArrayList<Job>();
		Job job = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		List<JobSkill> jobskills = (List<JobSkill>) jobData.getJobSkills();
		String query = "";
		String query1 = "select group_concat(js.jobSkill_TECHNOLOGY_ID), j.jobpost_id, j.job_USER_ID, j.company, j.position, j.place, j.hours, DATE_FORMAT(j.deadline_submission, '%d/%m/%Y') as deadline_submission, j.date_created, j.city_name, j.country_name from (select jobpost.*, c.city_name, co.country_name from jobpost \r\njoin city as c on jobpost.job_city_id = c.city_id \r\njoin country as co on jobpost.job_country_id = co.country_id) as j \r\n";
		String query2 = "left join jobskill as js on j.jobpost_id= js.jobskill_jobpost_id \r\n";
		String query3 = "where is_show = 1 and deleted = 0 and (deadline_submission> current_date()) and job_USER_ID>0 \r\n"
				+ "and (position like ? or company like ?)";
		int countJobSkill = 0;
		try {
			if (jobskills != null && jobskills.size() != 0) {
				System.out.println("jobskill not empty");
				query3 += " and JOBPOST_ID not in (select jobSkill_JOBPOST_ID from jobskill where jobSkill_TECHNOLOGY_ID in (";
				for (JobSkill jobskill : jobskills) {
					countJobSkill +=1;
					query3 += jobskill.getTechnologyID();
					if(countJobSkill != jobskills.size()) {
						query3 += ",";
					}
				}
				query3 += "))";
			}
			if (jobData.getCity().getCityID() != 0) {
				query3 += " and  job_city_ID =" + jobData.getCity().getCityID();
			}
			if (jobData.getCountry().getCountryID() != 0) {
				query3 += " and job_country_id =" + jobData.getCountry().getCountryID();
			}
			if (jobData.getPlace() != null && !jobData.getPlace().isEmpty()) {
				query3 += " and  place ='" + jobData.getPlace() + "'";
			}
			if (jobData.getHours() != null && !jobData.getHours().isEmpty()) {
				query3 += " and hours ='" + jobData.getHours() + "'";
			}

		} finally {
			query  = query1+ query2+ query3;
			query += " group by jobpost_id ";
			query += " order by date_created DESC LIMIT ?,15;";
		}
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, "%" + jobData.getPosition() + "%");
			myPreStat.setString(2, "%" + jobData.getCompanyName() + "%");
			myPreStat.setInt(3, offset);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				job = new Job();
				City city = new City();
				Country country = new Country();
				job.setJobID(myrs.getInt("JOBPOST_ID"));
				job.setCompanyName(myrs.getString("company"));
				job.setPosition(myrs.getString("position"));
				job.setPlace(myrs.getString("place"));
				job.setHours(myrs.getString("hours"));
				job.setDeadlineSubmission(myrs.getString("deadline_submission"));
				job.setCreatedDate(convertDate.countCreatedDate(myrs.getTimestamp("date_created")));
				job.setUserID(myrs.getInt("job_USER_ID"));
				city.setCityName(myrs.getString("city_name"));
				job.setCity(city);
				country.setCountryName(myrs.getString("country_name"));
				job.setCountry(country);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return jobs;
		} finally {
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return jobs;
	}

	public int insertFeedback(Feedback feedback, int userID) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String[] column = { "FEEDBACK_ID" };
		String query = "insert into feedback (feedback_user_id, category, feedback_message) values (?,?,?)";
		System.out.println(query);
		try {
			conn.setAutoCommit(false);
			myPreStat = conn.prepareStatement(query, column);
			myPreStat.setInt(1, userID);
			myPreStat.setString(2, feedback.getFeedbackCategory());
			myPreStat.setString(3, feedback.getFeedbackMessage());
			myPreStat.executeUpdate();
			int generatedID = 0;
			ResultSet rs = myPreStat.getGeneratedKeys();
			if (rs.next()) {
				generatedID = rs.getInt(1);
				result.put(userID, generatedID);
			}
			rowsAffected = this.updateActivity(conn, myPreStat,
					new Activity(userID, "create", "feedback", "feedback_ID:" + generatedID));
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
			DBConnection.close((Statement) myPreStat, conn);
		}
		
		return rowsAffected;
	}

	public int updateNotification(Connection conn, PreparedStatement myPreStat, Notification notif) {
		int rowsAffected = 0;
		myPreStat = this.setNotificationStatementValue(conn, notif);
		try {
			rowsAffected = myPreStat.executeUpdate();
			System.out.println(myPreStat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public int updateActivity(Connection conn, PreparedStatement myPreStat, Activity act) {
		int rowsAffected = 0;
		myPreStat = this.setActivityStatementValue(conn, act);
		try {
			rowsAffected = myPreStat.executeUpdate();
			System.out.println(myPreStat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public PreparedStatement setNotificationStatementValue(Connection conn, Notification notif) {
		PreparedStatement myPreStat = null;
		System.out.println(notif);
		String query = "insert into notification (notification_USER_ID, trigger_USER_ID, link, notification_category, messages) values (?,?,?,?,?)";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, notif.getUserID());
			myPreStat.setInt(2, notif.getTriggerUserID());
			myPreStat.setString(3, notif.getLink());
			myPreStat.setString(4, notif.getNotificationCategory());
			myPreStat.setString(5, notif.getNotificationMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myPreStat;
	}

	public PreparedStatement setActivityStatementValue(Connection conn, Activity act) {
		PreparedStatement myPreStat = null;
		String query = "insert into activity (activity_USER_ID, action, entity, activity_note) values (?,?,?,?)";
		System.out.println(query);
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, act.getUserID());
			myPreStat.setString(2, act.getAction());
			myPreStat.setString(3, act.getEntity());
			myPreStat.setString(4, act.getActivityNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myPreStat;
	}

	public List<Technology> getTechnologyLists() {
		List<Technology> technologies = new ArrayList<Technology>();
		Technology tech = null;
		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM technology";
		try {
			Statement myStat = conn.createStatement();
			ResultSet myrs = myStat.executeQuery(query);
			while (myrs.next()) {
				tech = new Technology();
				tech.setTechnologyID(myrs.getInt("TECHNOLOGY_ID"));
				tech.setTechnologyName(myrs.getString("technology_name"));
				technologies.add(tech);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return technologies;
	}

	public int updateTechnology(Technology tech) {
		PreparedStatement myPreStat = null;
		int rowsAffected = 0;
		Connection conn = DBConnection.getConnection();
		String query = "Update technology set technology_name = ? where technology_id=?";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setString(1, tech.getTechnologyName());
			myPreStat.setInt(2, tech.getTechnologyID());
			rowsAffected = myPreStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public int deleteTechnology(List<Technology> techs) {
		List<Technology> technologies = new ArrayList<Technology>();
		PreparedStatement myPreStat = null;
		int rowsAffected = 0;
		Technology tech = null;
		Connection conn = DBConnection.getConnection();
		String query = "delete from technology where technology_id=?";
		try {
			Iterator<Technology> it = techs.iterator();
			while (it.hasNext()) {
				tech = it.next();
				myPreStat = conn.prepareStatement(query);
				myPreStat.setInt(1, tech.getTechnologyID());
				rowsAffected = myPreStat.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public int addTechnology(Technology tech) {
		PreparedStatement myPreStat = null;
		int generatedID = 0;
		int rowsAffected = 0;
		String[] column = { "TECHNOLOGY_ID" };
		Connection conn = DBConnection.getConnection();
		String query = "insert into technology set technology_name = ?";
		try {
			myPreStat = conn.prepareStatement(query, column);
			myPreStat.setString(1, tech.getTechnologyName());
			rowsAffected = myPreStat.executeUpdate();
			ResultSet myrs = myPreStat.getGeneratedKeys();
			while (myrs.next()) {
				generatedID = myrs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedID;
	}

	public String getAllEmail() {
		Connection conn = DBConnection.getConnection();
		String emails = "";
		String query = "SELECT group_concat(email) as emails FROM user;";
		try {
			Statement myStat = conn.createStatement();
			ResultSet myrs = myStat.executeQuery(query);
			while (myrs.next()) {
				emails = myrs.getString("emails");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emails;
	}

	public List<Notification> getNotificationList(int userID) {
		List<Notification> notifications = new ArrayList<Notification>();
		Notification notif = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement myPreStat = null;
		String query = "SELECT *, date_format(date_created, '%H:%i') as created_time FROM notification where notification_USER_ID = ? order by date_created desc;";
		try {
			myPreStat = conn.prepareStatement(query);
			myPreStat.setInt(1, userID);
			System.out.println(myPreStat);
			ResultSet myrs = myPreStat.executeQuery();
			while (myrs.next()) {
				notif = new Notification();
				notif.setNotificationID(myrs.getInt("NOTIFICATION_ID"));
				notif.setUserID(myrs.getInt("notification_USER_ID"));
				notif.setTriggerUserID(myrs.getInt("trigger_USER_ID"));
				notif.setLink(myrs.getString("link"));
				notif.setNotificationCategory(myrs.getString("notification_category"));
				notif.setNotificationMessage(myrs.getString("messages"));
				notif.setCreatedTime(myrs.getString("created_time"));
				notif.setCreatedDate(convertDate.countCreatedDateDay(myrs.getTimestamp("date_created")));
				notif.setRead(myrs.getBoolean("is_read"));
				notifications.add(notif);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(myPreStat, conn);
		}
		return notifications;
	}

}