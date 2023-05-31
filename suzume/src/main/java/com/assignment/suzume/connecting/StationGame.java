package com.assignment.suzume.connecting;

import java.util.Scanner;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.engine.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.tictactoe.system.*;

public class StationGame {
    private static final String[] ENGINE_DIFFICULTY = { "Easy", "Medium", "Hard" };
    private static final String[] BOARD_TYPE = { "Regular", "Reverse", "Variant" };
    private Scanner scanner;

    int modeChoice;
    GameRunner gameRunner;

    public StationGame() {
        this.scanner = new Scanner(System.in);
        initializeGame();
    }

    public void play() {
        gameRunner.gamePlay();
    }

    public boolean isSuzumeWin() {
        return gameRunner.isOneWin();
    }

    private void initializeGame() {
        int modeChoice = getGameMode();

        Player p1 = null, p2 = null;
        switch(modeChoice) {
            case 1 -> {
                p1 = getGamer();
                p2 = getGamer();
            }
            case 2 -> {
                p1 = getGamer();
                p2 = getEngine();
            }
            case 3 -> {
                p1 = getEngine();
                p2 = getEngine();
            }
        }

        GamingBoard board = getBoard();
        this.gameRunner = new GameRunner(p1, p2, board);
    }

    public int getGameMode() {
        while (true) {
            System.out.println("Please choose the game mode:");
            System.out.println("  [1] Player vs Player");
            System.out.println("  [2] Player vs Engine");
            System.out.println("  [3] Engine vs Engine");
            int modeChoice = scanner.nextInt();
            scanner.nextLine();

            if(modeChoice >= 1 && modeChoice <= 3) {
                return modeChoice;
            }

            System.out.println("Invalid choice. Please try again.");
        }
    }

    public Gamer getGamer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        return new Gamer(name);
    }

    public GamingBoard getBoard() {
        while (true) {
            System.out.println("Please choose the gaming board: ");
            for(int i = 0; i < BOARD_TYPE.length; i++) {
                System.out.printf("  [%d] %s Board\n", i + 1, BOARD_TYPE[i]);
            }
            int boardChoice = scanner.nextInt();
            scanner.nextLine();

            switch(boardChoice) {
                case 1 : return new RegularBoard();
                case 2 : return new ReverseBoard();
                case 3 : return new VariantBoard();
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public Engine getEngine() {
        while (true) {
            System.out.println("Please choose the engine difficulty:");
            for(int i = 0; i < ENGINE_DIFFICULTY.length; i++) {
                System.out.printf("  [%d] %s\n", i + 1, ENGINE_DIFFICULTY[i]);
            }
            int engineChoice = scanner.nextInt();
            scanner.nextLine();

            switch(engineChoice) {
                case 1 : return new EasyEngine();
                case 2 : return new MediumEngine();
                case 3 : return new HardEngine();
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
