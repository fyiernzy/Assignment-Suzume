package com.assignment.suzume.connecting;

import com.assignment.suzume.tictactoe.board.*;
import java.util.Scanner;

public class Main {
    // Main method to run the game
    public static void main(String[] args) {
        ReverseBoard game = new ReverseBoard();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            game.printBoard();
            System.out.println("Enter row and column where you want to place your mark (e.g. 1 2): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (game.placeMark(row, col)) {
                if (game.checkForWin(row, col, game.getCurrentPlayerMark())) {
                    game.printBoard();
                    if (game instanceof ReverseBoard) {
                        game.changePlayer();
                        System.out.println("Congratulations, player " + game.getCurrentPlayerMark() + " has won!");
                    }
                    else
                        System.out.println("Congratulations, player " + game.getCurrentPlayerMark() + " has won!");
                    isRunning = false;
                }
                else if (game.isFull()) {
                    game.printBoard();
                    System.out.println("It's a tie!");
                    isRunning = false;
                }
                else {
                    game.changePlayer();
                }
            }
            else {
                System.out.println("Invalid move, please try again.");
            }
        }
        scanner.close();
    }
}
