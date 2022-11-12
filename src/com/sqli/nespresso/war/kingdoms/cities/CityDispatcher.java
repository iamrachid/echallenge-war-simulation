package com.sqli.nespresso.war.kingdoms.cities;

import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

public class CityDispatcher implements ICommunityDispatcher {

    private static CityDispatcher instance;

    private CityDispatcher() {
    }

    public static CityDispatcher getInstance() {
        if (instance == null) {
            instance = new CityDispatcher();
        }
        return instance;
    }

    @Override
    public String report(ICommunity ICommunity) {
        City  city = (City) ICommunity;
        return city.getNbreOfSoldiers() + "-" + city.getNbrOfCitizens();
    }

    @Override
    public int currentPower(ICommunity ICommunity) {
        City  city = (City) ICommunity;
        return city.getNbreOfSoldiers();
    }
}
