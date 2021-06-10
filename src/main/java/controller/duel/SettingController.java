package controller.duel;

import models.Player;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

public class SettingController {

    Card selectedCard = null;
    boolean hasSummonedInThisTurn = false;
    Player player;
    GamePhase gamePhase;
    //TODO correct these

    public String set() {
        if (selectedCard instanceof MonsterCard)
            return setMonster();
        else
            return setSpellTrap();
    }

    private String setSpellTrap() {
        if (selectedCard == null)
            return "no card is selected yet";
        if (!player.getPlayerBoard().getHandCards().contains(selectedCard))
            return "you can't set this card";
        if (gamePhase != GamePhase.MAIN1 && gamePhase != GamePhase.MAIN2)
            return "you can't do this action in this phase";
        if (player.getPlayerBoard().getSpellTraps().size() == 5)
            return "spell card zone is full";
        SpellTrapCard selectedSpellTrap = (SpellTrapCard) selectedCard;
        selectedSpellTrap.setLocation(Location.FIELD);
        selectedSpellTrap.setIsHidden(true);
        player.getPlayerBoard().getHandCards().remove(selectedSpellTrap);
        player.getPlayerBoard().getSpellTraps().add(selectedSpellTrap);
        return "set successfully";
    }

    private String setMonster() {
        if (SummonController.checkNormalSummonSetConditions() != null)
            return SummonController.checkNormalSummonSetConditions();
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
        selectedMonster.setLocation(Location.FIELD);
        selectedMonster.setIsHidden(true);
        selectedMonster.setMode(Mode.DEFENSE);
        player.getPlayerBoard().getHandCards().remove(selectedMonster);
        player.getPlayerBoard().getMonsterCards().add(selectedMonster);
        hasSummonedInThisTurn = true;
        return "summoned successfully";
    }

    public String setPosition(String position) {
        if (selectedCard == null)
            return "no card is selected yet";
        if (!player.getPlayerBoard().getMonsterCards().contains((MonsterCard) selectedCard))
            return "you can't change this card position";
        if (gamePhase != GamePhase.MAIN1 && gamePhase != GamePhase.MAIN2)
            return "you can't do this action in this phase";
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
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
