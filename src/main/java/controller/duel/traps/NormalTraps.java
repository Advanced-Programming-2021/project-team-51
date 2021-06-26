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
        for (int i = rivalBoard.getMonsters().size() - 1; i >= 0; i--) {
            if (rivalBoard.getMonsters().get(i).getName().equals(cardName)) {
                rivalBoard.getMonsters().get(i).setLocation(Location.GRAVEYARD);
                rivalBoard.removeMonster(i);
                hasFoundName = true;
            }
        }
        for (int i = rivalBoard.getSpellTraps().size() - 1; i >= 0; i--) {
            if (rivalBoard.getSpellTraps().get(i).getName().equals(cardName)) {
                rivalBoard.getSpellTraps().get(i).setLocation(Location.GRAVEYARD);
                rivalBoard.removeSpellAndTrap(i);
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
