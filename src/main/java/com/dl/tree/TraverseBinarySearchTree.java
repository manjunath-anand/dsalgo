package com.dl.tree;

import java.util.Stack;

/**
 * Created by iruve on 1/4/17.
 */
public class TraverseBinarySearchTree {

    public BinarySearchTree binaryTree = new BinarySearchTree();

    public void preOrderTraverse(BinarySearchTree.Node node) {
        if(node == null) return;
        System.out.println(node);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    public void inOrderTraverse(BinarySearchTree.Node node) {
        if(node == null) return;
        inOrderTraverse(node.left);
        System.out.println(node);
        inOrderTraverse(node.right);
    }

    public void postOrderTraverse(BinarySearchTree.Node node) {
        if(node == null) return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(node);
    }

    public void dfs(BinarySearchTree.Node node) {
        if(node == null) return;
        System.out.println(node);
        dfs(node.left);
        dfs(node.right);
    }

    public void dfsNonRec(BinarySearchTree.Node node) {
        if(node == null) return;
        Stack<BinarySearchTree.Node> stk = new Stack();
        stk.push(node);
        while(true) {
            System.out.println(node);
            node = node.left;
            if(node == null) break;
            stk.push(node);
        }
        while(stk.isEmpty()) {
            node = stk.pop();
            node = node.right;
            if(node != null) {
                while(true) {
                    System.out.println(node);
                    node = node.left;
                    if(node == null) break;
                    stk.push(node);
                }
            }
        }
    }


    public void bfs() {
        Stack<BinarySearchTree.Node> stk = new Stack();
        BinarySearchTree.Node node = binaryTree.root;
        stk.push(node);
        while(!stk.isEmpty()) {
            node = stk.pop();
            if(node == null) continue;
            System.out.println(node);
            stk.push(node.left);
            stk.push(node.right);
        }
    }
}
