package com.assignment.suzume.profile;

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
        String format = "%" + formatSize + "s : %s\n";
        System.out.println("Account Analysis");
        System.out.printf(format, "Name", user.getName());
        System.out.printf(format, "Win", user.getWin());
        System.out.printf(format, "Lose", user.getLose());
        System.out.printf(format, "Draw", user.getDraw());
        System.out.printf(format, "Win Rate", user.getWinRate() + "%");
        System.out.printf(format, "Lose Rate", user.getLoseRate() + "%");
        System.out.printf(format, "Draw Rate", user.getDrawRate() + "%");
        System.out.printf(format, "Score", user.getScore());
    }

    public void showDashboard() {
        System.out.println("Welcome back, " + user.getName());

        loop: while (true) {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println(" --> [1] Check account analysis");
            System.out.println(" --> [2] Start challenge the Suzume's Adventure");
            System.out.println(" --> [3] Show leaderboard");
            System.out.println(" --> [4] Show game replay");
            System.out.println(" --> [5] Logout");

            ConsolePrinter.printDecorator();


            int choice = InputHandler.getIntInput();


            switch (choice) {
                case 1 -> checkAccountAnalysis();
                case 2 -> new ConsoleGame(MapUtils.getCombinedMap()).play();
                case 3 -> DatabaseManager.getInstance().showDatabase();
                case 4 -> {
                    GamingBoard board = GameFileDataManager.getInstance().loadGameReplay();
                    VideoPlayer player = VideoPlayer.getSuitableVideoPlayer(board);
                    player.replay();
                }
                case 5 -> {
                    System.out.println("Logging out...");
                    for(int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                    System.out.println("\nLogout successfully.");
                    break loop;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }


}
