package org.example.Deck;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Integer getWeight() {
        return rank.getWeight();
    }

    public String toString() {
        return rank.getDisplayName() + " of " + suit.getDisplayName();
    }
}
