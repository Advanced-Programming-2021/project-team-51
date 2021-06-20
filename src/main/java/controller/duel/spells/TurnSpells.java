package controller.duel.spells;

import models.Board;
import models.cards.MakeCards;
import models.cards.spelltrap.SpellTrapCard;

import java.util.HashMap;
import java.util.Map;

public class TurnSpells {
    private static final HashMap<SpellTrapCard, Integer> turnsActivated = new HashMap<>();

    public static void activate(SpellTrapCard spellCard, Board myBoard, Board rivalBoard, boolean isAnyMonsterDead) {
        if (spellCard.getName().equals("Swords of Revealing Light"))
            activateSwordsOfRevealingLight(myBoard);
        else if (spellCard.getName().equals("Supply Squad"))
            activateSupplySquad(isAnyMonsterDead, myBoard);

    }

    private static void activateSupplySquad(boolean isAnyMonsterDead, Board myBoard) {
        if (!isAnyMonsterDead)
            return;
        //Take out a card
    }

    public static void checkTurn(Board board) {

        for (Map.Entry turnsCard : turnsActivated.entrySet()) {
            SpellTrapCard spellCard = (SpellTrapCard) turnsCard.getKey();

            if (!board.getSpellTraps().contains(spellCard))
                continue;

            if ((Integer) turnsCard.getValue() < 3) {
                turnsCard.setValue((Integer) turnsCard.getValue() + 1);
                continue;
            }

            if (spellCard.getName().equals("Swords of Revealing Light"))
                deActivateSwordsOfRevealingLight(board);
        }
    }

    private static void activateSwordsOfRevealingLight(Board myBoard) {
        myBoard.getOwner().setRivalReveled(true);
        myBoard.getOwner().setCanRivalAttack(false);
    }

    private static void deActivateSwordsOfRevealingLight(Board board) {
        board.getOwner().setRivalReveled(false);
        board.getOwner().setCanRivalAttack(true);
    }
}
