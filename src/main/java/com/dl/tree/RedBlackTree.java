package com.dl.tree;

import java.awt.*;

/**
 * Created by iruve on 5/4/17.
 * http://cs.lmu.edu/~ray/notes/redblacktrees/
 * For delete pls refer to snapshots present in
 * /home/iruve/workspace/DSAlgo/
 */
public class RedBlackTree<K extends Comparable> {

    public Node<K> root;
    public boolean addReturn = true;
    public boolean delReturn = true;

    public static class Node<K> {
        public Node<K> left;
        public Node<K> right;
        public Node<K> parent;
        public K key;
        public Color color;
        public Node(K key) {
            this.key = key;
            this.color = Color.RED;
        }
    }

    public boolean add(K key) {
        addReturn = true;
        if(root == null) {
            root = new Node<K>(key);
        } else {
            Node<K> current = root;
            while(true) {
                int cmp = key.compareTo(current.key);
                if(cmp < 0) {
                    current = current.left;
                    if(current == null) {
                        current.left = new Node<K>(key);
                    }
                } else if (cmp > 0) {
                    current = current.right;
                    if(current == null) {
                        current.right = new Node<K>(key);
                    }
                } else {
                    addReturn = false;
                    break;
                }
            }
        }
        return addReturn;
    }

    /**
     * Classic algorithm for fixing up a tree after inserting a node.
     */
    private void adjustAfterInsertion(Node n) {
        // Step 1: color the node red
        setColor(n, Color.red);

        // Step 2: Correct double red problems, if they exist
        if (n != null && n != root && isRed(parentOf(n))) {

            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.black);
                setColor(siblingOf(parentOf(n)), Color.black);
                setColor(grandparentOf(n), Color.red);
                adjustAfterInsertion(grandparentOf(n));
            }

            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateRight(grandparentOf(n));
            }

            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateLeft(grandparentOf(n));
            }
        }

        // Step 3: Color the root black
        setColor((Node) root, Color.black);
    }

    /**
     * Removes the node containing the given value. Does nothing if there is no
     * such node.
     */
    public void remove(K data) {
        Node node = (Node) nodeContaining(data);
        if (node == null) {
            // No such object, do nothing.
            return;
        } else if (node.left != null && node.right != null) {
            // Node has two children, Copy predecessor data in.
            Node predecessor = predecessor(node);
            node.key = predecessor.key;
            node = (Node) predecessor;
        }
        // At this point node has zero or one child
        Node pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
        if (pullUp != null) {
            // Splice out node, and adjust if pullUp is a double black.
            if (node == root) {
                root = pullUp;
            } else if (node.parent.left == node) {
                node.parent.left = pullUp;
            } else {
                node.parent.right = (pullUp);
            }
            if (isBlack(node)) {
                adjustAfterRemoval(pullUp);
            }
        } else if (node == root) {
            // Nothing to pull up when deleting a root means we emptied the tree
            root = null;
        } else {
            // The node being deleted acts as a double black sentinel
            if (isBlack(node)) {
                adjustAfterRemoval(node);
            }
            removeFromParent(node);
        }
    }

    /**
     * Removes this node, and all its descendants, from whatever
     * tree it is in.  Does nothing if this node is a root.
     */
    public void removeFromParent(Node node) {
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else if (node.parent.right == node) {
                node.parent.right = null;
            }
            node.parent = null;
        }
    }

    /**
     * A special helper method that returns the node containing
     * an object that compares equal to the given object.  This
     * is used in both contains and remove.
     */
    protected Node<K> nodeContaining(K data) {
        for (Node<K> n = root; n != null;) {
            int comparisonResult = data.compareTo(n.key);
            if (comparisonResult == 0) {
                return n;
            } else if (comparisonResult < 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        return null;
    }

    /**
     * Returns the rightmost node in the left subtree.
     */
    protected Node<K> predecessor(Node<K> node) {
        Node<K> n = node.left;
        if (n != null) {
            while (n.right != null) {
                n = n.right;
            }
        }
        return n;
    }
    /**
     * Classic algorithm for fixing up a tree after removing a node; the
     * parameter to this method is the node that was pulled up to where the
     * removed node was.
     */
    private void adjustAfterRemoval(Node n) {
        while (n != root && isBlack(n)) {
            if (n == leftOf(parentOf(n))) {
                // Pulled up node is a left child
                Node sibling = rightOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, Color.black);
                    setColor(parentOf(n), Color.red);
                    rotateLeft(parentOf(n));
                    sibling = rightOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, Color.red);
                    n = parentOf(n);
                } else {
                    if (isBlack(rightOf(sibling))) {
                        setColor(leftOf(sibling), Color.black);
                        setColor(sibling, Color.red);
                        rotateRight(sibling);
                        sibling = rightOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), Color.black);
                    setColor(rightOf(sibling), Color.black);
                    rotateLeft(parentOf(n));
                    n = (Node) root;
                }
            } else {
                // pulled up node is a right child
                Node sibling = leftOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, Color.black);
                    setColor(parentOf(n), Color.red);
                    rotateRight(parentOf(n));
                    sibling = leftOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, Color.red);
                    n = parentOf(n);
                } else {
                    if (isBlack(leftOf(sibling))) {
                        setColor(rightOf(sibling), Color.black);
                        setColor(sibling, Color.red);
                        rotateLeft(sibling);
                        sibling = leftOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), Color.black);
                    setColor(leftOf(sibling), Color.black);
                    rotateRight(parentOf(n));
                    n = (Node) root;
                }
            }
        }
        setColor(n, Color.black);
    }

    public void rotateLeft(Node node) {
        if(node == null || node.right == null) return;
        Node oldRight = node.right;
        node.right = oldRight.left;
        if(node.parent == null) {
            root = oldRight;
        } else if (node.parent.left == node) {
            node.parent.left = oldRight;
        } else {
            node.parent.right = oldRight;
        }
        oldRight.left = node;
    }

    public void rotateRight(Node node) {
        if(node == null || node.left == null) return;
        Node oldLeft = node.left;
        node.left = oldLeft.right;
        if(node.parent == null) {
            root = oldLeft;
        } else if (node.parent.right == node) {
            node.parent.right = oldLeft;
        } else {
            node.parent.left = oldLeft;
        }
        oldLeft.right = node;
    }

    public Node<K> grandParent(Node node) {
        return (node.parent != null) ? node.parent.parent : null;
    }

    private Color colorOf(Node n) {
        return n == null ? Color.black : n.color;
    }

    private boolean isRed(Node n) {
        return n != null && colorOf(n) == Color.red;
    }

    private boolean isBlack(Node n) {
        return n == null || colorOf(n) == Color.black;
    }

    private void setColor(Node n, Color c) {
        if (n != null)
            n.color = c;
    }

    private Node parentOf(Node n) {
        return n == null ? null : (Node) n.parent;
    }

    private Node grandparentOf(Node n) {
        return (n == null || n.parent == null) ? null : (Node) n
                .parent.parent;
    }

    private Node siblingOf(Node n) {
        return (n == null || n.parent == null) ? null : (n == n
                .parent.left) ? (Node) n.parent.right
                : (Node) n.parent.left;
    }

    private Node leftOf(Node n) {
        return n == null ? null : (Node) n.left;
    }

    private Node rightOf(Node n) {
        return n == null ? null : (Node) n.right;
    }
}
