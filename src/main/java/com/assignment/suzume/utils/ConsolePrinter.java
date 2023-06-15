package com.assignment.suzume.utils;

import java.io.*;
import org.apache.commons.lang3.StringUtils;
import com.assignment.suzume.tictactoe.board.rules.Rule;
import com.assignment.suzume.configuration.Configuration;

public class ConsolePrinter {
    private static String banner;
    private static String welcomeMessage;

    public static void printDecorator() {
        System.out.printf("%s ", Configuration.getDecorator());
    }

    public static void printRule(Rule rule) {
        System.out.println("=".repeat(50));
        System.out.println(rule.getContent());
        System.out.println("=".repeat(50));
    }

    public static void printBanner() {
        if (banner == null) {
            banner = getContentFromFile(Configuration.BANNER_URL);
        }
        System.out.println(banner);
    }

    public static void printWelcomeMessage() {
        if (welcomeMessage == null) {
            String[] tmp = banner.split("\n");
            int size = tmp[tmp.length - 1].length();

            StringBuilder content = new StringBuilder();
            content.append("-".repeat(size)).append('\n');
            content.append('|').append(" ".repeat(size - 2)).append("|\n");
            try (BufferedReader br = new BufferedReader(new FileReader(Configuration.WELCOME_MESSAGE_URL))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append('|').append(StringUtils.center(line, size - 2, ' ')).append("|\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            content.append('|').append(" ".repeat(size - 2)).append("|\n");
            content.append("-".repeat(size)).append('\n');
            welcomeMessage = content.toString();
        }

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
