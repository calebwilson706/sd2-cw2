package org.example.DataStructures;

public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }

        rear = newNode;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T value = front.getValue();

        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getValue();
    }
}

