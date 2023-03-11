package org.example.Deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    public void testShuffle() {
        // Given a deck is created
        Deck originalDeck = new Deck();
        Deck deckToShuffle = new Deck();

        // When it is shuffled
        deckToShuffle.shuffle();

        // The cards order should change
        Assertions.assertNotEquals(originalDeck.getCards(), deckToShuffle.getCards(), "Deck was not shuffled");
    }

    @Test
    public void testDeal() {
        // Given a deck is created
        Deck deck = new Deck();

        // When cards are dealt
        Card[] dealtCards = deck.deal(5);

        // It should return the correct amount of cards
        Assertions.assertEquals(5, dealtCards.length, "Wrong number of cards were dealt");

        // It should remove thc cards from the deck
        for (Card card : dealtCards) {
            Assertions.assertFalse(deck.contains(card), "Dealt card is still in the deck");
        }
        int expectedRemainingCards = 52 - 5;
        Assertions.assertEquals(expectedRemainingCards, deck.size(), "Wrong number of cards remaining in the deck");
    }
}



