package com.assignment.suzume.connecting.game;

import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.connecting.account.data.GameFileManager;

public class UserActionHandler implements Serializable {
    private int gameMode;
    private GamingBoard board;
    private BoardGameRunner runner;

    transient private GameFileManager gameFileManager;
    transient private Scanner scanner;

    UserActionHandler(int gameMode, GamingBoard board, BoardGameRunner runner) {
        this.gameMode = gameMode;
        this.scanner = new Scanner(System.in);
        this.board = board;
        this.runner = runner;
        this.gameFileManager = GameFileManager.getInstance();
    }

    public int[] showUserMenu(Gamer gamer) {
        while (true) {
            System.out.println("Please select an option:");
            System.out.println(" --> [1] Undo previous move");
            System.out.println(" --> [2] Make a move");
            System.out.println(" --> [3] Save game");
            System.out.println(" --> [4] Exit game");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleUndoMove();
                    break;
                case 2:
                    int[] move = gamer.makeMove(board);
                    return new int[] { GameConstant.MOVE, move[0], move[1] };
                case 3:
                    saveGame();
                    break;
                case 4:
                    return new int[] { GameConstant.EXIT, -1, -1 };
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void handleUndoMove() {
        if (board.takeBackMove()) {
            System.out.println("Move undone successfully!");
        } else {
            System.out.println("No move to undo!");
        }
    }

    private void saveGame() {
        gameFileManager.saveGame(runner, gameMode);
        System.out.println("Game saved successfully!");
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = new Scanner(System.in);
    }
}
