package org.example.DataStructures;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<Node<T>> {
    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    public boolean IsEmpty() {
        return head == null;
    }

    public Node<T> first() {
        return head;
    }

    public Node<T> last() {
        Node<T> lastNode = null;

        for (Node<T> node : this) {
            lastNode = node;
        }

        return lastNode;
    }

    public void append(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            return;
        }

        last().setNext(newNode);
    }

    public boolean contains(T data) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(data)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new LinkedListIterator(head);
    }

    private class LinkedListIterator implements Iterator<Node<T>> {
        private Node<T> currentNode;

        public LinkedListIterator(Node<T> head) {
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Node<T> next() {
            Node<T> node = currentNode;
            currentNode = currentNode.getNext();
            return node;
        }
    }
}