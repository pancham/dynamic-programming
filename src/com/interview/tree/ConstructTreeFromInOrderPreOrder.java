package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Test cases:
 * Empty tree
 * One node tree
 * All left side tree
 * All right side tree
 * Mixed tree
 * Full tree
 * complete tree
 *
 * Let us consider the below traversals:
 *
 * Inorder sequence: D B E A F C
 * Preorder sequence: A B D E C F
 *
 * Level : A B C D E F
 *
 * In a Preorder sequence, leftmost element is the root of the tree. So we know ‘A’ is root for given sequences.
 * By searching ‘A’ in Inorder sequence, we can find out all elements on left side of ‘A’ are in left subtree
 * and elements on right are in right subtree. So we know below structure now.
 *
 *                  A
 *                /   \
 *              /       \
 *            D B E     F C
 * We recursively follow above steps and get the following tree.
 *
 *          A
 *        /   \
 *      /       \
 *     B         C
 *    / \        /
 *  /     \    /
 * D       E  F
 */
public class ConstructTreeFromInOrderPreOrder {

    private int index = 0;
    public Node createTree(int inorder[],int preorder[]){
        Node result =  createTree(inorder,preorder,0,inorder.length-1);
        index = 0;
        return result;
    }

    private Node createTree(int inorder[],int preorder[], int start, int end) {
        if(start > end){
            return null;
        }
        int i;
        for(i=start; i <= end; i++){
            if(preorder[index] == inorder[i]){
                break;
            }
        }
        Node node = Node.newNode(preorder[index]);
        index++;
        node.left = createTree(inorder,preorder,start,i-1);
        node.right = createTree(inorder,preorder,i+1,end);
        return node;
    }

    private Node createTree(char inorder[],char preorder[], int start, int end) {
        if(start > end){
            return null;
        }
        int i;
        for(i=start; i <= end; i++){
            if(preorder[index] == inorder[i]){
                break;
            }
        }
        Node node = Node.newNode(preorder[index]);
        index++;
        node.left = createTree(inorder,preorder,start,i-1);
        node.right = createTree(inorder,preorder,i+1,end);
        return node;
    }

    /* This funtcion is here just to test buildTree() */
    private void printInorder(Node node)
    {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print((char)node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    // driver program to test above functions
    public static void main(String args[])
    {
        ConstructTreeFromInOrderPreOrder tree = new ConstructTreeFromInOrderPreOrder();
        char in[] = new char[] { 'D', 'B', 'E', 'A', 'F', 'C' };
        char pre[] = new char[] { 'A', 'B', 'D', 'E', 'C', 'F' };
        int len = in.length;
        Node root = tree.createTree(in, pre, 0, len - 1);

        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
        tree.printInorder(root);
    }
}
