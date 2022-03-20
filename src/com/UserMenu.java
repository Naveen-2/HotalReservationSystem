package com;

import java.util.Scanner;

public class UserMenu {
    Scanner input = new Scanner(System.in);

    /**
     * showMainMenu - method to generate main menu and print on screen
     * @return users selection from the menu is returned
     */
    public int showMainMenu() {
        System.out.println("""
                Main Menu:
                 1. Add Hotel
                 2. Find Cheapest Hotel
                 3. Print Booked Hotel
                 4. Add User Ratings for a hotel
                 5. Find Cheapest best rated hotel""");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

}