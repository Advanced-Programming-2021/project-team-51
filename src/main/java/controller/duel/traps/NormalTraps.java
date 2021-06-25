package controller.duel.traps;

import models.Board;
import models.Chain;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.util.Random;

public class NormalTraps {
    public static boolean activate(SpellTrapCard trapCard, Board myBoard, Board rivalBoard) {
        if (trapCard.getName().equals("Mind Crush"))
            Chain.addSpell(trapCard, myBoard, rivalBoard, null, null);
        else if (trapCard.getName().equals("Call of The Haunted"))
            Chain.addSpell(trapCard, myBoard, rivalBoard, null, null);
        else
            return false;

        trapCard.setLocation(Location.GRAVEYARD);
        myBoard.removeSpellAndTrap(myBoard.getSpellTrapIndexInSpellTrapBoard(trapCard));
        return true;
    }

    public static void activateCallOfHaunted(Board myBoard) {
        //TODO summon a card from graveyard
    }

    public static void activateMindCrush(Board myBoard, Board rivalBoard) {
        boolean hasFoundName = false;
        //TODO get a card name
        String cardName = "";
        for (MonsterCard monsterCard : rivalBoard.getMonsters()) {
            if (monsterCard.getName().equals(cardName)) {
                monsterCard.setLocation(Location.GRAVEYARD);
                rivalBoard.removeMonster(rivalBoard.getMonsterIndexInMonsterBoard(monsterCard));
                hasFoundName = true;
            }
        }
        for (SpellTrapCard spellTrapCard : rivalBoard.getSpellTraps()) {
            if (spellTrapCard.getName().equals(cardName)) {
                spellTrapCard.setLocation(Location.GRAVEYARD);
                rivalBoard.removeSpellAndTrap(rivalBoard.getSpellTrapIndexInSpellTrapBoard(spellTrapCard));
                hasFoundName = true;
            }
        }
        if (!hasFoundName) {
            Random random = new Random();
            int index = random.nextInt(myBoard.getHandCards().size());
            Card card = myBoard.getHandCards().get(index);
            card.setLocation(Location.GRAVEYARD);
            myBoard.getHandCards().remove(card);
            myBoard.addToGraveyard(card);
        }
    }
}
