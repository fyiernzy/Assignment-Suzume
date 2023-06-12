package com.assignment.suzume.connecting.game;

import java.util.Scanner;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.connecting.account.User;
import com.assignment.suzume.connecting.game.analyzer.*;
import com.assignment.suzume.tictactoe.player.engine.*;
import com.assignment.suzume.tictactoe.board.rules.Rule;

public class ConsoleGameSetup {
    public static final String[] ENGINE_DIFFICULTY = { "Easy", "Medium", "Hard" };
    public static final String[] BOARD_TYPE = { "Regular", "Reverse", "Variant" };
    private static final char P1_MARK = 'X';
    private static final char P2_MARK = 'O';

    private Scanner scanner;
    private Player p1;
    private Player p2;
    private Rule rule;
    private int modeChoice;
    private int boardChoice;

    ConsoleGameSetup() {
        scanner = new Scanner(System.in);
        initializeConfiguration();
    }

    BoardGameRunner getBoardGameRunner() {
        GamingBoard board = createGamingBoard(boardChoice);
        GameAnalyzer monitor = createGameMonitor(boardChoice, board);
        return new BoardGameRunner(modeChoice, p1, p2, rule, board, monitor);
    }

    public int getModeChoice() {
        return modeChoice;
    }

    public Rule getRule() {
        return rule;
    }

    void initializeConfiguration() {
        chooseGameMode();

        switch (modeChoice) {
            case 1 -> {
                p1 = new Gamer(User.getInstance().getName(), P1_MARK);
                p2 = chooseGamer(P2_MARK);
            }
            case 2 -> {
                p1 = new Gamer(User.getInstance().getName(), P1_MARK);
                p2 = chooseEngineDifficulty(P2_MARK);
            }
            case 3 -> {
                p1 = chooseEngineDifficulty(P1_MARK);
                p2 = chooseEngineDifficulty(P2_MARK);
            }
        }

        chooseBoardType();
        rule = GameConstant.RULES[boardChoice - 1];
    }

    Gamer chooseGamer(char mark) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        return new Gamer(name, mark);
    }

    void chooseGameMode() {
        while (true) {
            System.out.println("Please choose the game mode:");
            System.out.println("  [1] Player vs Player");
            System.out.println("  [2] Player vs Engine");
            System.out.println("  [3] Engine vs Engine");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= 3) {
                modeChoice = choice;
                return;
            }

            System.out.println("Invalid choice. Please try again.");
        }
    }

    void chooseBoardType() {
        while (true) {
            System.out.println("Please choose the gaming board: ");
            for (int i = 0; i < BOARD_TYPE.length; i++) {
                System.out.printf("  [%d] %s Board\n", i + 1, BOARD_TYPE[i]);
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= 3) {
                boardChoice = choice;
                return;
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    Engine chooseEngineDifficulty(char mark) {
        while (true) {
            System.out.println("Please choose the engine difficulty:");
            for (int i = 0; i < ENGINE_DIFFICULTY.length; i++) {
                System.out.printf("  [%d] %s\n", i + 1, ENGINE_DIFFICULTY[i]);
            }
            int engineChoice = scanner.nextInt();
            scanner.nextLine();

            switch (engineChoice) {
                case 1:
                    return new EasyEngine(mark);
                case 2:
                    return new MediumEngine(mark);
                case 3:
                    return new HardVariantEngine(mark);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    GamingBoard createGamingBoard(int boardChoice) {
        switch (boardChoice) {
            case 1:
                return new RegularBoard();
            case 2:
                return new ReverseBoard();
            case 3:
                return new VariantBoard();
            default:
                System.out.println("Invalid board choice. Default board is used.");
                return new VariantBoard();
        }
    }

    GameAnalyzer createGameMonitor(int boardChoice, GamingBoard board) {
        switch (boardChoice) {
            case 1:
                return new RegularGameAnalyzer(p1.getMark(), p2.getMark(), board);
            case 2:
                return new ReverseGameAnalyzer(p1.getMark(), p2.getMark(), board);
            case 3:
                return new VariantGameAnalyzer(p1.getMark(), p2.getMark(), board);
            default:
                return new VariantGameAnalyzer(p1.getMark(), p2.getMark(), board);
        }
    }
}
