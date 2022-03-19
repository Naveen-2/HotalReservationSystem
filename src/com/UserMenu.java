package com;

import java.util.Scanner;

public class UserMenu {
    Scanner input = new Scanner(System.in);

    /**
     * showMainMenu - method to generate main menu and print on screen
     * @return users selection from the menu is returned
     */
    public int showMainMenu() {
        System.out.println("Main Menu:\n 1. Add Hotel\n 2. Find Cheap Hotel\n 3. Print Booked Hotel\n 4. Add User Ratings for a hotel");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

}