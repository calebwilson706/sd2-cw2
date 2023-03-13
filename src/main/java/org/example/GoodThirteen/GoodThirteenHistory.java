package org.example.GoodThirteen;

import org.example.DataStructures.Queue;
import org.example.Deck.Card;
import org.example.Utilities.CardUtilities;
import org.example.Utilities.InputService;

import java.io.IOException;

public class GoodThirteenHistory extends Queue<GoodThirteenHistoryEvent> {
    private final Card[] initialActiveCards;
    private final InputService inputService;

    public GoodThirteenHistory(Card[] initialActiveCards, InputService inputService) {
        super();

        this.initialActiveCards = initialActiveCards;
        this.inputService = inputService;
    }

    public void replayHistory() throws IOException {
        System.out.println("The game started with:");
        CardUtilities.displayListOfCards(this.initialActiveCards);

        GoodThirteenHistoryEvent current = this.dequeue();
        while (current != null) {
            System.out.println("\n");
            System.out.println("You played:");
            CardUtilities.displayListOfCards(current.move());

            if (current.newActiveCards().length > 0) {
                System.out.println("To leave:");
                CardUtilities.displayListOfCards(current.newActiveCards());
            } else {
                System.out.println("To win the game.");
            }

            inputService.waitForEnterKeyPress();
            current = this.dequeue();
        }
    }
}

record GoodThirteenHistoryEvent(Card[] move, Card[] newActiveCards) {
}

