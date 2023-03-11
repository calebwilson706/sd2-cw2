package org.example.Deck;
public record Card(Rank rank, Suit suit) {
    public Integer getWeight() {
        return rank.getWeight();
    }

    public String toString() {
        return rank.getDisplayName() + " of " + suit.getDisplayName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return rank == card.rank && suit == card.suit;
    }
}

