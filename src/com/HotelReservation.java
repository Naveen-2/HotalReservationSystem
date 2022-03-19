package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

public class HotelReservation {
    /*
     hotelList - ArrayList of hotel objects can be stored in this variable
     */
    List<Hotel> hotelList = new ArrayList<>();

    LocalDate[] bookingDates;
    List<Hotel> bookedHotel = new ArrayList<>();
    Double totalBookingRate = 0.0;

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
     * @param dates - dates in which cx want to book the hotel room
     */
    void findCheapestHotel(LocalDate[] dates){
        if(hotelList.isEmpty()){
            System.out.println("Add at least one hotel before requesting to find cheap hotel.");
        } else {
            List<Hotel> sortedHotelList;

            for (LocalDate date : dates) {
                if (isWeekend(date)) {
                    sortedHotelList = hotelList.stream()
                            .sorted(Comparator.comparingDouble(Hotel::getWeekendRates)).toList();
                    bookedHotel.add(sortedHotelList.get(sortedHotelList.size() - 1));
                } else {
                    sortedHotelList = hotelList.stream()
                            .sorted(Comparator.comparingDouble(Hotel::getWeekdayRates)).toList();
                    bookedHotel.add(sortedHotelList.get(0));
                }
            }
        }
    }

    /**
     * isWeekend - method to confirm if the given date is weekend or not
     * @param date - input date from user
     * @return true if input date is weekend or false if weekday
     */
    public static boolean isWeekend(LocalDate date)
    {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    /**
     * getBookingDates - method to read booking dates from user
     * @return - array of dates which user request to book room
     */
    public LocalDate[] getBookingDates(){

        int n = ScannerUtil.getInt("Enter Number of days to book: ");
        Scanner input = new Scanner(System.in);

        bookingDates = new LocalDate[n];
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");

        System.out.println("Enter date in (M/d/yyyy) format: ");

        for(int i=0; i<n; i++) {
            try {
                bookingDates[i] = LocalDate.parse(input.nextLine(), dateFormat);
            } catch (Exception e){
                System.out.println("Entered date is in wrong format.");
                break;
            }
        }

        return bookingDates;
    }

    /**
     * printBookedHotels - method to print booked hotels
     */
    public void printBookedHotels(){
        if(hotelList.isEmpty()){
            System.out.println("Add at least one hotel before requesting to print.");
        } else {
            if(bookedHotel.isEmpty()){
                System.out.println("Hotels not booked yet. Enter choice 2 to find and book cheapest hotels.");
            } else {
                for (int i = 0; i < bookedHotel.size(); i++) {
                    if (isWeekend(bookingDates[i])) {
                        System.out.println("Date: " + bookingDates[i]
                                + "  Hotel name: " + bookedHotel.get(i).name
                                + "  Rate: $" + bookedHotel.get(i).getWeekendRates());
                        totalBookingRate += bookedHotel.get(i).getWeekendRates();
                    } else {
                        System.out.println("Date: " + bookingDates[i]
                                + "Hotel name: " + bookedHotel.get(i).name
                                + "Rate: $" + bookedHotel.get(i).getWeekdayRates());
                        totalBookingRate += bookedHotel.get(i).getWeekdayRates();
                    }
                }
            }
        }

        System.out.println("Total rate for the entered dates: $" + totalBookingRate);

    }

}
