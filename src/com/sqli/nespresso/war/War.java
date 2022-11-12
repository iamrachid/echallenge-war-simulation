package com.sqli.nespresso.war;

import com.sqli.nespresso.war.WarDispatcher;
import com.sqli.nespresso.war.kingdoms.Kingdom;
import com.sqli.nespresso.war.kingdoms.countries.Country;

import java.util.*;

public class War {
    private List<Kingdom> kingdoms;
    private String map;

    private WarDispatcher warDispatcher;


    public War() {
        kingdoms = new ArrayList<>();
        warDispatcher = WarDispatcher.getInstance();
    }

    public void prepareAttack(){
        warDispatcher.prepareKingdoms(kingdoms, map);
    }

    public void start(){
        warDispatcher.startAttack();
    }


    public List<Kingdom> getKingdoms() {
        return kingdoms;
    }

    public void setKingdoms(List<Kingdom> kingdoms) {
        this.kingdoms = kingdoms;
    }

    public String getMap() {
        return map;
    }
    public void  setMap(String map){
        this.map = map;
    }
}
