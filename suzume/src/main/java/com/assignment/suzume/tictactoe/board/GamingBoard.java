package com.assignment.suzume.tictactoe.board;

import java.util.Stack;
import com.assignment.suzume.tictactoe.board.rules.Rule;

public abstract class GamingBoard extends Board {
    private Rule rule;
    private Stack<int[]> moveHistory;

    GamingBoard(int size, Rule rule) {
        super(size);
        this.rule = rule;
        this.moveHistory = new Stack<>();
    }

    public abstract boolean checkForWin(int row, int col, char mark);

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

    public void printRule() {
        System.out.println("=".repeat(50));
        System.out.println(rule.getContent());
        System.out.println("=".repeat(50));
    }

    protected void setRule(Rule rule) {
        this.rule = rule;
    }
}
