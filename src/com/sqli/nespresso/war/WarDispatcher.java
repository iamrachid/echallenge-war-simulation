package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdoms.Kingdom;
import com.sqli.nespresso.war.kingdoms.KingdomDispatcher;
import com.sqli.nespresso.war.kingdoms.cities.City;
import com.sqli.nespresso.war.kingdoms.countries.Country;

import java.util.*;

public class WarDispatcher implements IWarDispatcher {
    private static WarDispatcher instance;
    private KingdomDispatcher kingdomDispatcher;


    private Kingdom mostPowerfulKingdom;

    private Country attackingCountry;
    private Country attackedCountry;


    private WarDispatcher() {
        kingdomDispatcher = KingdomDispatcher.getInstance();
    }

    public static WarDispatcher getInstance() {
        if (instance == null)
            instance = new WarDispatcher();
        return instance;
    }


    public void prepareKingdoms(List<Kingdom> kingdoms, String map) {

        mostPowerfulKingdom = kingdoms.stream().max(Comparator.comparingInt(Kingdom::currentPower)).get();
        String nearestCountries = getNearestCountries(mostPowerfulKingdom, map);
        String country = nearestCountries.split(":")[0],
                countryEnemy = nearestCountries.split(":")[1];

        for (Kingdom k : kingdoms) {
            if (k == mostPowerfulKingdom)
                continue;
            for (Country c : k.getCountries())
                if (c.getName().equals(countryEnemy)) {
                    attackedCountry = c;
                }
        }
        for (Country c : mostPowerfulKingdom.getCountries())
            if (c.getName().equals(country)) {
                attackingCountry = c;
            }
        prepareKingdom(mostPowerfulKingdom, attackingCountry);
    }

    public void prepareKingdom(Kingdom kingdom, Country country) {
        int movedArmy = 0;
        for (Country co: kingdom.getCountries()) {
            if (co == country)
                continue;
            for (City c : co.getCities()) {
                movedArmy += c.getNbreOfSoldiers() / 2;
                c.setNbreOfSoldiers(c.getNbreOfSoldiers() / 2);
            }
        }
        country.setSoldiersOnEdges(country.getSoldiersOnEdges() + movedArmy);
    }

    @Override
    public void startAttack() {
        int deceasedSoldiers = Math.min(attackedCountry.getSoldiersOnEdges(),
                attackingCountry.getSoldiersOnEdges());
        attackedCountry.setSoldiersOnEdges(attackedCountry.getSoldiersOnEdges() - deceasedSoldiers);
        attackingCountry.setSoldiersOnEdges(attackingCountry.getSoldiersOnEdges() - deceasedSoldiers);
    }

    @Override
    public String getNearestCountries(Kingdom kingdom, String map) {
        int nearest = Integer.MAX_VALUE;
        String distance = "";
        for (String d: map.split(",")){
            String e[] = d.split(":");
            if (Integer.parseInt(e[1]) > nearest)
                continue;
            boolean b1 = kingdom.getCountries().stream().anyMatch(c -> c.getName().equals(e[0])),
                    b2 = kingdom.getCountries().stream().anyMatch(c -> c.getName().equals(e[2]));
            if (b1 ^ b2) {
                nearest = Integer.parseInt(e[1]);
                distance = b1 ? e[0]+":"+e[2] : e[2]+":"+e[0];
            }
        }
        return distance;
    }
}
