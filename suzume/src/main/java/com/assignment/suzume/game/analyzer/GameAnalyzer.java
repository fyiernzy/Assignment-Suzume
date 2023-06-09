package com.assignment.suzume.game.analyzer;

import java.io.Serializable;
import java.util.List;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class GameAnalyzer implements Serializable {
    transient protected static final int WIN_SCORE = Integer.MAX_VALUE >> 16;
    protected char playerOne;
    protected char playerTwo;
    protected boolean isPlayerOneTurn;
    protected GamingBoard gamingBoard;

    GameAnalyzer(char playerOne, char playerTwo, GamingBoard gamingBoard) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gamingBoard = gamingBoard;
    }

    public void setPlayerTurn(boolean isPlayerOneTurn) {
        this.isPlayerOneTurn = isPlayerOneTurn;
    }

    public double[] getWinProbability() {
        int[] playerOneScore = getScore(playerOne);
        int[] playerTwoScore = getScore(playerTwo);
        int totalScore = Math.abs(playerOneScore[0] + playerTwoScore[0]);

        if (totalScore == 0) return new double[] { 0.5, 0.5 };
        if (totalScore == playerOneScore[0]) return new double[] { 1, 0 };
        if (totalScore == playerTwoScore[0]) return new double[] { 0, 1 };

        int playerOneWinMove = playerOneScore[1];
        int playerTwoWinMove = playerTwoScore[1];

        if(isPlayerOneTurn && playerOneWinMove >= 1) return new double[] { 1, 0 };
        if(isPlayerOneTurn && playerTwoWinMove >= 2) return new double[] { 0, 1 };
        if(!isPlayerOneTurn && playerTwoWinMove >= 1) return new double[] { 0, 1 };
        if(!isPlayerOneTurn && playerOneWinMove >= 2) return new double[] { 1, 0 };
        
        double playerOneWinProbability = (totalScore + playerOneScore[0]) % totalScore / (double) totalScore;
        double playerTwoWinProbability = (totalScore + playerTwoScore[0]) % totalScore / (double) totalScore;

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

    protected abstract int[] getScore(char mark);

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

    public boolean hasWinningMove(GamingBoard board, char mark) {
        List<int[]> emptyCells = board.getEmptyCells();

        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);

            if (board.checkForWin(row, col, mark)) {
                board.removeMark(row, col);
                return true;
            }
            board.removeMark(row, col);
        }
        return false;
    }

    public boolean hasPlayerWinningMove(GamingBoard board) {
        return hasWinningMove(board, playerOne);
    }

    public boolean hasOpponentWinningMove(GamingBoard board) {
        return hasWinningMove(board, playerTwo);
    }

    protected char getOpponentMark(char playerMark) {
        return playerMark == 'X' ? 'O' : 'X';
    }
}
