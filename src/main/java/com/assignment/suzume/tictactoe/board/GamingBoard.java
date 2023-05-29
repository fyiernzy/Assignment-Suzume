package com.assignment.suzume.tictactoe.board;

import java.util.Scanner;

import com.assignment.suzume.tictactoe.board.rules.Rule;
import com.assignment.suzume.tictactoe.player.Player;

public abstract class GamingBoard extends Board {
    protected char currentPlayerMark;
    protected Player currentPlayer;
    private Rule rule;

    GamingBoard(int size, Rule rule) {
        super(size);
        this.rule = rule;
    }

    public abstract boolean checkForWin(int row, int col);

    public char pickMark() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose 'X' or 'O'.");
        char mark = sc.next().charAt(0);
        while (mark != 'X' && mark != 'O') {
            System.out.println("Invalid mark. Please choose 'X' or 'O'.");
            mark = sc.next().charAt(0);
        }
        this.currentPlayerMark = mark;
        return mark;
    }

    // Changes player mark each turn.
    public void changePlayer() {
        currentPlayerMark = currentPlayerMark == 'X' ? 'O' : 'X';
    }

    // Loop through all cells of the board and if one is found to be empty (contains
    // char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Places a mark at the cell specified by row and col with the mark of the
    // current player.
    public boolean placeMark(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayerMark;
            emptyCells.removeIf(cell -> cell[0] == row && cell[1] == col);
            return true;
        }

        return false;
    }

    public boolean isValidMove(int row, int col) {
        return !isBoardFull()
                && (row >= 0 && row < size) && (col >= 0 && col < size)
                && isEmpty(row, col);
    }

    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public char getNextPlayerMark() {
        return currentPlayerMark == 'X' ? 'O' : 'X';
    }

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    public String getRule() {
        return rule.getContent();
    }

    protected void setRule(Rule rule) {
        this.rule = rule;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
