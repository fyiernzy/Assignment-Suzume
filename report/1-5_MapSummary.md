# **1.5 Map Summary**

In summary, both `PathNumberFinder` and `ShortestPathFinder` employ similar concepts to achieve their respective purposes, while their implementation details may differ.

The pseudocode can be expressed as follows:

1. Determine the current node and check if it is the final destination. If it is the final destination, check if the number of stations meets the specified requirements. If yes, take appropriate action; otherwise, continue to the next node.

   - In `ShortestPathFinder`, traverse all nodes and record the full path.
   - In `PathNumberFinder`, increment the `numberOfPaths` by 1.

2. If the current node is not the final destination, find its neighboring nodes and take appropriate action.

   - For BFS, find the neighbor nodes and add them to the queue. Traverse each neighbor node and perform necessary actions.
   - For DFS, try different combinations of neighbor nodes, prioritizing reaching the destination first before exploring other combinations. Perform necessary actions accordingly.

3. Implement necessary actions. If the current node is a station, increment the `numOfStations` by 1 and pass the updated value to the next node/neighbor nodes. Ignore invalid or visited nodes to prevent infinite loops or `IndexOutOfBoundsException`. Implement additional measures to ensure smooth and correct code execution.

4. Finally, incorporate optimization techniques. For example, if the current node has more `numOfStations` than required, proceed to the next node directly. Similarly, if the current destination node doesn't meet the specified criteria, such as having too few or too many `numOfStations`, ignore it.

## Pseudocode

### `searching.DFSFinder.java`

As of required by the question, I've put the pseudocode for each of the implementation here:

1. Start with an empty grid of size 2x2 and initialize the number of visited locations, number of stations, and number of paths as 0.

2. Call the `countPaths` method, passing the desired number of stations as an argument.

3. Inside the `countPaths` method, create a 2D array called "visited" to keep track of visited locations.

4. Invoke the `countPathsHelper` method, passing the "visited" array, number of stations, starting row and column (initially 0), and the initial number of stations as 0.

5. In the `countPathsHelper` method:

   - Check if the current location is invalid or already visited. If so, return 0 (indicating no path).
   - If the current location is a station, increment the number of stations and check if it exceeds the desired number of stations. If so, return 0 (indicating no path).
   - Check if the current location is the destination and the desired number of stations have been reached. If so, return 1 (indicating a valid path).
   - Mark the current location as visited by setting `visited[row][col]` to 1.
   - Initialize a variable `count` to keep track of the number of paths found.
   - Iterate over the possible directions (up, down, left, right):
     - Recursively invoke `countPathsHelper` for each neighboring location, passing the updated row and column coordinates, the current number of stations, and the updated "visited" array.
     - Add the returned count from each recursive call to the `count` variable.
   - Mark the current location as unvisited by setting `visited[row][col]` back to 0.
   - Return the final count, representing the total number of paths found.

6. The `countPaths` method will return the final count of paths.

By following this workflow and providing the desired number of stations, you can use the DFS algorithm to find all possible paths in a 2x2 grid.

### `searching.BFSFinder.java`

1. Start with an empty grid of size 2x2.

2. Create a queue called `pixelQueue` and a queue called `visitedQueue` to store the pixels and their corresponding visited states.

3. Create a BitSet called `startVisited` and set the bit at the starting position (0, 0) in the grid.

4. Enqueue the starting pixel (0, 0, 0) into `pixelQueue` with the number of stations as 0.

5. Enqueue the `startVisited` BitSet into `visitedQueue`.

6. Initialize the `numberOfPath` variable to 0, which will keep track of the total number of paths.

7. Perform a BFS traversal using the following steps until `pixelQueue` is empty:

   - Dequeue the current pixel from `pixelQueue` and the corresponding visited BitSet from `visitedQueue`.
   - Extract the row, column, and current station from the current pixel.
   - Get the neighboring pixels that are valid and not visited using the `getNeighbors` function.
   - For each neighboring pixel:
     - Extract the new row and column.
     - If the neighboring pixel is a station and the current station count is less than the desired number of stations:
       - Increment the station count by 1.
     - If the neighboring pixel is the destination and the current station count matches the desired number of stations:
       - Increment the `numberOfPath` variable by 1.
     - Enqueue the neighboring pixel into `pixelQueue` with the updated station count.
     - Create a clone of the visited BitSet and set the bit corresponding to the neighboring pixel.
     - Enqueue the cloned visited BitSet into `visitedQueue`.

