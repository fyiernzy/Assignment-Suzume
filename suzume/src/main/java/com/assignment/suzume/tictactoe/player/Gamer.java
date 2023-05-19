package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {
    private int id;
     private String name, password;
     private int win, lose, draw;
     private int score;

     Gamer(String name, String password) {
          this.name = name;
          this.password = password;
     }

     @Override
     public int[] makeMove(GamingBoard board) {
        return null;
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

     public String getPassword() {
          return password;
     }
}
