package com.dl.tree;

/**
 * Created by iruve on 31/3/17.
 */
public class BinarySearchTree<K extends Comparable> {
    public Node<K> root;
    public boolean addReturn = true;
    public boolean removeReturn = true;
    public static class Node<K extends Comparable> {
        Node<K> left;
        Node<K> right;
        public K key;
        public Node(K key, Node<K> left, Node<K> right) {
            this.key = key;
        }
    }
    public boolean insert(K key) {
        insertToTreeNode(root,key);
        return addReturn;
    }
    // recursive
    public Node<K> insertToTreeNode(Node<K> node, K key) {
        if(root == null) {
            root = new Node<K>(key, null, null);
            return root;
        } else if (node == null) {
            return new Node<K>(key, null, null);
        } else if (node.key.equals(key)) {
            addReturn = false;
        } else if (node.key.compareTo(key) < 0) {
            node.left = insertToTreeNode(node.left, key);
        } else {
            node.right = insertToTreeNode(node.right, key);
        }
        return node;
    }
    // non recursive
    public void insertToTreeNodeNonRec(Node<K> node, K key) {
        if(root == null) {
            root = new Node<K>(key, null, null);
            return;
        }
        Node parent = node;
        Node current = parent;
        while (true) {
            parent = current;
            if(current.key.equals(key)) {
                break;
            } else if(current.key.compareTo(key) < 0) {
                current = current.left;
                if(current == null) {
                    parent.left = new Node<K>(key, null, null);
                    break;
                }
            } else {
                current = current.right;
                if(current == null) {
                    parent.right = new Node<K>(key, null, null);
                    break;
                }
            }
        }
    }

    public boolean removeRec(K key) {
        removeFromTreeNodeRec(root, key);
        return removeReturn;
    }

    public boolean remove(K key) {
        removeFromTreeNode(root, key);
        return removeReturn;
    }

    public Node<K> removeFromTreeNodeRec(Node<K> node, K key) {
        if(node == null) {
            removeReturn = false;
        } else if(node.key.compareTo(key) == 0) {
            if(node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Node being deleted has 2 children, replace the data with preorder successor.
                Node<K> successor = findSuccessor(node);
                return successor;
            }
        } else if (node.key.compareTo(key) < 0) {
            node.left = removeFromTreeNodeRec(node.left, key);
        } else {
            node.right = removeFromTreeNodeRec(node.right, key);
        }
        return node;
    }

    public Node<K> removeFromTreeNode(Node<K> node, K key) {
        Node<K> currentNode = node;
        Node<K> currentNodeParent = currentNode;
        while(true) {
            if(currentNode == null) {
                removeReturn = false;
                break;
            } else if (currentNode.key.compareTo(key) < 0) {
                currentNodeParent = currentNode;
                currentNode = currentNode.left;
            } else if (currentNode.key.compareTo(key) > 0) {
                currentNodeParent = currentNode;
                currentNode = currentNode.right;
            } else {
                if(currentNode == root
                        && currentNode.left == null
                        && currentNode.right == null) {
                    root = null;
                }
                if(currentNode.left == null) {
                    currentNodeParent.left =  currentNode.right;
                    if(currentNode == root) {
                        root = currentNode.right;
                    }
                } else if (currentNode.right == null) {
                    currentNodeParent.right =  currentNode.left;
                    if(currentNode == root) {
                        root = currentNode.left;
                    }
                } else {
                    // Node being deleted has 2 children, replace the data with preorder successor.
                    Node<K> successor = findSuccessor(currentNode);
                    if(currentNode == root) {
                        root = successor;
                    } else if(currentNodeParent.key.compareTo(successor.key) < 0) {
                        currentNodeParent.left = successor;
                    } else {
                        currentNodeParent.right = successor;
                    }
                }
                break;
            }
        }
        return node;
    }

    public Node<K> findSuccessor(Node delNode) {
        Node<K> successor = delNode.right;
        Node<K> successorParent = successor;
        while(successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }
        if(successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
            successor.left = delNode.left;
        }
        return successor;
    }
}
