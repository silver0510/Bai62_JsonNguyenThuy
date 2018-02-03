package com.example.sinki.model;

/**
 * Created by Sinki on 9/9/2017.
 */

public class Contact {
    private String name;
    private String city;
    private String country;

    public Contact(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String Country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.city + "\n" + this.country;
    }
}
