package com.sqli.nespresso.war.kingdoms;

import com.sqli.nespresso.war.CommunityFactory;
import com.sqli.nespresso.war.kingdoms.countries.Country;
import com.sqli.nespresso.war.interfaces.ICommunityFactory;

public class KingdomBuilder {
    private Kingdom kingdom;
    private static ICommunityFactory communityFactory = CommunityFactory.getInstance();

    public KingdomBuilder() {
        this.kingdom = new Kingdom();
    }

    public KingdomBuilder addKing(String king){
        kingdom.setKing(king);
        return this;
    }
    
    public KingdomBuilder addCountry(String countryName, String ...numbers){
        Country country = communityFactory.createCountry(
                countryName,
                numbers);
        kingdom.getCountries().add(country);
        return this;
    }

    public KingdomBuilder addSoldiersOnEdges(String soldiersOnEdges){
        kingdom.getCountries().forEach(
                c -> c.setSoldiersOnEdges(Integer.parseInt(soldiersOnEdges)));
        return this;
    }
    
    public Kingdom build(){
        return kingdom;
    }
}
