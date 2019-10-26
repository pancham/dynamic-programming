package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 */
public class BinaryTreeToDoubleLinkList {

    public void toDoubleLL(Node root){
        NodeRef prev = new NodeRef();
        toDoubleLL(root,prev);
    }

    private void toDoubleLL(Node root, NodeRef prev){
        if(root == null){
            return;
        }
        toDoubleLL(root.left,prev);
        if(prev.node != null){
            prev.node.right = root;
            root.left = prev.node;

        }
        prev.node = root;
        toDoubleLL(root.right,prev);
    }

    // Initialize previously visited node as NULL. This is
    // static so that the same value is accessible in all recursive
    // calls
//    static Node prev = null;
    private void toDoubleLL2(Node root, Node prev){
        // Base case
        if (root == null)
            return;

        // Recursively convert left subtree
        toDoubleLL2(root.left, prev);

        // Now convert this node
        if (prev != null)
        {
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        // Finally convert right subtree
        toDoubleLL2(root.right, prev);
    }

    public void print(Node root){
        Node curr = null;
        while(root != null){
            curr = root;
            System.out.print(root.data + " ");
            root = root.right;
        }
        System.out.println();
        root = curr;
        while(root != null){
            System.out.print(root.data + " ");
            root = root.left;
        }
    }

    public static void main(String args[]){
        BinaryTreeToDoubleLinkList btd = new BinaryTreeToDoubleLinkList();
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
//        head = bt.addNode(7, head);
//        head = bt.addNode(-7, head);
        btd.toDoubleLL(head);
        btd.print(head);


        System.out.println();
        bt = new BinaryTree();
        head = null;
        head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
//        head = bt.addNode(7, head);
//        head = bt.addNode(-7, head);
        btd.toDoubleLL2(head, null);
        btd.print(head);
    }

    private static BinaryTree populateTree() {
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

        return bt;
    }
}
