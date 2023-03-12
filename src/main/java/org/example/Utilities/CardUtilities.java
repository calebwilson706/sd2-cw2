package org.example.Utilities;

import org.example.Deck.Card;

public class CardUtilities {
    public static void displayListOfCards(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            System.out.println("Card " + (i + 1) + ": " + card.toString());
        }
    }
}
