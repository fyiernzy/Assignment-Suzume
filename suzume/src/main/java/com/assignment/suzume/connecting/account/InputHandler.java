package com.assignment.suzume.connecting.account;

import java.util.*;
public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            System.out.print(">>>");
            return getIntInput();
        }
    }

    public static String getStringInput(){
        String input = scanner.nextLine();
        return input;
    }



}
