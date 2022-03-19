package com;

/**
 * Class with main method
 *
 * @author NAVEEN
 *
 */

public class Main {

    /**
     * handleUserSelection - method to process the option that user selects
     * from the menu provided by the system
     */
    public void handleUserSelection() {
        /*
        Object containing the user menu items
         */
        UserMenu userMenu = new UserMenu();

        HotelReservation hotelReservation = new HotelReservation();

        int x;

        do {
            /*
            Shows the available menu options
            And switch case is used to read input from user and call the
            required function
             */
            int choice = userMenu.showMainMenu();
            switch (choice) {
                case 1 -> hotelReservation.addNewHotel();
                case 2 -> hotelReservation.findCheapestHotel(hotelReservation.getBookingDates());
                case 3 -> hotelReservation.printBookedHotels();
                case 4 -> hotelReservation.addUserRatings();
                default -> System.out.println("Invalid Choice.");

            }
            /*
            When user press 1 after execution of process, loop will continue
             */
            x = ScannerUtil.getInt("Do you want to continue...press 1\n");
        } while (x == 1);
    }

    public static void main(String[] args) {
        /*
        HotelReservation object is created
         */
        System.out.println("Welcome to Hotel Reservation System");
        Main userMenu = new Main();
        userMenu.handleUserSelection();

        /*
        New Hotel can be added to hotelList
        by calling addNewHotel in HotelReservation class
         */
    }
}
