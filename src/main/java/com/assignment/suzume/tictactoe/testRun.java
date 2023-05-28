package com.assignment.suzume.tictactoe;

import com.assignment.suzume.tictactoe.board.VariantBoard;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.system.GameRunner;

public class testRun {
    public static void main(String[] args) {
        GameRunner game= new GameRunner(new Gamer("sy"), new Gamer("ng"), new VariantBoard());
        game.gamePlay();
    }
    
    

}
