package org.example.Utilities;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerWrapper {
    private final Scanner scanner;

    public ScannerWrapper(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
