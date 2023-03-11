package org.example;

import org.example.Deck.Card;
import org.example.Deck.Deck;
import org.example.Deck.Rank;
import org.example.Deck.Suit;
import org.example.Utilities.ScannerWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GoodThirteenTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private Deck deck;

    private ScannerWrapper scanner;

    @BeforeEach
    public void setUp() {
        this.outContent = new ByteArrayOutputStream();
        this.originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        this.deck = Mockito.mock(Deck.class);
        this.scanner = Mockito.mock(ScannerWrapper.class);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
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
    public void testgetUsersNextMoveWhenFirstCardIsKing() {
        // Given there is a king face up
        setUpDeckWhichDealsValidMoves();

        // And the king is selected
        Mockito.when(scanner.nextLine()).thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.getUsersNextMove();


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testgetUsersNextMoveWhenAValidPairIsSelected() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And two cards which add to 13 are selected
        Mockito.when(scanner.nextLine())
                .thenReturn("2")
                .thenReturn("4");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.getUsersNextMove();


        // It should return a move containing the two cards
        Card[] expected = new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        };
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testgetUsersNextMoveWhenAValidPairIsSelectedAfterAnInvalidPair() {
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
        Card[] result = target.getUsersNextMove();


        // It should return a move containing the two cards
        Card[] expected = new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.CLUBS)
        };
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testgetUsersNextMoveWhenAnInvalidIndexIsSelected() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And a number is entered which is an invalid index
        // Then the king is selected
        Mockito.when(scanner.nextLine())
                .thenReturn("5")
                .thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.getUsersNextMove();


        // It should return a move containing the king
        Card[] expected = new Card[]{new Card(Rank.KING, Suit.CLUBS)};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testgetUsersNextMoveWhenThereIsAnInvalidInput() {
        // Given there are valid moves
        setUpDeckWhichDealsValidMoves();

        // And a number is entered which is an invalid index
        // Then the king is selected
        Mockito.when(scanner.nextLine())
                .thenReturn("not a number")
                .thenReturn("1");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        Card[] result = target.getUsersNextMove();


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

    @Test
    public void shouldDisplayA1CardHint() {
        // When the king is an option
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.KING, Suit.CLUBS)
        });

        // And a hint is requested
        Mockito.when(scanner.nextLine())
                .thenReturn("yes");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        target.displayHint();

        // The hint should be displayed
        Assertions.assertEquals(outContent.toString(), """
        Starting the game...
        Drawing the first 10 cards.
        Would you like a hint? Enter 'yes' if you would.
        A valid move you could play is:
        \tKing of Clubs
        """);
    }

    @Test
    public void shouldDisplayA2CardHint() {
        // When there is a 2 card option
        Mockito.when(deck.deal(10)).thenReturn(new Card[]{
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.DIAMONDS)
        });

        // And a hint is requested
        Mockito.when(scanner.nextLine())
                .thenReturn("yes");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        target.displayHint();

        // The hint should be displayed
        Assertions.assertEquals(outContent.toString(), """
        Starting the game...
        Drawing the first 10 cards.
        Would you like a hint? Enter 'yes' if you would.
        A valid move you could play is:
        \tQueen of Clubs
        \tand Ace of Diamonds
        """);
    }

    @Test
    public void shouldNotDisplayACardHint() {
        // When a hint is not requested
        Mockito.when(scanner.nextLine())
                .thenReturn("no");
        GoodThirteen target = new GoodThirteen(deck, scanner);
        target.displayHint();

        // The hint should be displayed
        Assertions.assertEquals(outContent.toString(), """
        Starting the game...
        Drawing the first 10 cards.
        Would you like a hint? Enter 'yes' if you would.
        """);
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