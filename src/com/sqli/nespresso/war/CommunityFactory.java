package com.sqli.nespresso.war;

import com.sqli.nespresso.war.interfaces.ICommunityFactory;
import com.sqli.nespresso.war.kingdoms.cities.City;
import com.sqli.nespresso.war.kingdoms.countries.Country;

import java.util.ArrayList;
import java.util.List;

public class CommunityFactory implements ICommunityFactory {
        private static CommunityFactory instance;

        private CommunityFactory(){}

        public static CommunityFactory getInstance() {
            if (instance == null)
                instance = new CommunityFactory();
            return instance;
        }


        public Country createCountry(String countryName, String ...numbers) {
            List<City> cities = createCities(numbers);
            return new Country(countryName, cities);
        }

        public List<City> createCities(String ...numbers) {
            List<City> cities = new ArrayList<>(numbers.length/2);
            for (int i = 0; i < numbers.length; i+=2) {
                cities.add(new City(
                        Integer.parseInt(numbers[i]),
                        Integer.parseInt(numbers[i + 1])));
            }
            return cities;
        }
    }