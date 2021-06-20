package controller.duel.traps;

import models.Board;
import models.cards.Location;
import models.cards.spelltrap.SpellTrapCard;

public class MagicJammer {
    public static boolean activate(SpellTrapCard trapCard, SpellTrapCard spellCard, Board myBoard, Board rivalBoard) {
        if (!trapCard.getName().equals("Magic Jammer"))
            return false;
        removeCard(trapCard, myBoard);
        removeCard(spellCard, rivalBoard);
        return true;
    }

    private static void removeCard(SpellTrapCard card, Board board) {
        card.setLocation(Location.GRAVEYARD);
        board.removeSpellAndTrap(board.getSpellTrapIndexInSpellTrapBoard(card));
    }
}
