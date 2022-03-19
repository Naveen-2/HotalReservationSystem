package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
                                ScannerUtil.getDouble("Enter rate for Regular Customer: "));
        hotelList.add(hotel);
    }

    /**
     * findCheapestHotel - method to print the cost and name of hotel
     * that is currently available at cheapest rate
     */
    void findCheapestHotel(){
        List<Hotel> sortedHotelList = hotelList.stream()
                .sorted(Comparator.comparingDouble(Hotel::getRates)).toList();
        System.out.println("Cheapest Hotel: " + sortedHotelList.get(0).name +
                            "\nCost: " + sortedHotelList.get(0).rates);
    }

}
