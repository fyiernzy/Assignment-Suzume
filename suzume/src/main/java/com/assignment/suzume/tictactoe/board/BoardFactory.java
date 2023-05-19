package com.assignment.suzume.tictactoe.board;

com.assignment.suzume.tictactoe.board.rules.Rule;

public class BoardFactory {
    private static final String PARENT = "../board/rules/";

    public static final int VARIANT = 0;
    public static final int REGULAR = 1;
    public static final int REVERSE = 2;

    public static GamingBoard getBoard(int boardType) {
        switch(boardType) {
            case "3x3":
                return new RegularBoard(new Rule(PARENT + "RegularBoard.txt"));
            case "4x4":
                return new GamingBoard(PARENT + "4x4.txt");
            case "5x5":
                return new GamingBoard(PARENT + "5x5.txt");
            default:
                return null;
        }
    }
}
