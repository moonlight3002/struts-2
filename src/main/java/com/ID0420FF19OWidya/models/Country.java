package com.ID0420FF19OWidya.models;

public class Country
{
    private int countryID;
    private String sortName;
    private String countryName;
    private int phoneCode;
    
    public int getCountryID() {
        return this.countryID;
    }
    
    public void setCountryID(final int countryID) {
        this.countryID = countryID;
    }
    
    public String getSortName() {
        return this.sortName;
    }
    
    public void setSortName(final String sortName) {
        this.sortName = sortName;
    }
    
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }
    
    public int getPhoneCode() {
        return this.phoneCode;
    }
    
    public void setPhoneCode(final int phoneCode) {
        this.phoneCode = phoneCode;
    }
    
    @Override
    public String toString() {
        return "Country [countryID=" + this.countryID + ", sortName=" + this.sortName + ", countryName=" + this.countryName + ", phoneCode=" + this.phoneCode + "]";
    }
}