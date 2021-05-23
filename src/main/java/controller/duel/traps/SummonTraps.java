package controller.duel.traps;

import models.Board;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.SummonType;
import models.cards.spelltrap.SpellTrapCard;

public class SummonTraps {
    public static boolean activate(SpellTrapCard spellTrapCard, MonsterCard summonedCard, Board myBoard, Board rivalBoard) {
        boolean hasActivated = false;
        if (spellTrapCard.getName().equals("Trap Hole"))
            hasActivated = activateTrapHole(summonedCard, rivalBoard);
        else if (spellTrapCard.getName().equals("Torrential Tribute"))
            hasActivated = activateTorrentialTribute(myBoard, rivalBoard);
        else if (spellTrapCard.getName().equals("Solemn Warning"))
            hasActivated = activateSolemnWarning(summonedCard, myBoard, rivalBoard);

        if (hasActivated) {
            spellTrapCard.setLocation(Location.GRAVEYARD);
            myBoard.getSpellTrapCards().remove(spellTrapCard);
            myBoard.addToGraveyard(spellTrapCard);
        }
        return hasActivated;
    }

    private static boolean activateTrapHole(MonsterCard summonedCard, Board rivalBoard) {
        if (summonedCard.getAttackPoint() > 1000 &&
                (summonedCard.getSummonType().equals(SummonType.FLIP_SUMMON) || summonedCard.getSummonType().equals(SummonType.NORMAL_SUMMON))) {
            summonedCard.setLocation(Location.GRAVEYARD);
            rivalBoard.getMonsterCards().remove(summonedCard);
            rivalBoard.addToGraveyard(summonedCard);
        }
        return true;
    }

    private static boolean activateTorrentialTribute(Board myBoard, Board rivalBoard) {
        removeAllMonsters(myBoard);
        removeAllMonsters(rivalBoard);
        return true;
    }

    private static boolean activateSolemnWarning(MonsterCard summonedCard, Board myBoard, Board rivalBoard) {
        myBoard.getOwner().setLifePoint(myBoard.getOwner().getLifePoint() - 2000);
        summonedCard.setLocation(Location.GRAVEYARD);
        rivalBoard.getMonsterCards().remove(summonedCard);
        rivalBoard.addToGraveyard(summonedCard);
        return true;
    }

    private static void removeAllMonsters(Board board) {
        for (MonsterCard monsterCard : board.getMonsterCards()) {
            monsterCard.setLocation(Location.GRAVEYARD);
            board.getMonsterCards().remove(monsterCard); // possible runtime error
            board.addToGraveyard(monsterCard);
        }
    }
}
