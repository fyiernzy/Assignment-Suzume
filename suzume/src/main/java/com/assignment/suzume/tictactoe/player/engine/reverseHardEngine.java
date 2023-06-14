package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class reverseHardEngine extends Engine {

    public reverseHardEngine(char mark) {
        super(mark);

    }

    private int evaluateBoard(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'O') {
                    return -1;
                } else if (board[i][0] == 'X') {
                    return 1;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'O') {
                    return -1;
                } else if (board[0][i] == 'X') {
                    return 1;
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'O') {
                return -1;
            } else if (board[0][0] == 'X') {
                return 1;
            }
        }

        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'O') {
                return -1;
            } else if (board[0][2] == 'X') {
                return 1;
            }
        }

        return 0; // No winner, return 0
    }

    private int minimax(char[][] board, int depth, boolean isMaximizingPlayer, int alpha, int beta) {
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
                        int currentScore = minimax(board, depth - 1, false, alpha, beta);
                        board[row][col] = ' ';
                        bestScore = Math.max(bestScore, currentScore);
                        alpha = Math.max(alpha, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
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
                        int currentScore = minimax(board, depth - 1, true, alpha, beta);
                        board[row][col] = ' ';
                        bestScore = Math.min(bestScore, currentScore);
                        beta = Math.min(beta, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        }
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int bestScore = Integer.MIN_VALUE;
        List<int[]> bestMoves = new ArrayList<>();
        char[][] gameBoard = board.getBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == ' ') {
                    char[][] copiedBoard =copyBoard(gameBoard,3);
                    int maxDepth = 5;
                    copiedBoard[row][col] = 'O';
                    int currentScore = minimax(copiedBoard, maxDepth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMoves.clear();
                        bestMoves.add(new int[] { row, col });
                    } else if (currentScore == bestScore) {
                        bestMoves.add(new int[] { row, col });
                    }
                }
            }
        }

        Random random = new Random();
        int[] move = bestMoves.get(random.nextInt(bestMoves.size()));
        board.placeMark(move[0], move[1], this.mark);
        return move;
    }

}
