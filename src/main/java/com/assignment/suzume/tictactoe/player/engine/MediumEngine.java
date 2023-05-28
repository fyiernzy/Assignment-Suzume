package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class MediumEngine extends Engine {

    @Override
    public int[] makeMove(GamingBoard board) {
        // Check if there is a winning move for the AI player
        int[] move;
        
        if((move = makeBestMove(board, board.getCurrentPlayerMark())) != null || 
            (move = makeBestMove(board, board.getNextPlayerMark())) != null) {
            return move;
        }

        // If no winning move found, select a random empty cell
        return makeRandomMove(board);
    }
}
