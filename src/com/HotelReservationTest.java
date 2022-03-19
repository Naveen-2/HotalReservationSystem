package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {

    List<Hotel> hotelList = new ArrayList<>();

    @org.junit.jupiter.api.Test
    void addNewHotel() {
        Hotel hotel1 = new Hotel("Lakewood", 110);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood", 160);
        hotelList.add(hotel2);
        Hotel hotel3 = new Hotel("Ridgewood", 220);
        hotelList.add(hotel3);
    }

    @org.junit.jupiter.api.Test
    void findCheapestHotel() {
        /*
         Adds 3 hotels to the list and sort it in ascending order
         After that we'll check if the correct output is generated or not
         */
        addNewHotel();
        List<Hotel> sortedHotelList = hotelList.stream()
                .sorted(Comparator.comparingDouble(Hotel::getRates)).toList();
        assertEquals("Lakewood",sortedHotelList.get(0).getName());
        assertEquals(110.0,sortedHotelList.get(0).getRates());

    }

}