package controller.duel.traps;

import models.Board;
import models.Chain;
import models.cards.Location;
import models.cards.spelltrap.SpellTrapCard;

import java.util.ArrayList;

public class TimeSeal {

    public static boolean activate(SpellTrapCard trapCard, Board myBoard) {
        if (trapCard.getName().equals("Time Seal"))
            Chain.addSpell(trapCard, myBoard, null, null, null);
        else
            return false;
        return true;
    }

    public static void activateTimeSeal(Board myBoard) {
        myBoard.getEffectsStatus().setCanRivalPickCard(false);
    }

}
