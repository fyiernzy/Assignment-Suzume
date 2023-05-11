import java.util.Scanner;

import TicTacToe.board.VariantBoard;

public class Main {
    // Main method to run the game
    public static void main(String[] args) {
        VariantBoard game = new VariantBoard();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("hello");
        while (isRunning) {
            game.printBoard();
            System.out.println("Enter row and column where you want to place your mark (e.g. 1 2): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (game.placeMark(row, col)) {
                if (game.checkForWin(row, col)) {
                    game.printBoard();
                    System.out.println("Congratulations, player " + game.getCurrentPlayerMark() + " has won!");
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
        scanner.close();
    }
}
