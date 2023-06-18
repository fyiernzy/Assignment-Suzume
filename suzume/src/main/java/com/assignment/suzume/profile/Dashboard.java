package com.assignment.suzume.profile;

import java.text.DecimalFormat;
import java.util.Optional;
import com.assignment.suzume.game.*;
import com.assignment.suzume.data.*;
import com.assignment.suzume.utils.*;
import com.assignment.suzume.constants.FontStyle;
import com.assignment.suzume.database.DatabaseManager;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Dashboard {
    private User user;

    public Dashboard(User user) {
        this.user = user;
    }

    public void checkAccountAnalysis() {
        final int FORMAT_SIZE = 20;
        final int FIELD_SIZE = FORMAT_SIZE - 1;
        final int VALUE_SIZE = FORMAT_SIZE - 3;
        final int LEFT_MARGIN = 2;
        final DecimalFormat DF = new DecimalFormat("#.00");

        String format = FontStyle.CYAN_BOLD 
                        + "|" + " ".repeat(LEFT_MARGIN)
                        + "%-" + FIELD_SIZE + "s"
                        + "|" + " ".repeat(LEFT_MARGIN)
                        + "%-" + VALUE_SIZE + "s"
                        + "|\n";

        String border = FontStyle.CYAN_BOLD 
                        + "+" 
                        + "-".repeat(FIELD_SIZE + LEFT_MARGIN) 
                        + "+" 
                        + "-".repeat(VALUE_SIZE + LEFT_MARGIN) 
                        + "+\n"; // Border design

        System.out.println(FontStyle.YELLOW_BOLD_BRIGHT + "Account Analysis");
        System.out.print(border);
        System.out.printf(format, "Name", user.getName());
        System.out.print(border);
        System.out.printf(format, "Win", Integer.toString(user.getWin()));
        System.out.printf(format, "Lose", Integer.toString(user.getLose()));
        System.out.printf(format, "Draw", Integer.toString(user.getDraw()));
        System.out.printf(format, "Win Rate", DF.format(user.getWinRate()) + "%");
        System.out.printf(format, "Lose Rate", DF.format(user.getLoseRate()) + "%");
        System.out.printf(format, "Draw Rate", DF.format(user.getDrawRate()) + "%");
        System.out.printf(format, "Score", Integer.toString(user.getScore()));
        System.out.print(border);
        System.out.println(FontStyle.MAGNETA_BOLD);
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
                    Optional<GamingBoard> board = GameFileInputHandler.getInstance().handleLoadReplay();
                    if (board.isPresent()) {
                        VideoPlayer player = VideoPlayer.getSuitableVideoPlayer((GamingBoard) board.get());
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
