package TicTacToe.engine;

import TicTacToe.board.GamingBoard;
import java.util.*;

public class EasyEngine implements Engine {

    @Override
    public void makeMove(GamingBoard board) {
        makeRandomMove(board);
    }

    public void makeRandomMove(GamingBoard board) {

        // Check for empty cells on the board
        List<int[]> emptyCells =board.getEmptyCells();

        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        int randomRow = randomCell[0];
        int randomCol = randomCell[1];
        board.placeMark(randomRow, randomCol);

    }

}
