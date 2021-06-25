package controller.duel;

import controller.duel.monsterseffect.ContinuousEffects;
import controller.duel.monsterseffect.SummonEffects;
import models.cards.Location;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;

public class SummonController {

    public static boolean hasSummonedInThisTurn = false;
    public static MonsterCard lastSummonedMonster;

    public String summon() {
        if (checkNormalSummonSetConditions() != null)
            return checkNormalSummonSetConditions();
        return finalizeSummon();
    }

    public String tributeSummon(String tributes) {
        if (checkNormalSummonSetConditions() != null)
            return checkNormalSummonSetConditions();
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        if ((selectedMonster.getLevel() < 7 && PhaseController.playerInTurn.getPlayerBoard().getMonsters().size() < 1)
                || selectedMonster.getLevel() > 6 && PhaseController.playerInTurn.getPlayerBoard().getMonsters().size() < 2)
            return "there are not enough cards for tribute";
        int firstMonster, secondMonster = 0;
        if (tributes.length() == 1)
            firstMonster = Integer.parseInt(tributes);
        else {
            firstMonster = Integer.parseInt(tributes.substring(0, 1));
            secondMonster = Integer.parseInt(tributes.substring(2, 3));
        }
        if (selectedMonster.getLevel() > 6 && secondMonster == 0
                || (selectedMonster.getLevel() > 6 && (PhaseController.playerInTurn.getPlayerBoard().getMonsterBoard().get(firstMonster - 1) == null
                || PhaseController.playerInTurn.getPlayerBoard().getMonsterBoard().get(secondMonster - 1) == null))
                || selectedMonster.getLevel() < 7 && PhaseController.playerInTurn.getPlayerBoard().getMonsterBoard().get(firstMonster - 1) == null)
            return "there are not enough monsters on these addresses";
        return finalizeSummon();
    }

    private String finalizeSummon() {
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        selectedMonster.setLocation(Location.FIELD);
        selectedMonster.setIsHidden(false);
        selectedMonster.setMode(Mode.ATTACK);
        PhaseController.playerInTurn.getPlayerBoard().summonOrSetMonster(selectedMonster);
        hasSummonedInThisTurn = true;
        //TODO what about ai
        lastSummonedMonster = selectedMonster;
        ContinuousEffects.run(PhaseController.playerInTurn.getPlayerBoard(), PhaseController.playerAgainst.getPlayerBoard());
        return "summoned successfully";
    }

    public static String checkNormalSummonSetConditions() {
        if (SelectionController.selectedCard == null)
            return "no card is selected yet";
        if (!PhaseController.playerInTurn.getPlayerBoard().getHandCards().contains(SelectionController.selectedCard)
                || !(SelectionController.selectedCard instanceof MonsterCard))
            return "you can't summon this card";
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        if (selectedMonster.getLevel() > 4)
            return "you can't summon this card";
        if (PhaseController.currentPhase != GamePhase.MAIN1 && PhaseController.currentPhase != GamePhase.MAIN2)
            return "action not allowed in this phase";
        if (PhaseController.playerInTurn.getPlayerBoard().getMonsters().size() == 5)
            return "monster card zone is full";
        if (hasSummonedInThisTurn)
            return "you already summoned/set on this turn";
        return null;
    }

    public String flipSummon() {
        if (SelectionController.selectedCard == null)
            return "no card is selected yet";
        if (!PhaseController.playerInTurn.getPlayerBoard().getMonsters().contains(SelectionController.selectedCard))
            return "you can't change this card position";
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        if (PhaseController.currentPhase != GamePhase.MAIN1 && PhaseController.currentPhase != GamePhase.MAIN2)
            return "action not allowed in this phase";
        if (!selectedMonster.getIsHidden())
            return "you can't flip this card";
        selectedMonster.setMode(Mode.ATTACK);
        selectedMonster.setIsHidden(false);
        //TODO what about ai
        lastSummonedMonster = selectedMonster;
        ContinuousEffects.run(PhaseController.playerInTurn.getPlayerBoard(), PhaseController.playerAgainst.getPlayerBoard());
        SummonEffects.run((MonsterCard) SelectionController.selectedCard, PhaseController.playerAgainst.getPlayerBoard(), PhaseController.playerInTurn.getPlayerBoard());
        return "flip summoned successfully";
    }
}
