package com.sqli.nespresso.war.kingdoms.cities;

import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

public class City implements ICommunity {

    private int nbrOfCitizens;
    private int nbreOfSoldiers;

    private ICommunityDispatcher cityDispatcher;

    public City(int nbreOfSoldiers, int nbrOfCitizens) {
        this.nbrOfCitizens = nbrOfCitizens;
        this.nbreOfSoldiers = nbreOfSoldiers;
        cityDispatcher = CityDispatcher.getInstance();
    }

    @Override
    public String report() {
        return cityDispatcher.report(this);
    }

    @Override
    public int currentPower() {
        return cityDispatcher.currentPower(this);
    }

    public int getNbrOfCitizens() {
        return nbrOfCitizens;
    }

    public void setNbrOfCitizens(int nbrOfCitizens) {
        this.nbrOfCitizens = nbrOfCitizens;
    }

    public int getNbreOfSoldiers() {
        return nbreOfSoldiers;
    }

    public void setNbreOfSoldiers(int nbreOfSoldiers) {
        this.nbreOfSoldiers = nbreOfSoldiers;
    }
}
