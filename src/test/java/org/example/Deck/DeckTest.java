package org.example.Deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void testShuffle() {
        Deck originalDeck = new Deck();
        Deck deckToShuffle = new Deck();

        deckToShuffle.shuffle();

        Assertions.assertNotEquals(originalDeck.getCards(), deckToShuffle.getCards(), "Deck was not shuffled");
    }

    @Test
    public void testDeal() {
        Deck deck = new Deck();

        Card[] dealtCards = deck.deal(5);

        Assertions.assertEquals(5, dealtCards.length, "Wrong number of cards were dealt");

        // Verify that the dealt cards are not in the deck
        for (Card card : dealtCards) {
            Assertions.assertFalse(deck.contains(card), "Dealt card is still in the deck");
        }

        // Verify that the remaining cards in the deck are correct
        int expectedRemainingCards = 52 - 5;
        Assertions.assertEquals(expectedRemainingCards, deck.size(), "Wrong number of cards remaining in the deck");
    }
}



