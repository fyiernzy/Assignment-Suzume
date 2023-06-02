package com.assignment.suzume.connecting.account;

import java.util.Scanner;
import com.assignment.suzume.connecting.*;
import static com.assignment.suzume.utils.MapGetter.getCombinedMap;

public class Dashboard {
    private User user;
    private Scanner scanner;

    Dashboard(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void checkAccountAnalysis() {
        final int formatSize = 20;
        String format = "%" + formatSize + "s : %s\n";
        System.out.println("Account Analysis");
        System.out.printf(format, "Name", user.name);
        System.out.printf(format, "Win", user.win);
        System.out.printf(format, "Lose", user.lose);
        System.out.printf(format, "Draw", user.draw);
        System.out.printf(format, "Win Rate", user.getWinRate() + "%");
        System.out.printf(format, "Lose Rate", user.getLoseRate() + "%");
        System.out.printf(format, "Draw Rate", user.getDrawRate() + "%");
        System.out.printf(format, "Score", user.getScore());
    }

    public void showDashboard() {
        System.out.println("Welcome back, " + user.name);
        System.out.println("What do you want to do?");

        loop: while (true) {
            System.out.println(" --> [1] Check account analysis");
            System.out.println(" --> [2] Start challenge the Suzume's Adventure");
            System.out.println(" --> [3] Logout");

            ConsolePrinter.printDecorator();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> checkAccountAnalysis();
                case 2 -> new ConsoleGame(getCombinedMap()).play();
                case 3 -> {
                    System.out.println("Logging out...");
                    for(int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                    System.out.println("Logout successfully.");
                    break loop;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }
}
