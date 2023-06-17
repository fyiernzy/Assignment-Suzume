package com.assignment.suzume.profile;

import com.assignment.suzume.constants.FontStyle;
import com.assignment.suzume.game.*;
import com.assignment.suzume.data.*;
import com.assignment.suzume.utils.*;
import com.assignment.suzume.database.DatabaseManager;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Dashboard {
    private User user;

    public Dashboard(User user) {
        this.user = user;
    }

    public void checkAccountAnalysis() {
        final int formatSize = 20;
        String format = "\u001B[1;36m|" + padRight("\u001B[1;36m%-" + (formatSize - 1) + "s", formatSize)
                + "\u001B[1;36m| %s|\n";
        String border = "\u001B[1;36m+" + repeat("-", formatSize + 7) + "+------------------+\n"; // Border design

        System.out.println("\033[1;93mAccount Analysis");
        System.out.print(border);
        System.out.printf(format, " Name", padRight(user.getName(), formatSize - 3));
        System.out.print(border);
        System.out.printf(format, " Win", padRight(Integer.toString(user.getWin()), formatSize - 3));
        System.out.printf(format, " Lose", padRight(Integer.toString(user.getLose()), formatSize - 3));
        System.out.printf(format, " Draw", padRight(Integer.toString(user.getDraw()), formatSize - 3));
        System.out.printf(format, " Win Rate", padRight(user.getWinRate() + "%", formatSize - 3));
        System.out.printf(format, " Lose Rate", padRight(user.getLoseRate() + "%", formatSize - 3));
        System.out.printf(format, " Draw Rate", padRight(user.getDrawRate() + "%", formatSize - 3));
        System.out.printf(format, " Score", padRight(Integer.toString(user.getScore()), formatSize - 3));
        System.out.print(border);
        System.out.println("\u001B[1;35m");
    }

    private static String repeat(String str, int times) {
        return new String(new char[times]).replace('\0', str.charAt(0));
    }

    private static String padRight(String text, int size) {
        return String.format("%-" + size + "s", text);
    }

    public void showDashboard() {
        System.out.println();
        System.out.println("Welcome back, " + user.getName() + "!");

        loop: while (true) {
            System.out.println(FontStyle.PURPLE_BOLD + "What do you want to do?");
            System.out.println(" --> [1] Check account analysis");
            System.out.println(" --> [2] Start challenge the Suzume's Adventure");
            System.out.println(" --> [3] Show leaderboard");
            System.out.println(" --> [4] Show game replay");
            System.out.println(" --> [5] Logout");

            int choice = InputHandler.getIntInput();

            switch (choice) {
                case 1 -> checkAccountAnalysis();
                case 2 -> new ConsoleGame(MapUtils.getCombinedMap()).play();
                case 3 -> DatabaseManager.getInstance().showDatabase();
                case 4 -> {
                    GamingBoard board = GameFileInputHandler.getInstance().handleLoadReplay();
                    if (board != null) {
                        VideoPlayer player = VideoPlayer.getSuitableVideoPlayer((GamingBoard) board);
                        player.replay();
                    }
                }
                case 5 -> {
                    System.out.println(FontStyle.BLUE_BOLD + "Logging out...");
                    for (int i = 0; i < 3; i++) {
                        Timer.waitInMilliseconds(500);
                        System.out.print(".");
                    }

                    Timer.waitInMilliseconds(500);
                    System.out.println("\n" + FontStyle.PURPLE_BOLD + "Logout successfully.\n");
                    break loop;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }

}