8. Return the final value of `numberOfPath`, which represents the total number of paths found.

### `shortest.BFSFinder.java`

1. Create an empty list called `shortestPaths` to store the shortest paths found.
2. Create an empty queue called `queue` to perform the BFS traversal.
3. Create a BitSet called `visited` and set the bit at the starting position (0, 0) in the grid.
4. Enqueue a new `Node` into the `queue` representing the starting position with initial distance, number of stations, visited BitSet, and parent as null.
5. Initialize `shortestDistance` to be the maximum possible value.

6. Perform a BFS traversal using the following steps until the `queue` is empty:

   - Dequeue the current `Node` from the `queue`.
   - If the current `Node`'s distance is greater than the `shortestDistance`, continue to the next iteration.
   - If the current `Node`'s number of stations is greater than the desired number of stations, continue to the next iteration.
   - If the current `Node` is at the destination:

     - If the current `Node`'s number of stations matches the desired number of stations:
       - If the current `Node`'s distance is less than the `shortestDistance`:
         - Reset `shortestPaths` to null (helps with memory usage).
         - Create a new empty `shortestPaths` list.
         - Update `shortestDistance` to the current `Node`'s distance.
       - Create a new list called `path` to store the path.
       - Traverse the parent nodes from the current `Node` to the starting position:
         - Add the coordinates of each node to the `path` list.
       - Reverse the order of the `path` list.
       - Add the `path` list to the `shortestPaths` list.
     - Continue to the next iteration.

   - For each valid neighboring position:
     - Calculate the new row and column coordinates.
     - Calculate the key for the new position.
     - If the new position is valid and not visited:
       - Create a new BitSet called `newVisited` as a clone of the current `Node`'s visited BitSet.
       - Set the bit corresponding to the new position in the `newVisited` BitSet.
       - Enqueue a new `Node` into the `queue` with updated distance, number of stations, visited BitSet, and current `Node` as the parent.

7. Convert the list of vector coordinates in `shortestPaths` to a list of string paths (if needed) using the `convertListVectorToName` function.

8. Return the `shortestPaths` list of all possible paths found.

### `shortest.DFSFinder.java`

1. Create an empty list called `shortestPaths` to store the shortest paths found.
2. Create an empty queue called `queue` to perform the BFS traversal.
3. Create a BitSet called `visited` and set the bit at the starting position (0, 0) in the grid.
4. Enqueue a new `Node` into the `queue` representing the starting position with initial distance, number of stations, visited BitSet, and parent as null.
5. Initialize `shortestDistance` to be the maximum possible value.

6. Perform a BFS traversal using the following steps until the `queue` is empty:

   - Dequeue the current `Node` from the `queue`.
   - If the current `Node`'s distance is greater than the `shortestDistance`, continue to the next iteration.
   - If the current `Node`'s number of stations is greater than the desired number of stations, continue to the next iteration.
   - If the current `Node` is at the destination:

     - If the current `Node`'s number of stations matches the desired number of stations:
       - If the current `Node`'s distance is less than the `shortestDistance`:
         - Reset `shortestPaths` to null (helps with memory usage).
         - Create a new empty `shortestPaths` list.
         - Update `shortestDistance` to the current `Node`'s distance.
       - Create a new list called `path` to store the path.
       - Traverse the parent nodes from the current `Node` to the starting position:
         - Add the coordinates of each node to the `path` list.
       - Reverse the order of the `path` list.
       - Add the `path` list to the `shortestPaths` list.
     - Continue to the next iteration.

   - For each valid neighboring position:
     - Calculate the new row and column coordinates.
     - Calculate the key for the new position.
     - If the new position is valid and not visited:
       - Create a new BitSet called `newVisited` as a clone of the current `Node`'s visited BitSet.
       - Set the bit corresponding to the new position in the `newVisited` BitSet.
       - Enqueue a new `Node` into the `queue` with updated distance, number of stations, visited BitSet, and current `Node` as the parent.

7. Convert the list of vector coordinates in `shortestPaths` to a list of string paths (if needed) using the `convertListVectorToName` function.

8. Return the `shortestPaths` list of all possible paths found.
