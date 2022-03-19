package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelReservationTest {
    List<Hotel> hotelList = new ArrayList<>();

    @org.junit.jupiter.api.Test
    void addNewHotel() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 50);
        hotelList.add(hotel2);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150);
        hotelList.add(hotel3);
    }

    @org.junit.jupiter.api.Test
    void findCheapestHotel() {
        /*
         Adds 3 hotels to the list and sort it in ascending order
         After that we'll check if the correct output is generated or not
         */
        addNewHotel();
        List<Hotel> sortedHotelList;

        if (isWeekend(LocalDate.of(2022, 3, 19))) {
            /*
            Sorted based on weekend rates in ascending order and prints first element
             */
            sortedHotelList = hotelList.stream()
                    .sorted(Comparator.comparingDouble(Hotel::getWeekendRates)).toList();
            System.out.println("Cheapest Hotel: " + sortedHotelList.get(0).name +
                    "\nCost: " + sortedHotelList.get(0).getWeekendRates());
            assertEquals("Bridgewood",sortedHotelList.get(0).getName());
            assertEquals(50.0,sortedHotelList.get(0).getWeekendRates());
        } else {
            /*
            Sorted based on weekday rates in ascending order and prints first element
             */
            sortedHotelList = hotelList.stream()
                    .sorted(Comparator.comparingDouble(Hotel::getWeekdayRates)).toList();
            System.out.println("Cheapest Hotel: " + sortedHotelList.get(sortedHotelList.size()-1).name +
                    "\nCost: " + sortedHotelList.get(sortedHotelList.size()-1).getWeekdayRates());
            assertEquals("Lakewood",sortedHotelList.get(0).getName());
            assertEquals(110.0,sortedHotelList.get(0).getWeekdayRates());
        }



    }

    public static boolean isWeekend(final LocalDate date)
    {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }



}