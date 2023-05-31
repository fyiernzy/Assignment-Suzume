package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class MediumEngine extends Engine {
    public MediumEngine(char mark) {
        super(mark);
    }
    
    @Override
    public int[] makeMove(GamingBoard board) {
        int[] move;
        return (move = makeWinningMove(board)) == null ? makeRandomMove(board) : move;
    }
}