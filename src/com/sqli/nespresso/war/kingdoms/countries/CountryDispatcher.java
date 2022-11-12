package com.sqli.nespresso.war.kingdoms.countries;

import com.sqli.nespresso.war.kingdoms.cities.City;
import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

public class CountryDispatcher implements ICommunityDispatcher {

    private static CountryDispatcher instance;

    private CountryDispatcher() {
    }

    public static CountryDispatcher getInstance() {
        if (instance == null)
            instance = new CountryDispatcher();
        return instance;
    }

    @Override
    public String report(ICommunity ICommunity) {
        Country country = (Country) ICommunity;
        StringBuilder builder = new StringBuilder();

        char countryChar = country.getName().charAt(0);
        builder.append(countryChar)
                .append(':')
                .append('<');
        int index = 1;
        for (City c : country.getCities()) {
            builder.append(countryChar)
                    .append('c')
                    .append(index)
                    .append(':')
                    .append(c.report())
                    .append(',');
            index++;
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append('>');
        if (country.getSoldiersOnEdges() != 0)
            builder.append('-')
                    .append(country.getSoldiersOnEdges());
        return  builder.toString();
    }

    @Override
    public int currentPower(ICommunity ICommunity) {
        Country country = (Country) ICommunity;
        return country.getCities().stream().mapToInt(value -> value.currentPower()).sum();
    }
}
