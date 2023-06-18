# **10. Utils**

Utils, short for "Utilities," is a package named `com.assignment.suzume.utils` that encompasses various helpful classes with methods designed to simplify tasks in our project.

## InputHandler

One of the most extensively used classes within our project is the `InputHandler`. As the name suggests, the `InputHandler` class handles all aspects related to user input, offering a convenient and robust solution. The class adheres to the principle of "Open for Extensibility, Closed for Modification," allowing for easy customization without altering its core functionality.

The `InputHandler` class effectively manages the following scenarios:

1. **Managing non-numeric inputs**

   The class includes a method called `isNumeric`, which determines whether a given input is numeric. By using the `Integer.parseInt()` method and catching a `NumberFormatException`, it can identify non-numeric inputs and return `false`. This method proves useful in subsequent conditional handling.

   ```Java
   public static boolean isNumeric(String val) {
     try {
        Integer.parseInt(val);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
   }
   ```

2. **Handling extra white spaces**

   The `InputHandler` employs the powerful combination of the `.trim()` method available to `String` objects and regular expressions (Regex) to handle white spaces. By utilizing `.trim()` on the input, leading and trailing white spaces are removed. Then, the input is split using the regular expression pattern `"\\s+"`, which matches any number of consecutive white spaces. This ensures that regardless of the amount of white space entered, the input is correctly split.

   ```java
   String[] splitInput = input.trim().split("\\s+");
   ```

Here are some examples:

```bash
# Normal condition
Enter your input: 2 3
You have entered: 2 3

# Excessive white spaces in between two inputs
Enter your input: 2                 3
You have entered: 2 3

# Excessive white spaces at the beginning and end of the inputs
Enter your input:            2 3
You have entered: 2 3
```

By employing these techniques, the `InputHandler` class ensures that user input is appropriately processed, regardless of accidental additional white spaces or non-numeric characters. This enhances the reliability and usability of the input functionality within our project.

Please note that the given code snippet demonstrates the `isNumeric` method and the usage of `.trim()` and the regular expression `"\\s+"` for splitting inputs.

The following text has been revised to improve grammar, vocabulary, clarity, and readability:

## KeyProcessor

In the assignment distribution, there is a mention of decrypting the number 17355, which appears as 1x2x2(?), in order to verify the correctness of the number of possible paths on the complete map. However, for the basic requirement (1 mark), we have a separate file that contains the necessary information to decrypt the number 17355.

Interestingly, we don't actually use the decrypted answer from the key processor to verify the correctness of our solution. Instead, we used our own answer, which we obtained by considering all possible paths, resulting in a value of 17282. This value served as inspiration for solving the question.

To gain insight from the decrypted answer, let's convert the decimal numbers into binary:

```php
17355 -> 100001111001011
17282 -> 100001110000010
```

For better readability and to meet the question's requirements, let's break down the binary numbers into blocks of three digits, starting from the back while maintaining their original order:

```php
17355 -> 100 001 111 001 011
17282 -> 100 001 110 000 010
```

What difference can we observe between these two decimal numbers? The answer lies in the fact that the last three blocks of binary numbers have been reduced by 1. This is an important clue. Now, let's examine the encryption process.

Let's revisit the requirements mentioned in the question:

```md
4. For each block:

   - Add the value of (secret key modulo 2) to the binary value of the block. For example, if the binary value is 101, it should be converted to its actual value, which is 5. Then, the value of (secret key modulo 2) is added to it, resulting in 6.

   - Convert the sum back to binary format, using blocks of three digits. For example, if the value is 2, the binary format would be 10. In this case, it needs to be padded to become 010.

   - Shift the bits of the secret key to the right by a positive integer below 10.
```

Let's examine the result of converting 7 to a binary number, applying modulus, and shifting its bits:

```java
int secretKey = 7;

for (int i = 0; i < 5; i++) {
    System.out.println("Dec value: " + secretKey);
    System.out.println("Bin value: " + Integer.toBinaryString(secretKey));
    System.out.println("Mod value: " + (secretKey % 2));

    secretKey = (secretKey >> 1) % 10;
}
```

The output will be as follows:

```bash
# Iteration 1
Dec value: 7
Bin value: 111
Mod value: 1

# Iteration 2
Dec value: 3
Bin value: 011
Mod value: 1

# Iteration 3
Dec value: 1
Bin value: 001
Mod value: 1

# Iteration 4
Dec value: 0
Bin value: 000
Mod value: 0

# Iteration 5
Dec value: 0
Bin value: 000
Mod value: 0
```

As we can observe, only three 1s are generated from this algorithm. Since the blocks are processed from the back, only the last three blocks are incremented by 1 in the encryption process. Therefore, to reverse the process, we simply need to subtract 1 from those three blocks using

