package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class HardEngine extends Engine {

    @Override
    public int[] makeMove(GamingBoard board) {
        int[] move;
        if ((move = makeWinningMove(board)) != null) 
            return move;

        if ((move = makeBlockingMove(board)) != null) 
            return move;
            
        if ((move = makeWiseMove(board)) != null) 
            return move;
        
        return makeRandomMove(board);
    }

    public int[] makeWiseMove(GamingBoard board) {
        int size = board.getSize();

        // Check if any corner cell is empty, followed by center cell and side cells
        int[][] choices = {
                { 0, 0 }, { 0, size - 1 }, { size - 1, 0 }, { size - 1, size - 1 }, // corners cells
                { size >> 1, size >> 1 }, // center cells
                { 0, 1 }, { 1, 0 }, { size - 1, 1 }, { 1, size - 1 } // side cells
        };

        int row, col;
        for (int[] choice : choices) {
            row = choice[0];
            col = choice[1];
            if (board.isValidMove(row, col)) {
                board.placeMark(row, col);
                return new int[] { row, col };
            }
        }
        return null;
    }
}