package com.assignment.suzume.connecting.account;

import java.util.Scanner;
import com.assignment.suzume.map.utils.MapUtils;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.connecting.game.ConsoleGame;
import com.assignment.suzume.connecting.game.ConsolePrinter;
import com.assignment.suzume.connecting.game.VideoPlayer;
import com.assignment.suzume.connecting.account.data.DatabaseManager;
import com.assignment.suzume.connecting.account.data.GameFileManager;

public class Dashboard {
    private static User user;
    private static Scanner scanner;

    Dashboard(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

public static void checkAccountAnalysis() {
    final int formatSize = 20;
    String format = "\u001B[1;36m|" + padRight("\u001B[1;36m%-" + (formatSize - 1) + "s", formatSize) + "\u001B[1;36m| %s|\n";
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

    public static void showDashboard() throws InterruptedException {
        loop: while (true) {
            System.out.println("What do you want to do?");
            System.out.println(" --> [1] Check account analysis");
            System.out.println(" --> [2] Start challenge the Suzume's Adventure");
            System.out.println(" --> [3] Show leaderboard");
            System.out.println(" --> [4] Show game replay");
            System.out.println(" --> [5] Logout");

            ConsolePrinter.printDecorator();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> checkAccountAnalysis();
                case "2" -> new ConsoleGame(MapUtils.getCombinedMap()).play();
                case "3" -> DatabaseManager.getInstance().showDatabase();
                case "4" -> {
                    GamingBoard board = GameFileManager.getInstance().loadGameReplay();
                    VideoPlayer player = VideoPlayer.getSuitableVideoPlayer(board);
                    player.replay();
                }
                case "5" -> {
                    System.out.println("\u001B[1;34mLogging out...");
                    for(int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                    Thread.sleep(500);
                    System.out.println("\n\u001B[1;35mLogout successfully.\n");
                    break loop;
                }
                default -> System.out.println("Invalid choice.\n");
            }
        }

    }
}