the same concept.

```java
private int process(int val, int secretKey, boolean isEncryption) {
    // Omitted code ...

    while (!blocks.isEmpty()) {
        String block = blocks.poll();
        int flag = secretKey % 2;
        int blockValue = Integer.parseInt(block, 2) + ((isEncryption) ? 1 : -1) * flag;
        processedBlocks.push(pad(blockValue));
        secretKey >>= 1;
    }
    // Omitted code ...
}
```

To solve this question, we introduced the use of two data structures: a `Stack` and a `Queue`.

```java
Queue<String> blocks = new LinkedList<>();
Stack<String> processedBlocks = new Stack<>();
```

As the names suggest, the `Queue` stores the blocks obtained from the binary string, while the `Stack` stores the processed blocks after encryption or decryption. The use of these data structures is suitable for this scenario. The `Queue` processes the blocks in a "First In First Out" manner from the back, and the `Stack` treats the processed blocks as "First In, Last Out," maintaining their original order.

## Password Encoder

The `PasswordEncoder` class is designed to encode passwords for secure storage in a database, ensuring sensitive information remains protected. Storing passwords in plain text is highly discouraged due to the inherent security risks involved.

Let's delve into the code of the `PasswordEncoder` class:

```java
public class PasswordEncoder {

    private static final String HASH_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to obtain " + HASH_ALGORITHM + " MessageDigest instance", e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
```

The algorithm employed by this class can be broken down into two distinct steps:

1. Hashing a string into bytes:

   To hash the password, the `hashPassword` method utilizes the widely respected SHA-256 algorithm. Let's examine the relevant code:

   ```java
   MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
   byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
   ```

2. Converting bytes into hexadecimal (hex) strings:

   The `bytesToHex` method takes the byte array obtained from the hashing process and converts it into a hexadecimal string representation. Here's the code responsible for this conversion:

   ```java
   StringBuilder hexString = new StringBuilder(hash.length * 2);
   for (byte b : hash) {
    String hex = String.format("%02x", b);
    hexString.append(hex);
   }
   return hexString.toString();
   ```

Now, let's explore the bytes generated by the algorithm:

The output:

```bash
Bytes = 94   Hex = 5e
Bytes = -120  Hex = 88
Bytes = 72   Hex = 48
Bytes = -104  Hex = 98
Bytes = -38   Hex = da
Bytes = 40   Hex = 28
Bytes = 4    Hex = 04
Bytes = 113  Hex = 71
Bytes = 81   Hex = 51
Bytes = -48   Hex = d0
Bytes = -27   Hex = e5
Bytes = 111  Hex = 6f
Bytes = -115  Hex = 8d
Bytes = -58   Hex = c6
Bytes = 41   Hex = 29
Bytes = 39   Hex = 27
Bytes = 115  Hex = 73
Bytes = 96   Hex = 60
Bytes = 61   Hex = 3d
Bytes = 13   Hex = 0d
Bytes = 106  Hex = 6a
Bytes = -85   Hex = ab
Bytes = -67   Hex = bd
Bytes = -42   Hex = d6
Bytes = 42 Hex = 2a
Bytes = 17 Hex = 11
Bytes = -17 Hex = ef
Bytes = 114 Hex = 72
Bytes = 29 Hex = 1d
Bytes = 21 Hex = 15
Bytes = 66 Hex = 42
Bytes = -40 Hex = d8
5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8
```

Finally, we can get a well-encrypted password.

## MapUtils

MapUtils is a class that provides useful functions related to `PixelMap`, `int[][]`, or `char[][]` for convenient project design. One of the key methods in MapUtils is `getCombinedMap()`, which retrieves the combined map obtained from four images through the `MapReader` (which will be introduced later).

As software developers, we are well aware that a significant portion of our time is dedicated to debugging rather than coding. In fact, it is estimated that approximately 80% of our time is spent on debugging and troubleshooting issues in our code. The `getCombinedMap()` method plays a crucial role in this process, as it provides us with the combined map that is essential for developing both the shortest path algorithm and the possible path algorithm.

Here's an example of how it is used:

```java
public static PixelMap getCombinedMap() {
    if (combinedMap == null) {
        List<PixelMap> list = getMapList();

        MapProcessor processor = new MapProcessor();
        combinedMap = processor.combinePixelMaps(NUM_OF_PATH, list);

        combinedMap.obstacles(map -> {
            final int numRow = map.size()[0] / 2;
            final int numCol = map.size()[1] / 2;
            int[][] grid = map.getPixelMap();

            for (int i = 0; i < 3; i++) {
                int targetRow = (i / 2 + 1) * numRow - 1;
                int targetCol = (i % 2 + 1) * numCol - 1;
                grid[targetRow][targetCol] = 1;
            }
            return map;
        });
    }

    return combinedMap;
}
```

