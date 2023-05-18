package TicTacToe.engine;

import TicTacToe.board.GamingBoard;
import java.util.*;

public class HardEngine implements Engine {

    @Override
    public void makeMove(GamingBoard board) {
        // Check if there is a winning move for the AI player
        List<int[]> emptyCells = board.getEmptyCells();
        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
                if (board.isValidMove(row, col)) {
                    // Place the AI player's mark and check if it leads to a win
                    board.placeMark(row, col);
                    if (board.checkForWin(row, col)) {
                        return; // Found a winning move, no need to continue
                    }
                    board.removeMark(row, col); // Undo the move
                }
            }
        
        // Check if there is a winning move for the human player and block it
        placeOpponentMark(board);

        // If no winning move found, select a center, corner or side, or else pick a random cell
        makeRandomMove(board);
    }


    public void placeOpponentMark(GamingBoard board) {
        char[][] gameBoard = board.getBoard();

        // Check for empty cells on the board
        List<int[]> emptyCells = board.getEmptyCells();
        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            gameBoard[row][col] = board.getCurrentPlayerMark();
            if (board.checkForWin(row, col)) {
                board.placeMark(row, col);
                return;
            }
            board.removeMark(row, col); // Reset the cell to empty
        }
    }

    public void makeRandomMove(GamingBoard board) {
        int size = board.getSize();
        char[][] gameBoard = board.getBoard();

        // Check if the center cell is empty
        int row = size / 2;
        int col = size / 2;
        if (board.isEmpty(row,col)) {
            board.placeMark(row,col);
            return;
        }
    
        // Check if any corner cell is empty
        int[][] corners = {{0, 0}, {0, size - 1}, {size - 1, 0}, {size - 1, size - 1}};
        for (int[] corner : corners) {
            row = corner[0];
            col = corner[1];
            if (board.isEmpty(row,col)) {
                board.placeMark(row, col);
                return;
            }
        }
    
        // Check if any side cell is empty
        int[][] sides = {{0, 1}, {1, 0}, {size - 1, 1}, {1, size - 1}};
        for (int[] side : sides) {
            row = side[0];
            col = side[1];
            if (board.isEmpty(row, col)) {
                board.placeMark(row, col);
                return;
            }
        }
    
        // If no corner, center, or side cell is empty, select a random empty cell
        // Check for empty cells on the board
        List<int[]> emptyCells = board.getEmptyCells();
        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        row = randomCell[0];
        col = randomCell[1];
        board.placeMark(row, col);
    }
    

}