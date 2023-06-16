package com.assignment.suzume.game;

import java.util.Stack;
import com.assignment.suzume.game.analyzer.*;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.utils.InputHandler;    

public class VideoPlayer {
    private GamingBoard board;
    private GameAnalyzer analyzer;
    private Stack<int[]> sequentialMoveHistory;
    private Stack<double[]> moveEvaluation;
    private Stack<Boolean> previousPlayerWinningMove;
    private Stack<Boolean> previousOpponentWinningMove;
    private boolean firstRound;

    public VideoPlayer(GamingBoard board, GameAnalyzer analyzer) {
        this.board = board;
        this.analyzer = analyzer;
        this.sequentialMoveHistory = new Stack<>();
        this.moveEvaluation = new Stack<>();
        this.previousPlayerWinningMove = new Stack<>();
        this.previousOpponentWinningMove = new Stack<>();
        this.firstRound = true;
        initializeVideoPlayer();
    }

    private void initializeVideoPlayer() {
        Stack<int[]> reverseMoveHistory = board.getMoveHistory();
        moveEvaluation.push(analyzer.getWinProbability());

        while (!reverseMoveHistory.isEmpty()) {
            int[] move = reverseMoveHistory.pop();
            sequentialMoveHistory.push(move);
            board.removeMark(move[0], move[1]);
            moveEvaluation.push(analyzer.getWinProbability());
        }
    }

    public void replay() {
        board.printBoard();

        while (!sequentialMoveHistory.isEmpty()) {
            int[] move = sequentialMoveHistory.pop();
            double[] previousEvaluation = moveEvaluation.pop();
            board.placeMark(move[0], move[1], (char) move[2]);
            double[] currentEvaluation = moveEvaluation.peek();
            boolean previousHasPlayerWinningMove = false;
            boolean previousHasOpponentWinningMove = false;
            board.printBoard();
            System.out.println();

            boolean hasPlayerWinningMove = analyzer.hasPlayerWinningMove(board);
            if (!firstRound) {
                previousHasPlayerWinningMove = previousPlayerWinningMove.pop();
            }
            previousPlayerWinningMove.push(hasPlayerWinningMove);
            boolean hasOpponentWinningMove = analyzer.hasOpponentWinningMove(board);
            if (!firstRound) {
                previousHasOpponentWinningMove = previousOpponentWinningMove.pop();
            }
            previousOpponentWinningMove.push(hasOpponentWinningMove);
            firstRound = false;

            if ((char) move[2] == 'X') {
                double winProbabilityChange = currentEvaluation[0] - previousEvaluation[0];

                if (previousHasPlayerWinningMove) {
                    if (hasPlayerWinningMove) {
                        System.out.println("The player already has a winning move.");
                        System.out.println("The player does not make the winning move.");
                        System.out.println("This move is: BAD.");
                    } else if (sequentialMoveHistory.isEmpty()) {
                        System.out.println("The opponent does not make the blocking move.");
                        System.out.println("The player WINS.");
                        break;
                    }
                } else if (previousHasOpponentWinningMove) {
                    System.out.println("The opponent already has a winning move.");
                    if (hasOpponentWinningMove) {
                        System.out.println("The player does not make the blocking move.");
                        System.out.println("This move is: BAD.");
                    } else {
                        System.out.println("The player makes the blocking move.");
                        System.out.println("The move is: GOOD.");
                    }
                } else if (hasPlayerWinningMove) {
                    System.out.println("The player has a winning move now.");
                    System.out.println("The move is: GOOD.");
                } else {
                    System.out.println("The move is: " + (winProbabilityChange > 0 ? "GOOD." : "BAD."));
                }

                System.out.println("+--------------------------------+");
                System.out.println("|         Move Evaluation        |");
                System.out.println("+--------------------------------+");
                System.out.format("| Previous Win Probability: %.2f |%n", previousEvaluation[0]);
                System.out.format("| Current Win Probability : %.2f |%n", currentEvaluation[0]);
                System.out.format("| Win Probability Change  : %.2f |%n", Math.abs(winProbabilityChange));
                System.out.println("+--------------------------------+");
            }
            System.out.println("\nPress ENTER to continue...");
            InputHandler.next();
        }
        System.out.println("Game ended.\n");
    }



    public static VideoPlayer getSuitableVideoPlayer(GamingBoard board) {
        if (board instanceof RegularBoard) {
            return new VideoPlayer(board, new RegularGameAnalyzer('X', 'O', board));
        } else if (board instanceof ReverseBoard) {
            return new VideoPlayer(board, new ReverseGameAnalyzer('X', 'O', board));
        } else if (board instanceof VariantBoard) {
            return new VideoPlayer(board, new VariantGameAnalyzer('X', 'O', board));
        } else {
            throw new IllegalArgumentException("Invalid board type");
        }
    }
}
