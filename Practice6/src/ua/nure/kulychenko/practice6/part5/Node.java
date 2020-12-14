package ua.nure.kulychenko.practice6.part5;

public class Node<E extends Comparable> {
    private E value;
    private Node<E> right;
    private Node<E> left;
    private Node<E> parent;

    public Node(E data, Node<E> parent) {
        this.value = data;
        this.parent = parent;
    }

    Node(E data) {
        this.value = data;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }


}
