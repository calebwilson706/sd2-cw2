package org.example.GoodThirteen;

import org.example.Deck.Card;
import org.example.Utilities.InputService;

public class GoodThirteenHistoryFactory {
    // Using instead of constructor for testing purposes
    public GoodThirteenHistory create(Card[] initialActiveCards, InputService inputService) {
        return new GoodThirteenHistory(initialActiveCards, inputService);
    }
}
