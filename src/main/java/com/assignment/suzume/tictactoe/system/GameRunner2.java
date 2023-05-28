package com.assignment.suzume.tictactoe.system;

import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GameRunner2 {
    Player one;
    Player two;
    GamingBoard board;
    char thisUser ;

    public GameRunner2(Player one, Player two, GamingBoard board) {
        this.one = one;
        this.two = two;
        this.board = board;
    }

    private void printRules() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println(board.getRule());
    }

    public void gamePlay() {
        thisUser = board.getCurrentPlayerMark();
        printRules();
        boolean isOneTurn = (int) (Math.random() * 2) == 0 ? true : false;
        int[] move = new int[2];

        while (!board.isBoardFull()) {
            System.out.println((isOneTurn ? one : two).getName() + "'s turn");
            board.printBoard();
            if (isOneTurn) {
                move = one.makeMove(board);
            } else {
                move = two.makeMove(board);
            }

            int row = move[0], col = move[1];
            if (board.checkForWin(row, col)) {
                board.printBoard();
                break;
            }

            isOneTurn = !isOneTurn;
            board.changePlayer();
        }

        if (board.isBoardFull()) {
            System.out.println("It's a tie!");
        } else {
            calScore(one, two);

            if (OneWin()) {
                System.out
                        .println("Congratulations, player " + one + " [" + board.getCurrentPlayerMark() + "] has won!");
            } else {
                System.out
                        .println("Congratulations, player " + two + " [" + board.getCurrentPlayerMark() + "] has won!");
            }
        }
    }

    public boolean OneWin() {
        return (thisUser == board.getCurrentPlayerMark());
    }

    public void calScore(Player one, Player two) {
        if (OneWin() && one instanceof Gamer) {
            ((Gamer) one).win();
            ((Gamer) two).lose();
        }
    }

}
