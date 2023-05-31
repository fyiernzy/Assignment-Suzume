package com.assignment.suzume.tictactoe.player.engine;

import java.util.*;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Engine extends Player {

    public int[] makeRandomMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        board.placeMark(randomCell[0], randomCell[1]);
        return randomCell;
    }

    private int[] makeBestMove(GamingBoard board, char mark) {
        List<int[]> emptyCells = board.getEmptyCells();
        List<int[]> validMoves = new ArrayList<>(emptyCells);
        for (int[] cell : validMoves) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);
            
            System.out.println("Engine: " + this + " is trying to place mark " + mark + " at " + row + ", " + col + ".");
            if (board.checkForWin(row, col, mark)) {
                return new int[] { row, col };
            }
            board.removeMark(row, col);
        }
        return null;
    }

    public int[] makeWinningMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        List<int[]> validMoves = new ArrayList<>(emptyCells);
        char mark = board.getCurrentPlayerMark();
        
        for (int[] cell : validMoves) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);
            
            if (board.checkForWin(row, col, mark)) {
                return new int[] { row, col };
            }
            board.removeMark(row, col);
        }
        return null;
    }

    public int[] makeBlockingMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        List<int[]> validMoves = new ArrayList<>(emptyCells);
        char mark = board.getNextPlayerMark();

        for (int[] cell : validMoves) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);

            if (board.checkForWin(row, col, mark)) {
                board.removeMark(row, col);
                board.placeMark(row, col, board.getCurrentPlayerMark());
                return new int[] { row, col };
            }
            board.removeMark(row, col);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
