package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.List;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class MediumEngine extends Engine {
    public MediumEngine(char mark) {
        super(mark);
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int[] move;
        if ((move = makeWinningMove(board)) != null)
            return move;

        if ((move = makeBlockingMove(board)) != null)
            return move;

        return makeRandomMove(board);
    }

}