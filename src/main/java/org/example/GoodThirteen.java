package org.example;

import org.example.DataStructures.LinkedList;
import org.example.Deck.Card;
import org.example.Deck.Deck;
import org.example.Utilities.ArrayUtilities;
import org.example.Utilities.ScannerWrapper;

public class GoodThirteen {
    private final Deck deck;
    private Card[] activeCards;

    private final ScannerWrapper scanner;

    public GoodThirteen(Deck deck, ScannerWrapper scanner) {
        this.scanner = scanner;
        System.out.println("Starting the game...");
        this.deck = deck;
        this.deck.shuffle();
        System.out.println("Drawing the first 10 cards.");
        this.activeCards = this.deck.deal(10);
    }

    public GameStatus getGameStatus() {
        if (this.activeCards.length == 0) {
            return GameStatus.SUCCESSFUL;
        } else if (getValidMoves().IsEmpty()) {
            return GameStatus.FAILED;
        } else {
            return GameStatus.TO_BE_CONTINUED;
        }
    }

    public Card[] GetUsersNextMove() {
        System.out.println("Select your next cards");
        Card firstCard = getInputCard("Enter the number for the first card.");

        if (firstCard.getWeight() == 13) {
            return new Card[] {firstCard};
        }

        Card secondCard = getInputCard("Enter the number for the second card.");

        if ((firstCard.getWeight() + secondCard.getWeight()) == 13) {
            return new Card[] {firstCard, secondCard};
        } else {
            System.out.println("This is an invalid combination, please try again");
            return GetUsersNextMove();
        }
    }

    private Card getInputCard(String description) {
        System.out.println(description);

        String firstCardIndexString = scanner.nextLine();

        try {
            int firstCardIndex = Integer.parseInt(firstCardIndexString) - 1;
            return activeCards[firstCardIndex];
        } catch (Exception exception) {
            System.out.println("Please enter a valid card index.");
            return getInputCard(description);
        }
    }

    public void executeUsersNextMove(Card[] move) {
        for (Card card : move) {
            for (int i = 0; i < activeCards.length; i++) {
                Card activeCard = activeCards[i];

                if (activeCard != null && activeCard.equals(card)) {
                    activeCards[i] = this.getReplacementCard();
                }
            }
        }

        this.activeCards = ArrayUtilities.removeNulls(activeCards);
    }

    private Card getReplacementCard() {
        return this.deck.isEmpty()
                ? null
                : deck.deal(1)[0];
    }

    public LinkedList<Card[]> getValidMoves() {
        LinkedList<Card[]> validMoves = new LinkedList<>();

        for (Card card : activeCards) {
            if (card.getWeight() == 13) {
                validMoves.append(new Card[]{ card });
                continue;
            }

            for (Card card2 : activeCards) {
                if (card.equals(card2)) continue;

                if ((card.getWeight() + card2.getWeight()) == 13) {
                    validMoves.append(new Card[]{ card, card2 });
                }
            }
        }

        return validMoves;
    }

    public void displayResult() {
        if (getGameStatus() == GameStatus.SUCCESSFUL) {
            System.out.println("Congratulations you won the game!");
        } else {
            System.out.println("Sorry, you reached a stalemate. Try again.");
        }
    }

    public void displayActiveCards() {
        System.out.println("The face up cards are:");
        for (int i = 0; i < this.activeCards.length; i++) {
            Card card = this.activeCards[i];
            System.out.println("Card " + (i + 1) + ": " + card.toString());
        }
    }

    public Card[] getActiveCards() {
        return activeCards;
    }
}

