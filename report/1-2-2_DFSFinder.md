# **1.2.2 DFSFinder**

## Introduction

As previously discussed in the BFSFinder, the DFSFinder shares many similarities in terms of constraints and variables. These include the usage of a `BitSet` for tracking purposes and a `List<int[]>` to record the path. However, DFSFinder employs the concept of recursion or method stack to carry out its implementation. Unlike BFS, which explores all neighbors and possibilities simultaneously, DFSFinder traverses a single path until reaching the end, checking if it meets the requirements. If not, it switches to another path accordingly.

## Source code explanation

Let's delve into the code to understand its functionality:

```java
public List<List<String>> findAllShortestPaths(int numOfStationRequired) {
    pathFinderHelper(0, 0, 0, numOfStationRequired, new ArrayList<>(), new BitSet());
    return convertListVectorToName(shortestPaths);
}

private void pathFinderHelper(int row, int col, int numOfStationVisited, int numOfStationRequired,
        List<int[]> currentPath, BitSet visited) {
    if (!isValidLocation(row, col) || visited.get(getKey(row, col))) {
        return;
    }

    if (isStation(row, col)) {
        numOfStationVisited++;
    }

    if (numOfStationVisited > numOfStationRequired) {
        return;
    }

    if (currentPath.size() > shortestDistance) {
        return;
    }

    currentPath.add(new int[] { row, col });

    if (isDestination(row, col)) {
        // Implement the logic when the node is the final destination
    }

    visited.set(getKey(row, col));
    for (int[] dir : DIRECTIONS) {
        List<int[]> tmp = new ArrayList<>(currentPath);
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        pathFinderHelper(newRow, newCol, numOfStationVisited,
                numOfStationRequired, tmp, visited);
    }
    visited.flip(getKey(row, col));
}
```

As you can observe from the code, the constraints and the code for finding neighbors remain the same:

```java
if (numOfStationVisited > numOfStationRequired) {
    return;
}

if (currentPath.size() > shortestDistance) {
    return;
}

// Omitted code

visited.set(getKey(row, col));
for (int[] dir : DIRECTIONS) {
    List<int[]> tmp = new ArrayList<>(currentPath);
    int newRow = row + dir[0];
    int newCol = col + dir[1];
    pathFinderHelper(newRow, newCol, numOfStationVisited,
            numOfStationRequired, tmp, visited);
}
visited.flip(getKey(row, col));
```

The only difference lies in the additional line `List<int[]> tmp = new ArrayList<>(currentPath);` and `visited.flip(getKey(row, col));`. This is necessary because both `List` and `BitSet` are object references, which means that changes made to them directly impact the original objects. Therefore, to avoid these effects, there are two possible approaches:

1. Create a copy of the entire array.
2. Undo the step.

The first approach should be applied to the `List` since using `removeLast()` directly would result in a `ConcurrentModificationException`. On the other hand, the second approach is suitable for the `BitSet` using the `.flip()` method.
