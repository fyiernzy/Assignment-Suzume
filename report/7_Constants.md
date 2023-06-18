# **7. Constants**

This file package contains 2 items, one of it is a normal Java class, and another one of it is an enum Java class. Both of these files are used to store constants that are repeatedly accessed

<br>

## **GameConstant Java Class**

The given Java class, **GameConstant**, is used to define various constants related to a game, specifically a tic-tac-toe game. These constants provide predefined values for different aspects of the game.

<br>

```java
public class GameConstant {
    public static final int WIN = 1;
    public static final int LOSE = -1;
    public static final int DRAW = 0;

    public static final int PVP = 1;
    public static final int PVE = 2;
    public static final int EVE = 3;

    public static final Rule[] RULES = { Rule.REGULAR, Rule.REVERSE, Rule.VARIANT};

    // Status code
    public static final int EXIT = 404;
    public static final int MOVE = 200;
}
```

**Game Result Constants :**

**WIN** : Represents the constant value 1, indicating a win in the game.

**LOSE** : Represents the constant value -1, indicating a loss in the game.

**DRAW** : Represents the constant value 0, indicating a draw or tie in the game.

**Game Mode Constants :**

**PVP** : Represents the constant value 1, indicating a player versus player mode.

**PVE** : Represents the constant value 2, indicating a player versus engine mode.

**EVE** : Represents the constant value 3, indicating an environment versus engine mode.

**Game Rules Constants :**

**RULES** : Represents an array of Rule objects, where Rule is a custom class representing different rules of the tic-tac-toe game. The array contains Rule.REGULAR, Rule.REVERSE, and Rule.VARIANT, indicating different rule sets for the game.

**Status Code Constants :**

**EXIT** : Represents the constant value 404, indicating an exit status code.

**MOVE** : Represents the constant value 200, indicating a move status code.

<br>

## **MapConstant Java Class**

This Java enum class, **MapConst**, is used to represent constants related to a map in a specific application or scenario. Each constant in the enum represents a different type of element that can exist in the map.

```java
public enum MapConst {
    OBSTACLE(1),
    STATION(2),
    DESTINATION(3);

    public final int VALUE;

    MapConst(int val) {
        this.VALUE = val;
    }

    public boolean is(int val) {
        return this.VALUE == val;
    }
}
```

**Enum Constants :**

OBSTACLE: Represents an obstacle element in the map, typically denoted by the constant value 1.

STATION: Represents a station element in the map, typically denoted by the constant value 2.

DESTINATION: Represents a destination element in the map, typically denoted by the constant value 3.

**Value Field :**

Each enum constant (OBSTACLE, STATION, DESTINATION) has a corresponding VALUE field, which stores the constant value associated with that particular element.

**Constructor :**

The enum class has a constructor that accepts an integer value (val) as a parameter. This constructor is used to initialize the VALUE field of each enum constant with the provided value.

**is() Method :**

The enum class includes an is() method that compares the provided integer value (val) with the VALUE field of the enum constant. If the values match, it returns true; otherwise, it returns false. This method can be used to check if a given value corresponds to a specific enum constant.
