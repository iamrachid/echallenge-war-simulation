package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdoms.Kingdom;
import com.sqli.nespresso.war.kingdoms.countries.Country;

import java.util.List;
import java.util.Map;

public interface IWarDispatcher {
    void prepareKingdoms(List<Kingdom> kingdoms, String map);

    void startAttack();


    String getNearestCountries(Kingdom mostPowerfulKingdom, String map);
}
