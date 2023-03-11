package org.example;

import org.example.Deck.Card;
import org.example.Deck.Deck;
import org.example.Utilities.ScannerWrapper;

public class Main {
    public static void main(String[] args) {
        GoodThirteen game = new GoodThirteen(new Deck(), new ScannerWrapper(System.in));

        while (game.getGameStatus() == GameStatus.TO_BE_CONTINUED) {
            game.displayActiveCards();
            game.displayHint();
            Card[] nextMove = game.getUsersNextMove();
            game.executeUsersNextMove(nextMove);
        }

        game.displayResult();
    }
}