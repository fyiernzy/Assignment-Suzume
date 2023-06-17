# 2.1.2 Path Number Finder

In this article, we will discuss the `PathNumberFinder` class, along with its implementations `BFSFinder` and `DFSFinder`, as they share a similar concept with the `ShortestPathFinder`. In fact, `PathNumberFinder` can be considered as a subset of the solution provided by `ShortestPathFinder`, as it implements a similar concept.

## BFSFinder

Let's examine the `BFSFinder.java` implementation:

```java
// Initialization ...
int numberOfPaths = 0;

while (!pixelQueue.isEmpty()) {
    int[] current = pixelQueue.poll();
    BitSet visited = visitedQueue.poll();

    int row = current[0];
    int col = current[1];
    int cStation = current[2];

    for (int[] neighbor : getNeighbors(row, col, cStation, visited)) {
        int newRow = neighbor[0];
        int newCol = neighbor[1];

        if (isStation(newRow, newCol)) {
            // Increment numberOfStations
        }

        if (isDestination(newRow, newCol)) {
            // Increment numberOfPaths
        }
            
        pixelQueue.offer(neighbor);
        BitSet newVisited = (BitSet) visited.clone();
        newVisited.set(getKey(newRow, newCol));
        visitedQueue.offer(newVisited);
    }
}
```

I have omitted some implementation details from the source code, as they are specific to the implementation and not essential to the core concept. Additionally, the optimization code has been excluded here to focus on the essence of the algorithm. As you can see, the code is simplified as it only requires the `numberOfPaths` variable to keep track of the total possible paths.

## DFSFinder

The `DFSFinder.java` implementation follows a similar approach:

```java
private int countPathsHelper(int[][] visited, int numOfStations, int row, int col, int stations) {
    if (!isValidLocation(row, col) || isVisited(visited, row, col)) {
        return 0;
    }

    if (isStation(row, col)) {
        // Increment stations
    }

    if (stations == numOfStations && isDestination(row, col)) {
        return 1;
    }

    visited[row][col] = 1;
    int count = 0;
    for(int[] step : DIRECTIONS) {
        count += countPathsHelper(visited, numOfStations, row + step[0], col + step[1], stations);
    } 
    visited[row][col] = 0;
    return count;
}
```
