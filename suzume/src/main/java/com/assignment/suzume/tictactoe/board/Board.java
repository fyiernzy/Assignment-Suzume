package com.assignment.suzume.tictactoe.board;

import java.io.Serializable;
import java.util.*;

public abstract class Board implements Serializable {
    protected int size;
    protected char[][] board;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }

    // Set/Reset the board back to all empty values.
    public void initializeBoard() {
        // Loop through rows
        for (int i = 0; i < size; i++) {
            // Loop through columns
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public char[][] getBoard() {
        return this.board;
    }

    public List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j] == ' ')
                    emptyCells.add(new int[] { i, j });
        return emptyCells;
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    // Loop through all cells of the board and if one is found to be empty (contains
    // char '-') then return false.
    // Otherwise the board is full.
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCellWithinBoard(int row, int col) {
        return (row >= 0 && row < size) && (col >= 0 && col < size);
    }

    public char getCellAt(int row, int col) {
        return board[row][col];
    }

    

}
