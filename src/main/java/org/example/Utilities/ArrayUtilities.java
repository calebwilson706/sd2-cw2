package org.example.Utilities;

import java.lang.reflect.Array;

public class ArrayUtilities {
    public static <T> T[] removeNulls(T[] array) {
        int nonNullCount = countNonNullObjects(array);
        return createNonNullArray(array, nonNullCount);
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] createNonNullArray(T[] original, int nonNullCount) {
        T[] result = (T[]) Array.newInstance(original.getClass().getComponentType(), nonNullCount);
        int index = 0;
        for (T element : original) {
            if (element != null) {
                result[index++] = element;
            }
        }

        return result;
    }

    private static <T> int countNonNullObjects(T[] array) {
        int nonNullCount = 0;

        // Count the number of non-null elements in the array
        for (T element : array) {
            if (element != null) nonNullCount++;
        }

        return nonNullCount;
    }
}
