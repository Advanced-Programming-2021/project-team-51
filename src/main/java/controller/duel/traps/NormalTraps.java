package controller.duel.traps;

import models.Board;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.util.Random;

public class NormalTraps {
    public static boolean activate(SpellTrapCard trapCard, Board myBoard, Board rivalBoard) {
        boolean hasActivated = false;
        if (trapCard.getName().equals("Mind Crush"))
            hasActivated = activateMindCrush(myBoard, rivalBoard);
        else if (trapCard.getName().equals("Call of The Haunted"))
            hasActivated = activateCallOfHaunted(myBoard);

        if (hasActivated) {
            trapCard.setLocation(Location.GRAVEYARD);
            myBoard.addToGraveyard(trapCard);
            myBoard.getSpellTrapCards().remove(trapCard);
        }
        return hasActivated;
    }

    private static boolean activateCallOfHaunted(Board myBoard) {
        //TODO summon a card from graveyard
        return true;
    }

    private static boolean activateMindCrush(Board myBoard, Board rivalBoard) {
        boolean hasFoundName = false;
        //TODO get a card name
        String cardName = "";
        for (MonsterCard monsterCard : rivalBoard.getMonsterCards()) {
            if (monsterCard.getName().equals(cardName)) {
                monsterCard.setLocation(Location.GRAVEYARD);
                rivalBoard.getMonsterCards().remove(monsterCard); //possible runtime error
                rivalBoard.addToGraveyard(monsterCard);
                hasFoundName = true;
            }
        }
        for (SpellTrapCard spellTrapCard : rivalBoard.getSpellTrapCards()) {
            if (spellTrapCard.getName().equals(cardName)) {
                spellTrapCard.setLocation(Location.GRAVEYARD);
                rivalBoard.getSpellTrapCards().remove(spellTrapCard); //possible runtime error
                rivalBoard.addToGraveyard(spellTrapCard);
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
        return true;
    }
}
