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

    public static void next(){
        scanner.nextLine();
    }

    public static int[] readTwoIntegers() {
        int[] numbers = new int[2];

        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine();

            String[] splitInput = input.trim().split("\\s+");
            if (splitInput.length == 2) {
                try {
                    numbers[0] = Integer.parseInt(splitInput[0]);
                    numbers[1] = Integer.parseInt(splitInput[1]);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter two valid integers.");
                    System.out.println("Enter your move (row column): ");
                }
            } else {
                System.out.println("Invalid input. Please enter two integers separated by a space.");
                System.out.println("Enter your move (row column): ");
            }
        }

        return numbers;
    }

}
