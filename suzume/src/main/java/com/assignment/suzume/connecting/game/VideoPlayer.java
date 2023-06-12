package com.assignment.suzume.connecting.game;

import java.util.Scanner;
import java.util.Stack;
import com.assignment.suzume.connecting.game.analyzer.*;
import com.assignment.suzume.tictactoe.board.*;

public class VideoPlayer {
    private Scanner scanner;
    private GamingBoard board;
    private GameAnalyzer analyzer;
    private Stack<int[]> sequentialMoveHistory;
    private Stack<double[]> moveEvaluation;

    public VideoPlayer(GamingBoard board, GameAnalyzer analyzer) {
        this.scanner = new Scanner(System.in);
        this.board = board;
        this.analyzer = analyzer;
        this.sequentialMoveHistory = new Stack<>();
        this.moveEvaluation = new Stack<>();
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
            board.printBoard();
            System.out.println();
            if ((char) move[2] == 'X') {
                double winProbabilityChange = currentEvaluation[0] - previousEvaluation[0];
                System.out.println(
                        "The move is: " + (winProbabilityChange > 0 ? "good" : "bad"));
                System.out.println("Previous win probability : " + previousEvaluation[0]);
                System.out.println("Current win probability : " + currentEvaluation[0]);
                System.out.println("The win probability changes : " + winProbabilityChange);
                System.out.println("Press ENTER to continue...");
                scanner.nextLine();
            }
        }
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
