package com.dl.tree;

/**
 * Created by iruve on 2/4/17.
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 * https://upload.wikimedia.org/wikipedia/commons/c/c4/Tree_Rebalancing.gif
 */
public class AVLTree<K extends Comparable> {
    Node<K> root;
    boolean insertResult;
    class Node<K> {
        K key;
        Node<K> left, right;
        int height;
        Node(K key) {
            this.key = key;
            height = 1;
        }
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    public int height(Node n) {
        if(n == null) return 0;
        return n.height;
    }

    public int getBalance(Node n) {
        if(n == null) return 0;
        return height(n.left) - height(n.right);
    }

    public boolean insert(K key) {
        insert(root,key);
        return insertResult;
    }

    public Node<K> insert(Node<K> node, K key) {
        if(node == null) return new Node<K>(key);
        int cmp = key.compareTo(node.key);
        if(cmp<0) {
            node.left = insert(node.left,key);
        } else if(cmp>0) {
            node.right = insert(node.right,key);
        } else {
            insertResult = false;
            return node;
        }
        node.height = 1+ max(height(node.left),height(node.right));
        int bal = getBalance(node);

        // LEFT-LEFT CASE
        if(bal > 1 && key.compareTo(node.left.key) < 0){
            return rotateRight(node);
        }
        // RIGHT-RIGHT CASE
        if(bal < -1 && key.compareTo(node.right.key) > 0) {
            return rotateLeft(node);
        }
        // LEFT-RIGHT CASE
        if(bal > 1 && key.compareTo(node.left.key) > 0){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RIGHT-LEFT CASE
        if(bal < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    Node minValueNode(Node node)
    {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    public Node deleteNode(Node root,K key) {
        if(root == null) return null;
        int cmp = key.compareTo(root.key);
        if(cmp<0) {
            root.left = deleteNode(root.left,key);
        } else if(cmp>0) {
            root.right = deleteNode(root.right,key);
        } else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else   // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            } else {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node<K> temp = minValueNode(root.right);
                // Copy the inorder successor's data to this node
                root.key = temp.key;
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }
        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public Node<K> rotateLeft(Node<K> x) {
        Node y = x.right;
        Node T2 = y.left;
        // Perform rotation
        y.left = x;
        x.right = T2;
        // Update heights
        x.height = max(height(x.left),height(x.right))+1;
        y.height = max(height(y.left),height(y.right))+1;
        // Return new root
        return y;
    }

    public Node<K> rotateRight(Node<K> y) {
        Node x = y.left;
        Node T2 = x.right;
        // Perform rotation
        x.right = y;
        y.left = T2;
        // Update heights
        x.height = max(height(x.left),height(x.right))+1;
        y.height = max(height(y.left),height(y.right))+1;
        // Return new root
        return x;
    }
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
        tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);

        /* The constructed AVL Tree would be
           9
          /  \
         1    10
        /  \    \
        0    5    11
        /    /  \
        -1   2    6
         */
        System.out.println("Preorder traversal of "+
                "constructed tree is : ");
        tree.preOrder(tree.root);

        tree.root = tree.deleteNode(tree.root, 10);

        /* The AVL Tree after deletion of 10
           1
          /  \
         0    9
        /     / \
        -1    5   11
        /  \
        2    6
         */
        System.out.println("");
        System.out.println("Preorder traversal after "+
                "deletion of 10 :");
        tree.preOrder(tree.root);
    }
}
