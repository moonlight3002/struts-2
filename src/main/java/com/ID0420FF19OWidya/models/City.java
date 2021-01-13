package com.ID0420FF19OWidya.models;

public class City
{
    private int cityID;
    private String cityName;
    private int stateID;
    
    public int getCityID() {
        return this.cityID;
    }
    
    public void setCityID(final int cityID) {
        this.cityID = cityID;
    }
    
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }
    
    public int getStateID() {
        return this.stateID;
    }
    
    public void setStateID(final int stateID) {
        this.stateID = stateID;
    }
    
    @Override
    public String toString() {
        return "City [cityID=" + this.cityID + ", cityName=" + this.cityName + ", stateID=" + this.stateID + "]";
    }
}