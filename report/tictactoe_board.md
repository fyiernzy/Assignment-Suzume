# Board

## **Regular Tic-Tac-Toe**
- Game board: A 5x5 square grid.
- Players: One player is designated as "X" and the other player is designated as "O".
- Turn Order: The system randomly determines which player (X or O) goes first.
- Turns: Players take turns placing their respective shapes (X or O) on any empty cell of the grid.
- Winning Condition: The first player to place 3 of their shapes in a horizontal, vertical, or diagonal row wins the game.
- Difficulty Levels for the Engine: The game engine provides three difficulty levels: Easy, Medium, and Hard.

  - Easy: The engine plays less optimal moves, allowing for easier gameplay.
  - Medium: The engine plays slightly optimal moves, providing a moderate challenge.
  - Hard: The engine plays mostly optimal moves, providing a strong challenge.


## **Misère/Reverse Tic-Tac-Toe**
- Game board: A 3x3 square grid.
- Players: One player is designated as "X," and the other player is designated as "O."
- Turn Order: The system randomly determines which player (X or O) goes first.
- Turns: Players take turns placing their respective shapes (X or O) on any empty cell of the grid.
- Losing Condition: The first player to place 3 of their shapes in a horizontal, vertical, or diagonal row loses the game (giving the win to the other player).
- Difficulty Levels for the Engine: The game engine provides three difficulty levels: Easy, Medium, and Hard.

  - Easy: The engine plays less optimal moves, allowing for easier gameplay.
  - Medium: The engine plays slightly optimal moves, providing a moderate challenge.
  - Hard: The engine plays mostly optimal moves, providing a strong challenge.


## **Custom Tic-Tac-Toe Variant**
- Game board: A 3x3 square grid.
- Players: One player is designated as "X" and the other player is designated as "O".
- Turn Order: The system randomly determines which player (X or O) goes first.
- Turns: Players take turns placing their respective shapes (X or O) on any empty cell of the grid.
- Winning Condition: The first player to place 3 of their shapes in a horizontal, vertical, or diagonal row wins the game.
- Difficulty Levels for the Engine: The game engine provides three difficulty levels: Easy, Medium, and Hard.

  - Easy: The engine plays less optimal moves, allowing for easier gameplay.
  - Medium: The engine plays slightly optimal moves, providing a moderate challenge.
  - Hard: The engine plays mostly optimal moves, providing a strong challenge.


The code includes different board implementations and game rules for regular tic-tac-toe, misère tic-tac-toe, and a custom tic-tac-toe variant. 

###  1. **`Board`** (Abstract Class)
```java
package com.assignment.suzume.tictactoe.board;

import java.io.Serializable;
import java.util.*;

public abstract class Board implements Serializable {
    // Class implementation
}
```
The `Board` class is an abstract class which represents the basic structure and functionality of a tic-tac-toe board. It contains methods to initialize the board, print it, retrieve its size, and check for empty cells or a full board.


### 2. **`GamingBoard`** (Abstract Class)
```java
package com.assignment.suzume.tictactoe.board;

import java.util.Stack;

public abstract class GamingBoard extends Board {
    // Class implementation
}

```
The `GamingBoard` abstract class extends `Board` and adds functionality for gameplay, such as recording moves, checking for wins, and validating moves. It also includes a stack to keep track of the move history, allowing for the ability to undo moves.


### 3. **`RegularBoard`** (Class)
```java
package com.assignment.suzume.tictactoe.board;

public class RegularBoard extends GamingBoard {
    // Class implementation
}

```
The `RegularBoard` class extends `GamingBoard` and implements the game rules for regular tic-tac-toe. It overrides the `checkForWin` method to check for winning conditions in rows, columns, and diagonals.


### 4. **`ReverseBoard`** (Class)
```java
package com.assignment.suzume.tictactoe.board;

public class ReverseBoard extends VariantBoard {
    // Class implementation
}

```
The `ReverseBoard` class extends `VariantBoard` and implements the game rules for misère tic-tac-toe. It inherits the `checkForWin` method from `GamingBoard` but does not modify it. This means that the win conditions remain the same as regular tic-tac-toe, where the first player to place three marks in a row, column, or diagonal wins.


### 5. **`VariantBoard`** (Class)
```java
package com.assignment.suzume.tictactoe.board;

public class VariantBoard extends GamingBoard {
    // Class implementation
}

```
The `VariantBoard` class extends `GamingBoard` and implements the game rules for a custom tic-tac-toe variant. It overrides the `checkForWin` method to check for winning conditions in rows, columns, and diagonals.


### 6. **`Rule`** (Enum)
```java
package com.assignment.suzume.tictactoe.board.rules;

import java.io.*;

public enum Rule {
    // Enum values and methods
}
```
The `Rule` enum represents the different game rules available. It provides an enumeration for each rule (`VARIANT`, `REGULAR`, `REVERSE`) and includes a URL to the corresponding rule file. The `readContent` method reads the content of the rule file and stores it in the `content `variable. The `getContent` method returns the content of the rule.

The provided code establishes the basic framework for implementing different tic-tac-toe game variants with various difficulty levels. It defines the board structure, gameplay rules, and difficulty levels, allowing for easy extensibility and customization of the game.
