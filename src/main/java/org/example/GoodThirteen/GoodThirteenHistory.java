package org.example.GoodThirteen;

import org.example.DataStructures.Stack;
import org.example.Deck.Card;
import org.example.Utilities.CardUtilities;

public class GoodThirteenHistory extends Stack<GoodThirteenHistoryEvent> {
    private final Card[] initialActiveCards;

    public GoodThirteenHistory(Card[] initialActiveCards) {
        super();

        this.initialActiveCards = initialActiveCards;
    }

    public void replayHistory() {
        GoodThirteenHistoryEvent current = this.pop();

        while (current != null) {
            System.out.println("You played:");
            CardUtilities.displayListOfCards(current.move());

            if (current.newActiveCards().length > 0) {
                System.out.println("To leave:");
                CardUtilities.displayListOfCards(current.newActiveCards());
            } else {
                System.out.println("To win the game.");
            }

            System.out.println("\n\n");
            current = this.pop();
        }

        System.out.println("The game started with:");
        CardUtilities.displayListOfCards(this.initialActiveCards);
    }
}

record GoodThirteenHistoryEvent(Card[] move, Card[] newActiveCards) {
}
