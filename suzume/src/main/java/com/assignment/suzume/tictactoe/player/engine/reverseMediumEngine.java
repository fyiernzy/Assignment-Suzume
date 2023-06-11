package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class reverseMediumEngine extends Engine {

    public reverseMediumEngine(char mark) {
        super(mark);

    }

    private int evaluateBoard(char[][] board) {
        int score = 0;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'O') {
                    score = -1; // Change to -1
                    break;
                } else if (board[i][0] == 'X') {
                    score = 1; // Change to 1
                    break;
                }
            }

            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'O') {
                    score = -1; // Change to -1
                    break;
                } else if (board[0][i] == 'X') {
                    score = 1; // Change to 1
                    break;
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'O') {
                score = -1; // Change to -1
            } else if (board[0][0] == 'X') {
                score = 1; // Change to 1
            }
        }

        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'O') {
                score = -1; // Change to -1
            } else if (board[0][2] == 'X') {
                score = 1; // Change to 1
            }
        }

        return score;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizingPlayer) {
        int score = evaluateBoard(board);

        if (score != 0 || depth == 0) {
            return score;
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'O';
                        int currentScore = minimax(board, depth - 1, false);
                        board[row][col] = ' ';
                        bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        int currentScore = minimax(board, depth - 1, true);
                        board[row][col] = ' ';
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        }
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int bestScore = Integer.MIN_VALUE;
        ArrayList<int[]> bestMoves = new ArrayList<>();

        List<int[]> emptyCells = board.getEmptyCells();
        char[][] gameBoard = board.getBoard();

        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];

            char[][] copiedBoard =copyBoard(gameBoard,3);
            copiedBoard[row][col] = this.getMark();
            int currentScore = minimax(copiedBoard, 2, false);

            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestMoves.clear();
                bestMoves.add(new int[] { row, col });
            } else if (currentScore == bestScore) {
                bestMoves.add(new int[] { row, col });
            }
        }

        Random random = new Random();
        int[] move = !bestMoves.isEmpty() ? bestMoves.get(random.nextInt(bestMoves.size())) : new int[] { 0, 0 };
        board.placeMark(move[0], move[1], this.mark);
        return move;
    }

}
