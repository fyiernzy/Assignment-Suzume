package com.assignment.suzume.tictactoe.player.engine;


import java.util.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class MediumEngine extends Engine {

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

        // If no winning move found, select a random empty cell
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
}