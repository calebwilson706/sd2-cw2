package org.example;

import org.example.Deck.Card;
import org.example.Deck.Deck;
import org.example.Deck.Rank;
import org.example.Deck.Suit;
import org.example.Utilities.ScannerWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GoodThirteenTest {
    private Deck deck;

    private ScannerWrapper scanner;

    @BeforeEach
    public void setUp() {
        this.deck = Mockito.mock(Deck.class);
        this.scanner = Mockito.mock(ScannerWrapper.class);
    }

    @Test
    public void testGetGameStatusWhenActiveCardsIsEmpty() {
        // Given there are no active cards
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{});
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // When we get the game status
        GameStatus status = target.getGameStatus();

        // It should be successful
        Assertions.assertEquals(GameStatus.SUCCESSFUL, status);
    }

    @Test
    public void testGetGameStatusWhenNoValidMoves() {
        // Given there are no valid moves
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
        });
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // When we get the game status
        GameStatus status = target.getGameStatus();

        // It should be failed
        Assertions.assertEquals(GameStatus.FAILED, status);
    }

    @Test
    public void testGetGameStatusWhenValidMoves() {
        // Given there are no valid moves
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.ACE, Suit.CLUBS),
                new Card(Rank.QUEEN, Suit.HEARTS),
        });
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // When we get the game status
        GameStatus status = target.getGameStatus();

        // It should be failed
        Assertions.assertEquals(GameStatus.TO_BE_CONTINUED, status);
    }

    @Test
    public void testGetUsersNextMoveWhenFirstCardIsThirteen() {
        // Given there is a king face up
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.KING, Suit.CLUBS)
        });

        // And the king is selected
        Mockito.when(scanner.nextLine()).thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, target.GetUsersNextMove());
    }

    //TODO: test valid pair
    //TODO: test invalid input
    //TODO: test pair which is not equal to 13

    //TODO: test execute move
    // - Replace card
    // - Replace card with null if the deck is empty
}