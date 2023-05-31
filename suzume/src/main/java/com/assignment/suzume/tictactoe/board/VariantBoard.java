package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;

public class VariantBoard extends GamingBoard {
    public VariantBoard() {
        super(3, Rule.VARIANT);
    }

    public boolean checkForWin(int row, int col, char mark) {
        // Check the row
        if (board[row][0] == mark && board[row][1] == mark && board[row][2] == mark)
            return true;

        // Check the column
        if (board[0][col] == mark && board[1][col] == mark && board[2][col] == mark)
            return true;

        // Check the diagonals
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)
            return true;

        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)
            return true;

        return false;
    }
}
