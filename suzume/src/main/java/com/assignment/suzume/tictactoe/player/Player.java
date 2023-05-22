package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Player {
    protected char mark;
    public abstract int[] makeMove(GamingBoard board);
}
