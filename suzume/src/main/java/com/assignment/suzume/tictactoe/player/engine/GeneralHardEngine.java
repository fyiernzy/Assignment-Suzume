package com.assignment.suzume.tictactoe.player.engine;

import java.util.List;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GeneralHardEngine extends Engine {
    char opponentMark;

    public GeneralHardEngine(char mark) {
        super(mark);
        this.opponentMark = mark == 'X' ? 'O' : 'X';
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        List<int[]> emptyCells = board.getEmptyCells();
        int[] bestMoves = new int[2];
        int bestScore = Integer.MIN_VALUE;

        for(int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];
            board.placeMark(row, col, mark);
            int score = minimaxHelper(board, false, 0, row, col);
            board.removeMark(row, col);

            if(score > bestScore) {
                bestScore = score;
                bestMoves[0] = row;
                bestMoves[1] = col;
            }
        }

        board.placeMark(bestMoves[0], bestMoves[1]);
        return bestMoves;
    }

    private int minimaxHelper(GamingBoard board, boolean maximizingPlayer, int depth, int currentRow,
            int currentCol) {

        int boardSize = board.getSize();

        if (board.checkForWin(currentRow, currentCol, mark)) {
            return (int) Math.pow(boardSize, 2) - depth; 
        }

        if (board.checkForWin(currentRow, currentCol, opponentMark)) {
            return depth - (int) Math.pow(boardSize, 2); 
        }

        if (board.isFull()) {
            return 0; // Draw
        }


        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<int[]> emptyCells = board.getEmptyCells();

        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];

            board.placeMark(row, col, maximizingPlayer ? mark : opponentMark);
            int score = minimaxHelper(board, !maximizingPlayer, depth + 1, row, col);
            board.removeMark(row, col);
            bestScore = maximizingPlayer ? Math.max(score, bestScore) : Math.min(score, bestScore);
        }

        return bestScore;
    }
}
