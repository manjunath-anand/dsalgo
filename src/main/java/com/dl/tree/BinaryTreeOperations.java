package com.dl.tree;

/**
 * Created by iruve on 2/4/17.
 * http://algs4.cs.princeton.edu/32bst/BST.java.html
 */
public class BinaryTreeOperations<K extends Comparable> {
    public BinarySearchTree<K> binaryTree = new BinarySearchTree();

    /**
     * Returns the largest key in the symbol table less than or equal to node
     * @param key
     * @return
     */
    public K floor(K key) {
        BinarySearchTree.Node<K> nodeAtWhichGoRight = null;
        BinarySearchTree.Node<K> node = binaryTree.root;
        //int cmp = binaryTree.root.key.compareTo(key);
        while(true) {
            int cmp = node.key.compareTo(key);
            if(cmp == 0) {
                return node.key;
            } else if(cmp < 0) {
                node = node.left;
            } else if(cmp > 0) {
                nodeAtWhichGoRight = node;
                node = node.right;
            }
            if(node == null) {
                return (nodeAtWhichGoRight == null) ? null : nodeAtWhichGoRight.key;
            }
        }
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     */
    public K ceiling(K key) {
        return null;
    }

    /* Returns the height of the BST (for debugging).
            *
            * @return the height of the BST (a 1-node tree has height 0)
    */
    public int height() {
        return height(binaryTree.root);
    }
    private int height(BinarySearchTree.Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(binaryTree.root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    // MN:-I think we can do it using BFS 
    private boolean isBST(BinarySearchTree.Node<K> x, K min, K max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

}
