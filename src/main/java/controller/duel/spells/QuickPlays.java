package controller.duel.spells;

import models.Board;
import models.cards.spelltrap.SpellTrapCard;

public class QuickPlays {
    //Didn't handle the same turn not working
    //guessed they will die after affection

    public static void quickActivate(SpellTrapCard spellTrapCard, Board myBoard, Board rivalBoard) {
        if (spellTrapCard.getIsHidden())
            activate(spellTrapCard, myBoard, rivalBoard);
    }

    public static void activate(SpellTrapCard spellTrapCard, Board myBoard, Board rivalBoard) {
            if (spellTrapCard.getName().equals("Twin Twisters"))
                activateTwinTwister(myBoard, rivalBoard);
            else if (spellTrapCard.getName().equals("Mystical space typhoon"))
                activateMysticalSpaceTyphoon(rivalBoard);
    }

    private static void activateMysticalSpaceTyphoon(Board rivalBoard) {
        //TODO Select a card and move to graveyard
    }

    private static void activateTwinTwister(Board myBoard, Board rivalBoard) {
        //TODO Select 3 cards and move to graveyard
    }

}
