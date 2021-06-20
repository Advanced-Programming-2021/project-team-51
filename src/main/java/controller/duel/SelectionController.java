package controller.duel;

import models.cards.Card;
import view.StatusEnum;

public class SelectionController {
    public static Card selectedCard;

    public String selectMyMonster(String monsterNum) {
        int monsterIndex = Integer.parseInt(monsterNum);
        if (monsterIndex > 5)
            return StatusEnum.INVALID_SELECTION.getStatus();
        selectedCard = PhaseController.playerInTurn.getPlayerBoard().getMonsterBoard().get(monsterIndex - 1);
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectRivalMonster(String monsterNum) {
        int monsterIndex = Integer.parseInt(monsterNum);
        if (monsterIndex > 5)
            return StatusEnum.INVALID_SELECTION.getStatus();
        selectedCard = PhaseController.playerAgainst.getPlayerBoard().getMonsterBoard().get(monsterIndex - 1);
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectMySpell(String spellNum) {
        int spellIndex = Integer.parseInt(spellNum);
        if (spellIndex > 5)
            return StatusEnum.INVALID_SELECTION.getStatus();
        selectedCard = PhaseController.playerInTurn.getPlayerBoard().getSpellAndTrapBoard().get(spellIndex - 1);
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectRivalSpell(String spellNum) {
        int spellIndex = Integer.parseInt(spellNum);
        if (spellIndex > 5)
            return StatusEnum.INVALID_SELECTION.getStatus();
        selectedCard = PhaseController.playerAgainst.getPlayerBoard().getSpellAndTrapBoard().get(spellIndex - 1);
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectMyFieldCard() {
        selectedCard = PhaseController.playerInTurn.getPlayerBoard().getFieldZone();
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectRivalFieldCard() {
        selectedCard = PhaseController.playerAgainst.getPlayerBoard().getFieldZone();
        if (selectedCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String selectHandCard(String cardNum) {
        int index = Integer.parseInt(cardNum);
        if (index > PhaseController.playerInTurn.getPlayerBoard().getHandCards().size())
            return StatusEnum.INVALID_SELECTION.getStatus();

        selectedCard = PhaseController.playerInTurn.getPlayerBoard().getHandCards().get(index - 1);
        return StatusEnum.CARD_SELECTED.getStatus();
    }

    public String deSelect() {
        if (selectedCard == null)
            return StatusEnum.NO_CARD_IS_SELECTED_YET.getStatus();
        selectedCard = null;
        return StatusEnum.CARD_DESELECTED.getStatus();
    }
}
