# **1 Map**

Basically, this package ·com.assignment.suzume.map· consists of code that provides a framework for path-finding algorithms on a two-dimensional map represented by a PixelMap. It consists of several classes that work together to find paths on a map and perform related operations.

Here is a breakdown of the classes and their functionalities:

## Sub-packages in map:

### **Images**

This package basically consists of the 4 images provided by the question, which are image_1.png, image_2.png, image_3.png and image_4.png. These images will be used for the following operations.

### **reader**

1. **`MapProcessor`**:
   This class provides methods for combining multiple PixelMaps into a single PixelMap, counting paths in pixel maps and validating their dimensions.

2. **`MapReader`**:
   This class is responsible for reading images and converting them into PixelMaps. It uses the `ImageIO` class to read images and convert them to grayscale.
   For example:

```java
            // Read the image and convert it to a PixelMap
            String imagePath = "map.png";
            MapReader mapReader = new MapReader();
            PixelMap pixelMap = mapReader.readImage(imagePath);
```

### **searching**

1. **`PathNumberFinder`**:
   An abstract class that serves as the base class for path number finding algorithms. It includes a method `countPaths(int numOfStation)` that counts the number of paths from the starting position to the destination position, considering a given number of stations.

2. **`BFSFinder`** and **`DFSFinder`**:
   Concrete implementations of path number finding algorithms using breadth-first search (BFS) and depth-first search (DFS) respectively. They extend the `PathNumberFinder` class.

### **shortest**

1. **`ShortestPathFinder`**:
   An abstract class that serves as the base class for shortest path finding algorithms. It includes a method `findAllShortestPaths()` that finds all the shortest paths from the starting position to the destination position.
   For example:

```java
            // Create a path finder using BFS algorithm
            ShortestPathFinder pathFinder = new BFSFinder(pixelMap);

            // Get the shortest path
            Path shortestPath = pathFinder.getShortestPath();
            System.out.println("Shortest Path:");
            System.out.println(shortestPath);
```

2. **`BFSFinder`** and **`DFSFinder`**:
   Concrete implementations of shortest path finding algorithms using breadth-first search (BFS) and depth-first search (DFS) respectively. They extend the `ShortestPathFinder` class and implement the `findAllShortestPaths()` method based on their respective algorithms.
   For example:

```java
            // Find all the shortest paths
            pathFinder.findAllShortestPaths();

            // Count the number of paths with 2 stations
            int numOfStations = 2;
            PathNumberFinder pathNumberFinder = new DFSFinder(pixelMap);
            int numPaths = pathNumberFinder.countPaths(numOfStations);
            System.out.println("Number of paths with " + numOfStations + " stations: " + numPaths);
```

Other classes:

1. **`PixelMap`**:
   This class represents a two-dimensional array of pixels, which can be used to represent an image or a map. It includes methods for accessing and modifying the pixels.

2. **`AbstractPathFinder`**：
   An abstract class used for implementing path-finding algorithms on a two-dimensional map. It contains common functionality and utility methods used by concrete path-finding implementations.

The code provides a framework that can be used to implement and extend different path-finding algorithms for pixel maps. The `BFSFinder` and `DFSFinder` classes are examples of such algorithms, implementing breadth-first search and depth-first search, respectively, to find paths and count the number of paths in a pixel map. The `MapProcessor` and `MapReader` classes provide utility methods for combining and reading pixel maps.
