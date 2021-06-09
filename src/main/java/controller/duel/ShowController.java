package controller.duel;

import models.Player;
import models.cards.Card;

public class ShowController {

    Player player;
    Card selectedCard;

    public String showGraveyard() {
        StringBuilder graveyardFormat = new StringBuilder();
        for (int i = 0; i < player.getPlayerBoard().getGraveyardCards().size(); i++)
            graveyardFormat.append(i + 1).append(". ").append(selectedCard.getName()).append(":").append(selectedCard.getDescription());
        return graveyardFormat.toString();
    }

    public String showSelectedCard() {
        if (selectedCard == null)
            return "no card is selected";
        if (selectedCard.getIsHidden() &&
                !player.getPlayerBoard().getMonsterCards().contains(selectedCard) &&
        !player.getPlayerBoard().getSpellTraps().contains(selectedCard) &&
        !player.getPlayerBoard().getHandCards().contains(selectedCard) &&
        !player.getPlayerBoard().getFieldZone().equals(selectedCard))
            return "card is not visible";
        return selectedCard.toString();
    }
}
