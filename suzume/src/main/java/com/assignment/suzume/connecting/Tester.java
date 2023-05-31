package com.assignment.suzume.connecting;
import java.util.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.tictactoe.player.engine.HardEngine;


import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.tictactoe.system.*;

public class Tester {
    public static void main(String[] args) {
        Player p1 = new HardEngine();
        Player p2 = new HardEngine();
        GameRunner gameRunner = new GameRunner(p1, p2, new VariantBoard());
        gameRunner.gamePlay();
    }
}
