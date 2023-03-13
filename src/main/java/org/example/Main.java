package org.example;

import org.example.Deck.Deck;
import org.example.GoodThirteen.GoodThirteen;
import org.example.GoodThirteen.GoodThirteenHistoryFactory;
import org.example.GoodThirteen.GoodThirteenManager;
import org.example.Utilities.InputService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputService inputService = new InputService(System.in);
        GoodThirteen goodThirteen = new GoodThirteen(new Deck(), inputService);
        GoodThirteenHistoryFactory goodThirteenHistoryFactory = new GoodThirteenHistoryFactory();
        GoodThirteenManager goodThirteenManager = new GoodThirteenManager(inputService, goodThirteen, goodThirteenHistoryFactory);
        goodThirteenManager.playUserControlledGame();
    }
}