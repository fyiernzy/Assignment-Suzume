# Engine

The `Engine` class is an abstract class that serves as the base class for different engine implementations used in a Tic-Tac-Toe game. It extends the `Player` class and provides common functionality and methods for the game engine.

## Class Structure

The `Engine` class has the following structure:

```java
package com.assignment.suzume.tictactoe.player.engine;

import java.util.*;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Engine extends Player {
    private static int id = 0;

    Engine(char mark) {
        this("Engine " + ++id, mark);
    }

    Engine(String name, char mark) {
        super(name, mark);
    }

    public int[] makeRandomMove(GamingBoard board) {
        // Generates a random move by selecting an empty cell on the board
        // and placing the engine's mark on it
    }

    private int[] makeBestMove(GamingBoard board, char mark) {
        // Finds the best move that leads to a win for the specified mark
    }

    protected int[] makeWinningMove(GamingBoard board) {
        // Finds the winning move for the engine
    }

    protected int[] makeBlockingMove(GamingBoard board) {
        // Finds the blocking move to prevent the opponent from winning
    }

    protected char[][] copyBoard(char[][] original, int size) {
        // Creates a copy of the game board
    }
}

```

## Engine Subclasses

## EasyEngine

The `EasyEngine` class extends the `Engine` class, which suggests that it inherits some common functionality from the parent class. In this case, the `EasyEngine` class only overrides the makeMove method.

In the makeMove method, the `EasyEngine` class calls the makeRandomMove method from the parent Engine class to make a random move on the given `GamingBoard`. This indicates that the `EasyEngine` class doesn't implement any specific logic for making moves and relies on the random move generation provided by the parent class.

```java
package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class EasyEngine extends Engine {
    public EasyEngine(char mark) {
        super(mark);
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        return makeRandomMove(board);
    }
}

```

## MediumEngine

The `MediumEngine` class also extends the `Engine` class, inheriting its common functionality. In addition, the `MediumEngine` class overrides the makeMove method.

In the makeMove method, the `MediumEngine` class attempts to make a winning move by calling the makeWinningMove method from the parent Engine class. If a winning move is found, it is returned.

If no winning move is found, the `MediumEngine` class proceeds to check for a blocking move by calling the makeBlockingMove method from the parent Engine class. If a blocking move is found, it is returned.

If neither a winning move nor a blocking move is found, the `MediumEngine` class falls back to making a random move by calling the makeRandomMove method from the parent `Engine` class.

This suggests that the `MediumEngine` class implements a medium level of game-playing strategy. It first tries to make a winning move if available, then attempts to block the opponent from winning, and finally resorts to a random move if neither of the previous options is available.

```java
package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.List;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class MediumEngine extends Engine {
    public MediumEngine(char mark) {
        super(mark);
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int[] move;
        if ((move = makeWinningMove(board)) != null)
            return move;

        if ((move = makeBlockingMove(board)) != null)
            return move;

        return makeRandomMove(board);
    }

}
```

## HardEngine

The `HardEngine` class also extends the `Engine` class, inheriting its common functionality. In addition, the `HardEngine` class overrides the makeMove method.

In the makeMove method, the `HardEngine` class follows a similar strategy as the `MediumEngine` class. It first attempts to make a winning move by calling the makeWinningMove method from the parent Engine class. If a winning move is found, it is returned.

If no winning move is found, the `HardEngine` class proceeds to check for a blocking move by calling the makeBlockingMove method from the parent Engine class. If a blocking move is found, it is returned.

If neither a winning move nor a blocking move is found, the `HardEngine` class introduces an additional strategy by calling the makeWiseMove method. The makeWiseMove method checks for specific cells on the game board in the following order:

