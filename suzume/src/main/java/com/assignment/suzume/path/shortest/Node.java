package com.assignment.suzume.path.shortest;

public class Node {

    public int row;
    public int col;
    int parent;
    int dist;
    Node left;
    Node right;

    Node(int row, int col, int dist, int parent) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.parent = parent;
    }

    public void addChildNode(Node node) {

        if (left == null) {
            left = node;
        } else {
            if (node.dist < left.dist) {
                this.dist = node.dist + 1;
                left = node;
            } else if (node.dist == left.dist) {
                right = node;
            }
        }
    }

    public String toString() {
        return "[" + row + ", " + col + "]";
    }
}
