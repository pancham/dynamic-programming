package com.interview.tree;

/**
 *
 * https://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 *
 * A Full Binary Tree is a binary tree where every node has either 0 or 2 children
 *
 *   1
 *       /   \
 *     2       3
 *   /  \     /  \
 *  4    5   6    7
 *
 *
 *        1
 *      /   \
 *    2      3
 *         /   \
 *        4     5
 *            /   \
 *           6    7
 *
 *
 *           1
 *         /   \
 *       2       3
 *     /  \     /  \
 *    4    5   6    7
 *  /  \
 * 8    9
 *
 * It is not possible to construct a general Binary Tree from preorder and postorder traversals (See this).
 * But if know that the Binary Tree is Full, we can construct the tree without ambiguity.
 * Let us understand this with the help of following example.
 *
 * Let us consider the two given arrays as pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7} and post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
 * In pre[], the leftmost element is root of tree. Since the tree is full and array size is more than 1.
 * The value next to 1 in pre[], must be left child of root. So we know 1 is root and 2 is left child.
 * How to find the all nodes in left subtree? We know 2 is root of all nodes in left subtree.
 * All nodes before 2 in post[] must be in left subtree. Now we know 1 is root, elements {8, 9, 4, 5, 2} are in left subtree,
 * and the elements {6, 7, 3} are in right subtree.
 *
 *
 *                   1
 *                 /   \
 *                /      \
 *      {8, 9, 4, 5, 2}     {6, 7, 3}
 * We recursively follow the above approach and get the final tree.
 */
public class ConstructFullTreeFromPreOrderPostOrder2 {

    // variable to hold index in pre[] array
    static int preindex;

    static class node
    {
        int data;
        node left, right;

        public node(int data)
        {
            this.data = data;
        }
    }

    // A recursive function to construct Full
    // from pre[] and post[]. preIndex is used
    // to keep track of index in pre[]. l is
    // low index and h is high index for the
    // current subarray in post[]
    static node constructTreeUtil(int pre[], int post[], int l,
                                  int h, int size)
    {

        // Base case
        if (preindex >= size || l > h)
            return null;

        // The first node in preorder traversal is
        // root. So take the node at preIndex from
        // preorder and make it root, and increment
        // preIndex
        node root = new node(pre[preindex]);
        preindex++;

        // If the current subarry has only one
        // element, no need to recur or
        // preIndex > size after incrementing
        if (l == h || preindex >= size)
            return root;
        int i;

        // Search the next element of pre[] in post[]
        for (i = l; i <= h; i++)
        {
            if (post[i] == pre[preindex])
                break;
        }
        // Use the index of element found in
        // postorder to divide postorder array
        // in two parts. Left subtree and right subtree
        if (i <= h)
        {
            root.left = constructTreeUtil(pre, post, l, i, size);
            root.right = constructTreeUtil(pre, post, i + 1, h, size);
        }
        return root;
    }

    // The main function to construct Full
    // Binary Tree from given preorder and
    // postorder traversals. This function
    // mainly uses constructTreeUtil()
    static node constructTree(int pre[], int post[], int size)
    {
        preindex = 0;
        return constructTreeUtil(pre, post, 0, size - 1, size);
    }

    static void printInorder(node root)
    {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args)
    {

        int pre[] = { 1, 2, 4, 8, 9, 5, 3, 6, 7 };
        int post[] = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };

        int size = pre.length;
        node root = constructTree(pre, post, size);

        System.out.println("Inorder traversal of the constructed tree:");
        printInorder(root);
    }
}
