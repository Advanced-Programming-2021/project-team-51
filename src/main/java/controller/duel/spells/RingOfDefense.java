package controller.duel.spells;

import models.Board;
import models.cards.spelltrap.SpellTrapCard;

public class RingOfDefense {
    public static boolean check(Board myBoard) {
        for (SpellTrapCard spellTrapCard : myBoard.getSpellTrapCards()) {
            if (spellTrapCard.getName().equals("Ring of defense"))
                return true;
        }
        return false;
    }
}