The `getCombinedMap()` method employs lazy initialization, meaning it returns the `private static PixelMap combinedMap` if it has already been initialized. This avoids repeated initialization and improves overall time complexity. If the combinedMap hasn't been initialized yet, it is initialized using the following code:

```java
combinedMap.obstacles(map -> {
    final int numRow = map.size()[0] / 2;
    final int numCol = map.size()[1] / 2;
    int[][] grid = map.getPixelMap();

    for (int i = 0; i < 3; i++) {
        int targetRow = (i / 2 + 1) * numRow - 1;
        int targetCol = (i % 2 + 1) * numCol - 1;
        grid[targetRow][targetCol] = 1;
    }
    return map;
});
```

The `obstacles()` method is responsible for converting the bottom-right cell from the destination to an obstacle (i.e., changing 3 to 1). Although the formula might seem complicated, let's simplify it:

Let's assume $\text{numRow} = 40$ and $\text{numCol} = 20$.

For $i = 0$:

- $\text{targetRow} = \left(\frac{0}{2} + 1\right) \times \text{numRow} - 1 = 1 \times 40 - 1 = 39$

- $\text{targetCol} = \left(0 \bmod 2 + 1\right) \times \text{numCol} - 1 = 1 \times 20 - 1 = 19$

For $i = 1$:

- $\text{targetRow} = \left(\frac{1}{2} + 1\right) \times \text{numRow} - 1 = 1 \times 40 - 1 = 39$

- $\text{targetCol} = \left(1 \bmod 2 + 1\right) \times \text{numCol} - 1 = 2 \times 20 - 1 = 39$

For $i = 2$:

- $\text{targetRow} = \left(\frac{2}{2} + 1\right) \times \text{numRow} - 1 = 2 \times 40 - 1 = 79$

- $\text{targetCol} = \left(2 \bmod 2 + 1\right) \times \text{numCol} - 1 = 1 \times 20 - 1 = 19$

Hence, we can simplify the formula as follows:

```java
combinedMap.obstacles(map -> {
    int[][] grid = map.getPixelMap();
    grid[39][19] = 1;
    grid[39][39] = 1;
    grid[79][19] = 1;
    return map;
});
```

To avoid unintended side effects, MapUtils provides the `getClonedMap()` method. In Java, arrays or high-dimensional matrices, including 2x2 grids, are considered reference objects. This means that changes made to them are reflected in their internal data, even when accessed from different places. To prevent this, we need to clone the matrix. The following code demonstrates how it is done:

```java
public static char[][] getClonedMap(char[][] map) {
    int numOfRow = map.length;
    int numOfCol = map[0].length;

    char[][] cloned = new char[numOfRow][numOfCol];
    for (int i = 0; i < numOfRow; i++) {
        System.arraycopy(map[i], 0, cloned[i], 0, numOfCol);
    }
    return cloned;
}
```

Here, `System.arraycopy` is used for cloning because it is faster than the `Array.copyOf` method. Another option, `Object.clone()`, was considered but not used due to potential errors.

The `transformMap()` method converts a 2D integer array map into a corresponding character array grid for visualization purposes. Here's an example of the conversion:

```bash
matrix[0][0] = 0;
grid[0][0]= ' ';

matrix[3][3] = 1;
grid[3][3] = '|';

matrix[15][0] = 2;
grid[1][0] = '@';

matrix[39][19] = 3;
grid[39][19] = 'X';
```

In code, it can be implemented as follows:

```java
public static char[][] transformMap(int[][] map) {
    int numOfRow = map.length;
    int numOfCol = map[0].length;
    char[][] grid = new char[numOfRow][numOfCol];

    for (int row = 0; row < numOfRow; row++) {
        for (int col = 0; col < numOfCol; col++) {
            switch (map[row][col]) {
                case 0 -> grid[row][col] = ' ';
                case 1 -> grid[row][col] = '|';
                case 2 -> grid[row][col] = '@';
                case 3 -> grid[row][col] = 'X';
            }
        }
    }
    return grid;
}
```

Finally, MapUtils provides various `printGrid` methods for displaying grids. Here's an example:

```java
public static void printGrid(char[][] grid, int formatSize) {
    int numRows = grid.length;
    int numCols = grid[0].length;
    String format = "%" + formatSize + "c";

    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            System.out.printf(format, grid[row][col]);
        }
        System.out.println();
    }

    System.out.println("\n\n");
}
```

