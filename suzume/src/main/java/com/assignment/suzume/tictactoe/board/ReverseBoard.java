package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;
public class ReverseBoard extends VariantBoard {
    public ReverseBoard() {
        this.setRule(Rule.REVERSE);
    }

    public boolean checkForWin(int row, int col) {
        return super.checkForWin(row, col);
    }
}

