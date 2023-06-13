# 2.1.1 BFSFinder

## Introduction

BFSFinder, as the name suggests, utilizes the Breadth-First Search (BFS) algorithm to find the shortest path. The core concept is to explore neighboring nodes by adding them to a queue, ensuring that the path satisfies certain conditions such as passing through exactly four stations. Let's consider a node at coordinates (0, 0) as an example.

To determine the neighbors of this node, we can define the four directions: down, up, right, and left. These directions can be represented as a set of pairs: {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}. However, since the nodes at coordinates (-1, 0) and (0, -1) fall outside the boundaries of a 2x2 grid, they are not considered as neighbors of the node at (0, 0).

```java
Node node = new Node(0,0)
int[] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left
```

```bash
All neighbors
------------------------
Neighbor_1 : Node(1, 0)
Neighbor_2 : Node(-1, 0)
Neighbor_3 : Node(0, 1)
Neighbor_4 : Node(0, -1)

Valid neighbors
------------------------
Neighbor_1 : Node(1, 0)
Neighbor_3 : Node(0, 1)
```

Hence, only two nodes will be added to the queue as valid neighbors. By continuously finding the neighbors of each current node and traversing the grid, the final destination can be reached. However, several potential issues need to be addressed:

1. **No backtrack**

    To prevent indefinite loops, we need to ensure that a node does not backtrack to a previous node. Otherwise, it could consume excessive memory and result in an `OutOfMemory` error.

2. **Find the shortest paths rather than exploring all possible paths**

    This requires evaluating the distance traveled and selecting the paths with the minimum distance.

3. **To trace back the parent node and obtain the complete path from the source point to the destination.**

    We need to store the parent node information for each visited node during the traversal.

4. **The algorithm should eliminate paths that do not pass through exactly four stations.**

    This can be achieved by keeping track of the number of stations visited and discarding paths that do not meet this requirement.

By addressing these challenges and implementing the necessary logic, the BFSFinder algorithm can effectively find the shortest path from the source to the destination, considering the constraints of a 2x2 grid.

## `Node` class

For the easier management, we have introduced a new class named Node which stores the following information, as defined in the `BFSFinder.java`:

```java
static class Node {
        int row;
        int col;
        int distance;
        int numOfStation;
        BitSet visited;
        Node parent;

        Node(int row, int col, int distance, int numOfStation, BitSet visited, Node parent) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.numOfStation = numOfStation;
            this.visited = visited;
            this.parent = parent;
        }
    }
```

## `BitSet`

To prevent backtracking and record the path taken, we need to implement a mechanism that efficiently stores the visited nodes. Using a `List` data structure seems intuitive, but the `indexOf` operation in a list has a high time complexity, which becomes impractical when dealing with a large number of nodes. Therefore, we need to consider a data structure with a constant time complexity of O(1).

Among the options, `Set` and `Map` are suitable choices, but since we only need to store simple information, a `Map` is unnecessary. Hence, we opt for a `Set` data structure.

However, using a `HashSet` consumes a significant amount of space, and its mechanism to check for object containment also takes some time. To address these concerns, we choose to utilize a `BitSet`, which operates at the bit level. Bit manipulation typically requires less time and space, which is crucial when dealing with a large number of nodes.

`BitSet` is a class in Java that represents a fixed-size collection of bits. Each bit can have two states: 0 or 1. The `BitSet` class provides various methods to manipulate and access these bits efficiently.

1. `set(int index)` method:
   - The `set` method is used to set the bit at a specified index to 1.
   - Here's an example:

     ```java
     BitSet bitSet = new BitSet(8); // Create a BitSet with 8 bits
     
     // Set the bit at index 2 to 1
     bitSet.set(2);
     ```

     After executing this code, the `bitSet` will have the bit at index 2 set to 1, and all other bits will remain unchanged.

2. `get(int index)` method:
   - The `get` method is used to retrieve the value of the bit at a specified index.
   - It returns `true` if the bit is set to 1, and `false` if the bit is set to 0.
   - Here's an example:

     ```java
     BitSet bitSet = new BitSet(8); // Create a BitSet with 8 bits
     
     // Set the bit at index 3 to 1
     bitSet.set(3);
     
     // Get the value of the bit at index 3
     boolean value = bitSet.get(3);
     System.out.println(value); // Output: true
     ```

     In this example, we set the bit at index 3 to 1 and then retrieve its value using the `get` method. The output will be `true` because the bit is set to 1.

Both the `set` and `get` operations in `BitSet` work efficiently with constant time complexity, making them suitable for scenarios where bit-level manipulation is required.
