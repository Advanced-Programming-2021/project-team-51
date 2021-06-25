package controller.duel;

import models.cards.Location;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

public class SettingController {


    public String set() {
        if (SelectionController.selectedCard instanceof MonsterCard)
            return setMonster();
        else
            return setSpellTrap();
    }

    private String setSpellTrap() {
        if (SelectionController.selectedCard == null)
            return "no card is selected yet";
        if (!PhaseController.playerInTurn.getPlayerBoard().getHandCards().contains(SelectionController.selectedCard))
            return "you can't set this card";
        if (PhaseController.currentPhase != GamePhase.MAIN1 && PhaseController.currentPhase != GamePhase.MAIN2)
            return "you can't do this action in this phase";
        if (PhaseController.playerInTurn.getPlayerBoard().getSpellTraps().size() == 5)
            return "spell card zone is full";
        SpellTrapCard selectedSpellTrap = (SpellTrapCard) SelectionController.selectedCard;
        selectedSpellTrap.setLocation(Location.FIELD);
        selectedSpellTrap.setIsHidden(true);
        PhaseController.playerInTurn.getPlayerBoard().summonOrSetSpellAndTrap(selectedSpellTrap);
        return "set successfully";
    }

    private String setMonster() {
        if (SummonController.checkNormalSummonSetConditions() != null)
            return SummonController.checkNormalSummonSetConditions();
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        selectedMonster.setLocation(Location.FIELD);
        selectedMonster.setIsHidden(true);
        selectedMonster.setMode(Mode.DEFENSE);
        PhaseController.playerInTurn.getPlayerBoard().summonOrSetMonster(selectedMonster);
        SummonController.hasSummonedInThisTurn = true;
        return "summoned successfully";
    }

    public String setPosition(String position) {
        if (SelectionController.selectedCard == null)
            return "no card is selected yet";
        if (!PhaseController.playerInTurn.getPlayerBoard().getMonsters().contains((MonsterCard) SelectionController.selectedCard))
            return "you can't change this card position";
        if (PhaseController.currentPhase != GamePhase.MAIN1 && PhaseController.currentPhase != GamePhase.MAIN2)
            return "you can't do this action in this phase";
        MonsterCard selectedMonster = (MonsterCard) SelectionController.selectedCard;
        if (selectedMonster.getMode().getLabel().equals(position))
            return "this card is already in the wanted position";
        if (selectedMonster.getIsSwitched())
            return "you already changed this card position in this turn";
        if (selectedMonster.getMode() == Mode.ATTACK)
            selectedMonster.setMode(Mode.DEFENSE);
        else
            selectedMonster.setMode(Mode.ATTACK);
        return "monster card position changed successfully";
    }
}
