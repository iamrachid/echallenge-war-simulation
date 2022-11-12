package com.sqli.nespresso.war.kingdoms.countries;

import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.kingdoms.cities.City;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

import java.util.List;

public class Country implements ICommunity {
    private String name;
    private List<City> cities;

    private int soldiersOnEdges;
    private ICommunityDispatcher countryDispatcher;

    public int getSoldiersOnEdges() {
        return soldiersOnEdges;
    }

    public void setSoldiersOnEdges(int soldiersOnEdges) {
        this.soldiersOnEdges = soldiersOnEdges;
    }

    public Country(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
        countryDispatcher = CountryDispatcher.getInstance();
    }

    @Override
    public String report() {
        return countryDispatcher.report(this);
    }

    @Override
    public int currentPower() {
        return countryDispatcher.currentPower(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
