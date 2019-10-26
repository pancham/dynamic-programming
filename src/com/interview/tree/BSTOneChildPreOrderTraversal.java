package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/check-if-each-internal-node-of-a-bst-has-exactly-one-child/
 *
 * Input: pre[] = {20, 10, 11, 13, 12}
 * Output: Yes
 * The give array represents following BST. In the following BST, every internal
 * node has exactly 1 child. Therefor, the output is true.
 *         20
 *        /
 *       10
 *        \
 *         11
 *           \
 *            13
 *            /
 *          12
 *
 * In Preorder traversal, descendants (or Preorder successors) of every node appear after the node.
 * In the above example, 20 is the first node in preorder and all descendants of 20 appear after it.
 * All descendants of 20 are smaller than it. For 10, all descendants are greater than it.
 * In general, we can say, if all internal nodes have only one child in a BST, then all the descendants
 * of every node are either smaller or larger than the node.
 * The reason is simple, since the tree is BST and every node has only one child,
 * all descendants of a node will either be on left side or right side, means all descendants will either be smaller
 * or greater.
 */
public class BSTOneChildPreOrderTraversal {

    public boolean isBST(int input[]){
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < input.length-1; i++){
            if(input[i] > min && input[i] < max){
                if(input[i+1] < input[i]){
                    max = input[i];
                }else{
                    min = input[i];
                }
            }else{
                return false;
            }
        }
        if(input[input.length-1] < max && input[input.length-1] > min){
            return true;
        }else{
            return false;
        }
    }

    boolean hasOnlyOneChild(int pre[]) {
        int size = pre.length;
        int nextDiff, lastDiff;

        for (int i = 0; i < size - 1; i++) {
            nextDiff = pre[i] - pre[i + 1];
            lastDiff = pre[i] - pre[size - 1];
            if (nextDiff * lastDiff < 0) {
                return false;
            };
        }
        return true;
    }

    public static void main(String args[]){
        int input[] = {20,10,14,15,17};
        BSTOneChildPreOrderTraversal boc = new BSTOneChildPreOrderTraversal();
        System.out.println(boc.isBST(input));
    }
}
