package com.assignment.suzume.tictactoe.player;

import java.io.Serializable;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Player implements Serializable {
    protected char mark;
    protected String name;
    private static final long serialVersionUID = 1L;
    
    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public abstract int[] makeMove(GamingBoard board);

    public char getMark() {
        return mark;
    }

    public char getOpponentMark() {
        return mark == 'X' ? 'O' : 'X';
    }
}
