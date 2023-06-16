package com.assignment.suzume.game;

import java.io.*;
import com.assignment.suzume.utils.InputHandler;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.constants.GameConstant;
import com.assignment.suzume.data.GameFileInputHandler;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class UserActionHandler implements Serializable {
    private int gameMode;
    private GamingBoard board;
    private BoardGameRunner runner;

    transient private GameFileInputHandler gameFileInputHandler;

    UserActionHandler(int gameMode, GamingBoard board, BoardGameRunner runner) {
        this.gameMode = gameMode;
        this.board = board;
        this.runner = runner;
        this.gameFileInputHandler = GameFileInputHandler.getInstance();
    }

    public void showSaveReplayMenu() {
        while(true) {
            System.out.println("Do you want to save the game replay? (Y/N)");
            String choice = InputHandler.getStringInput();
            
            if(choice.toUpperCase().startsWith("Y")) {
                saveGameReplay();
                break;
            } else if(choice.toUpperCase().startsWith("N")) {
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    public int[] showUserMenu(Gamer gamer) {
        while (true) {
            System.out.println("Please select an option:");
            System.out.println(" --> [1] Undo previous move");
            System.out.println(" --> [2] Make a move");
            System.out.println(" --> [3] Save game");
            System.out.println(" --> [4] Exit game");

            int choice = InputHandler.getIntInput();

            switch (choice) {
                case 1:
                    handleUndoMove();
                    board.printBoard();
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

    private void saveGameReplay() {
        gameFileInputHandler.handleSaveReplay(board);
    }

    private void handleUndoMove() {
        if (board.takeBackMove()) {
            System.out.println("Move undone successfully!");
        } else {
            System.out.println("No move to undo!");
        }
    }

    private void saveGame() {
        gameFileInputHandler.handleSaveGame(runner, gameMode);
    }
}
