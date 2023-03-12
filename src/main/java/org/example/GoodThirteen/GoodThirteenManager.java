package org.example.GoodThirteen;

import org.example.Deck.Card;
import org.example.Deck.Deck;
import org.example.GameStatus;
import org.example.Utilities.InputService;

public class GoodThirteenManager {
    private final InputService inputService;

    public GoodThirteenManager(InputService inputService) {
        this.inputService = inputService;
    }

    public void PlayUserControlledGame() {
        GoodThirteen game = new GoodThirteen(new Deck(), inputService);
        GoodThirteenHistory history = new GoodThirteenHistory(game.getActiveCards());

        while (game.getGameStatus() == GameStatus.TO_BE_CONTINUED) {
            game.displayActiveCards();
            game.displayHint();
            Card[] nextMove = game.getUsersNextMove();
            game.executeUsersNextMove(nextMove);
            history.push(new GoodThirteenHistoryEvent(
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
