package org.example.DataStructures;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void testPushAndPop() {
        // Given a stack is created
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());

        // When items are pushed
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Those items are now in the stack
        assertFalse(stack.isEmpty());
        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeek() {
        // Given a stack is created with items
        Stack<String> stack = new Stack<>();
        stack.push("apple");
        stack.push("banana");

        // Peek should return the item on the top of the stack
        assertEquals("banana", stack.peek());
        assertEquals("banana", stack.pop());
        assertEquals("apple", stack.peek());
        assertEquals("apple", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmpty() {
        Stack<Object> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    public void testPeekEmpty() {
        Stack<Object> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }
}