Corner cells: { 0, 0 }, { 0, size - 1 }, { size - 1, 0 }, { size - 1, size - 1 }
Center cell: { size >> 1, size >> 1 }
Side cells: { 0, 1 }, { 1, 0 }, { size - 1, 1 }, { 1, size - 1 }
The makeWiseMove method iterates over the choices and checks if the corresponding cell is empty and valid for a move. If an empty and valid cell is found, the HardEngine places its mark in that cell and returns the corresponding row and column as the move.

If no suitable move is found using the makeWiseMove strategy, the HardEngine class falls back to making a random move by calling the makeRandomMove method from the parent `

```java
package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class HardEngine extends Engine {
    public HardEngine(char mark) {
        super(mark);
    }
    
    @Override
    public int[] makeMove(GamingBoard board) {
        int[] move;
        if ((move = makeWinningMove(board)) != null) 
            return move;

        if ((move = makeBlockingMove(board)) != null) 
            return move;
            
        if ((move = makeWiseMove(board)) != null) 
            return move;
        
        return makeRandomMove(board);
    }

    public int[] makeWiseMove(GamingBoard board) {
        int size = board.getSize();

        // Check if any corner cell is empty, followed by center cell and side cells
        int[][] choices = {
                { 0, 0 }, { 0, size - 1 }, { size - 1, 0 }, { size - 1, size - 1 }, // corners cells
                { size >> 1, size >> 1 }, // center cells
                { 0, 1 }, { 1, 0 }, { size - 1, 1 }, { 1, size - 1 } // side cells
        };

        int row, col;
        for (int[] choice : choices) {
            row = choice[0];
            col = choice[1];
            if (board.isValidMove(row, col)) {
                board.placeMark(row, col, this.mark);
                return new int[] { row, col };
            }
        }
        return null;
    }
}
```

## reverseMediumEngine

The `reverseMediumEngine` class also extends the `Engine` class, inheriting its common functionality. In addition, the `reverseMediumEngine` class overrides the makeMove method.

The `reverseMediumEngine` class implements a modified version of the minimax algorithm for game playing. The evaluateBoard method evaluates the current state of the game board and returns a score based on the outcome (win, loss, or draw).

The minimax method recursively explores different game states by simulating moves for both players, maximizing the score for the maximizing player and minimizing the score for the minimizing player. The depth parameter controls the depth of the search tree.

In the makeMove method, the `reverseMediumEngine` class determines the best move by applying the minimax algorithm to the current game board. It considers all possible moves and evaluates their scores using the minimax algorithm. The best moves with the lowest score (maximizing player's perspective) are stored in the bestMoves list.

Finally, a random move is selected from the bestMoves list, and the corresponding row and column are returned as the move. The game board is updated with the engine's mark placed in the chosen cell.

```java
package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class reverseMediumEngine extends Engine {

    public reverseMediumEngine(char mark) {
        super(mark);

    }

    private int evaluateBoard(char[][] board) {
        int score = 0;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'O') {
                    score = -1; // Change to -1
                    break;
                } else if (board[i][0] == 'X') {
                    score = 1; // Change to 1
                    break;
                }
            }

            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'O') {
                    score = -1; // Change to -1
                    break;
                } else if (board[0][i] == 'X') {
                    score = 1; // Change to 1
                    break;
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'O') {
                score = -1; // Change to -1
            } else if (board[0][0] == 'X') {
                score = 1; // Change to 1
            }
        }

        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'O') {
                score = -1; // Change to -1
            } else if (board[0][2] == 'X') {
                score = 1; // Change to 1
            }
        }

        return score;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizingPlayer) {
        int score = evaluateBoard(board);

        if (score != 0 || depth == 0) {
            return score;
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'O';
                        int currentScore = minimax(board, depth - 1, false);
                        board[row][col] = ' ';
                        bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        int currentScore = minimax(board, depth - 1, true);
                        board[row][col] = ' ';
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        }
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int bestScore = Integer.MIN_VALUE;
        ArrayList<int[]> bestMoves = new ArrayList<>();

        List<int[]> emptyCells = board.getEmptyCells();
        char[][] gameBoard = board.getBoard();

        for (int[] cell : emptyCells) {
            int row = cell[0];
            int col = cell[1];

            char[][] copiedBoard =copyBoard(gameBoard,3);
            copiedBoard[row][col] = this.getMark();
            int currentScore = minimax(copiedBoard, 2, false);

            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestMoves.clear();
                bestMoves.add(new int[] { row, col });
            } else if (currentScore == bestScore) {
                bestMoves.add(new int[] { row, col });
            }
        }

        Random random = new Random();
        int[] move = !bestMoves.isEmpty() ? bestMoves.get(random.nextInt(bestMoves.size())) : new int[] { 0, 0 };
        board.placeMark(move[0], move[1], this.mark);
        return move;
    }

}

