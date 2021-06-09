package controller.duel;

import models.Player;
import models.cards.Card;

public class GameController {
    private static Player owner;
    private static Card selectedCard;

    public static void setOwner(Player owner) {
        GameController.owner = owner;
    }

    public static Player getPlayer1() {
        return owner;
    }

    public static void setSelectedCard(Card selectedCard) {
        GameController.selectedCard = selectedCard;
    }

    public static Card getSelectedCard() {
        return selectedCard;
    }

    public void discard() {
        GameController.selectedCard = null;
    }

    public void setMonster() {
    }

    public void summonMonster() {
    }

    public void changePhase() {
    }
}
