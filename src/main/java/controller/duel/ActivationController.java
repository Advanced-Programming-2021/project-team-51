package controller.duel;

import controller.duel.spells.*;
import controller.duel.traps.MagicJammer;
import controller.duel.traps.NormalTraps;
import controller.duel.traps.SummonTraps;
import controller.duel.traps.TimeSeal;
import models.Board;
import models.cards.CardType;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;
import view.StatusEnum;

public class ActivationController {

    public String equip(int monsterIndex) {
        if (checkActivationConditions() != null)
            return checkActivationConditions();
        if (monsterIndex > 5)
            return StatusEnum.INVALID_SELECTION.getStatus();
        MonsterCard monsterCard = PhaseController.playerInTurn.getPlayerBoard().getMonsterBoard().get(monsterIndex - 1);
        if (monsterCard == null)
            return StatusEnum.NO_CARD_FOUND_IN_POSITION.getStatus();
        boolean hasAffected = EquipSpells.equip((SpellTrapCard) SelectionController.selectedCard, monsterCard);
        if (!hasAffected)
            return "wrong selection";
        return StatusEnum.SPELL_ACTIVATED.getStatus();
    }

    public String activate() {
        if (checkActivationConditions() != null)
            return checkActivationConditions();
        if (SelectionController.selectedCard.getCardType() == CardType.SPELL)
            return activateSpell();
        else
            return activateTrap();
    }

    public String activateTrap() {
        if (checkActivationConditions() != null)
            return checkActivationConditions();
        SpellTrapCard trapCard = (SpellTrapCard) SelectionController.selectedCard;
        Board myBoard = PhaseController.playerInTurn.getPlayerBoard();
        Board rivalBoard = PhaseController.playerAgainst.getPlayerBoard();
        if (MagicJammer.activate(trapCard, myBoard, rivalBoard))
            return StatusEnum.SPELL_OR_TRAP_ACTIVATED.getStatus();
        else if (NormalTraps.activate(trapCard, myBoard, rivalBoard))
            return StatusEnum.SPELL_OR_TRAP_ACTIVATED.getStatus();
        else if (SummonTraps.activate(trapCard, SummonController.lastSummonedMonster, myBoard, rivalBoard))
            return StatusEnum.SPELL_OR_TRAP_ACTIVATED.getStatus();
        else if (TimeSeal.activate(trapCard, myBoard))
            return StatusEnum.SPELL_OR_TRAP_ACTIVATED.getStatus();
        return "trap can't be activated";
    }

    public String activateSpell() {
        if (checkActivationConditions() != null)
            return checkActivationConditions();
        SpellTrapCard spellCard = (SpellTrapCard) SelectionController.selectedCard;
        if (MessengerOfPeace.activate(spellCard, PhaseController.playerInTurn.getPlayerBoard()))
            return StatusEnum.SPELL_ACTIVATED.getStatus();
        else if (NormalActivate.activate(spellCard, PhaseController.playerInTurn.getPlayerBoard(), PhaseController.playerAgainst.getPlayerBoard()))
            return StatusEnum.SPELL_ACTIVATED.getStatus();
        else if (TurnSpells.activate(spellCard, PhaseController.playerInTurn.getPlayerBoard(), AttackController.isAnyMonsterDead))
            return StatusEnum.SPELL_ACTIVATED.getStatus();
        else if (QuickPlays.activate(spellCard, PhaseController.playerInTurn.getPlayerBoard(), PhaseController.playerAgainst.getPlayerBoard()))
            return StatusEnum.SPELL_ACTIVATED.getStatus();
        return StatusEnum.PREPARATION_OF_SPELL_NOT_DONE.getStatus();
    }

    public String activateOnMonster(int monsterIndex) {
        if (checkActivationConditions() != null)
            return checkActivationConditions();
        if (OnMonsterSpells.activate((SpellTrapCard) SelectionController.selectedCard,
                PhaseController.playerInTurn.getPlayerBoard(), PhaseController.playerAgainst.getPlayerBoard(), monsterIndex))
            return StatusEnum.SPELL_ACTIVATED.getStatus();
        return "wrong selection";
    }

    private String checkActivationConditions() {
        if (SelectionController.selectedCard == null)
            return StatusEnum.NO_CARD_SELECTED_YET.getStatus();
        if (SelectionController.selectedCard.getCardType() == CardType.MONSTER)
            return "activate is not for monsters";
        return null;
    }
}
