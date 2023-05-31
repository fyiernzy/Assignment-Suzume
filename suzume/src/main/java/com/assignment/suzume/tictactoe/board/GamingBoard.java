package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;
import com.assignment.suzume.tictactoe.player.Player;

public abstract class GamingBoard extends Board {
    protected char currentPlayerMark;
    protected Player currentPlayer;
    private Rule rule;

    GamingBoard(int size, Rule rule) {
        super(size);
        this.currentPlayerMark = 'X';
        this.rule = rule;
    }

    public abstract boolean checkForWin(int row, int col, char mark);

    // Places a mark at the cell specified by row and col with the mark of the
    // current player.
    public boolean placeMark(int row, int col, char mark) {
        if (isValidMove(row, col)) {
            board[row][col] = mark;
            return true;
        }
        return false;
    }

    public boolean placeMark(int row, int col) {
        if(isValidMove(row, col)) {
            placeMark(row, col, currentPlayerMark);
            return true;
        }
        return false;
    }

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    public boolean isValidMove(int row, int col) {
        return !isFull() && isCellWithinBoard(row, col) && isCellEmpty(row, col);
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
