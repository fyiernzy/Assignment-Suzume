package Assignment.clone.TicTacToe;

import Assignment.clone.TicTacToe.board.Board;

public class Player {
     private int id;
     private String name, password;
     private int win, lose, draw;
     private int score;

     Player(String name, String password) {
          this.name = name;
          this.password = password;
     }

     public void makeMove(Board board) {

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