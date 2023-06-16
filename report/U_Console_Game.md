# Console Game

The Console Game section revolves around the integration of the map into the gameplay. In this game, our protagonist Suzume embarks on an adventure across the entire map, playing TicTacToe whenever she encounters a station. This seamless gaming experience is made possible through three key classes: `ConsoleGameSetup`, `ConsoleGameStatus`, and `ConsoleGame`.

## ConsoleGameSetup

As stated in the question requirements, the player is given the freedom to choose the game mode, board game type, and engine difficulty (depending on the game mode) before starting the console game. The `ConsoleGameSetup` class takes care of storing these settings and can return a `BoardGameRunner` object based on those configurations. This saves a significant amount of time and avoids writing redundant code to initialize the `BoardGameRunner` object.

Let's delve into the instance variables of a `ConsoleGameSetup` object:

```java
private Player p1;
private Player p2;
private Rule rule;
private int modeChoice;
private int boardChoice;
```

These instance variables clearly indicate the type of information that will be stored. So, how is `ConsoleGameSetup` configured? Let's examine its constructor and the `initializeConfiguration` method.

```java
ConsoleGameSetup() {
    initializeConfiguration();
}

void initializeConfiguration() {
    chooseGameMode();

    switch (modeChoice) {
        case 1 -> {
            p1 = new Gamer(User.getInstance().getName(), P1_MARK);
            p2 = chooseGamer(P2_MARK);
        }
        case 2 -> {
            p1 = new Gamer(User.getInstance().getName(), P1_MARK);
            p2 = chooseEngineDifficulty(P2_MARK);
        }
        case 3 -> {
            p1 = chooseEngineDifficulty(P1_MARK);
            p2 = chooseEngineDifficulty(P2_MARK);
        }
    }

    chooseBoardType();
    rule = GameConstant.RULES[boardChoice - 1];
}
```

By analyzing the `initializeConfiguration` method, we can understand the straightforward process of configuration:

1. Allow the user to choose the game mode.
2. Set the players based on the user's choices.
3. Let the user choose the board type.
4. Automatically set the corresponding rule.

The related methods such as `chooseGameMode()`, `chooseEngineDifficulty()`, and `chooseBoardType()` utilize the `InputHandler.getIntInput()` static method to obtain user choices. To explore the specific code implementation, you can refer to the source code in our GitHub repository.

Now, let's examine how `ConsoleGameSetup` automatically generates the `BoardGameRunner` that we need:

```java
BoardGameRunner getBoardGameRunner() {
    GamingBoard board = createGamingBoard(boardChoice);
    GameAnalyzer monitor = createGameMonitor(boardChoice, board);
    return new BoardGameRunner(modeChoice, p1, p2, rule, board, monitor);
}
```

From this code snippet, we can observe that:

- `ConsoleGameSetup` incorporates its own logic, such as `createGamingBoard`, to obtain the appropriate board based on the `boardChoice` instance variable.
- `ConsoleGameSetup` incorporates its own logic, such as `createGameMonitor`, to obtain the correct game monitor (in this case, the `GameAnalyzer`).

<p align="center">
    <img src="image-8.png" alt="Alt Text" style="width:400px;" />
    <br />
    <em>Suzume savors her meticulously crafted meal.</em>
</p>

Through the initialization and creation process, all the necessary components required to construct a `BoardGameRunner` are prepared and ready. The `getBoardGameRunner` method acts as a skilled chef, combining the prepared ingredients to serve up a scrumptious meal: the `BoardGameRunner`.

Stay tuned as we continue exploring the fascinating details of this console game!

## ConsoleGameStatus

The `ConsoleGameStatus` class serves as a record keeper for the current state of the console game, as well as the historical status of the stations visited. Let's take a look at its instance variables:

```java
public class ConsoleGameStatus {
    private int currentTile;
    private int currentRow;
    private int currentCol;
    private int currentStep;
    private char[][] currentMap;
    private boolean isContinueJourney;
    private Stack<int[]> stationHistory; // format: [row, col, step]
    private Stack<char[][]> mapHistory;
}
```

Some of the instance variables are self-explanatory. Now, let's dive into the `stationHistory` variable, which is the heart of the `ConsoleGameStatus` class. As specified in the assignment:

> *In short, the basic requirement for this part is to at least create a console interaction, make Suzume stop at respective stations and play the station games. If she wins, she can proceed to the next station; otherwise, she has to fall back to the previous station. If she fails at the very first station, her journey ends. Likewise, if she succeeds at all the stations, her journey ends, but with glory.*

To implement a similar functionality, a stack structure is used. The following methods are built around the `stationHistory`:

