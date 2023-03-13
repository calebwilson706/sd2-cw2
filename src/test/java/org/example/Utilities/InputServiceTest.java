package org.example.Utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class InputServiceTest {
    @Test
    void testNextLine() {
        InputService inputService = buildInputService("Hello World");

        Assertions.assertEquals("Hello World", inputService.nextLine());
    }

    @Test
    void testGetYesOrNoAnswer_yes() {
        InputService inputService = buildInputService("yes");
        Assertions.assertTrue(inputService.getYesOrNoAnswer("Do you like ice cream?"));
    }

    @Test
    void testGetYesOrNoAnswer_YES() {
        InputService inputService = buildInputService("YES");
        Assertions.assertTrue(inputService.getYesOrNoAnswer("Do you like ice cream?"));
    }

    @Test
    void testGetYesOrNoAnswer_No() {
        InputService inputService = buildInputService("no");
        Assertions.assertFalse(inputService.getYesOrNoAnswer("Do you like broccoli?"));
    }

    private InputService buildInputService(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return new InputService(inputStream);
    }
}
