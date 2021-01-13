package com.ID0420FF19OWidya.actions;

import com.ID0420FF19OWidya.dao.Dao;
import com.ID0420FF19OWidya.models.City;
import java.util.List;
import com.ID0420FF19OWidya.models.Country;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class GetLocationAction extends ActionSupport implements ModelDriven<Country>
{
    private List<Country> countries;
    private List<City> cities;
    private Country country;
    
    public List<Country> getCountries() {
        return this.countries;
    }
    
    public void setCountries(final List<Country> countries) {
        this.countries = countries;
    }
    
    public List<City> getCities() {
        return this.cities;
    }
    
    public void setCities(final List<City> cities) {
        this.cities = cities;
    }
    
    public String getCountryList() {
        System.out.println("in getlocationaction getcountrylist");
        System.out.println(this.country);
        final Dao dao = new Dao();
        this.countries = (List<Country>)dao.getCountries();
        return "success";
    }
    
    public String getCityList() {
        System.out.println("in getlocationaction getcitylist");
        System.out.println(this.country);
        final Dao dao = new Dao();
        this.cities = (List<City>)dao.getCities(this.country.getCountryID());
        return "success";
    }
    
    public Country getModel() {
        return this.country = new Country();
    }
}