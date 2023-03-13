package org.example.GoodThirteen;

import org.example.Deck.Card;
import org.example.GameStatus;
import org.example.Utilities.InputService;

import java.io.IOException;

public class GoodThirteenManager {
    private final InputService inputService;

    private final GoodThirteen game;

    private final GoodThirteenHistoryFactory factory;

    public GoodThirteenManager(
            InputService inputService,
            GoodThirteen game,
            GoodThirteenHistoryFactory factory
    ) {
        this.inputService = inputService;
        this.game = game;
        this.factory = factory;
    }

    public void playUserControlledGame() throws IOException {
        GoodThirteenHistory history = factory.create(game.getActiveCards(), inputService);

        while (game.getGameStatus() == GameStatus.TO_BE_CONTINUED) {
            game.displayActiveCards();
            game.displayHint();
            Card[] nextMove = game.getUsersNextMove();
            game.executeUsersNextMove(nextMove);
            history.enqueue(new GoodThirteenHistoryEvent(
                    nextMove,
                    game.getActiveCards()
            ));
        }

        game.displayResult();

        if (inputService.getYesOrNoAnswer("Would you like to view the game replay?")) {
            history.replayHistory();
        }
    }
}
