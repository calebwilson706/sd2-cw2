package org.example.GoodThirteen;

import org.example.Deck.Card;
import org.example.Deck.Rank;
import org.example.Deck.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GoodThirteenHistoryTest {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        this.outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testReplayHistory() {
        // Given there is a history with som events
        Card[] initialCards = new Card[] {
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        };
        Card[] move1 = new Card[] {
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES),
        };
        Card[] newActiveCards1 = new Card[] {
                new Card(Rank.NINE, Suit.CLUBS),
                new Card(Rank.NINE, Suit.SPADES),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        };
        Card[] move2 = new Card[] {
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        };
        Card[] newActiveCards2 = new Card[] {
                new Card(Rank.NINE, Suit.CLUBS),
                new Card(Rank.NINE, Suit.SPADES),
        };
        GoodThirteenHistoryEvent event1 = new GoodThirteenHistoryEvent(move1, newActiveCards1);
        GoodThirteenHistoryEvent event2 = new GoodThirteenHistoryEvent(move2, newActiveCards2);

        GoodThirteenHistory history = new GoodThirteenHistory(initialCards);
        history.push(event1);
        history.push(event2);


        // When the history is replayed
        history.replayHistory();


        // The output should be as follows
        String output = outputStream.toString();
        String expectedOutput = """
                You played:
                Card 1: Ace of Hearts
                Card 2: Ace of Diamonds
                To leave:
                Card 1: 9 of Clubs
                Card 2: 9 of Spades
                                
                                
                                
                You played:
                Card 1: Ace of Clubs
                Card 2: Ace of Spades
                To leave:
                Card 1: 9 of Clubs
                Card 2: 9 of Spades
                Card 3: Ace of Hearts
                Card 4: Ace of Diamonds
                                
                                
                                
                The game started with:
                Card 1: Ace of Clubs
                Card 2: Ace of Spades
                Card 3: Ace of Hearts
                Card 4: Ace of Diamonds
                """;
        Assertions.assertEquals(expectedOutput, output);
    }
}