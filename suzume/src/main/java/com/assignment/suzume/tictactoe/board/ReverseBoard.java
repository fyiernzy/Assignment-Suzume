package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;
public class ReverseBoard extends VariantBoard {
    ReverseBoard(Rule rule) {
        super(rule);
    }

    public boolean checkForWin(int row, int col) {
        return super.checkForWin(row, col);
    }
}

