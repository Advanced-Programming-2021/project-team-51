package controller.duel.spells;

import models.Board;
import models.Chain;
import models.cards.spelltrap.SpellTrapCard;

public class QuickPlays {

    public static boolean quickActivate(SpellTrapCard spellTrapCard, Board myBoard, Board rivalBoard) {
        if (spellTrapCard.getIsHidden())
            return activate(spellTrapCard, myBoard, rivalBoard);
        return false;
    }

    public static boolean activate(SpellTrapCard spellTrapCard, Board myBoard, Board rivalBoard) {
        if (spellTrapCard.getName().equals("Twin Twisters")) {
            if (Chain.getSize() == 0) activateTwinTwister(myBoard, rivalBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null, null);
        }
        else if (spellTrapCard.getName().equals("Mystical space typhoon")) {
            if (Chain.getSize() == 0) activateMysticalSpaceTyphoon(rivalBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null, null);
        }
        else if (spellTrapCard.getName().equals("Ring of defense")) {
            if (Chain.getSize() == 0) activateRingOfDefenses(myBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null, null);
        }
        else
            return false;
        return true;
    }

    public static void activateRingOfDefenses(Board myBoard) {
        myBoard.getEffectsStatus().setRivalTrapsBlocked(true);
    }

    public static void activateMysticalSpaceTyphoon(Board rivalBoard) {
        //TODO Select a card and move to graveyard
    }

    public static void activateTwinTwister(Board myBoard, Board rivalBoard) {
        //TODO Select 3 cards and move to graveyard
    }


}
