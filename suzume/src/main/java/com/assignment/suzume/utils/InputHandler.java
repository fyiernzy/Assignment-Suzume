package com.assignment.suzume.utils;

import java.util.Scanner;
import com.assignment.suzume.configuration.Configuration;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput() {
        while (true) {
            System.out.print(Configuration.getDecorator() + " ");
            String input = scanner.nextLine().trim();
            if (isNumeric(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }

    public static String getStringInput() {
        String input = scanner.nextLine();
        return input;
    }

    public static void next() {
        scanner.nextLine();
    }

    public static int[] readTwoIntegers(String message) {
        int[] numbers = new int[2];

        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            String[] splitInput = input.trim().split("\\s+");

            if (splitInput.length != 2) {
                System.out.println("Invalid input. Please enter two integers separated by a space.");
                continue;
            }

            if (!isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
                System.out.println("Invalid input. Please enter two valid integers.");
                continue;
            }

            numbers[0] = Integer.parseInt(splitInput[0]);
            numbers[1] = Integer.parseInt(splitInput[1]);
            break;
        }

        return numbers;
    }

    public static int[] readOneOrTwoIntegers(String message) {
        int[] numbers = new int[2];

        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            String[] splitInput = input.trim().split("\\s+");

            if (splitInput.length != 2 && splitInput.length != 1) {
                System.out.println("Invalid input. Please enter two integers separated by a space or a the cell number.");
                continue;
            }

            if (!isNumeric(splitInput[0]) || (splitInput.length == 2 && !isNumeric(splitInput[1]))) {
                System.out.println("Invalid input. Please enter two valid integers.");
                continue;
            }

            numbers[0] = Integer.parseInt(splitInput[0]);
            numbers[1] = splitInput.length == 2 ? Integer.parseInt(splitInput[1]) : -1;

            break;
        }

        return numbers;
    }

    public static boolean isNumeric(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean getYesNoInput() {
        while (true) {
            System.out.print(Configuration.getDecorator() + " ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Invalid input. Please enter either 'y' or 'n'.");
        }
    }
}
