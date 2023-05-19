package com.assignment.suzume.tictactoe.board;

import com.assignment.suzume.tictactoe.board.rules.Rule;

public class BoardFactory {
    public static final int VARIANT = 0;
    public static final int REGULAR = 1;
    public static final int REVERSE = 2;

    public static GamingBoard getBoard(int boardType) {
        switch (boardType) {
            case 0:
                return new RegularBoard(Rule.REGULAR);
            case 1:
                return new ReverseBoard(Rule.REVERSE);
            case 2:
                return new RegularBoard(Rule.VARIANT);
            default:
                throw new IllegalArgumentException("Invalid board type");
        }
    }
}
