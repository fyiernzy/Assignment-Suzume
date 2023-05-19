package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;
public class VariantBoard extends GamingBoard {
    VariantBoard(Rule rule) {
        super(3, rule);
    }

    // Returns true if the player has won after placing their mark in the given row
    // and column
    public boolean checkForWin(int row, int col) {
        // Check the row
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2])
            return true;

        // Check the column
        if (board[0][col] == board[1][col] && board[1][col] == board[2][col])
            return true;

        // Check the diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return true;
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return true;

        return false;
    }
}
