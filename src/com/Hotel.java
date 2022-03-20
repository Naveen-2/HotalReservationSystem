package com;

public class Hotel {
    String name;
    double weekdayRatesRegular;
    double weekendRatesRegular;
    double weekdayRatesRewards;
    double weekendRatesRewards;
    double userRatings;

    /*
    Hotel constructor to add name and rates for regular customer
     */
    public Hotel(String name, double weekdayRatesRegular, double weekendRatesRegular){
        this.name = name;
        this.weekdayRatesRegular = weekdayRatesRegular;
        this.weekendRatesRegular = weekendRatesRegular;
    }

    /*
      getWeekdayRates - getter method to return hotel's weekday rates
     */
    public double getWeekdayRatesRegular() {
        return weekdayRatesRegular;
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
    public double getWeekendRatesRegular() {
        return weekendRatesRegular;
    }

    /*
    setUserRatings - setter to set user ratings for the hotel
     */
    public void setUserRatings(double userRatings) {
        this.userRatings = userRatings;
    }

    /*
    getUserRatings - getter to get user ratings
     */
    public double getUserRatings() {
        return userRatings;
    }

    /*
    getWeekdayRatesRewards - getter method to get weekday rates for rewards customer
     */
    public double getWeekdayRatesRewards() {
        return weekdayRatesRewards;
    }

    /*
    getWeekendRatesRewards - getter method to get weekend rates for rewards customer
     */
    public double getWeekendRatesRewards() {
        return weekendRatesRewards;
    }
}
