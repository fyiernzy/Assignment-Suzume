package com.assignment.suzume.tictactoe.board;

import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

public abstract class GamingBoard extends Board {
    private Stack<int[]> moveHistory;

    public GamingBoard(int size) {
        super(size);
        this.moveHistory = new Stack<>();
    }

    public abstract boolean checkForWin(int row, int col, char mark);

    public Stack<int[]> getMoveHistory() {
        return moveHistory;
    }

    public void recordMove(int[] move) {
        moveHistory.push(move);
    }

    public boolean takeBackMove() {
        if (moveHistory.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                int[] move = moveHistory.pop();
                removeMark(move[0], move[1]);
            }
            return true;
        }
        return false;
    }

    public void placeMark(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public void placeMark(int cellNumber, char mark) {
        int row = (cellNumber - 1) / size;
        int col = (cellNumber - 1) % size;

        if (isValidMove(row, col)) {
            board[row][col] = mark;
        }
    }

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    // public void removeMark(int cellNumber) {
    // int row = (cellNumber - 1) / size;
    // int col = (cellNumber - 1) % size;

    // board[row][col] = ' ';
    // }

    public boolean isValidMove(int row, int col) {
        return !isFull() && isCellWithinBoard(row, col) && isCellEmpty(row, col);
    }

    public void printBoard() {
        System.out.println("-".repeat(6 * size + 1));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cellNumber = i * size + j + 1;
                char mark = board[i][j];
                String val = String.valueOf(mark == ' ' ? cellNumber : String.valueOf(mark));
                System.out.printf("| %s ", StringUtils.center(val, 3));
            }
            System.out.println("|");
            System.out.println("-".repeat(6 * size + 1));
        }
    }
}
