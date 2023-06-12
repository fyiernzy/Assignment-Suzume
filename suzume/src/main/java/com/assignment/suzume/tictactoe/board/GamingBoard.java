package com.assignment.suzume.tictactoe.board;

import java.util.Stack;

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
        if(moveHistory.size() >= 2) {
            for(int i = 0; i < 2; i++) {
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

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    public boolean isValidMove(int row, int col) {
        return !isFull() && isCellWithinBoard(row, col) && isCellEmpty(row, col);
    }
}
