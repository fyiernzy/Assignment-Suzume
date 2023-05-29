package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Player {
    protected String name;
    protected char mark;

    public Player(String name) {
        this.name = name;
    }

    public Player() {

    }
    public abstract int[] makeMove(GamingBoard board);

    public String getName() {
        return name;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark =mark;
    }

}
