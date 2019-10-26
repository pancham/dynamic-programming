package com.interview.tree;

/**
 * Date 11/01/2015
 * @author Tushar Roy
 *
 * Given an array reprsentation of binary tree where index is data while value at index is
 * parent create the binary tree. Value of -1 indicates root node.
 *
 * References:
 * http://www.geeksforgeeks.org/construct-a-binary-tree-from-parent-array-representation/
 *
 * Input: parent[] = {1, 5, 5, 2, 2, -1, 3}
 * Output: root of below tree
 *           5
 *         /  \
 *        1    2
 *       /    / \
 *      0    3   4
 *          /
 *         6
 * Explanation:
 * Index of -1 is 5.  So 5 is root.
 * 5 is present at indexes 1 and 2.  So 1 and 2 are
 * children of 5.
 * 1 is present at index 0, so 0 is child of 1.
 * 2 is present at indexes 3 and 4.  So 3 and 4 are
 * children of 2.
 * 3 is present at index 6, so 6 is child of 3.
 *
 *
 * Input: parent[] = {-1, 0, 0, 1, 1, 3, 5};
 * Output: root of below tree
 *          0
 *        /   \
 *       1     2
 *      / \
 *     3   4
 *    /
 *   5
 *  /
 * 6
 */
public class BinaryTreeFromParentRepresentation {

    public Node create(int input[]) {
        Node t[] = new Node[input.length];

        for(int i = 0; i < input.length; i++) {
            t[i] = new Node();
            t[i].data = i;
        }

        Node root = null;
        for(int i = 0; i < input.length; i++) {
            int parentIndex = input[i];
            if(parentIndex == -1) {
                root = t[i];
                continue;
            }
            Node parent = t[parentIndex];
            if(parent.left == null) {
                parent.left = t[i];
            } else {
                parent.right = t[i];
            }
        }
        return root;
     }

     public static void main(String args[]) {
         BinaryTreeFromParentRepresentation btpp = new BinaryTreeFromParentRepresentation();
         int input[] =  {1, 5, 5, 2, 2, -1, 3};
         Node root = btpp.create(input);
         TreeTraversals tt = new TreeTraversals();
         tt.inOrder(root);
         System.out.println();
         tt.preOrder(root);
     }
}

