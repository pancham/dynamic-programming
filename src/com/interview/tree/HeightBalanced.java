package com.interview.tree;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * Time complexity O(logn)
 *
 * Reference
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
 */

public class HeightBalanced {
    public boolean isBalanced(Node root) {
        return isBalancedUtil(root) >= 0;
    }

    private int isBalancedUtil(Node root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedUtil(root.left);
        if (left < 0) {
            return -1;
        }
        int right = isBalancedUtil(root.right);
        if (right < 0) {
            return -1;
        }
        int diff = Math.abs(left - right);
        return diff <= 1 ? (Math.max(left, right) + 1) : -1;
    }

    boolean isBalanced2(Node root, Height height)
    {
        /* If tree is empty then return true */
        if (root == null) {
            height.height = 0;
            return true;
        }

        /* Get heights of left and right sub trees */
        Height lheight = new Height(), rheight = new Height();
        boolean l = isBalanced2(root.left, lheight);
        boolean r = isBalanced2(root.right, rheight);
        int lh = lheight.height, rh = rheight.height;

        /* Height of current node is max of heights of
           left and right subtrees plus 1*/
        height.height = (lh > rh ? lh : rh) + 1;

        /* If difference between heights of left and right
           subtrees is more than 2 then this node is not balanced
           so return 0 */
        if ((lh - rh >= 2) || (rh - lh >= 2))
            return false;

        /* If this node is balanced and left and right subtrees
           are balanced then return true */
        else
            return l && r;
    }

    public static void main(String[] args) {

        Height height = new Height();

        /* Constructed binary tree is
                   1
                 /   \
                2      3
              /  \    /
            4     5  6
            /
           7         */
        BinaryTree tree = new BinaryTree();
        tree.root = Node.newNode(1);
        tree.root.left = Node.newNode(2);
        tree.root.right = Node.newNode(3);
        tree.root.left.left = Node.newNode(4);
        tree.root.left.right = Node.newNode(5);
        tree.root.right.right = Node.newNode(6);
        tree.root.left.left.left = Node.newNode(7);


        HeightBalanced hb = new HeightBalanced();
        if (hb.isBalanced2(tree.root, height))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");

        /*
               1
              / \
             2   2
            / \
           3   3
          / \
         4   4
        */
        tree = new BinaryTree();
        tree.root = Node.newNode(1);
        tree.root.left = Node.newNode(2);
        tree.root.right = Node.newNode(2);
        tree.root.left.left = Node.newNode(3);
        tree.root.left.right = Node.newNode(3);

        tree.root.left.left.left = Node.newNode(4);
        tree.root.left.left.right = Node.newNode(4);

        if (hb.isBalanced2(tree.root, height))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");

    }
}
