package com.assignment.suzume.connecting.game.analyzer;

import java.io.Serializable;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class GameAnalyzer implements Serializable {
    transient protected static final int WIN_SCORE = Integer.MAX_VALUE >> 16;
    protected char playerOne;
    protected char playerTwo;
    protected GamingBoard gamingBoard;

    GameAnalyzer(char playerOne, char playerTwo, GamingBoard gamingBoard) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gamingBoard = gamingBoard;
    }

    public double[] getWinProbability() {
        int playerOneScore = getScore(playerOne);
        int playerTwoScore = getScore(playerTwo);
        int totalScore = playerOneScore + playerTwoScore;

        if (totalScore == 0)
            return new double[] { 0.5, 0.5 };

        if (totalScore == playerOneScore)
            return new double[] { 1, 0 };

        if (totalScore == playerTwoScore)
            return new double[] { 0, 1 };

        double playerOneWinProbability = (totalScore + playerOneScore) % totalScore / (double) totalScore;
        double playerTwoWinProbability = (totalScore + playerTwoScore) % totalScore / (double) totalScore;
        return new double[] { playerOneWinProbability, playerTwoWinProbability };
    }

    protected int getLineScore(char mark, char[] line) {
        int score = 0;
        for (char point : line) {
            if (point == mark)
                score++;
            else if (point != ' ')
                return 0;
        }
        return score;
    }

    protected abstract int getScore(char mark);

    protected int[] get3X3Score(char mark, int topLeftRow, int topLeftCol) {
        int totalScore = 0, score = 0;
        int winMove = 0;

        // Check the rows
        for (int row = 0; row < 3; row++) {
            score = getLineScore(mark, new char[] {
                    gamingBoard.getCellAt(topLeftRow + row, topLeftCol),
                    gamingBoard.getCellAt(topLeftRow + row, topLeftCol + 1),
                    gamingBoard.getCellAt(topLeftRow + row, topLeftCol + 2)
            });
            winMove += score >= 2 ? 1 : 0;
            totalScore += score > 0 ? 1 : 0;
        }

        // Checks the cols
        for (int col = 0; col < 3; col++) {
            score = getLineScore(mark, new char[] {
                    gamingBoard.getCellAt(topLeftRow, topLeftCol + col),
                    gamingBoard.getCellAt(topLeftRow + 1, topLeftCol + col),
                    gamingBoard.getCellAt(topLeftRow + 2, topLeftCol + col)
            });
            winMove += score >= 2 ? 1 : 0;
            totalScore += score > 0 ? 1 : 0;
        }

        // Check the diagonals
        score = getLineScore(mark, new char[] {
                gamingBoard.getCellAt(topLeftRow, topLeftCol),
                gamingBoard.getCellAt(topLeftRow + 1, topLeftCol + 1),
                gamingBoard.getCellAt(topLeftRow + 2, topLeftCol + 2)
        });
        winMove += score >= 2 ? 1 : 0;
        totalScore += score > 0 ? 1 : 0;

        // Check the diagonals
        score = getLineScore(mark, new char[] {
                gamingBoard.getCellAt(topLeftRow, topLeftCol + 2),
                gamingBoard.getCellAt(topLeftRow + 1, topLeftCol + 1),
                gamingBoard.getCellAt(topLeftRow + 2, topLeftCol)
        });
        winMove += score >= 2 ? 1 : 0;
        totalScore += score > 0 ? 1 : 0;

        return new int[] { totalScore, winMove };
    }

}
