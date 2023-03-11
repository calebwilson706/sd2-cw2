package org.example.Deck;

import java.util.Random;

public class Deck {
    private Card[] cards;

    public Deck() {
        cards = new Card[52];
        int i = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[i] = new Card(rank, suit);
                i++;
            }
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public Integer size() {
        return cards.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //Using the fisher yates shuffle - https://en.wikipedia.org/wiki/Fisher–Yates_shuffle
    public void shuffle() {
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card[] deal(Integer numberOfCards) {
        Card[] dealtCards = new Card[numberOfCards];
        Card[] reducedDeckCards = new Card[cards.length - numberOfCards];

        System.arraycopy(cards, 0, dealtCards, 0, numberOfCards);
        System.arraycopy(cards, numberOfCards, reducedDeckCards, 0, cards.length - numberOfCards);

        this.cards = reducedDeckCards;
        return dealtCards;
    }

    public boolean contains(Card theCard) {
        for (Card card : cards) {
            if (card.equals(theCard)) return true;
        }

        return false;
    }
}
