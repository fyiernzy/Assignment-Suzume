package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {

    }

    protected char mark;

    public abstract int[] makeMove(GamingBoard board);

    public String getName() {
        return name;
    }

}
