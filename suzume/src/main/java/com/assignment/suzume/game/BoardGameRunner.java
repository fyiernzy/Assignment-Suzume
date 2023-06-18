package com.assignment.suzume.game;

import java.io.Serializable;
import com.assignment.suzume.utils.*;
import com.assignment.suzume.game.analyzer.*;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.constants.GameConstant;
import com.assignment.suzume.tictactoe.board.rules.Rule;

public class BoardGameRunner implements Serializable {
    private Rule rule;
    private Player one;
    private Player two;
    private GamingBoard board;
    private GameAnalyzer analyzer;
    private boolean isFirstPlayerTurn;
    private UserActionHandler userActionHandler;

    public BoardGameRunner(int gameMode, Player one, Player two, Rule rule, GamingBoard board, GameAnalyzer analyzer) {
        this.one = one;
        this.two = two;
        this.rule = rule;
        this.board = board;
        this.analyzer = analyzer;
        this.isFirstPlayerTurn = Math.random() < 0.5;
        this.userActionHandler = new UserActionHandler(gameMode, board, this);
        analyzer.setPlayerTurn(isFirstPlayerTurn);
    }

    public int play() {
        Player winner = null;
        Player currentPlayer = null;
        boolean hasWon = false;
        ConsolePrinter.printRule(rule);

        while (!board.isFull()) {
            displayGameInfo();
            currentPlayer = getCurrentPlayer();
            System.out.println(currentPlayer + "'s turn (" + currentPlayer.getMark() + ")");

            int[] action = getPlayerAction(currentPlayer);

            if (action[0] == GameConstant.EXIT) {
                return GameConstant.EXIT;
            }

            makeMoveAndUpdateBoard(currentPlayer, action);

            int row = action[1];
            int col = action[2];
            hasWon = board.checkForWin(row, col, currentPlayer.getMark());

            if (hasWon) {
                winner = board instanceof ReverseBoard ? (isFirstPlayerTurn ? two : one) : currentPlayer;
                break;
            }

            switchTurns();
        }

        board.printBoard();

        int gameCode = determineGameResult(winner);
        userActionHandler.showSaveReplayMenu();
        return gameCode;
    }

    private int determineGameResult(Player winner) {
        if (winner == null) {
            return GameConstant.DRAW;
        } else {
            System.out.println("Congratulations, " + winner + " (" + winner.getMark() + ") has won!\n");
            return winner == one ? GameConstant.WIN : GameConstant.LOSE;
        }
    }

    private Player getCurrentPlayer() {
        return isFirstPlayerTurn ? one : two;
    }

    private void displayGameInfo() {
        board.printBoard();
        System.out.println();
        displayWinProbability();
        System.out.println();
    }

    private void displayWinProbability() {
        double[] winProbability = analyzer.getWinProbability();
        System.out.printf("Win probability for %s: %.2f%%\n", one.getName(), winProbability[0] * 100);
        System.out.printf("Win probability for %s: %.2f%%\n", two.getName(), winProbability[1] * 100);
    }

    private void switchTurns() {
        isFirstPlayerTurn = !isFirstPlayerTurn;
        analyzer.setPlayerTurn(isFirstPlayerTurn);
    }

    private int[] getPlayerAction(Player currentPlayer) {
        if (currentPlayer instanceof Gamer) {
            return userActionHandler.showUserMenu((Gamer) currentPlayer);
        } else {
            int[] move = currentPlayer.makeMove(board);
            return new int[] { GameConstant.MOVE, move[0], move[1] };
        }
    }

    private void makeMoveAndUpdateBoard(Player currentPlayer, int[] action) {
        int[] move = { action[1], action[2], (int) currentPlayer.getMark() };
        board.recordMove(move);
        Timer.waitInSecond(1);
    }
}
