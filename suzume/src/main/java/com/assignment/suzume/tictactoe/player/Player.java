package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Player {
    protected char mark;
    protected String name;
    public abstract int[] makeMove(GamingBoard board);

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }
}
