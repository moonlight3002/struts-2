package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ID0420FF19OWidya.util.listYear;
import java.io.IOException;
import com.ID0420FF19OWidya.dao.ProfileDao;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.Skill;
import com.ID0420FF19OWidya.models.Experience;
import com.ID0420FF19OWidya.models.Education;
import com.ID0420FF19OWidya.models.Technology;
import com.ID0420FF19OWidya.models.Follow;
import java.util.List;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class ProfileAction extends ActionSupport implements Preparable
{
    private static final long serialVersionUID = 1L;
    private User user;
    private User sessionUser;
    private String profileUserName;
    private boolean alreadyFollowing;
    private List<User> followUsers;
    private int offset;
    private String inputValue;
    private String directory;
    private int followResult;
    private Follow follow;
    private List<Technology> technologies;
    private List<Integer> year;
    private Education education;
    private Experience experience;
    private Skill skill;
    private int updateResult;
    private int deleteResult;
    
    public int getDeleteResult() {
        return this.deleteResult;
    }
    
    public void setDeleteResult(final int deleteResult) {
        this.deleteResult = deleteResult;
    }
    
    public Education getEducation() {
        return this.education;
    }
    
    public void setEducation(final Education education) {
        this.education = education;
    }
    
    public List<Integer> getYear() {
        return this.year;
    }
    
    public void setYear(final List<Integer> year) {
        this.year = year;
    }
    
    public int getUpdateResult() {
        return this.updateResult;
    }
    
    public void setUpdateResult(final int updateResult) {
        this.updateResult = updateResult;
    }
    
    public Experience getExperience() {
        return this.experience;
    }
    
    public void setExperience(final Experience experience) {
        this.experience = experience;
    }
    
    public Skill getSkill() {
        return this.skill;
    }
    
    public void setSkill(final Skill skill) {
        this.skill = skill;
    }
    
    public List<Technology> getTechnologies() {
        return this.technologies;
    }
    
    public void setTechnologies(final List<Technology> technologies) {
        this.technologies = technologies;
    }
    
    public Follow getFollow() {
        return this.follow;
    }
    
    public void setFollow(final Follow follow) {
        this.follow = follow;
    }
    
    public int getFollowResult() {
        return this.followResult;
    }
    
    public void setFollowResult(final int followResult) {
        this.followResult = followResult;
    }
    
    public List<User> getFollowUsers() {
        return this.followUsers;
    }
    
    public void setFollowUsers(final List<User> followUsers) {
        this.followUsers = followUsers;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public String getInputValue() {
        return this.inputValue;
    }
    
    public void setInputValue(final String inputValue) {
        this.inputValue = inputValue;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
    
    public boolean isAlreadyFollowing() {
        return this.alreadyFollowing;
    }
    
    public void setAlreadyFollowing(final boolean alreadyFollowing) {
        this.alreadyFollowing = alreadyFollowing;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public String getProfileUserName() {
        return this.profileUserName;
    }
    
    public void setProfileUserName(final String profileUserName) {
        this.profileUserName = profileUserName;
    }
    
    public String viewProfile() {
        System.out.println("--profileAction view Profile");
        System.out.println("User Object :" + this.user);
        System.out.println("-profileUserName:" + this.profileUserName);
        final Dao dao = new Dao();
        System.out.println("sessionUser" + this.sessionUser);
        final int requestProfileUserID = dao.checkUser(this.profileUserName.toString(), "user_name");
        System.out.println(requestProfileUserID);
        final ProfileDao profileDao = new ProfileDao();
        if (this.sessionUser.getUserName().equals(this.profileUserName) || this.sessionUser.getRole().equals("admin")) {
            if (requestProfileUserID != 0) {
                if (this.sessionUser.getRole().equals("admin")) {
                    this.user = profileDao.showProfile(this.sessionUser, this.profileUserName, 1);
                    this.alreadyFollowing = profileDao.isFollowingUserProfile(this.user.getUserID(), this.sessionUser.getUserID());
                }
                else if (requestProfileUserID > 0) {
                    this.user = profileDao.showProfile(this.sessionUser, this.profileUserName, 2);
                }
                System.out.println("-private" + this.user);
                this.technologies = (List<Technology>)dao.getTechnologyLists();
                return "successPrivate";
            }
            return "error";
        }
        else {
            if (requestProfileUserID > 0) {
                this.user = profileDao.showProfile(this.sessionUser, this.profileUserName, 3);
                this.alreadyFollowing = profileDao.isFollowingUserProfile(this.user.getUserID(), this.sessionUser.getUserID());
                System.out.println("isFollowing: " + this.alreadyFollowing);
                System.out.println("-public" + this.user);
                return "successPublic";
            }
            return "error";
        }
    }
    
    public String searchFollower() throws IOException {
        System.out.println("--profileAction searchFollower");
        System.out.println("user" + this.user);
        System.out.println("offset" + this.offset);
        System.out.println("inputValue" + this.inputValue);
        System.out.println("directory" + this.directory);
        final ProfileDao proDao = new ProfileDao();
        this.followUsers = (List<User>)proDao.getFollowLists(this.user.getUserID(), this.inputValue, this.offset, this.directory, "follower");
        return "success";
    }
    
    public String searchFollowing() throws IOException {
        System.out.println("--profileAction searchFollowing");
        System.out.println("user" + this.user);
        System.out.println("offset" + this.offset);
        System.out.println("inputValue" + this.inputValue);
        System.out.println("directory" + this.directory);
        final ProfileDao proDao = new ProfileDao();
        this.followUsers = (List<User>)proDao.getFollowLists(this.user.getUserID(), this.inputValue, this.offset, this.directory, "following");
        return "success";
    }
    
    public String followUser() {
        System.out.println("--profileAction followUser");
        System.out.println("follow: " + this.follow);
        this.follow.setFollowerUserID(this.sessionUser.getUserID());
        final ProfileDao proDao = new ProfileDao();
        this.followResult = proDao.insertFollow(this.follow, this.sessionUser);
        return "success";
    }
    
    public String unfollowUser() {
        System.out.println("--profileAction unfollowUser");
        final ProfileDao proDao = new ProfileDao();
        this.follow.setFollowerUserID(this.sessionUser.getUserID());
        this.followResult = proDao.deleteFollow(this.follow, this.sessionUser);
        return "success";
    }
    
    public String viewAllSkills() {
        System.out.println("--profileAction viewAllSkill");
        final ProfileDao proDao = new ProfileDao();
        if (!this.sessionUser.getRole().equals("admin")) {
            this.user.setUserID(this.sessionUser.getUserID());
            this.user = proDao.getUserSkills(this.user, 2);
        }
        else {
            this.user = proDao.getUserSkills(this.user, 1);
        }
        System.out.println("user:" + this.user);
        return "success";
    }
    
    public String viewAllEducations() {
        System.out.println("--profileAction viewAllEducations");
        final ProfileDao proDao = new ProfileDao();
        if (!this.sessionUser.getRole().equals("admin")) {
            this.user.setUserID(this.sessionUser.getUserID());
            this.user = proDao.getUserEducations(this.user, 2);
        }
        else {
            this.user = proDao.getUserEducations(this.user, 1);
        }
        System.out.println("user:" + this.user);
        return "success";
    }
    
    public String viewAllExperiences() {
        System.out.println("--profileAction viewAllExperiences");
        final ProfileDao proDao = new ProfileDao();
        if (!this.sessionUser.getRole().equals("admin")) {
            this.user.setUserID(this.sessionUser.getUserID());
            this.user = proDao.getUserExperiences(this.user, 2);
        }
        else {
            this.user = proDao.getUserExperiences(this.user, 1);
        }
        System.out.println("user:" + this.user);
        return "success";
    }
    
    public String viewAboutme() {
        System.out.println("--profileAction viewAboutme");
        if (!this.sessionUser.getRole().equals("admin")) {
            this.user.setUserID(this.sessionUser.getUserID());
            this.user.setUserName(this.sessionUser.getUserName());
        }
        System.out.println("user:" + this.user);
        final ProfileDao proDao = new ProfileDao();
        this.user = proDao.getUserAbout(this.user);
        return "success";
    }
    
    public String viewConnection() {
        System.out.println("--profileAction viewConnection");
        if (!this.sessionUser.getRole().equals("admin")) {
            this.user.setUserID(this.sessionUser.getUserID());
            this.user.setUserName(this.sessionUser.getUserName());
        }
        System.out.println("user:" + this.user);
        final ProfileDao proDao = new ProfileDao();
        this.user = proDao.getUserAbout(this.user);
        return "success";
    }
    
    public String addSkill() {
        System.out.println("--profileAction addSkill");
        System.out.println("skill:" + this.skill);
        final ProfileDao proDao = new ProfileDao();
        String generatedID = null;
        if (!this.sessionUser.getRole().equals("admin")) {
            generatedID = proDao.insertSkill(this.sessionUser.getUserID(), this.skill);
        }
        else {
            generatedID = proDao.insertSkill(this.user.getUserID(), this.skill);
        }
        if (generatedID != null) {
            System.out.println("generated ID:" + generatedID);
            this.skill.setSkillID(generatedID);
            return "success";
        }
        return "none";
    }
    
    public String addEducation() {
        System.out.println("--profileAction addEducation");
        System.out.println("education:" + this.education);
        final ProfileDao proDao = new ProfileDao();
        int generatedID = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            generatedID = proDao.insertEducation(this.sessionUser.getUserID(), this.education);
        }
        else {
            generatedID = proDao.insertEducation(this.user.getUserID(), this.education);
        }
        if (generatedID > 0) {
            System.out.println("generated ID:" + generatedID);
            this.education.setEducationID(generatedID);
            return "success";
        }
        return "none";
    }
    
    public String addExperience() {
        System.out.println("--profileAction addSkill");
        System.out.println("experience:" + this.experience);
        final ProfileDao proDao = new ProfileDao();
        int generatedID = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            generatedID = proDao.insertExperience(this.sessionUser.getUserID(), this.experience);
        }
        else {
            generatedID = proDao.insertExperience(this.user.getUserID(), this.experience);
        }
        if (generatedID > 0) {
            System.out.println("generated ID:" + generatedID);
            this.experience.setExperienceID(generatedID);
            return "success";
        }
        return "none";
    }
    
    public String updateSkill() {
        System.out.println("--profileAction updateSkill");
        System.out.println("user" + this.user);
        final ProfileDao proDao = new ProfileDao();
        int updateSkillRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updateSkillRowsAffected = proDao.updateSkill(this.sessionUser.getUserID(), this.user.getSkills(), 2);
        }
        else {
            updateSkillRowsAffected = proDao.updateSkill(this.user.getUserID(), this.user.getSkills(), 1);
        }
        this.updateResult = updateSkillRowsAffected;
        return "success";
    }
    
    public String updateExperience() {
        System.out.println("--profileAction updateExperience");
        System.out.println("user" + this.user);
        final ProfileDao proDao = new ProfileDao();
        int updateExperienceRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updateExperienceRowsAffected = proDao.updateExperience(this.sessionUser.getUserID(), this.user.getExperiences(), 2);
        }
        else {
            updateExperienceRowsAffected = proDao.updateExperience(this.user.getUserID(), this.user.getExperiences(), 1);
        }
        this.updateResult = updateExperienceRowsAffected;
        return "success";
    }
    
    public String updateEducation() {
        System.out.println("--profileAction updateEducation");
        System.out.println("user" + this.user);
        final ProfileDao proDao = new ProfileDao();
        int updateEducationRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updateEducationRowsAffected = proDao.updateEducation(this.sessionUser.getUserID(), this.user.getEducations(), 2);
        }
        else {
            updateEducationRowsAffected = proDao.updateEducation(this.user.getUserID(), this.user.getEducations(), 1);
        }
        this.updateResult = updateEducationRowsAffected;
        return "success";
    }
    
    public String updateConnection() {
        System.out.println("--profileAction updateConnection");
        System.out.println("user" + this.user);
        final ProfileDao proDao = new ProfileDao();
        int updateConnectionRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updateConnectionRowsAffected = proDao.updateUserConnection(this.sessionUser.getUserID(), this.user);
        }
        else {
            updateConnectionRowsAffected = proDao.updateUserConnection(this.user.getUserID(), this.user);
        }
        proDao.updateUserConnection(this.sessionUser.getUserID(), this.user);
        this.updateResult = updateConnectionRowsAffected;
        return "success";
    }
    
    public String updateAboutme() {
        System.out.println("--profileAction updateAboutme");
        System.out.println("user" + this.user);
        final ProfileDao proDao = new ProfileDao();
        int updateAboutmeRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updateAboutmeRowsAffected = proDao.updateUserAbout(this.sessionUser.getUserID(), this.user);
        }
        else {
            updateAboutmeRowsAffected = proDao.updateUserAbout(this.user.getUserID(), this.user);
        }
        this.updateResult = updateAboutmeRowsAffected;
        return "success";
    }
    
    public String deleteEducation() {
        System.out.println("--profileAction deleteEducation");
        System.out.println("education" + this.education);
        final ProfileDao proDao = new ProfileDao();
        int updatedeleteEducationRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updatedeleteEducationRowsAffected = proDao.deleteEducation(this.sessionUser.getUserID(), this.education);
        }
        else {
            updatedeleteEducationRowsAffected = proDao.deleteEducation(this.user.getUserID(), this.education);
        }
        this.updateResult = updatedeleteEducationRowsAffected;
        return "success";
    }
    
    public String deleteExperience() {
        System.out.println("--profileAction deleteExperience");
        System.out.println("experience" + this.experience);
        final ProfileDao proDao = new ProfileDao();
        int updatedeleteExperienceRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updatedeleteExperienceRowsAffected = proDao.deleteExperience(this.sessionUser.getUserID(), this.experience);
        }
        else {
            updatedeleteExperienceRowsAffected = proDao.deleteExperience(this.user.getUserID(), this.experience);
        }
        this.updateResult = updatedeleteExperienceRowsAffected;
        return "success";
    }
    
    public String deleteSkill() {
        System.out.println("--profileAction deleteSkill");
        System.out.println("skill" + this.skill);
        final ProfileDao proDao = new ProfileDao();
        int updatedeleteSkillRowsAffected = 0;
        if (!this.sessionUser.getRole().equals("admin")) {
            updatedeleteSkillRowsAffected = proDao.deleteSkill(this.sessionUser.getUserID(), this.skill);
        }
        else {
            updatedeleteSkillRowsAffected = proDao.deleteSkill(this.user.getUserID(), this.skill);
        }
        this.updateResult = updatedeleteSkillRowsAffected;
        return "success";
    }
    
    public String deleteEducationPermanently() {
        System.out.println("--profileAction deleteEducation2");
        System.out.println("education" + this.education);
        final ProfileDao proDao = new ProfileDao();
        int deleteEducationRowsAffected = 0;
        deleteEducationRowsAffected = proDao.deleteEducationPermanently(this.user.getUserID(), this.education);
        this.deleteResult = deleteEducationRowsAffected;
        return "success";
    }
    
    public String deleteExperiencePermanently() {
        System.out.println("--profileAction deleteExperience2");
        System.out.println("experience" + this.experience);
        final ProfileDao proDao = new ProfileDao();
        int deleteExperienceRowsAffected = 0;
        deleteExperienceRowsAffected = proDao.deleteExperiencePermanently(this.user.getUserID(), this.experience);
        this.deleteResult = deleteExperienceRowsAffected;
        return "success";
    }
    
    public String deleteSkillPermanently() {
        System.out.println("--profileAction deleteSkill2");
        System.out.println("skill" + this.skill);
        final ProfileDao proDao = new ProfileDao();
        int deleteSkillRowsAffected = 0;
        deleteSkillRowsAffected = proDao.deleteSkillPermanently(this.user.getUserID(), this.skill);
        this.deleteResult = deleteSkillRowsAffected;
        return "success";
    }
    
    public void prepare() throws Exception {
        this.directory = this.getText("path.directory");
        this.year = (List<Integer>)listYear.produceYear(Integer.parseInt(this.getText("year.minimum")), Integer.parseInt(this.getText("year.maximum")));
        this.user = new User();
        this.sessionUser = (User) ActionContext.getContext().getSession().get("userData");
    }
}