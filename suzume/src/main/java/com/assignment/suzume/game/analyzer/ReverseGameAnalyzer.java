package com.assignment.suzume.game.analyzer;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class ReverseGameAnalyzer extends GameAnalyzer {
    public ReverseGameAnalyzer(char playerOne, char playerTwo, GamingBoard gamingBoard) {
        super(playerOne, playerTwo, gamingBoard);
    }

    @Override
    public double[] getWinProbability() {
        int[] playerOneScore = getScore(playerOne);
        int[] playerTwoScore = getScore(playerTwo);
        int totalScore = Math.abs(playerOneScore[0] + playerTwoScore[0]);

        if (totalScore == 0)
            return new double[] { 0.5, 0.5 };
        if (totalScore == Math.abs(playerOneScore[0]))
            return new double[] { 0, 1 };
        if (totalScore == Math.abs(playerTwoScore[0]))
            return new double[] { 1, 0 };
        
        if (isBoardAlmostFull()) {
            if(isPlayerOneTurn && playerOneScore[1] > 0)
                return new double[] { 0, 1 };
            if(!isPlayerOneTurn && playerTwoScore[1] > 0)
                return new double[] { 1, 0 };
        }

        double playerOneWinProbability = (totalScore + playerOneScore[0]) % totalScore / (double) totalScore;
        double playerTwoWinProbability = (totalScore + playerTwoScore[0]) % totalScore / (double) totalScore;

        return new double[] { playerOneWinProbability, playerTwoWinProbability };
    }

    @Override
    public int[] getScore(char mark) {
        int[] scores = get3X3Score(mark, 0, 0);
        int totalScore = scores[0];
        int winMove = scores[1];
        return new int[] { ~totalScore + 1, winMove };
    }

    private boolean isBoardAlmostFull() {
        int count = 0;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) 
                if(gamingBoard.getBoard()[i][j] != ' ')
                    count++;
        return count >= 8;
    }
}
