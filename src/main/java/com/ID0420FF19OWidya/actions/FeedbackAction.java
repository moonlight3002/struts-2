package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.util.validateInput;
import java.util.List;
import java.util.Map;
import com.ID0420FF19OWidya.models.User;
import com.ID0420FF19OWidya.models.Feedback;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAction extends ActionSupport implements Preparable
{
    private Feedback feedback;
    private User user;
    private Map<Integer, Integer> result;
    int rowsAffected;
    private List<Feedback> feeds;
    private int fromUserID;
    private int toUserID;
    private int fromID;
    private int toID;
    private int offset;
    private String category;
    private int read;
    private int solved;
    private String fromDate;
    private String toDate;
    private int deleteResult;
    private int updateResult;
    
    public FeedbackAction() {
        this.fromUserID = 0;
        this.toUserID = 0;
        this.fromID = 0;
        this.toID = 0;
        this.offset = 0;
    }
    
    public String getFromDate() {
        return this.fromDate;
    }
    
    public void setFromDate(final String fromDate) {
        this.fromDate = fromDate;
    }
    
    public String getToDate() {
        return this.toDate;
    }
    
    public void setToDate(final String toDate) {
        this.toDate = toDate;
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
    
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(final String category) {
        this.category = category;
    }
    
    public int getRead() {
        return this.read;
    }
    
    public void setRead(final int read) {
        this.read = read;
    }
    
    public int getSolved() {
        return this.solved;
    }
    
    public void setSolved(final int solved) {
        this.solved = solved;
    }
    
    public int getFromUserID() {
        return this.fromUserID;
    }
    
    public void setFromUserID(final int fromUserID) {
        this.fromUserID = fromUserID;
    }
    
    public int getToUserID() {
        return this.toUserID;
    }
    
    public void setToUserID(final int toUserID) {
        this.toUserID = toUserID;
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
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public List<Feedback> getFeeds() {
        return this.feeds;
    }
    
    public void setFeeds(final List<Feedback> feeds) {
        this.feeds = feeds;
    }
    
    public Feedback getFeedback() {
        return this.feedback;
    }
    
    public void setFeedback(final Feedback feedback) {
        this.feedback = feedback;
    }
    
    public Map<Integer, Integer> getResult() {
        return this.result;
    }
    
    public void setResult(final Map<Integer, Integer> result) {
        this.result = result;
    }
    
    public int getRowsAffected() {
        return this.rowsAffected;
    }
    
    public void setRowsAffected(final int rowsAffected) {
        this.rowsAffected = rowsAffected;
    }
    
    public String sendFeedback() {
        System.out.println("-in feedbackAction sendFeedback");
        System.out.println(this.feedback);
        final boolean isInputValid = validateInput.validateFeedbackForm(this.feedback);
        if (isInputValid) {
            System.out.println("isInputValid: true");
            final Dao dao = new Dao();
            this.rowsAffected = dao.insertFeedback(this.feedback, this.user.getUserID());
            System.out.println("send feedback rows affected" + this.rowsAffected);
        }
        else {
            System.out.println("isInputValid: false");
            this.rowsAffected = 0;
        }
        return "success";
    }
    
    public String deleteFeedback() {
        System.out.println("-in feedbackAction deleteFeedback");
        final Dao dao = new Dao();
        this.deleteResult = dao.deleteFeedback((List)this.feeds);
        System.out.println("result" + this.deleteResult);
        return "success";
    }
    
    public String viewFeedback() {
        System.out.println("-in feedbackAction viewFeedback");
        System.out.println("feedback" + this.feedback);
        final Dao dao = new Dao();
        this.feedback = dao.seeFeedback(this.feedback.getFeedbackID());
        System.out.println("feedback" + this.feedback);
        return "success";
    }
    
    public String solveFeedback() {
        System.out.println("-in feedbackAction solveFeedback");
        final Dao dao = new Dao();
        this.updateResult = dao.updateFeedback(this.feedback.getFeedbackID());
        System.out.println("result" + this.updateResult);
        return "success";
    }
    
    public String getFeedbackList() {
        System.out.println("-in feedbackAction getFeedbackList");
        final Dao dao = new Dao();
        System.out.println(String.valueOf(this.category) + this.read + this.solved + this.fromUserID + this.toUserID + this.fromID + this.toID + this.offset + this.fromDate + this.toDate);
        this.feeds = (List<Feedback>)dao.getFeedbackList(this.category, this.read, this.solved, this.fromUserID, this.toUserID, this.fromID, this.toID, this.offset, this.fromDate, this.toDate);
        System.out.println("feeds" + this.feeds);
        return "success";
    }
    
    public void prepare() throws Exception {
        System.out.println("-in feedbackAction prepare");
        System.out.println(this.feedback);
        this.user = new User();
        final User sessionuser = (User) ActionContext.getContext().getSession().get("userData");
        this.user.setUserID(sessionuser.getUserID());
    }
}