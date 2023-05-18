package TicTacToe.engine;

import TicTacToe.board.GamingBoard;

public interface Engine {
    void makeMove(GamingBoard board);
    void makeRandomMove(GamingBoard board);
}