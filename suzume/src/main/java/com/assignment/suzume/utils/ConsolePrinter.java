package com.assignment.suzume.utils;

import java.io.*;
import org.apache.commons.lang3.StringUtils;
import com.assignment.suzume.constants.FontStyle;
import com.assignment.suzume.tictactoe.board.rules.Rule;

import com.assignment.suzume.configuration.Configuration;

public class ConsolePrinter {
    private static String mascot;
    private static String banner;
    private static String welcomeMessage;

    public static void printContentWithGradient(String content, String[] ansiColors, int interval) {
        String[] contentLines = content.split("\n");
        int length = Math.min(contentLines.length, ansiColors.length);
        for(int i = 0; i < length; i++) {
            printWithColor(contentLines[i], ansiColors[i]);
            Timer.waitInMilliseconds(interval);
        }
    }

    public static void printWithColor(String text, String ansiColor) {
        System.out.println(applyColor(text, ansiColor));
    }

    private static String applyColor(String text, String ansiColor) {
        return ansiColor + text + FontStyle.RESET;
    }

    public static void printDecorator() {
        System.out.printf("%s ", Configuration.getDecorator());
    }

    public static void printRule(Rule rule) {
        System.out.println(FontStyle.YELLOW_BOLD + "=".repeat(165));
        System.out.println(FontStyle.ITALIC + FontStyle.CYAN_BOLD + rule.getContent());
        System.out.println(FontStyle.YELLOW_BOLD + "=".repeat(165));
    }

    public static void printMascot() {
        if(mascot == null) {
            mascot = getContentFromFile(Configuration.MASCOT_URL);
        }

        printContentWithGradient(mascot, new String[] {
            FontStyle.RESET,
            FontStyle.PURPLE,
            FontStyle.PURPLE,
            FontStyle.PURPLE,
            FontStyle.RED,
            FontStyle.RED,
            FontStyle.RED,
            FontStyle.YELLOW,
            FontStyle.YELLOW,
            FontStyle.YELLOW,
            FontStyle.YELLOW,
            FontStyle.CYAN,
            FontStyle.CYAN,
            FontStyle.BLUE,
            FontStyle.BLUE,
        }, 100);
    }

    public static void printBanner() {
        if (banner == null) { 
            banner = getContentFromFile(Configuration.BANNER_URL);
        }
        printContentWithGradient(banner, new String[] {
            FontStyle.PURPLE,
            FontStyle.PURPLE,
            FontStyle.CYAN,
            FontStyle.CYAN,
            FontStyle.YELLOW,
            FontStyle.YELLOW,
            FontStyle.BLUE,
            FontStyle.BLUE,
        }, 200);
    }

    public static void printWelcomeMessage() {
        if (welcomeMessage == null) {
            String[] tmp = banner.split("\n");
            int size = tmp[tmp.length - 1].length() - 2;

            String tmpContent = getContentFromFile(Configuration.WELCOME_MESSAGE_URL);
            StringBuilder content = new StringBuilder();

            content.append(FontStyle.BOLD + FontStyle.PURPLE);

            content.append('+')
                   .append("-".repeat(size))
                   .append('+')
                   .append('\n');

            content.append('|')
                   .append(" ".repeat(size))
                   .append('|')
                   .append('\n');

            for(String line : tmpContent.split("\n")) {
                content.append(FontStyle.RESET)
                       .append('|')
                       .append(FontStyle.BOLD + FontStyle.ITALIC + FontStyle.CYAN)
                       .append(StringUtils.center(line, size, ' '))
                       .append(FontStyle.RESET)
                       .append('|')
                       .append('\n');
            }

            content.append(FontStyle.BOLD + FontStyle.PURPLE);

            content.append('|')
                   .append(" ".repeat(size))
                   .append('|')
                   .append('\n');

            content.append("+")
                   .append("-".repeat(size))
                   .append("+");

            welcomeMessage = content.toString();
        }

        Timer.waitInMilliseconds(200);
        System.out.println(welcomeMessage);
    }

    public static String getContentFromFile(String url) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void main(String[] args) {
        System.out.println("\u001B[43mHello");
        printMascot();
    }
}
