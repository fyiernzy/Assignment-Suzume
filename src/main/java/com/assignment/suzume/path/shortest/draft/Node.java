package com.assignment.suzume.path.shortest.draft;

public class Node {

    public int row;
    public int col;
    int dist;
    Node left;
    Node right;
    int leftParent;
    int rightParent;

    public Node(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public void addParent(int key) {
        if(leftParent == -1) {
            leftParent = key;
        } else {
            rightParent = key;
        }
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

    public int[] getPosition() {
        return new int[] { row, col };
    }

    public String toString() {
        return "[" + row + ", " + col + "]";
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public Node getLeftChild() {
        return this.left;
    }

    public Node getRightChild() {
        return this.right;
    }
}
