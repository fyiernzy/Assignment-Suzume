package com.assignment.suzume.connecting.account;

public class User {
    String name;
    String password;
    int win;
    int lose;
    int draw;

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
        return 5 * win + 2 * draw - 3 * lose;
    }
}
