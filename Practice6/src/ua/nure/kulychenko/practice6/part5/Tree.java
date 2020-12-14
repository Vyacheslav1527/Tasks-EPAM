package ua.nure.kulychenko.practice6.part5;

import java.util.ArrayList;
import java.util.Arrays;


public class Tree<E extends Comparable> {
    private Node<E> head;

    public boolean add(E element) {

        if (head == null) {
            head = new Node<>(element);
            return true;
        }
        Node<E> current = head;
        while (true) {
            if (element.compareTo(current.getValue()) < 0) {
                if (current.getRight() == null) {
                    current.setRight(new Node<>(element, current));
                    return true;
                } else {
                    current = current.getRight();

                }
            } else if (element.compareTo(current.getValue()) > 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(element, current));
                    return true;
                } else {
                    current = current.getLeft();

                }
            } else {
                return false;
            }
        }
    }

    public void add(E[] elementsArray) {
        Arrays.stream(elementsArray).forEach(this::add);
    }

    public boolean remove(E element) {
        Node<E> current = search(element);
        if (current == null) {
            return false;
        }
        if (current.getLeft() == null && current.getRight() == null) {
            removeWithoutSons(current);
            return true;
        }
        if ((current.getRight() != null && current.getLeft() == null) ||
                (current.getRight() == null && current.getLeft() != null)) {
            removeWithOneSon(current);
            return true;
        }
        if (current.getLeft() != null && current.getRight() != null) {
            removeWithTwoSons(current);
            return true;
        }
        return false;
    }

    private void removeWithTwoSons(Node<E> current) {
        if (current.getLeft().getRight() != null) {
            Node<E> maxRight = current.getLeft().getRight();
            while (maxRight.getRight() != null) {
                maxRight = maxRight.getRight();
            }
            maxRight.getParent().setRight(null);
            if (current.getParent().getLeft() == current) {
                current.getParent().setLeft(maxRight);
            } else {
                current.getParent().setRight(maxRight);
            }
            maxRight.setParent(current.getParent());
            maxRight.setRight(current.getRight());
            maxRight.setLeft(current.getLeft());
            maxRight.getRight().setParent(maxRight);
            maxRight.getLeft().setParent(maxRight);
        } else {
            Node<E> replacingNode = current.getLeft();
            if (replacingNode.getRight() != null) {
                Node<E> maxRight = replacingNode.getRight();
                while (maxRight.getRight() != null) {
                    maxRight = maxRight.getRight();
                }
                maxRight.setRight(current.getRight());
                current.getRight().setParent(maxRight);
            } else {
                replacingNode.setRight(current.getRight());
                if (current.getRight() != null) {
                    current.getRight().setParent(replacingNode);
                }
            }
            if (current.getParent().getLeft() == current) {
                current.getParent().setLeft(replacingNode);
                replacingNode.setParent(current.getParent());
            } else {
                current.getParent().setRight(replacingNode);
                replacingNode.setParent(current.getParent());
            }


        }
    }

    private void removeWithOneSon(Node<E> current) {
        if (current == head) {
            if (head.getLeft() != null) {
                head = head.getLeft();
            } else {
                head = head.getRight();
            }
            head.setParent(null);
        } else {
            if (current == current.getParent().getRight()) {
                if (current.getRight() != null) {
                    current.getParent().setRight(current.getRight());
                    current.getRight().setParent(current.getParent());
                } else {
                    current.getParent().setRight(current.getLeft());
                    current.getLeft().setParent(current.getParent());
                }
            } else if (current == current.getParent().getLeft()) {
                if (current.getRight() != null) {
                    current.getParent().setLeft(current.getRight());
                    current.getRight().setParent(current.getParent());
                } else {
                    current.getParent().setLeft(current.getLeft());
                    current.getLeft().setParent(current.getParent());
                }
            }
        }
    }

    private void removeWithoutSons(Node<E> current) {
        if (current == head) {
            head = null;
        } else if (current == current.getParent().getRight()) {
            current.getParent().setRight(null);
        } else {
            current.getParent().setLeft(null);
        }
    }

    public void print() {
        if (head != null) {
            Object[] represent = printRec(head);
            Arrays.stream(represent).forEach(System.out::println);
        }
    }

    private Node<E> search(E element) {
        Node<E> current = head;
        while (true) {
            if (current == null) {
                return null;
            }
            if (element.compareTo(current.getValue()) < 0) {
                current = current.getRight();
            } else if (element.compareTo(current.getValue()) > 0) {
                current = current.getLeft();
            } else {
                return current;
            }
        }
    }


    private Object[] printRec(Node<E> current) {
        ArrayList<String> result = new ArrayList<>();
        if (current.getRight() != null) {
            Object[] temp = printRec(current.getRight());
            for (int i = 0; i < temp.length; i++) {
                result.add("  " + temp[i]);
            }
        }
        result.add(String.valueOf(current.getValue()));
        if (current.getLeft() != null) {
            Object[] recurs = printRec(current.getLeft());
            for (int i = 0; i < recurs.length; i++) {
                result.add("  " + recurs[i]);
            }
        }
        return result.toArray();
    }


}
