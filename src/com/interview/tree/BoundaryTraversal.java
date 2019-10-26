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
public class BoundaryTraversal {

    public void traversal(Node root){
        //find starting point for right side
        Node current = root;
        while(current != null){
            if(current.right != null && current.left != null){
                current = current.right;
                break;
            }
            current = current.left != null ? current.left : current.right;
        }
        printRightSide(current);
        printLeaves(root);
        printLeftSide(root);
    }

    private void printRightSide(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        System.out.println(root.data);
        if(root.right != null){
            printRightSide(root.right);
        }else{
            printRightSide(root.left);
        }
    }

    private void printLeftSide(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        if(root.left != null){
            printLeftSide(root.left);
        }else{
            printRightSide(root.right);
        }
        System.out.println(root.data);
    }

    private void printLeaves(Node root){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            System.out.println(root.data);
        }
        printLeaves(root.right);
        printLeaves(root.left);
    }

    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-7, head);
        BoundaryTraversal bd = new BoundaryTraversal();
        bd.traversal(head);
    }
}
