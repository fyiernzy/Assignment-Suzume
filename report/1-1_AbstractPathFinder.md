# AbstractPathFinder

The `AbstractPathFinder` class is an abstract base class for path-finding algorithms in a two-dimensional map. It provides common functionality and utility methods used by concrete path-finding implementations.

The class encapsulates methods for checking the validity of locations, identifying obstacles, and determining whether a location is the destination. Concrete path-finding algorithms can extend this class and implement their specific logic for traversing the map and finding optimal paths.

## Key features and functions

The `AbstractPathFinder` class includes the following key features and functions:

- Providing constructors to initialize the map data either directly or through a `PixelMap` object.
- Storing the map data in a two-dimensional integer array.
- Verifying whether a given location is the destination.
- Checking if a location is an obstacle within the map.
- Ensuring that a location falls within the map boundaries.
- Validating whether a location is both within the map boundaries and not an obstacle.
- Computing a unique key for a given location based on its row and column coordinates.

**Note:** This class is abstract and should be extended by concrete path-finding algorithms to provide their specific implementations.

## Constants

The `AbstractPathFinder` class defines the following constant array `DIRECTIONS`, which represents the possible movements in the map (e.g., up, down, left, or right). This array is used by concrete path-finding algorithms to explore neighboring locations.

## Constructors

- `AbstractPathFinder(int[][] map)`: Initializes the `AbstractPathFinder` with the provided map data.
- `AbstractPathFinder(PixelMap pixelMap)`: Initializes the `AbstractPathFinder` with the map data obtained from a `PixelMap` object.

## Methods

- `isDestination(int row, int col)`: Checks if a given location is the destination.
- `isObstacle(int row, int col)`: Checks if a given location is an obstacle.
- `isStation(int row, int col)`: Checks if the given coordinates represent a station on the map.
- `isValidLocation(int row, int col)`: Checks if a given location is valid, i.e., within the map boundaries and not an obstacle.
- `getKey(int row, int col)`: Computes the key of a given location.
