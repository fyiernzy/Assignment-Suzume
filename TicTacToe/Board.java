package TicTacToe;

public class Board {
    int size;
    char[][] board;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
    }
    
    public void mark(char ch, int x, int y) {
        board[y][x] = ch;
    }
    
    public char[][] getBoard() {
        return board;
    }
}
