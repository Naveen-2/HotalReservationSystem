package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

public class HotelReservation {
    /*
     hotelList - ArrayList of hotel objects can be stored in this variable
     */
    List<Hotel> hotelList = new ArrayList<>();

    /**
     * addNewHotel - method to add new hotel to hotel list
     */
    void addNewHotel(){
        Hotel hotel = new Hotel(ScannerUtil.getString("Enter Hotel name: "),
                                ScannerUtil.getDouble("Enter week day rate: "),
                                ScannerUtil.getDouble("Enter week end rate: "));
        hotelList.add(hotel);
    }

    /**
     * findCheapestHotel - method to print the cost and name of hotel
     * that is currently available at cheapest rate
     * @param date - date in which cx want to book the hotel room
     */
    void findCheapestHotel(LocalDate date){
        List<Hotel> sortedHotelList;
        if(isWeekend(date)){
            sortedHotelList = hotelList.stream()
                    .sorted(Comparator.comparingDouble(Hotel::getWeekendRates)).toList();
            System.out.println("Cheapest Hotel: " + sortedHotelList.get(sortedHotelList.size()-1).name +
                    "\nCost: " + sortedHotelList.get(sortedHotelList.size()-1).getWeekendRates());
        } else {
            sortedHotelList = hotelList.stream()
                                .sorted(Comparator.comparingDouble(Hotel::getWeekdayRates)).toList();
        System.out.println("Cheapest Hotel: " + sortedHotelList.get(0).name +
                            "\nCost: " + sortedHotelList.get(0).getWeekdayRates());
        }
    }


    public static boolean isWeekend(LocalDate date)
    {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

}
