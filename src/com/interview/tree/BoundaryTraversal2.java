package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 * Test cases
 * All left children
 * All right children
 * Full tree
 * Complete tree
 *
 * We break the problem in 3 parts:
 * 1. Print the left boundary in top-down manner.
 * 2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
 * …..2.1 Print all leaf nodes of left sub-tree from left to right.
 * …..2.2 Print all leaf nodes of right subtree from left to right.
 * 3. Print the right boundary in bottom-up manner.
 */
public class BoundaryTraversal2 {
    Node root;

    // A simple function to print leaf nodes of a binary tree
    void printLeaves(Node node)
    {
        if (node == null)
            return;

        printLeaves(node.left);
        // Print it if it is a leaf node
        if (node.left == null && node.right == null)
            System.out.print(node.data + " ");
        printLeaves(node.right);
    }

    // A function to print all left boundary nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    void printBoundaryLeft(Node node)
    {
        if (node == null)
            return;

        if (node.left != null) {
            // to ensure top down order, print the node
            // before calling itself for left subtree
            System.out.print(node.data + " ");
            printBoundaryLeft(node.left);
        }
        else if (node.right != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.right);
        }

        // do nothing if it is a leaf node, this way we avoid
        // duplicates in output
    }

    // A function to print all right boundary nodes, except a leaf node
    // Print the nodes in BOTTOM UP manner
    void printBoundaryRight(Node node)
    {
        if (node == null)
            return;

        if (node.right != null) {
            // to ensure bottom up order, first call for right
            // subtree, then print this node
            printBoundaryRight(node.right);
            System.out.print(node.data + " ");
        }
        else if (node.left != null) {
            printBoundaryRight(node.left);
            System.out.print(node.data + " ");
        }
        // do nothing if it is a leaf node, this way we avoid
        // duplicates in output
    }

    // A function to do boundary traversal of a given binary tree
    void printBoundary(Node node)
    {
        if (node == null)
            return;

        System.out.print(node.data + " ");

        // Print the left boundary in top-down manner.
        printBoundaryLeft(node.left);

        // Print all leaf nodes
        printLeaves(node.left);
        printLeaves(node.right);

        // Print the right boundary in bottom-up manner
        printBoundaryRight(node.right);
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        BoundaryTraversal2 tree = new BoundaryTraversal2();
        tree.root = Node.newNode(20);
        tree.root.left = Node.newNode(8);
        tree.root.left.left = Node.newNode(4);
        tree.root.left.right = Node.newNode(12);
        tree.root.left.right.left = Node.newNode(10);
        tree.root.left.right.right = Node.newNode(14);
        tree.root.right = Node.newNode(22);
        tree.root.right.right = Node.newNode(25);
        tree.printBoundary(tree.root);
    }
}
