package com;

public class Hotel {
    String name;
    double weekdayRates;
    double weekendRates;

    /*
    Hotel constructor to add name and rates for regular customer
     */
    public Hotel(String name, double weekdayRates, double weekendRates){
        this.name = name;
        this.weekdayRates = weekdayRates;
        this.weekendRates = weekendRates;
    }

    /*
      getWeekdayRates - getter method to return hotel's weekday rates
     */
    public double getWeekdayRates() {
        return weekdayRates;
    }

    /*
    getName - getter method to return hotel name
     */
    public String getName() {
        return name;
    }

    /*
    getWeekendRates - getter method to return hotel's weekend rates
     */
    public double getWeekendRates() {
        return weekendRates;
    }
}