```java
public boolean backToPreviousStation() {
    if (stationHistory.isEmpty()) {
        return false;
    }
    int[] lastStation = stationHistory.pop();
    currentRow = lastStation[0];
    currentCol = lastStation[1];
    currentStep = lastStation[2];
    currentMap = mapHistory.pop();
    return true;
}

public void saveCurrentStation() {
    stationHistory.push(new int[] { currentRow, currentCol, currentStep });
    mapHistory.push(MapUtils.getClonedMap(currentMap));
}
```

If Suzume, our protagonist, loses at a station, the `backToPreviousStation` method is called to set the status of the `ConsoleGame` to the most recently visited station. It returns a boolean value. If there are no more stations to return to, it will return `false`, indicating that Suzume has lost the game. Otherwise, it will return `true`.

The `.saveCurrentStation()` and `.updateGamePosition()` methods simply perform what their names suggest—they save the current station's status and update the game position.

## ConsoleGame

In the realm of **Suzume Adventure**, the epic gameplay unfolds within the grand structure of the `ConsoleGame` class. It holds the key to orchestrating the game's flow and defining its captivating journey. Let's explore how this class handles the game's progression:

```java
public void play() {
    // Initialization and printing unimportant messages

    while (status.getCurrentStep() < totalStep && status.isContinueJourney()) {
        handleNextStep(steps);
        if (status.isCurrentPositionDestination()) {
            System.out.println("Congratulations! You have reached the end!");
            break;
        }
    }
}
```

As you can see, the game flow is elegantly simple and straightforward. It revolves around a stopping condition: if the total number of steps taken equals the required steps to reach the final destination. As long as this condition remains unfulfilled, the `ConsoleGame` proceeds to the next step, gracefully advancing the gameplay. If the next step happens to be the final destination, the game joyfully concludes, celebrating the player's triumph. Remarkably simple, isn't it?

<p align="center">
    <img src="image-7.png" alt="Alt Text" style="width:400px;" />
    <br />
    <em>"Emm ya, I think in the same way too.."</em>
</p>

Now, let's delve into the enigmatic `handleNextStep()` method and unravel the magic concealed within.

```java
private void handleNextStep(List<String> steps) {
    status.updatePosition(steps.get(status.getCurrentStep()));
    MapUtils.printGrid(status.getCurrentMap());
    sleepFor(500, TimeUnit.MILLISECONDS);

    if (status.isCurrentPositionStation()) {
        handleStation();
    }
}
```

This piece of code continues the trend of simplicity and clarity. However, the true intrigue lies within the `handleStation()` method.

```java
private void handleStation() {
    // Print some informative messages

    int result = getBoardGameRunner().play();

    if (result == GameConstant.EXIT) {
        System.out.println("Suzume has chosen to exit the game.");
        status.setContinueJourney(false);
        return;
    }

    if (result == GameConstant.LOSE) {
        handleBoardGameLoss();
    } else {
        handleBoardGameWin();
    }

    if (setup.getModeChoice() != GameConstant.EVE) {
        user.updateResult(result);
        dbManager.updateUserGameStatus(user);
    }

    status.setContinueJourney(true);
}
```

As you can see, the formidable `BoardGameRunner`, as we discussed earlier, graciously returns a valuable message code, carrying significant implications. For instance, the code `GameConstant.EXIT` signifies that the player has decided to exit the board game. Consequently, we respond to this code by taking relevant and appropriate actions. We also handle the player's victories (`handleBoardGameWin()`) and defeats (`handleBoardGameLoss()`) with finesse and resilience.

<p align="center">
    <img src="image-6.png" alt="Alt Text" style="width:400px;" />
    <br />
    <em>Suzume faces defeat, but she remains resilient, refusing to shed a tear.</em>
</p>

```java
private void handleBoardGameLoss() {
    System.out.println("Alas! Suzume has been defeated in the station game.");        

    boolean isBackToPreviousStation = status.backToPreviousStation();
    if (!isBackToPreviousStation) {
        System.out.println(
                "Oh no! Suzume has failed at the very first station. Her journey comes to an unfortunate end.");
        status.setContinueJourney(false);
    } else {
        System.out.println("But fear not! Suzume bravely falls back to the previous station.");
    }
}

private void handleBoardGameWin() {
    System.out.println("Hooray! Suzume emerges victorious in the station game.");
    status.saveCurrentStation();
}
```

These meticulously crafted methods smoothly handle the player's victories and defeats. They are designed with simplicity and clarity, precisely fulfilling the requirements of the question:

> *If Suzume wins, she can proceed to the next station, but if she fails, she bravely falls back to the previous station. However, if she is unfortunate enough to fail at the very first station, her remarkable journey comes to an end.*

The elegance of the code lies in its straightforwardness and adherence to the game's rules. It captures the essence of Suzume's adventure, where triumph and setback intertwine in a thrilling quest.

The precise implementation details can be found in our illustrious GitHub repository. Feel free to check it out!
