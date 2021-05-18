package controller.duel.spells;

import models.Board;
import models.cards.spelltrap.SpellTrapCard;

import java.util.ArrayList;

public class MessengerOfPeace {
    private static final ArrayList<SpellTrapCard> messengers = new ArrayList<>();

    public static void checkSummoned(SpellTrapCard spellTrapCard, Board myBoard) {
        if (!spellTrapCard.getName().equals("Messenger of peace"))
            return;

        myBoard.getOwner().setCanStrongRivalAttack(false);
        messengers.add(spellTrapCard);
    }

    public static void remove(SpellTrapCard spellTrapCard) {
        if (spellTrapCard.getName().equals("Messenger of peace"))
            messengers.remove(spellTrapCard);
    }

    public static void checkStandBy(Board myBoard) {
        for (SpellTrapCard spellTrapCard : myBoard.getSpellTrapCards()) {
            if(messengers.contains(spellTrapCard))
                myBoard.getOwner().setLifePoint(myBoard.getLifePoints() - 100);
        }
    }
}
