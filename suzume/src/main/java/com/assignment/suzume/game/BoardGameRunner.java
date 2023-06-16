package com.assignment.suzume.game;

import java.io.Serializable;
import com.assignment.suzume.game.analyzer.*;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.utils.ConsolePrinter;
import com.assignment.suzume.constants.GameConstant;
import com.assignment.suzume.tictactoe.board.rules.Rule;

public class BoardGameRunner implements Serializable {
    private Rule rule;
    private Player one;
    private Player two;
    private GamingBoard board;
    private GameAnalyzer analyzer;
    private UserActionHandler userActionHandler;

    public BoardGameRunner(int gameMode, Player one, Player two, Rule rule, GamingBoard board, GameAnalyzer analyzer) {
        this.one = one;
        this.two = two;
        this.rule = rule;
        this.board = board;
        this.analyzer = analyzer;
        this.userActionHandler = new UserActionHandler(gameMode, board, this);
    }

    public int play() {
        Player winner = null;
        Player currentPlayer = null;
        boolean hasWon = false;
        boolean isFirstPlayerTurn = Math.random() < 0.5;

        ConsolePrinter.printRule(rule);

        while (!board.isFull()) {
            board.printBoard();
            System.out.println();

            currentPlayer = isFirstPlayerTurn ? one : two;
            System.out.println(currentPlayer + "'s turn (" + currentPlayer.getMark() + ")");
            printWinProbability();

            int[] action = { -1, -1, -1 };

            if (currentPlayer instanceof Gamer) {
                action = userActionHandler.showUserMenu((Gamer) currentPlayer);
            } else {
                int[] move = currentPlayer.makeMove(board);
                action = new int[] { GameConstant.MOVE, move[0], move[1] };
            }

            if (action[0] == GameConstant.EXIT) {
                return GameConstant.EXIT;
            }

            int[] move = { action[1], action[2], (int) currentPlayer.getMark() };
            board.recordMove(move);

            sleepForOneSecond();

            int row = move[0];
            int col = move[1];
            hasWon = board.checkForWin(row, col, currentPlayer.getMark());

            if (hasWon) {
                winner = board instanceof ReverseBoard ? (isFirstPlayerTurn ? two : one) : currentPlayer;
                break;
            }

            isFirstPlayerTurn = !isFirstPlayerTurn;
        }

        board.printBoard();

        if (board.isFull() && !hasWon) {
            System.out.println("It's a tie!");
            userActionHandler.showSaveReplayMenu();
            return GameConstant.DRAW;
        } else {
            System.out.println("Congratulations, " + winner + " (" + winner.getMark() + ") has won!\n");
            userActionHandler.showSaveReplayMenu();
            return winner == one ? GameConstant.WIN : GameConstant.LOSE;
        }
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printWinProbability() {
        double[] winProbability = analyzer.getWinProbability();
        System.out.printf("Win probability --> %s: %.2f%%\n", one, winProbability[0] * 100);
        System.out.printf("Win probability --> %s: %.2f%%\n", two, winProbability[1] * 100);
    }
}
