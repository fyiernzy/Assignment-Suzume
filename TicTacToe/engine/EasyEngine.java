package TicTacToe.engine;

import TicTacToe.board.GamingBoard;
import java.util.*;

public class EasyEngine implements Engine {

    @Override
    public void makeMove(GamingBoard board) {
        makeRandomMove(board);
    }

    private void makeRandomMove(GamingBoard board) {
        Random random = new Random();
        int size = board.getSize();

        // Keep generating random indices until a valid move is found
        int row = -1, col = -1;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.isValidMove(row, col));

        // Place the AI player's mark on the valid random position
        board.placeMark(row, col);
    }

}
