package com.assignment.suzume.tictactoe.system;

import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GameRunner2 {
    Player one;
    Player two;
    GamingBoard board;

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
        printRules();
        one.setMark(board.pickMark());
        two.setMark(one.getMark() == 'X' ? 'O' : 'X');
        boolean isOneTurn = (int) (Math.random() * 2) == 0 ? true : false;
        int[] move = new int[2];
        boolean hasWon = false;
        if(!isOneTurn){
            board.changePlayer();
        }
        while(!board.isBoardFull()) {
            board.printBoard();
            System.out.println();
            if(isOneTurn) {
                board.setCurrentPlayer(one);
                System.out.println(one.getName() + "'s turn");
                move = one.makeMove(board);
                
            } else {
                board.setCurrentPlayer(two);
                System.out.println(two.getName() + "'s turn");
                move = two.makeMove(board);
            }
            
            int row = move[0], col = move[1];
            hasWon = board.checkForWin(row, col);
            if(hasWon) {
                break;
            }
            
            isOneTurn = !isOneTurn;
            board.changePlayer();
        }

        if(board.isBoardFull() && !hasWon) {
            board.printBoard();
            System.out.println("It's a tie!");
        } else {
            board.printBoard();
            System.out.println("Congratulations, " + board.getCurrentPlayer() + "(" + board.getCurrentPlayerMark() + ")" + " has won!\n");
        }
    }

    public boolean OneWin() {
        return board.getCurrentPlayer().equals(one);
    }
}
