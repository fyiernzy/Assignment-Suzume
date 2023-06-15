package com.assignment.suzume.tictactoe.player.engine;

import java.util.*;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Engine extends Player {
    private static int id = 0;

    Engine(char mark) {
        this("Engine " + ++id, mark);
    }

    Engine(String name, char mark) {
        super(name, mark);
    }

    public int[] makeRandomMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        board.placeMark(randomCell[0], randomCell[1], this.mark);
        return randomCell;
    }

    private int[] makeBestMove(GamingBoard board, char mark) {
        List<int[]> emptyCells = board.getEmptyCells();

        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);
            
            if (board.checkForWin(row, col, mark)) {
                board.removeMark(row, col);
                board.placeMark(row, col, this.mark);
                return new int[] { row, col };
            }
            board.removeMark(row, col);
        }
        return null;
    }

    protected int[] makeWinningMove(GamingBoard board) {
        return makeBestMove(board, this.mark);
    }

    protected int[] makeBlockingMove(GamingBoard board) {
        return makeBestMove(board, this.getOpponentMark());
    }

    protected char[][] copyBoard(char[][] original, int size) {
        char[][] copy = new char[size][size];
    
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                copy[row][col] = original[row][col];
            }
        }
    
        return copy;
    }
    
   
    
}
