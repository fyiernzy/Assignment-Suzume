package com.assignment.suzume.tictactoe.system;

import com.assignment.suzume.tictactoe.player.*;

public class GameRunnerFactory {
    public static final int PVP = 1;
    public static final int EVP = 2;
    public static final int EVE = 3;

    public static GameRunner getGameRunner(int gameType) {
        if (gameType.equals("HvH")) {
            return new HumanVsHumanGameRunner();
        } else if (gameType.equals("HvE")) {
            return new HumanVsEngineGameRunner();
        } else if (gameType.equals("EvE")) {
            return new EngineVsEngineGameRunner();
        } else {
            return null;
        }
    }
}
