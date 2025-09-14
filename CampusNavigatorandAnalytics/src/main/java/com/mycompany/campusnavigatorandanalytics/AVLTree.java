/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusnavigatorandanalytics;

public class AVLTree {
    private class Node {
        Estudiante estudiante;
        Node left, right;
        int height;
        Node(Estudiante est) { this.estudiante = est; this.height = 1; }
    }

    private Node root;

    public void insert(Estudiante e) { root = insert(root, e); }
    public void delete(int id) { root = delete(root, id); }
    public Estudiante search(int id) { return search(root, id); }

    private Node insert(Node node, Estudiante e) {
        if (node == null) return new Node(e);
        if (e.getId() < node.estudiante.getId())
            node.left = insert(node.left, e);
        else if (e.getId() > node.estudiante.getId())
            node.right = insert(node.right, e);
        else return node; // ID duplicado, no se inserta

        return balance(node);
    }

    private Node delete(Node node, int id) {
        if (node == null) return null;
        if (id < node.estudiante.getId())
            node.left = delete(node.left, id);
        else if (id > node.estudiante.getId())
            node.right = delete(node.right, id);
        else {
            if (node.left == null || node.right == null)
                node = (node.left != null) ? node.left : node.right;
            else {
                Node min = minNode(node.right);
                node.estudiante = min.estudiante;
                node.right = delete(node.right, min.estudiante.getId());
            }
        }
        return (node != null) ? balance(node) : null;
    }

    private Estudiante search(Node node, int id) {
        if (node == null) return null;
        if (id == node.estudiante.getId()) return node.estudiante;
        return id < node.estudiante.getId() ? search(node.left, id) : search(node.right, id);
    }

    private Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // --- utilidades AVL ---
    private int height(Node n) { return n == null ? 0 : n.height; }
    private int getBalance(Node n) { return (n == null) ? 0 : height(n.left) - height(n.right); }

    private Node balance(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    // Devuelve todos los estudiantes en orden ascendente de ID
    public java.util.List<Estudiante> inOrder() {
        java.util.List<Estudiante> list = new java.util.ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(Node node, java.util.List<Estudiante> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.estudiante);
            inOrder(node.right, list);
        }
    }
}
