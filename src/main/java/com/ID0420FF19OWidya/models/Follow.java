package com.ID0420FF19OWidya.models;

public class Follow
{
    private String followID;
    private int followerUserID;
    private int followingUserID;
    
    public String getFollowID() {
        return this.followID;
    }
    
    public void setFollowID(final String followID) {
        this.followID = followID;
    }
    
    public int getFollowerUserID() {
        return this.followerUserID;
    }
    
    public void setFollowerUserID(final int followerUserID) {
        this.followerUserID = followerUserID;
    }
    
    public int getFollowingUserID() {
        return this.followingUserID;
    }
    
    public void setFollowingUserID(final int followingUserID) {
        this.followingUserID = followingUserID;
    }
    
    @Override
    public String toString() {
        return "Follow [followID=" + this.followID + ", followerUserID=" + this.followerUserID + ", followingUserID=" + this.followingUserID + "]";
    }
}
