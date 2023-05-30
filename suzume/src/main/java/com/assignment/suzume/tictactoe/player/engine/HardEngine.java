package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class HardEngine extends Engine {

    @Override
    public int[] makeMove(GamingBoard board) {
        // Check if there is a winning move for the AI player
        int[] move;

        if ((move = makeBestMove(board)) != null ) {
            return move;
        }

        // If no winning move found, select a random empty cell
        return makeRandomMove(board);
    }

    @Override
    public int[] makeRandomMove(GamingBoard board) {
        int size = board.getSize();

        // Check if any corner cell is empty, followed by center cell and side cells
        int[][] choices = { 
                { 0, 0 }, { 0, size - 1 }, { size - 1, 0 }, { size - 1, size - 1 }, // corners cells
                { size >> 1, size >> 1 }, // center cells
                { 0, 1 }, { 1, 0 }, { size - 1, 1 }, { 1, size - 1 } // side cells
        };

        int row, col;
        for (int[] choice : choices) {
            row = choice[0]; col = choice[1];
            if (board.isValidMove(row, col)) {
                board.placeMark(row, col);
                return new int[] { row, col };
            }
        }

        // If no choice, center, or side cell is empty, select a random empty cell
        return makeRandomMove(board);
    }
}