package com.ID0420FF19OWidya.actions;

import java.io.IOException;
import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.Technology;
import com.ID0420FF19OWidya.models.User;
import java.util.List;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport implements Preparable
{
    private List<User> userCollections;
    private String inputValue;
    private String directory;
    private int[] technologyids;
    private int searchOffset;
    private List<Technology> technologyLists;
    
    public SearchAction() {
        this.searchOffset = 0;
    }
    
    public List<Technology> getTechnologyLists() {
        return this.technologyLists;
    }
    
    public void setTechnologyLists(final List<Technology> technologyLists) {
        this.technologyLists = technologyLists;
    }
    
    public int getSearchOffset() {
        return this.searchOffset;
    }
    
    public void setSearchOffset(final int searchOffset) {
        this.searchOffset = searchOffset;
    }
    
    public int[] getTechnologyids() {
        return this.technologyids;
    }
    
    public void setTechnologyids(final int[] technologyids) {
        this.technologyids = technologyids;
    }
    
    public String getInputValue() {
        System.out.println("getInputValue");
        return this.inputValue;
    }
    
    public void setInputValue(final String inputValue) {
        System.out.println("setInputValue");
        this.inputValue = inputValue;
    }
    
    public List<User> getUserCollections() {
        return this.userCollections;
    }
    
    public void setUserCollections(final List<User> userCollections) {
        this.userCollections = userCollections;
    }
    
    public String execute() throws IOException {
        System.out.println("in search execute");
        final Dao dao = new Dao();
        this.userCollections = (List<User>)dao.searchUser(this.inputValue, this.directory);
        System.out.println("-collections" + this.userCollections);
        System.out.println(this.userCollections.isEmpty());
        if (!this.userCollections.isEmpty()) {
            System.out.println("in success block");
            return "success";
        }
        return "none";
    }
    
    public String searchUser() throws IOException {
        System.out.println("in searchUser");
        System.out.println("inputValue: " + this.inputValue);
        System.out.println("technologyids : " + this.technologyids);
        System.out.println("offset: " + this.searchOffset);
        final Dao dao = new Dao();
        this.userCollections = (List<User>)dao.searchMoreUser(this.inputValue, this.technologyids, this.searchOffset);
        this.technologyLists = (List<Technology>)dao.getTechnologyLists();
        System.out.println("-collections" + this.userCollections);
        System.out.println("-technologyLists" + this.technologyLists);
        System.out.println(this.userCollections.isEmpty());
        if (!this.userCollections.isEmpty()) {
            System.out.println("in success block");
            return "success";
        }
        return "none";
    }
    
    public void prepare() throws Exception {
        this.directory = this.getText("path.directory");
    }
}