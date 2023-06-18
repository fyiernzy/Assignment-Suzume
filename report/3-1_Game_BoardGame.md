# **3.1 Board Game: A Smooth and Exciting Journey**

The `BoardGameRunner` class takes on the crucial role of orchestrating the board game, bringing together various necessary components for a delightful gaming experience. To accomplish this, several classes are imported, including

```java
import com.assignment.suzume.game.analyzer.*;
import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.utils.ConsolePrinter;
import com.assignment.suzume.constants.GameConstant;
import com.assignment.suzume.tictactoe.board.rules.Rule;
```

Let's delve into the instance variables of the `BoardGameRunner` class:

```java
public class BoardGameRunner {
    private Rule rule;
    private Player one;
    private Player two;
    private GamingBoard board;
    private GameAnalyzer analyzer;
    private UserActionHandler userActionHandler;
}
```

As the name suggests, `Rule` is responsible for defining the rules of the board game. These rules are stored in a `.txt` file. Let's explore the structure of the `Rule` enum:

```java
public enum Rule {
    VARIANT("../board/rules/VariantBoard.txt"),
    REGULAR("../board/rules/RegularBoard.txt"),
    REVERSE("../board/rules/ReverseBoard.txt");

    private String url;
    private String content;

    Rule(String url) {
        this.url = url;
        readContent();
    }

    private void readContent() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.url))) {
            int bytesRead;
            char[] buff = new char[8096];
            while ((bytesRead = reader.read(buff)) != -1) {
                sb.append(buff, 0, bytesRead);
            }
            this.content = sb.toString();
        } catch (IOException e) {
            // Handle the exception gracefully
        }
    }

    public String getContent() {
        if (this.content == null)
            readContent();
        return this.content;
    }
}
```

The `Rule` class is responsible for reading the rules from the `.txt` file. This design allows for easier rule management, as modifying the rules in a text file is more convenient than using a series of `System.out.println` statements.

Huh, what is enum?

> _In Java, an enum (short for enumeration) is a special data type used to define a set of named constant values. It allows you to create a group of related constants that can be treated as a data type on their own. Enums provide a convenient way to represent a fixed set of values that are meaningful and limited in scope._

The functions of `GameAnalyzer` and `UserActionHandler` will be introduced in the **Auxiliary Tools** section. Now, let's focus on the core function of the `BoardGameRunner` class: `.play()`. The `.play()` function can be divided into three parts.

## Initialization

```java
public int play() {
    Player winner = null;
    Player currentPlayer = null;
    boolean hasWon = false;
    boolean isFirstPlayerTurn = Math.random() < 0.5;
    // Code omitted
}
```

One intriguing setting here lies in the line `boolean isFirstPlayerTurn = Math.random() < 0.5`. As we learned in WIX1002 (Fundamentals of Programming), `Math.random()` returns a double value between 0 and 1, denoted as the range $[0, 1]$.

<p align="center">
    <img src="image-2.png" alt="Alt Text" style="width:200px;" />
    <br />
    <em>Coin-tossing process</em>
</p>

If the returned value is less than 0.5, it will be the first player's turn; otherwise, it will be the second player's turn. This concept resembles a coin-tossing process, where a player can guess heads or tails. In classical probability, the chances of getting heads or tails are equal.

## Playing the Game

```java
while (!board.isFull()) {
    // Some print statements

    int[] action = { -1, -1, -1 };

    // Let the user make a move
    // Code omitted

    if (action[0] == GameConstant.EXIT) {
        return GameConstant.EXIT;
    }

    int[] move = { action[1], action[2], (int) currentPlayer.getMark() };
    board.recordMove(move);

    sleepForOneSecond();

    int row = move[0];
    int col = move[1];
    hasWon = board.checkForWin(row, col, currentPlayer.getMark());

    if (hasWon) {
        // Handling the situation when someone has won
    }

    isFirstPlayerTurn = !isFirstPlayerTurn;
}
```

To simplify the explanation and focus on the high-level design, we have omitted some code details. The `.play()` method follows a straightforward approach:

1. Let the user make a move.
2. Handle the movement.
3. Handle the situation when someone has won.
4. Switch to the next player's turn.

You may wonder what each variable in the `int[] action` represents:

- `action[0]` indicates the movement code. It can be `GameConstant.EXIT`, indicating that the player no longer wants to play the board game, or `GameConstant.MOVE`, indicating that the player has made a move.

```java
public static final int EXIT = 404;
public static final int MOVE = 200;
```

These constants, defined as `static final` with capitalized variable names, use integer-based codes commonly seen on the Internet.

<p align="center">
    <img src="image-3.png" alt="Alt Text" style="width:300px;" />
    <br />
    <em>404 Error</em>
</p>

- `action[1]` and `action[2]` respectively represent the row and column movements.

The concept of switching players is achieved with `isFirstPlayerTurn = !isFirstPlayerTurn`. The exclamation mark (`!`) denotes negation, meaning that the current boolean statement is reversed. Consider the following example:

```java
boolean isFirstPlayerTurn = true;
System.out.println(isFirstPlayerTurn);
System.out.println(isFirstPlayerTurn = !isFirstPlayerTurn);
```

The output will be:

```bash
true
false
```

The `// Let the user make a move` part will be discussed later in the **Auxiliary Tools** section.

## Handling the Result

The code for handling the result is simple and intuitive. Let's examine it:

```java
if (board.isFull() && !hasWon) {
    System.out.println("It's a tie!");
    userActionHandler.showSaveReplayMenu();
    return GameConstant.DRAW;
} else {
    System.out.println("Congratulations, " + winner + " (" + winner.getMark() + ") has won!\n");
    userActionHandler.showSaveReplayMenu();
    return winner == one ? GameConstant.WIN : GameConstant.LOSE;
}
```

If the game ends in a tie (when the board is full and no one has won), a message is printed, and the user is prompted to save the replay. The function returns `GameConstant.DRAW`. In the case of a winner, a congratulatory message is displayed, followed by the prompt to save the replay. The function then returns either `GameConstant.WIN` or `GameConstant.LOSE`, depending on whether the winner is player one or player two.

With this smooth and engaging design, the board game guarantees an enjoyable experience for all participants.
