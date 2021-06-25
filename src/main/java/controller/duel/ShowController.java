package controller.duel;

public class ShowController {

    public String showGraveyard() {
        StringBuilder graveyardFormat = new StringBuilder();
        for (int i = 0; i < PhaseController.playerInTurn.getPlayerBoard().getGraveyardCards().size(); i++)
            graveyardFormat.append(i + 1).append(". ").append(SelectionController.selectedCard.getName()).
                    append(":").append(SelectionController.selectedCard.getDescription());
        return graveyardFormat.toString();
    }

    public String showSelectedCard() {
        if (SelectionController.selectedCard == null)
            return "no card is selected";
        if (SelectionController.selectedCard.getIsHidden() &&
                !PhaseController.playerInTurn.getPlayerBoard().getMonsters().contains(SelectionController.selectedCard) &&
                !PhaseController.playerInTurn.getPlayerBoard().getSpellTraps().contains(SelectionController.selectedCard) &&
                !PhaseController.playerInTurn.getPlayerBoard().getHandCards().contains(SelectionController.selectedCard) &&
                !PhaseController.playerInTurn.getPlayerBoard().getFieldZone().equals(SelectionController.selectedCard))
            return "card is not visible";
        return SelectionController.selectedCard.toString();
    }
}
