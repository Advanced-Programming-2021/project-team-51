package controller.duel;

import models.Player;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;

public class SummonController {

    static Card selectedCard = null;
    static boolean hasSummonedInThisTurn = false;
    static Player player;
    static GamePhase gamePhase;
    //TODO correct these

    public String summon() {
        if (checkNormalSummonSetConditions() != null)
            return checkNormalSummonSetConditions();
        return finalizeSummon();
    }

    public String tributeSummon(String tributes) {
        if (checkNormalSummonSetConditions() != null)
            return checkNormalSummonSetConditions();
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
        if ((selectedMonster.getLevel() < 7 && player.getPlayerBoard().getMonsters().size() < 1)
                || selectedMonster.getLevel() > 6 && player.getPlayerBoard().getMonsters().size() < 2)
            return "there are not enough cards for tribute";
        int firstMonster, secondMonster = 0;
        if (tributes.length() == 1)
            firstMonster = Integer.parseInt(tributes);
        else {
            firstMonster = Integer.parseInt(tributes.substring(0, 1));
            secondMonster = Integer.parseInt(tributes.substring(2, 3));
        }
        if (selectedMonster.getLevel() > 6 && secondMonster == 0
                || (selectedMonster.getLevel() > 6 && (player.getPlayerBoard().getMonsterBoard().get(firstMonster - 1) == null
                || player.getPlayerBoard().getMonsterBoard().get(secondMonster - 1) == null))
                || selectedMonster.getLevel() < 7 && player.getPlayerBoard().getMonsterBoard().get(firstMonster - 1) == null)
            return "there are not enough monsters on these addresses";
        return finalizeSummon();
    }

    private String finalizeSummon() {
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
        selectedMonster.setLocation(Location.FIELD);
        selectedMonster.setIsHidden(false);
        selectedMonster.setMode(Mode.ATTACK);
        player.getPlayerBoard().summonOrSetMonster(selectedMonster);
        hasSummonedInThisTurn = true;
        return "summoned successfully";
    }

    public static String checkNormalSummonSetConditions() {
        if (selectedCard == null)
            return "no card is selected yet";
        if (!player.getPlayerBoard().getHandCards().contains(selectedCard)
                || !(selectedCard instanceof MonsterCard))
            return "you can't summon this card";
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
        if (selectedMonster.getLevel() > 4)
            return "you can't summon this card";
        if (gamePhase != GamePhase.MAIN1 && gamePhase != GamePhase.MAIN2)
            return "action not allowed in this phase";
        if (player.getPlayerBoard().getMonsters().size() == 5)
            return "monster card zone is full";
        if (hasSummonedInThisTurn)
            return "you already summoned/set on this turn";
        return null;
    }

    public String flipSummon() {
        if (selectedCard == null)
            return "no card is selected yet";
        if (!player.getPlayerBoard().getMonsters().contains(selectedCard))
            return "you can't change this card position";
        MonsterCard selectedMonster = (MonsterCard) selectedCard;
        if (gamePhase != GamePhase.MAIN1 && gamePhase != GamePhase.MAIN2)
            return "action not allowed in this phase";
        if (!selectedMonster.getIsHidden())
            return "you can't flip this card";
        selectedMonster.setMode(Mode.ATTACK);
        selectedMonster.setIsHidden(false);
        return "flip summoned successfully";
    }
}
