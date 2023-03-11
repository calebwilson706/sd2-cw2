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
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // When we get the game status
        GameStatus status = target.getGameStatus();

        // It should be failed
        Assertions.assertEquals(GameStatus.TO_BE_CONTINUED, status);
    }

    @Test
    public void testGetUsersNextMoveWhenFirstCardIsThirteen() {
        // Given there is a king face up
        setUpDeckWhichDealsValidMoves();

        // And the king is selected
        Mockito.when(scanner.nextLine()).thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.GetUsersNextMove();


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetUsersNextMoveWhenAValidPairIsSelected() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And two cards which add to 13 are selected
        Mockito.when(scanner.nextLine())
                .thenReturn("2")
                .thenReturn("4");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.GetUsersNextMove();


        // It should return a move containing the two cards
        Card[] expected = new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        };
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetUsersNextMoveWhenAValidPairIsSelectedAfterAnInvalidPair() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And two cards which do not to 13 are selected
        // Then two cards which do add to 13 are selected
        Mockito.when(scanner.nextLine())
                .thenReturn("3")
                .thenReturn("1")
                .thenReturn("2")
                .thenReturn("4");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.GetUsersNextMove();


        // It should return a move containing the two cards
        Card[] expected = new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        };
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetUsersNextMoveWhenAnInvalidIndexIsSelected() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And a number is entered which is an invalid index
        // Then the king is selected
        Mockito.when(scanner.nextLine())
                .thenReturn("5")
                .thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.GetUsersNextMove();


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetUsersNextMoveWhenThereIsAnInvalidInput() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And a number is entered which is an invalid index
        // Then the king is selected
        Mockito.when(scanner.nextLine())
                .thenReturn("not a number")
                .thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.GetUsersNextMove();


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testExecuteUserMoveReplacesThePlayedCards() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // And there are cards in the deck
        Mockito.when(deck.deal(1))
                .thenReturn(new Card[]{ new Card(Rank.TWO, Suit.HEARTS) })
                .thenReturn(new Card[]{ new Card(Rank.ACE, Suit.HEARTS) });

        // When a valid move is played
        target.executeUsersNextMove(new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        });

        // It should replace the played cards
        Assertions.assertEquals(target.getActiveCards()[1], new Card(Rank.TWO, Suit.HEARTS));
        Assertions.assertEquals(target.getActiveCards()[3], new Card(Rank.ACE, Suit.HEARTS));
    }

    @Test
    public void testExecuteUserMoveWhenThereAreNoCardsInTheDeck() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();
        GoodThirteen target = new GoodThirteen(deck, scanner);

        // And there are no cards in the deck
        Mockito.when(deck.isEmpty())
                .thenReturn(true);

        // When a valid move is played
        target.executeUsersNextMove(new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        });

        // It should not replace the played cards
        Assertions.assertArrayEquals(target.getActiveCards(), new Card[] {
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.HEARTS)
        });
    }

    private void setUpDeckWhichDealsValidMoves() {
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.KING, Suit.CLUBS),
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.HEARTS),
                new Card(Rank.ACE, Suit.CLUBS)
        });
    }
}