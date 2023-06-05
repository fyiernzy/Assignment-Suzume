package com.assignment.suzume.tictactoe.board;

import java.util.Stack;
import com.assignment.suzume.tictactoe.board.rules.Rule;
import com.assignment.suzume.tictactoe.player.Player;

public abstract class GamingBoard extends Board {
    protected char currentPlayerMark;
    protected Player currentPlayer;
    private Rule rule;
    private Stack<int[]> moveHistory;

    GamingBoard(int size, Rule rule) {
        super(size);
        this.currentPlayerMark = 'X';
        this.rule = rule;
        this.moveHistory = new Stack<>();
    }

    public abstract boolean checkForWin(int row, int col, char mark);

    // Places a mark at the cell specified by row and col with the mark of the
    // current player.

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

    public void placeMark(int row, int col) {
        placeMark(row, col, currentPlayerMark);
    }

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    public boolean isValidMove(int row, int col) {
        return !isFull() && isCellWithinBoard(row, col) && isCellEmpty(row, col);
    }

    public char getCellValue(int row, int col) {
        return board[row][col];
    }

    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public char getNextPlayerMark() {
        return currentPlayerMark == 'X' ? 'O' : 'X';
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    // Changes player mark each turn.
    public void changePlayer() {
        currentPlayerMark = currentPlayerMark == 'X' ? 'O' : 'X';
    }

    public String getRule() {
        return rule.getContent();
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
