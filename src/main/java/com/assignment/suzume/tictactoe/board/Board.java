package com.assignment.suzume.tictactoe.board;

import java.util.*;

public abstract class Board {
    protected int size;
    protected char[][] board;
    protected List<int[]> emptyCells;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
        initializeEmptyCells();
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

    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("----".repeat(size) + "-");

        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("----".repeat(size) + "-");
        }
    }

    public int getSize() {
        return this.size;
    }

    public char[][] getBoard() {
        return this.board;
    }

    private void initializeEmptyCells() {
        this.emptyCells = new ArrayList<>();
        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size; j++) 
                emptyCells.add(new int[] { i, j });
    }

    public List<int[]> getEmptyCells() {
        return emptyCells;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == ' ';
    }
}
