package com.assignment.suzume.connecting;

import java.util.*;
import com.assignment.suzume.path.shortest.DFSFinder;
import static com.assignment.suzume.utils.MapGetter.*;
import com.assignment.suzume.tictactoe.player.engine.EasyEngine;
import com.assignment.suzume.tictactoe.player.engine.HardEngine;
import com.assignment.suzume.tictactoe.player.engine.MediumEngine;
import com.assignment.suzume.tictactoe.system.GameRunner;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.tictactoe.board.*;

public class ConsoleGame {
    private static final Scanner scanner = new Scanner(System.in);

    public void play() {
        System.out.print("Enter name (Player 1): ");
        String p1 = scanner.nextLine();

        DFSFinder finder = new DFSFinder(getCombinedMap());
        int[][] grid = getCombinedMap().getPixelMap();
        List<String> steps = finder.findAllShortestPaths().get(0);
        int currentRow = 0;
        int currentCol = 0;
        int station = 0;
        int stationRow = 0;
        int stationColumn =0;
        int stationStep =0;

        int currentStep = 0, totalStep = steps.size();
        while (currentStep < totalStep) {
            switch (steps.get(currentStep)) {
                case "Up" -> currentRow--;
                case "Down" -> currentRow++;
                case "Left" -> currentCol--;
                case "Right" -> currentCol++;
            }

            if (grid[currentRow][currentCol] == 2) {
                System.out.println(
                        "Suzume has arrived at station " + station+ " (" + currentRow + ", " + currentCol + ").");
                        station++;

                Player player1 = null;
                Player player2 = null;
                GamingBoard board = null;
                System.out.println("You reached a game tile!");
                while (true) {
                    System.out.println("Please choose the game mode:");
                    System.out.println("[1] Player vs Player");
                    System.out.println("[2] Player vs Engine");
                    System.out.println("[3] Engine vs Engine");
                    int modeChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (modeChoice == 1) {
                        // System.out.print("Enter name (Player 1): ");
                        // String p1 = scanner.nextLine();
                        System.out.print("Enter name (Player 2): ");
                        String p2 = scanner.nextLine();
                        player1 = new Gamer(p1);
                        player2 = new Gamer(p2);
                        break;
                    } else if (modeChoice == 2) {
                        // System.out.print("Enter name (Player 1): ");
                        // String p1 = scanner.nextLine();
                        player1 = new Gamer(p1);
                        player2 = enginePlayer();
                        break;
                    } else if (modeChoice == 3) {
                        player1 = enginePlayer();
                        player2 = enginePlayer();
                        break;
                    } else
                        System.out.println("Invalid choice. Please try again.");
                }

                while (true) {
                    System.out.println("Please choose the gaming board: ");
                    System.out.println("[1] Regular Board");
                    System.out.println("[2] Reverse Board");
                    System.out.println("[3] Variant Board");
                    int boardChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (boardChoice == 1) {
                        board = new RegularBoard();
                        break;
                    } else if (boardChoice == 2) {
                        board = new ReverseBoard();
                        break;
                    } else if (boardChoice == 3) {
                        board = new VariantBoard();
                        break;
                    } else
                        System.out.println("Invalid choice. Please try again.");
                }

                GameRunner gameRunner = new GameRunner(player1, player2, board);
                gameRunner.gamePlay();
                boolean SuzumeWin = gameRunner.isOneWin();

                if (!SuzumeWin) {
                    System.out.println("Suzume has lost the station game.");
                    if (station== 1) {
                        System.out.println("Suzume has failed at the first station. Her journey ends.");
                        return;
                    } else {
                        System.out.println("Suzume falls back to the previous station.");
                        currentStep = stationStep;
                        currentRow = stationRow;
                        currentCol = stationColumn;
                    }
                } 
                else {
                    stationRow = currentRow;
                    stationColumn = currentCol;
                    stationStep = currentStep;
                }
            }

            if (grid[currentRow][currentCol] == 3) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }

            currentStep++;
            
        }
    }

    private Player enginePlayer() {
        System.out.println("Please choose the engine difficulty:");
        System.out.println("[1] Easy");
        System.out.println("[2] Medium");
        System.out.println("[3] Hard");
        int engineChoice = scanner.nextInt();
        scanner.nextLine();
        if (engineChoice == 1)
            return new EasyEngine();
        if (engineChoice == 2)
            return new MediumEngine();
        if (engineChoice == 3)
            return new HardEngine();
        else {
            System.out.println("Invalid choice. Please try again.");
            return enginePlayer();
        }

    }

    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.play();
    }

}
