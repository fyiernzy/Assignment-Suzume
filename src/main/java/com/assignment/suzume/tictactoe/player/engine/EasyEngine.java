package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class EasyEngine extends Engine {
    @Override
    public int[] makeMove(GamingBoard board) {
        return makeRandomMove(board);
    }
}
