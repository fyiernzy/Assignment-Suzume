package PathUtils.Searching;

import java.util.*;

public class BFSFinder extends PathNumberFinder {

    public BFSFinder(int[][] map) {
        super(map);
    }

    static class Node {
        int row;
        int col;
        int cStation; // Cumulative number of station
        Node parent;
        Set<Node> visited;

        Node(int row, int col, Node parent, int cStation, Set<Node> visited) {
            this.row = row;
            this.col = col;
            this.parent = parent;
            this.cStation = cStation;
            this.visited = visited;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            Node other = (Node) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    @Override
    public int countPaths(int numberOfStation) {
        Node src = new Node(0, 0, null, 0, new HashSet<>());
        Queue<Node> queue = new LinkedList<>();
        queue.offer(src);

        int numberOfPath = 0;
        while (!queue.isEmpty()) {
            // System.out.println("Current queue: " + queue);
            Node current = queue.poll();
            // System.out.println("Current position: " + current + ", number of station: " +
            // current.cStation + ", Visited = " + current.visited);
            current.visited.add(current);

            if (isStation(current.row, current.col)) {
                current.cStation++;
                if (current.cStation > numberOfStation) {
                    continue;
                }
            }

            if (isDestination(current.row, current.col) && current.cStation == numberOfStation) {
                numberOfPath++;
                continue;
            }

            for (Node neighbor : getNeighbors(current)) {
                queue.offer(neighbor);
            }
        }

        return numberOfPath;
    }

    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int nextRow = node.row + dir[0];
            int nextCol = node.col + dir[1];
            if (isValid(nextRow, nextCol) && !isVisited(node.visited, nextRow, nextCol)) {
                neighbors.add(new Node(nextRow, nextCol, node, node.cStation, new HashSet<>(node.visited)));
            }
        }
        return neighbors;
    }

    public boolean isVisited(Set<Node> track, int nextRow, int nextCol) {
        return track.stream().anyMatch(node -> node.row == nextRow && node.col == nextCol);
    }
}
