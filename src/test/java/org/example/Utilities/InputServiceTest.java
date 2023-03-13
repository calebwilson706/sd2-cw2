package org.example.Utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

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

    @Test
    void testWaitForEnterKeyPress() throws IOException {
        // Given there is a user input
        InputService inputService = buildInputService("");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // When we wait for an enter keypress
        inputService.waitForEnterKeyPress();

        // It should print the appropriate message after returning
        Assertions.assertEquals("Press the enter key to continue...\n", outContent.toString());
    }

    private InputService buildInputService(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return new InputService(inputStream);
    }
}
