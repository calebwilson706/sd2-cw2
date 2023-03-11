package org.example.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    public void testAppend() {
        // Given a list is created
        LinkedList<String> list = new LinkedList<>();

        // When items are added to the list
        list.append("hello");
        list.append("world");
        list.append("foo");
        list.append("bar");

        // Those items should be in the list
        assertEquals("hello world foo bar ", listToString(list));
    }

    @Test
    public void testContains() {
        // Given a list is created
        LinkedList<Integer> list = new LinkedList<>();

        // When items are added to the list
        list.append(1);
        list.append(2);
        list.append(3);

        // Contains should return true for an item in the list
        assertTrue(list.contains(2));
        // Contains should return false for an item not in the list
        assertFalse(list.contains(4));
    }

    private static <T> String listToString(LinkedList<T> list) {
        StringBuilder sb = new StringBuilder();
        for (Node<T> item : list) {
            sb.append(item.getValue()).append(" ");
        }
        return sb.toString();
    }
}