package com.assignment.suzume.game.analyzer;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class RegularGameAnalyzer extends GameAnalyzer{
    public RegularGameAnalyzer(char playerOne, char playerTwo, GamingBoard gamingBoard) {
        super(playerOne, playerTwo, gamingBoard);
    }

    @Override
    public int getScore(char mark) {
        int totalScore = 0;
        int winMove = 0;

        for (int bigRow = 0; bigRow < 3; bigRow++) {
            for (int bigCol = 0; bigCol < 3; bigCol++) {
                int[] scores = get3X3Score(mark, bigRow, bigCol);
                totalScore += scores[0];
                winMove += scores[1];
            }
        }

        return winMove >= 2 ? winMove * WIN_SCORE : totalScore;
    }
}
