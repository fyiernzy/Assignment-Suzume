package com.assignment.suzume.connecting.game;

import java.io.*;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import com.assignment.suzume.connecting.configuration.Configuration;
import com.assignment.suzume.tictactoe.board.rules.Rule;

public class ConsolePrinter {
    private static String banner;
    private static String welcomeMessage;

    public static void printDecorator() {
        System.out.printf("%s ", Configuration.getDecorator());
    }

    public static void printRule(Rule rule) {
        System.out.println("\033[1;33m=".repeat(165));
        System.out.println("\u001B[3m\u001B[1;36m" + rule.getContent());
        System.out.println("\033[1;33m=".repeat(165) + "\033[0m" + "\033[1;35m");
    }

    public static void printBanner() {
        if (banner == null) {
            banner = getContentFromFile(Configuration.BANNER_URL);
        }

        System.out.print("\033[H\033[2J"); // Clear console screen
        System.out.flush();

        System.out.println("\u001B[35m    _____                                 _        ___      _                 _                  ");
        waitMilliseconds(200);

        System.out.println("   |  ___|                               ( )      / _ \\    | |               | |                 ");
        waitMilliseconds(200);

        System.out.println("\u001B[36m   \\  `--. _   _ _____   _ _ __ ___   ___|/ ___  / /_\\ \\ __| |_   _____ _ __ | |_ _   _ _ __ ___ ");
        waitMilliseconds(200);

        System.out.println("     `--. \\ | | |_  / | | | '_ ` _ \\ / _ \\ / __| |  _  |/ _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\ ");
        waitMilliseconds(200);

        System.out.println("\u001B[33m   / \\__/ / |_| |/ /| |_| | | | | | |  __/ \\__ \\ | | | | (_| |\\ V /  __/ | | | |_| |_| | | |  __/");
        waitMilliseconds(200);

        System.out.println("    \\____/ \\__,_/___|\\__,_|_| |_| |_|\\___| |___/ \\_| |_/\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|");
        waitMilliseconds(200);

//        System.out.println("\n");
//        waitMilliseconds(200);

        System.out.println("\u001B[34m ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______");
        waitMilliseconds(200);

        System.out.println("|______|______|______|______|______|______|______|______|______|______|______|______|______|______|");
        waitMilliseconds(200);

//        System.out.println("\n");
//        waitMilliseconds(200);
    }

    private static void waitMilliseconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printWelcomeMessage() {
        String styleReset = "\u001B[0m";
        String bold = "\u001B[1m";
        String italic = "\u001B[3m";
        String underline = "\u001B[4m";

        if (welcomeMessage == null) {
            String[] tmp = banner.split("\n");
            int size = tmp[tmp.length - 1].length() - 2;

            StringBuilder content = new StringBuilder();
            content.append(bold + "\u001B[35m").append("+").append("-".repeat(size)).append("+\n");
            content.append("\u001B[35m" + styleReset).append('|').append("\u001B[35m").append(" ".repeat(size - 2)).append("\u001B[35m").append("  |\n");
            try (BufferedReader br = new BufferedReader(new FileReader(Configuration.WELCOME_MESSAGE_URL))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(bold + "\u001B[36m" + styleReset).append('|').append(bold + italic + "\u001B[36m").append(StringUtils.center(line, size - 2, ' ')).append("\u001B[36m" + styleReset).append("  |\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            content.append(bold + "\u001B[35m").append('|').append("\u001B[35m").append(" ".repeat(size - 2)).append("\u001B[35m").append("  |\n");
            content.append(bold + "\u001B[35m").append("+").append("-".repeat(size)).append("+");
            welcomeMessage = content.toString();
        }
        waitMilliseconds(200);
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
}
