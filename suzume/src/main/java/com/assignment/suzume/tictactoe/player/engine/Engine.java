package com.assignment.suzume.tictactoe.player.engine;

import java.util.*;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Engine extends Player {
    
    public int[] makeRandomMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        int randomIndex = (int)(Math.random() * emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        board.placeMark(randomCell[0], randomCell[1]);
        return randomCell;
    }

    public int[] makeBestMove(GamingBoard board, char mark) {
        List<int[]> emptyCells = board.getEmptyCells();
        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            if (board.checkForWin(row, col)) {
                board.placeMark(row, col);
                return new int[] { row, col };
            }
        }
        return null;
    }
}
