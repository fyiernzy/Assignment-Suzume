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

        Node(int row, int col, Node parent, int cStation) {
            this.row = row;
            this.col = col;
            this.parent = parent;
            this.cStation = cStation;
            this.visited = new HashSet<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            Node other = (Node) obj;
            return row == other.row && col == other.col;
        }
    }

    @Override
    public int countPaths(int numberOfStation) {
        Node src = new Node(0, 0, null, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(src);

        int numberOfPath = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.visited.add(current);

            if (isStation(current.row, current.col)) {
                current.cStation++;
            }

            if (isDestination(current.row, current.col)) {
                if (current.cStation == numberOfStation) {
                    numberOfPath++;
                }
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
            if (isValid(nextRow, nextCol)
                    && (node.parent == null || !(node.parent.row == nextRow && node.parent.col == nextCol))
                    && (!node.visited.stream().anyMatch(n -> n.row == nextRow && n.col == nextCol))) {
                neighbors.add(new Node(nextRow, nextCol, node, node.cStation));
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        
    }
}
