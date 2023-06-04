package com.assignment.suzume.connecting.account;

public class User {
    String name;
    String password;
    int win;
    int lose;
    int draw;
    int score;

    public User(String name, int win, int lose, int draw, int score) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.score = score;
    }

    public int getWinRate() {
        return (win + lose + draw) == 0 ? -1 : (win * 100) / (win + lose + draw);
    }

    public int getLoseRate() {
        return (win + lose + draw) == 0 ? -1 : (lose * 100) / (win + lose + draw);
    }

    public int getDrawRate() {
        return (win + lose + draw) == 0 ? -1 : (draw * 100) / (win + lose + draw);
    }

    public int getScore() {
        return score;
    }
}
