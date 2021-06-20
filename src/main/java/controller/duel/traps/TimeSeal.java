package controller.duel.traps;

import models.Board;
import models.cards.Location;
import models.cards.spelltrap.SpellTrapCard;

import java.util.ArrayList;

public class TimeSeal {

    private static final ArrayList<SpellTrapCard> timeSeals = new ArrayList<>();

    public static boolean activate(SpellTrapCard trapCard, Board myBoard) {
        if (!trapCard.getName().equals("Time Seal"))
            return false;
        myBoard.getOwner().setCanRivalPickCard(false);
        timeSeals.add(trapCard);
        return true;
    }

    public static boolean checkTimeSeal(Board myBoard, Board rivalBoard) {
        if (!checkEachBoard(myBoard))
            return checkEachBoard(rivalBoard);
        return true;
    }

    private static boolean checkEachBoard(Board board) {
        if (!board.getOwner().getCanRivalPickCard()) {
            for (SpellTrapCard spellTrapCard : board.getSpellTraps()) {
                if (timeSeals.contains(spellTrapCard)) {
                    timeSeals.remove(spellTrapCard);
                    spellTrapCard.setLocation(Location.GRAVEYARD);
                    board.removeSpellAndTrap(board.getSpellTrapIndexInSpellTrapBoard(spellTrapCard));
                    if (!isThereAnotherTimeSeal(board))
                        board.getOwner().setCanRivalPickCard(true);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isThereAnotherTimeSeal(Board board) {
        for (SpellTrapCard spellTrapCard : board.getSpellTraps()) {
            if (timeSeals.contains(spellTrapCard))
                return true;
        }
        return false;
    }
}
