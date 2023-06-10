package com.assignment.suzume.connecting.game.monitor;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class VariantGameMonitor extends GameMonitor {
    public VariantGameMonitor(char playerOne, char playerTwo, GamingBoard gamingBoard) {
        super(playerOne, playerTwo, gamingBoard);
    }

    @Override
    public int getScore(char mark) {
        int[] scores = get3X3Score(mark, 0, 0);
        int totalScore = scores[0];
        int winMove = scores[1];
        return winMove >= 2 ? winMove * WIN_SCORE : totalScore;
    }
}