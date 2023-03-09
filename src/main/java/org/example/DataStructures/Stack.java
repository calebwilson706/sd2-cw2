package org.example.DataStructures;

import java.util.NoSuchElementException;

public class Stack<T> {
    private Node<T> top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(this.top);
        this.top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T data = this.top.getValue();
        this.top = this.top.getNext();
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return this.top.getValue();
    }
}

