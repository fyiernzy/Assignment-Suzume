package com.assignment.suzume;

import com.assignment.suzume.tictactoe.system.GameRunner;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.engine.EasyEngine;
import com.assignment.suzume.tictactoe.player.engine.HardEngine;
import com.assignment.suzume.tictactoe.board.*;

public class TicTacToeTester {
    public static void main(String[] args) {
        Gamer one = new Gamer("Player 1");
        GamingBoard variant = new VariantBoard();
        HardEngine two = new HardEngine();
        GameRunner runner = new GameRunner(one, two, new VariantBoard());
        runner.gamePlay();
    } 
}
