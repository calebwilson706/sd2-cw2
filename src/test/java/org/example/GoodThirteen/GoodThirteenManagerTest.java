package org.example.GoodThirteen;

import org.example.Deck.Card;
import org.example.Deck.Rank;
import org.example.Deck.Suit;
import org.example.GameStatus;
import org.example.Utilities.InputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GoodThirteenManagerTest {
    GoodThirteen goodThirteen;

    InputService inputService;

    GoodThirteenManager target;

    GoodThirteenHistory history;

    GoodThirteenHistoryFactory factory;

    @BeforeEach
    public void setUp() {
        goodThirteen = Mockito.mock(GoodThirteen.class);
        setUpHistory();
        setUpInputService();
        target = new GoodThirteenManager(inputService, goodThirteen, factory);
    }

    @Test
    public void testPlayUserControlledGameDisplaysResult() throws IOException {
        // Given the game will end
        Mockito.when(goodThirteen.getGameStatus())
                .thenReturn(GameStatus.SUCCESSFUL);

        // When the game is completed
        target.playUserControlledGame();

        // It should display the result
        verify(goodThirteen, times(1)).displayResult();
    }

    @Test
    public void testPlayUserControlledDoesNotReplayHistory() throws IOException {
        // Given the game will end
        // And the history is not replayed
        Mockito.when(goodThirteen.getGameStatus())
                .thenReturn(GameStatus.SUCCESSFUL);

        // When the game is completed
        target.playUserControlledGame();

        // It should not replay the history
        verify(history, times(0)).replayHistory();
    }

    @Test
    public void testPlayUserControlledDoesReplayHistory() throws IOException {
        // Given the game will end
        // And the history is not replayed
        Mockito.when(goodThirteen.getGameStatus())
                .thenReturn(GameStatus.SUCCESSFUL);

        // When the game is completed and replay is requested
        Mockito.when(inputService.getYesOrNoAnswer(Mockito.anyString()))
                .thenReturn(true);
        target.playUserControlledGame();

        // It should replay the history
        verify(history, times(1)).replayHistory();
    }

    @Test
    public void testPlayUserControlledGamePlayThrough() throws IOException {
        // Given the game will end after 2 iterations
        Mockito.when(goodThirteen.getGameStatus())
                .thenReturn(GameStatus.TO_BE_CONTINUED)
                .thenReturn(GameStatus.TO_BE_CONTINUED)
                .thenReturn(GameStatus.FAILED);
        Card[] move1 = new Card[] { new Card(Rank.KING, Suit.SPADES)};
        Card[] move2 = new Card[] { new Card(Rank.KING, Suit.HEARTS)};
        Mockito.when(goodThirteen.getUsersNextMove())
                .thenReturn(move1)
                .thenReturn(move2);

        // And the game is carried out
        target.playUserControlledGame();

        // It should carry out 2 iterations
        verify(goodThirteen, times(2)).displayActiveCards();
        verify(goodThirteen, times(2)).displayHint();
        verify(goodThirteen, times(2)).getUsersNextMove();

        // It should execute the two returned moves
        verify(goodThirteen, times(1)).executeUsersNextMove(move1);
        verify(goodThirteen, times(1)).executeUsersNextMove(move2);

        // It should add the two moves to history
        verify(history, times(1)).enqueue(
                Mockito.argThat(argument -> argument.move() == move1)
        );
        verify(history, times(1)).enqueue(
                Mockito.argThat(argument -> argument.move() == move2)
        );
    }

    private void setUpInputService() {
        inputService = Mockito.mock(InputService.class);
        Mockito.when(inputService.getYesOrNoAnswer(Mockito.anyString()))
                .thenReturn(false);
    }

    private void setUpHistory() {
        history = Mockito.mock(GoodThirteenHistory.class);
        factory = Mockito.mock(GoodThirteenHistoryFactory.class);
        Mockito.when(goodThirteen.getActiveCards())
                        .thenReturn(new Card[] {});
        Mockito.when(factory.create(Mockito.any(Card[].class), Mockito.any(InputService.class)))
                .thenReturn(history);
    }
}