package com.assignment.suzume.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/assignment/suzume/utils/tester.txt"))) {
            String line;
            int colorIndex = 0;

            while ((line = reader.readLine()) != null) {
                String coloredLine = applyColor(line, colorIndex);
                System.out.println(coloredLine);
                colorIndex = (colorIndex + 1) % Color.values().length; // Cycle through colors
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String applyColor(String text, int colorIndex) {
        Color color = Color.values()[colorIndex];
        String colorCode = getColorCode(color);
        String resetCode = "\u001B[0m"; // Reset to default color

        return colorCode + text + resetCode;
    }

    private static String getColorCode(Color color) {
        switch (color) {
            case RED:
                return "\u001B[31m";
            case GREEN:
                return "\u001B[32m";
            case BLUE:
                return "\u001B[34m";
            case YELLOW:
                return "\u001B[33m";
            case MAGENTA:
                return "\u001B[35m";
            case CYAN:
                return "\u001B[36m";
            default:
                return "";
        }
    }

    private enum Color {
        RED, GREEN, BLUE, YELLOW, MAGENTA, CYAN
    }
}

