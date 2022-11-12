package com.sqli.nespresso.war.kingdoms;

import com.sqli.nespresso.war.kingdoms.countries.Country;
import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

import java.util.ArrayList;
import java.util.List;

public class Kingdom implements ICommunity {
    private String king;
    private List<Country> countries;
    private ICommunityDispatcher kingdomDispatcher;

    public String getKing() {
        return king;
    }

    public void setKing(String king) {
        this.king = king;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Kingdom() {
        countries = new ArrayList<>();
        kingdomDispatcher = KingdomDispatcher.getInstance();
    }

    @Override
    public String report() {
        return kingdomDispatcher.report(this);
    }

    @Override
    public int currentPower() {
        return kingdomDispatcher.currentPower(this);
    }

}
