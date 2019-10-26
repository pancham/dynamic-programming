package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 *
 *
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
 * and all nodes are as far left as possible. See the following examples.
 *
 * The following trees are examples of Complete Binary Trees
 *     1
 *   /   \
 *  2     3
 *
 *        1
 *     /    \
 *    2       3
 *   /
 *  4
 *
 *        1
 *     /    \
 *    2      3
 *   /  \    /
 *  4    5  6
 * The following trees are examples of Non-Complete Binary Trees
 *     1
 *       \
 *        3
 *
 *        1
 *     /    \
 *    2       3
 *     \     /  \
 *      4   5    6
 *
 *        1
 *     /    \
 *    2      3
 *          /  \
 *         4    5
 *
 *
 * level order traversal post can be easily modified to check whether a tree is Complete or not.
 * To understand the approach, let us first define the term ‘Full Node’. A node is ‘Full Node’ if both left and right
 * children are not empty (or not NULL).
 * The approach is to do a level order traversal starting from the root. In the traversal, once a node is found which
 * is NOT a Full Node, all the following nodes must be leaf nodes.
 * Also, one more thing needs to be checked to handle the below case: If a node has an empty left child, then the
 * right child must be empty.
 */
public class IsCompleteBinaryTree {

    public boolean isComplete(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        boolean foundFirstNonFull = false;
        while(!queue.isEmpty()){
            root = queue.poll();
            if(foundFirstNonFull){
                if(root.left != null || root.right != null){
                    return false;
                }
                continue;
            }
            if(root.left != null && root.right != null){
                queue.offer(root.left);
                queue.offer(root.right);
            }else if(root.left != null){
                queue.offer(root.left);
                foundFirstNonFull = true;
            }else if(root.right != null){  // left null and right is not null
                return false;
            }else{
                foundFirstNonFull = true;
            }
        }
        return true;
    }

    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(3, head);
        head = bt.addNode(-6, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-10, head);
        head = bt.addNode(-15, head);
        head = bt.addNode(-4, head);
        head = bt.addNode(4, head);
        head = bt.addNode(11, head);
        head = bt.addNode(-9, head);

        IsCompleteBinaryTree icbt = new IsCompleteBinaryTree();
        System.out.println(icbt.isComplete(head));
    }
}
