package com.assignment.suzume.tictactoe.player;

import java.util.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {
     // private int id;
     private int win, lose, draw;
     private int score;

     public Gamer(String name, char mark) {
          super(name, mark);
     }

     @Override
     public int[] makeMove(GamingBoard board) {
          Scanner scanner = new Scanner(System.in);
          int[] move = new int[2];
          while (true) {
               System.out.println("Enter your move: ");
               move[0] = scanner.nextInt();
               move[1] = scanner.nextInt();
               System.out.println("Your move is: " + move[0] + " " + move[1]);
               if (!board.isValidMove(move[0], move[1])) {
                    System.out.print("Invalid move. ");
                    continue;
               }
               break;
          }
          board.placeMark(move[0], move[1]);
          return move;
     }

     public int getWin() {
          return this.win;
     }

     public int getLose() {
          return this.lose;
     }

     public int getDraw() {
          return this.draw;
     }

     public int getWinPercentage() {
          return (win / (win + lose + draw)) * 100;
     }

     public int getLosePercentage() {
          return (lose / (win + lose + draw)) * 100;
     }

     public int getDrawPercentage() {
          return (draw / (win + lose + draw)) * 100;
     }

     public int getScore() {
          return this.score;
     }

     public String getName() {
          return name;
     }

     @Override
     public String toString(){
          return getName();
     }
}
