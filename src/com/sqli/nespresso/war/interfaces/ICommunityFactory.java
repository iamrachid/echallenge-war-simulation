package com.sqli.nespresso.war.interfaces;

import com.sqli.nespresso.war.kingdoms.cities.City;
import com.sqli.nespresso.war.kingdoms.countries.Country;

import java.util.List;

public interface ICommunityFactory {
    Country createCountry(String countryName, String... numbers);

    List<City> createCities(String... numbers);
}
