package com;

import java.util.ArrayList;
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

}
