package controller.duel.spells;

import models.Board;
import models.cards.spelltrap.SpellTrapCard;

public class SpellAbsorption {
    public static void check(Board myBoard , Board rivalBoard) {
        activateSpellAbsorption(myBoard);
        activateSpellAbsorption(rivalBoard);
    }

    private static void activateSpellAbsorption(Board board) {
        for (SpellTrapCard spellTrapCard : board.getSpellTraps()) {
            if (spellTrapCard.getName().equals("Spell Absorption"))
                board.getOwner().setLifePoint(board.getOwner().getLifePoint() + 500);
            //TODO what about ai
        }
    }
}
