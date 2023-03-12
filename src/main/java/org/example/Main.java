package org.example;

import org.example.GoodThirteen.GoodThirteenManager;
import org.example.Utilities.InputService;

public class Main {
    public static void main(String[] args) {
        InputService inputService = new InputService(System.in);
        GoodThirteenManager goodThirteenManager = new GoodThirteenManager(inputService);
        goodThirteenManager.PlayUserControlledGame();
    }
}