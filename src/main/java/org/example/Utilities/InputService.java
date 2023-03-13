package org.example.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public Boolean getYesOrNoAnswer(String prompt) {
        System.out.println(prompt + " Enter 'yes' if you would.");
        String input = scanner.nextLine();

        return input.equalsIgnoreCase("yes");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void waitForEnterKeyPress() throws IOException {
        System.out.println("Press the enter key to continue...");
        System.in.read();
    }
}
