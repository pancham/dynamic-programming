package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
 *
 * Let us consider the above example.
 *
 * in[] = {4, 8, 10, 12, 14, 20, 22};
 * level[] = {20, 8, 22, 4, 12, 10, 14};
 *
 * In a Levelorder sequence, the first element is the root of the tree. So we know ’20’ is root for given sequences.
 * By searching ’20’ in Inorder sequence, we can find out all elements on left side of ‘20’ are in left subtree and elements on
 * right are in right subtree. So we know below structure now.
 *
 *              20
 *            /    \
 *           /      \
 *  {4,8,10,12,14}  {22}
 * Let us call {4,8,10,12,14} as left subarray in Inorder traversal and {22} as right subarray in Inorder traversal.
 * In level order traversal, keys of left and right subtrees are not consecutive. So we extract all nodes from level order traversal
 * which are in left subarray of Inorder traversal. To construct the left subtree of root, we recur for the extracted elements
 * from level order traversal and left subarray of inorder traversal. In the above example, we recur for following two arrays.
 *
 * // Recur for following arrays to construct the left subtree
 * In[]    = {4, 8, 10, 12, 14}
 * level[] = {8, 4, 12, 10, 14}
 * Similarly, we recur for following two arrays and construct the right subtree.
 *
 * // Recur for following arrays to construct the right subtree
 * In[]    = {22}
 * level[] = {22}
 */

public class ConstructTreeFromLevelOrderInOrder2
{
    Node root;

    Node buildTree(int in[], int level[])
    {
        Node startnode = null;

        Index lIndex = new Index();
        lIndex.index = 0;

        return constructTree(startnode, level, in, 0, in.length - 1, lIndex);
    }

    Node constructTree(Node startNode, int[] levelOrder, int[] inOrder,
                       int inStart, int inEnd, Index lIndex)
    {

        // if start index is more than end index
        if (inStart > inEnd)
            return null;

        if (inStart == inEnd)
            return Node.newNode(inOrder[inStart]);

        boolean found = false;

        // it represents the index in inOrder array of element that
        // appear first in levelOrder array.
        int index = 0;

        for (int i = lIndex.index; i < levelOrder.length - 1; i++)
        {
            int data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++)
            {
                if (data == inOrder[j])
                {
                    startNode = Node.newNode(data);
                    index = j;
                    found = true;
                    break;
                }
            }
            if (found == true)
                break;
        }

        lIndex.index++;

        //elements present before index are part of left child of startNode.
        //elements present after index are part of right child of startNode.
        startNode.left = (constructTree(startNode, levelOrder, inOrder,
                inStart, index - 1, lIndex));
        startNode.right = (constructTree(startNode, levelOrder, inOrder,
                index + 1, inEnd, lIndex));

        return startNode;
    }

    /* Utility function to print inorder traversal of binary tree */
    void printInorder(Node node)
    {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    // Driver program to test the above functions
    public static void main(String args[])
    {
        ConstructTreeFromLevelOrderInOrder2 tree = new ConstructTreeFromLevelOrderInOrder2();
        int in[] = new int[]{4, 8, 10, 12, 14, 20, 22};
        int level[] = new int[]{20, 8, 22, 4, 12, 10, 14};
        int n = in.length;
        Node node = tree.buildTree(in, level);

        /* Let us test the built tree by printing Inorder traversal */
        System.out.print("Inorder traversal of the constructed tree is ");
        tree.printInorder(node);
    }
}

// This code has been contributed by Mayank Jaiswal
