package com.sqli.nespresso.war.kingdoms;

import com.sqli.nespresso.war.interfaces.ICommunity;
import com.sqli.nespresso.war.interfaces.ICommunityDispatcher;

public class KingdomDispatcher implements ICommunityDispatcher {
    private static KingdomDispatcher instance;

    private KingdomDispatcher() {}

    public static KingdomDispatcher getInstance() {
        if (instance == null)
            instance = new KingdomDispatcher();
        return instance;
    }

    @Override
    public String report(ICommunity ICommunity) {
        Kingdom kingdom = (Kingdom) ICommunity;
        StringBuilder builder = new StringBuilder();
        builder.append(kingdom.getKing());
        builder.append(':');
        builder.append('|');
       kingdom.getCountries().forEach(c -> {
            builder.append(c.report());
            builder.append(',');
        });
        builder.deleteCharAt(builder.length() - 1);
        builder.append('|');
        return builder.toString();
    }

    @Override
    public int currentPower(ICommunity ICommunity) {
        Kingdom kingdom = (Kingdom) ICommunity;
        return kingdom.getCountries().stream().mapToInt(value -> value.currentPower()).sum();
    }
}
