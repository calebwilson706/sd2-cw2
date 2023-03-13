package org.example.DataStructures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {
    Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }
    @Test
    public void testEnqueue() {
        // Given we have an empty queue
        Assertions.assertTrue(queue.isEmpty());

        // When we enqueue some elements
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // The first element should be at the front of the queue
        Assertions.assertEquals(1, queue.peek());
    }

    @Test
    public void testDequeue() {
        // Given we have an empty queue
        // Dequeue should return null
        Assertions.assertNull(queue.dequeue());

        // When we add some items
        queue.enqueue(1);
        queue.enqueue(2);

        // They should be dequeued in order
        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertEquals(2, queue.dequeue());
    }

    @Test
    public void testPeek() {
        // Given we have an empty queue
        // Peek should return null
        Assertions.assertNull(queue.peek());

        // When we add some items
        queue.enqueue(1);
        queue.enqueue(2);

        // They should be returned but not removed
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertEquals(2, queue.peek());
    }
}