```


## reverseHardEngine

The `reverseHardEngine` class extends the `Engine` class and overrides the makeMove method. It implements a more advanced version of the minimax algorithm with alpha-beta pruning to optimize the search.

The evaluateBoard method is responsible for evaluating the current state of the game board and returning a score based on the outcome (win, loss, or draw). It checks the rows, columns, and diagonals for a winning configuration.

The minimax method recursively explores different game states by simulating moves for both players, maximizing the score for the maximizing player and minimizing the score for the minimizing player. The alpha-beta pruning technique is used to reduce unnecessary exploration of branches.

In the makeMove method, the `reverseHardEngine` class determines the best move by applying the minimax algorithm with alpha-beta pruning to the current game board. It considers all possible moves and evaluates their scores using the minimax algorithm. The best moves with the highest score (maximizing player's perspective) are stored in the bestMoves list.

Finally, a random move is selected from the bestMoves list, and the corresponding row and column are returned as the move. The game board is updated with the engine's mark placed in the chosen cell.

```java
package com.assignment.suzume.tictactoe.player.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public class reverseHardEngine extends Engine {

    public reverseHardEngine(char mark) {
        super(mark);

    }

    private int evaluateBoard(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'O') {
                    return -1;
                } else if (board[i][0] == 'X') {
                    return 1;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'O') {
                    return -1;
                } else if (board[0][i] == 'X') {
                    return 1;
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'O') {
                return -1;
            } else if (board[0][0] == 'X') {
                return 1;
            }
        }

        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'O') {
                return -1;
            } else if (board[0][2] == 'X') {
                return 1;
            }
        }

        return 0; // No winner, return 0
    }

    private int minimax(char[][] board, int depth, boolean isMaximizingPlayer, int alpha, int beta) {
        int score = evaluateBoard(board);

        if (score != 0 || depth == 0) {
            return score;
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'O';
                        int currentScore = minimax(board, depth - 1, false, alpha, beta);
                        board[row][col] = ' ';
                        bestScore = Math.max(bestScore, currentScore);
                        alpha = Math.max(alpha, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        int currentScore = minimax(board, depth - 1, true, alpha, beta);
                        board[row][col] = ' ';
                        bestScore = Math.min(bestScore, currentScore);
                        beta = Math.min(beta, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        }
    }

    @Override
    public int[] makeMove(GamingBoard board) {
        int bestScore = Integer.MIN_VALUE;
        List<int[]> bestMoves = new ArrayList<>();
        char[][] gameBoard = board.getBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == ' ') {
                    char[][] copiedBoard =copyBoard(gameBoard,3);
                    int maxDepth = 5;
                    copiedBoard[row][col] = 'O';
                    int currentScore = minimax(copiedBoard, maxDepth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMoves.clear();
                        bestMoves.add(new int[] { row, col });
                    } else if (currentScore == bestScore) {
                        bestMoves.add(new int[] { row, col });
                    }
                }
            }
        }

        Random random = new Random();
        int[] move = bestMoves.get(random.nextInt(bestMoves.size()));
        board.placeMark(move[0], move[1], this.mark);
        return move;
    }

}

```
