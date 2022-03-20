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

    String customerType = "";

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

            if (!bookedHotel.isEmpty())
                clearListIfHotelBooked();
            customerType = ScannerUtil.getString("Enter Customer Type(Regular/Rewards): ");
            if (customerType.equalsIgnoreCase("Regular")) {
                for (LocalDate date : dates) {
                    if (isWeekend(date)) {
                        sortedHotelList = hotelList.stream()
                                .sorted(Comparator.comparingDouble(Hotel::getWeekendRatesRegular)).toList();
                        bookedHotel.add(sortedHotelList.get(sortedHotelList.size() - 1));
                    } else {
                        sortedHotelList = hotelList.stream()
                                .sorted(Comparator.comparingDouble(Hotel::getWeekdayRatesRegular)).toList();
                        bookedHotel.add(sortedHotelList.get(0));
                    }
                }
            } else if(customerType.equalsIgnoreCase("Rewards")){
                for (LocalDate date : dates) {
                    if (isWeekend(date)) {
                        sortedHotelList = hotelList.stream()
                                .sorted(Comparator.comparingDouble(Hotel::getWeekendRatesRewards)).toList();
                        bookedHotel.add(sortedHotelList.get(sortedHotelList.size() - 1));
                    } else {
                        sortedHotelList = hotelList.stream()
                                .sorted(Comparator.comparingDouble(Hotel::getWeekdayRatesRewards)).toList();
                        bookedHotel.add(sortedHotelList.get(0));
                    }
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
     *
     */
    public void printHotelsList(){
        if (hotelList.isEmpty()){
            System.out.println("Hotel list empty. Please add hotels.");
        } else {
            System.out.println("Available hotels: ");
            for (Hotel hotel : hotelList) {
                System.out.println("Hotel: " + hotel.name);
            }
        }
    }

    /**
     * printBookedHotels - method to print booked hotels
     */
    public void printBookedHotels(){
        if(hotelList.isEmpty()){
            System.out.println("Hotels list empty. Please add hotels.");
        } else {
            if(bookedHotel.isEmpty()){
                System.out.println("Hotels not booked yet. Enter choice 2 to find and book cheapest hotels.");
            } else {
                for (int i = 0; i < bookedHotel.size(); i++) {
                    if (isWeekend(bookingDates[i])) {
                        System.out.println("Date: " + bookingDates[i]
                                + "  Hotel name: " + bookedHotel.get(i).name
                                + "  Rate: $" + bookedHotel.get(i).getWeekendRatesRegular());
                        totalBookingRate += bookedHotel.get(i).getWeekendRatesRegular();
                    } else {
                        System.out.println("Date: " + bookingDates[i]
                                + "Hotel name: " + bookedHotel.get(i).name
                                + "Rate: $" + bookedHotel.get(i).getWeekdayRatesRegular());
                        totalBookingRate += bookedHotel.get(i).getWeekdayRatesRegular();
                    }
                }
            }
        }

        System.out.println("Total rate for the entered dates: $" + totalBookingRate);

    }

    /**
     * addUserRatings - method to add ratings for the hotels from user end
     */
    public void addUserRatings(){
        printHotelsList();
        if(!hotelList.isEmpty()) {
            String hotelName = ScannerUtil.getString("Enter hotel name to give ratings: ");

            for (Hotel hotel : hotelList) {
                if (hotelName.equalsIgnoreCase(hotel.name)) {
                    hotel.setUserRatings(ScannerUtil.getDouble("Enter ratings for the selected hotel: "));
                    break;
                }
            }
        }
    }

    /**
     * findCheapestBestRatedHotel - method to find the cheapest best rated hotel
     */
    public void findCheapestBestRatedHotel(){
        List<Hotel> sortedHotelList;
        String bestRatedHotel = "";
        double totalCharge = 0.0;
        if (!hotelList.isEmpty()){
            LocalDate[] bookingDates = getBookingDates();
            if (!bookedHotel.isEmpty()) {
                clearListIfHotelBooked();
                findCheapestHotel(bookingDates);
            }
            sortedHotelList = bookedHotel.stream()
                    .sorted(Comparator.comparingDouble(Hotel::getUserRatings)).toList();
            bestRatedHotel = sortedHotelList.get(0).name;
            if(customerType.equalsIgnoreCase("Regular")) {
                for (LocalDate bookingDate : bookingDates) {
                    if (isWeekend(bookingDate)) {
                        totalCharge += sortedHotelList.get(0).getWeekendRatesRegular();
                    } else {
                        totalCharge += sortedHotelList.get(0).getWeekdayRatesRegular();
                    }
                }
            } else if (customerType.equalsIgnoreCase("Rewards")){
                for (LocalDate bookingDate : bookingDates) {
                    if (isWeekend(bookingDate)) {
                        totalCharge += sortedHotelList.get(0).getWeekendRatesRewards();
                    } else {
                        totalCharge += sortedHotelList.get(0).getWeekdayRatesRewards();
                    }
                }
            }
            System.out.println("Best rated hotel: " + bestRatedHotel +
                    "\nTotal Charge: $" + totalCharge);
        } else {
            System.out.println("Please add a hotel to list first.");
        }
    }

    /**
     * clearListIfHotelBooked - method to clear the list in bookedHotels
     */
    public void clearListIfHotelBooked(){
        for (Hotel hotel : bookedHotel) {
            bookedHotel.remove(hotel);
        }
    }

    /**
     * findBestRatedHotel - method to find the best rated hotel from hotel list
     */
    public void findBestRatedHotel() {
        List<Hotel> sortedHotelList;
        String bestRatedHotel = "";
        double totalCharge = 0.0;
        if (!hotelList.isEmpty()){
            LocalDate[] bookingDates = getBookingDates();
            if (!bookedHotel.isEmpty()) {
                clearListIfHotelBooked();
            }
            customerType = ScannerUtil.getString("Enter Customer Type(Regular/Rewards): ");

            sortedHotelList = hotelList.stream()
                    .sorted(Comparator.comparingDouble(Hotel::getUserRatings)).toList();
            bestRatedHotel = sortedHotelList.get(0).name;
            if(customerType.equalsIgnoreCase("Regular")) {
                for (LocalDate bookingDate : bookingDates) {
                    if (isWeekend(bookingDate)) {
                        totalCharge += sortedHotelList.get(0).getWeekendRatesRegular();
                    } else {
                        totalCharge += sortedHotelList.get(0).getWeekdayRatesRegular();
                    }
                }
            } else if (customerType.equalsIgnoreCase("Rewards")){
                for (LocalDate bookingDate : bookingDates) {
                    if (isWeekend(bookingDate)) {
                        totalCharge += sortedHotelList.get(0).getWeekendRatesRewards();
                    } else {
                        totalCharge += sortedHotelList.get(0).getWeekdayRatesRewards();
                    }
                }
            }
            System.out.println("Best rated hotel: " + bestRatedHotel +
                    "\nTotal Charge: $" + totalCharge +
                    "\nCustomer Type: " + customerType);
        } else {
            System.out.println("Please add a hotel to list first.");
        }

    }


}
