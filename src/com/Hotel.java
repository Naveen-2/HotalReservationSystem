package com;

public class Hotel {
    String name;
    double rates;

    /*
    Hotel constructor to add name and rates for regular customer
     */
    public Hotel(String name, double ratesForRegularCustomerDuringWeekDays){
        this.name = name;
        this.rates = ratesForRegularCustomerDuringWeekDays;
    }

    public double getRates() {
        return rates;
    }

    public String getName() {
        return name;
    }
}
