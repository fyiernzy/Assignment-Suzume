package com.assignment.suzume.connecting.account;

public class User {
    private static User user; // Singleton
    private String name;
    private int win;
    private int lose;
    private int draw;
    private int score;

    private User() {

    }

    private User(String name, int win, int lose, int draw, int score) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.score = score;
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void initializeProfile(String name, int win, int lose, int draw, int score) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.score = score;
    }

    public void updateResult(int result) {
        this.win += Math.max(result, 0);
        this.lose += Math.max(-1 * result, 0);
        this.draw += Math.max(1 - Math.abs(result), 0);
        this.score = 5 * win + 2 * draw - 3 * lose;
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

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
