package TicTacToe.engine;

import TicTacToe.board.GamingBoard;
import java.util.*;

public class MediumEngine implements Engine {

    @Override
    public void makeMove(GamingBoard board) {
        int size = board.getSize();

        // Check if there is a winning move for the AI player
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isValidMove(row, col)) {
                    // Place the AI player's mark and check if it leads to a win
                    board.placeMark(row, col);
                    if (board.checkForWin(row, col)) {
                        return; // Found a winning move, no need to continue
                    }
                    board.removeMark(row, col); // Undo the move
                }
            }
        }

        // Check if there is a winning move for the human player and block it
        // or else, choose a random empty cell
        placeOpponentMark(board);

    }

    public void placeOpponentMark(GamingBoard board) {
        int size = board.getSize();
        char[][] gameBoard = board.getBoard();

        // Check for empty cells on the board
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (gameBoard[row][col] == ' ') {
                    emptyCells.add(new int[] { row, col });
                }
            }
        }

        // Check if placing a mark in any empty cell leads to a win
        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            gameBoard[row][col] = board.getCurrentPlayerMark();
            if (board.checkForWin(row, col)) {
                board.placeMark(row, col);
            }
            board.removeMark(row, col); // Reset the cell to empty

        }

        // If no winning move found, select a random empty cell
        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        int randomRow = randomCell[0];
        int randomCol = randomCell[1];
        board.placeMark(randomRow, randomCol);
    }

}