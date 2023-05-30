package com.assignment.suzume.tictactoe.player;

import java.util.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.tictactoe.board.VariantBoard;

public class Gamer extends Player {
     private int id;
     private String username, password;
     private int win, lose, draw;
     private int score;

     public Gamer() {
     }

     public Gamer(String name) {
          this.username = name;
     }

     public Gamer(String username, String password) {
          this.username = username;
          this.password = password;
          win = 0;
          lose = 0;
          draw = 0;
          score = 0;
     }

     public static void main(String[] args) {
          Gamer gamer = new Gamer("Player 1");
          gamer.makeMove(new VariantBoard());
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
          scanner.close();
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

     public String getUsername() {
          return username;
     }

     public void setId(int id) {
          this.id = id;
     }

     public void setUsername(String name) {
          this.username = name;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public void setWin(int win) {
          this.win = win;
     }

     public void setLose(int lose) {
          this.lose = lose;
     }

     public void setDraw(int draw) {
          this.draw = draw;
     }

     public void setScore(int score) {
          this.score = score;
     }

     public int getId() {
          return id;
     }

     public String getPassword() {
          return password;
     }
}
