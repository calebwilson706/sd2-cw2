package org.example.Utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilitiesTest {
    @Test
    public void testRemoveNulls() {
        // Given an array is created with null values
        Integer[] input = {1, null, 2, null, null, 3};

        // When we remove the nulls
        Integer[] output = ArrayUtilities.removeNulls(input);

        // The null values should be removed
        Integer[] expected = {1, 2, 3};
        assertArrayEquals(expected, output);
        assertEquals(output.length, 3);
    }
}