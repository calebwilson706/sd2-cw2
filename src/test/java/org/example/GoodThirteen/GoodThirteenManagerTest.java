package org.example.GoodThirteen;

import org.example.Deck.Card;
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

    private void setUpInputService() {
        inputService = Mockito.mock(InputService.class);
        Mockito.when(inputService.getYesOrNoAnswer(Mockito.anyString()))
                .thenReturn(false);
    }

    private void setUpHistory() {
        history = Mockito.mock(GoodThirteenHistory.class);
        factory = Mockito.mock(GoodThirteenHistoryFactory.class);
        Mockito.when(factory.create(Mockito.any(Card[].class), Mockito.any(InputService.class)))
                .thenReturn(history);
    }
}