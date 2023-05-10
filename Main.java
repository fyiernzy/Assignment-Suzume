package Assignment;

import java.util.Scanner;

public class    Main {
    // Main method to run the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            game.printBoard();
            System.out.println("Enter row and column where you want to place your mark (e.g. 1 2): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (game.placeMark(row, col)) {
                if (game.checkForWin(row, col)) {
                    game.printBoard();
                    System.out.println("Congratulations, player " + game.currentPlayerMark + " has won!");
                    isRunning = false;
                }
                else if (game.isBoardFull()) {
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
    }
}
