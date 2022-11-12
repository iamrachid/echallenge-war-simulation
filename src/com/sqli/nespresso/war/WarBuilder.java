package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdoms.Kingdom;

public class WarBuilder {
    private War war;

    public WarBuilder() {
        this.war = new War();
    }

    public WarBuilder addKingdom(Kingdom kingdom){
        war.getKingdoms().add(kingdom);
        return this;
    }

    public WarBuilder addMap(String map){
        war.setMap(map);

        return this;
    }

    public War build(){
        return war;
    }
}