These methods print grids with or without formatting, providing flexibility for different use cases. The `formatSize` parameter specifies the width of each element in the grid. If not specified, a default value is used.

## PathUtils

PathUtils, similar to MapUtils, provides essential methods for handling path-related tasks. Two commonly used methods are:

1. Obtain one shortest path that passes through exactly 4 stations.

   Since there are 6 possible paths, the method randomly selects one using the `Math.random()` function.

   ```java
   public static List<String> getOneShortestPath() {
       BFSFinder finder = new BFSFinder(MapUtils.getCombinedMap   ());
       List<List<String>> paths = finder.findAllShortestPaths();
       return paths.get((int) (Math.random() * paths.size()));
   }
   ```

2. Convert the vector to its corresponding name.

   The usage of this method has been discussed in the Shortest Path Finder. Let's examine the source code.

   ```java
   public static List<String> convertVectorToName(List<int[]> positions) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < positions.size() - 1; i++) {
            int[] current = positions.get(i);
            int[] next = positions.get(i + 1);
            result.add(getDirectionName(next[0] - current[0], next[1] - current[1]));
    }
       return result;
   }
   ```

Essentially, the `convertVectorToName` method determines the difference between two positions by calculating the difference in rows and columns. For example, consider the following scenario:

```java
int[] previousPosition = {15, 4};
int[] currentPosition = {14, 4};
```

In this case, the difference in rows and columns, stored in an integer array, would be:

```java
int[] vector = {-1, 0};
```

This indicates that Suzume has moved one step upward from the previous position. But how can we map the vector to the corresponding direction, such as "Up" and "Down"? Therefore, we have introduced a dedicated method to take the vector and convert it into its respective direction name.

```java
public static String getDirectionName(int row, int col) {
    if (row == 1 && col == 0) {
        return "Down";
    } else if (row == -1 && col == 0) {
        return "Up";
    } else if (row == 0 && col == 1) {
        return "Right";
    } else if (row == 0 && col == -1) {
        return "Left";
    } else {
        throw new IllegalArgumentException("Invalid direction");
    }
}
```

## ConsolePrinter

The `ConsolePrinter` class is designed to provide utility methods for printing content in a console-based application. It offers various methods to print decorators, rules, banners, and welcome messages. Let's delve into each method and explore their functionalities.

### 1. `printDecorator()`

The `printDecorator()` method is responsible for printing the decorator defined in the `Configuration` class. It uses `System.out.printf()` to display the decorator on the console.

Code snippet:

```java
ConsolePrinter.printDecorator();
```

Output:

```bash
>>>
```

### 2. `printRule(Rule rule)`

The `printRule()` method takes a `Rule` object as a parameter and prints it with a decorative rule line above and below the content. It uses the `String.repeat()` method to create a line of equal signs (`=`) and displays the content of the rule.

Code snippet:

```java
Rule rule = new Rule("path/to/rule");
ConsolePrinter.printRule(rule);
```

Output:

```bash
==================================================
This is a rule example
==================================================
```

### 3. `printBanner()`

The `printBanner()` method prints the banner, which is loaded from a file specified in the `Configuration.BANNER_URL` variable. It first checks if the banner content has been loaded before, and if not, it retrieves the content from the file. The banner is then displayed on the console.

Code snippet:

```java
ConsolePrinter.printBanner();
```

Output:

```bash
[Contents of the banner file]
```

### 4. `printWelcomeMessage()`

The `printWelcomeMessage()` method prints a formatted welcome message. It begins by checking if the welcome message has been loaded previously. If not, it reads the content line by line from the file specified in `Configuration.WELCOME_MESSAGE_URL`. The welcome message is then centered within a box formed by hyphens (`-`) and vertical bars (`|`) based on the size of the last line in the banner. Finally, the formatted welcome message is displayed on the console.

Code snippet:

```java
ConsolePrinter.printWelcomeMessage();
```

Output:

```bash
------------------------
|                      |
|   Welcome to the     |
|   Console Printer!   |
|                      |
------------------------
```

### 5. `getContentFromFile(String url)`

The `getContentFromFile()` method reads the content of a file specified by the `url` parameter and returns it as a string. It reads the file line by line using a `BufferedReader` and appends each line to a `StringBuilder`. The resulting content is then returned as a string.

Code snippet:

```java
String content = ConsolePrinter.getContentFromFile("file.txt");
System.out.println(content);
```

Output:

```bash
[Contents of the file.txt]
```

By utilizing the methods provided by the `ConsolePrinter` class, developers can easily print decorators, rules, banners, and welcome messages in their console-based applications. These methods simplify the printing process and allow for a more visually appealing user interface.
