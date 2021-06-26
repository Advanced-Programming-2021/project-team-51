package controller.duel.spells;

import models.Board;
import models.Chain;
import models.cards.CardType;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.Icon;
import models.cards.spelltrap.SpellTrapCard;

public class NormalActivate {

    public static boolean activate(SpellTrapCard spellCard, Board myBoard, Board rivalBoard) {
        if (spellCard.getIsHidden())
            return false;
        if (spellCard.getName().equals("Pot of Greed"))
            Chain.addSpell(spellCard, myBoard, rivalBoard, null, null);
        else if (spellCard.getName().equals("Raigeki"))
            Chain.addSpell(spellCard, myBoard, rivalBoard, null, null);
        else if (spellCard.getName().equals("Harpie's Feather Duster"))
            Chain.addSpell(spellCard, myBoard, rivalBoard, null, null);
        else if (spellCard.getName().equals("Dark Hole"))
            Chain.addSpell(spellCard, myBoard, rivalBoard, null, null);
        else if (spellCard.getName().equals("Terraforming"))
            Chain.addSpell(spellCard, myBoard, rivalBoard, null, null);
        else
            return false;
        removeSpellCard(spellCard, myBoard);
        return true;
    }

    public static void activateTeraforming(Board myBoard) {
        for (int i = myBoard.getDeck().getMainDeck().size() - 1; i >= 0 ; i--) {
            if (myBoard.getDeck().getMainDeck().get(i).getCardType() == CardType.SPELL) {
                SpellTrapCard spellTrapCard = (SpellTrapCard) myBoard.getDeck().getMainDeck().get(i);
                if (spellTrapCard.getIcon() == Icon.FIELD)
                    myBoard.setFieldZone(spellTrapCard);
            }
        }
    }

    public static boolean affectDarkHole(Board myBoard, Board rivalBoard) {
        for (int i = myBoard.getMonsters().size() - 1; i >= 0; i--) {
            myBoard.getMonsters().get(i).setLocation(Location.GRAVEYARD);
            myBoard.removeMonster(i);
        }
        for (int i = rivalBoard.getMonsters().size() - 1; i >= 0; i--) {
            rivalBoard.getMonsters().get(i).setLocation(Location.GRAVEYARD);
            rivalBoard.removeMonster(i);
        }
        return true;
    }

    public static boolean affectHarpie(Board rivalBoard) {
        for (int i = rivalBoard.getSpellTraps().size() - 1; i >= 0; i--) {
            rivalBoard.getSpellTraps().get(i).setLocation(Location.GRAVEYARD);
            rivalBoard.removeSpellAndTrap(i);
        }
        return true;
    }


    public static boolean affectReigeki(Board rivalBoard) {
        for (int i = rivalBoard.getMonsters().size() - 1; i >= 0; i--) {
            rivalBoard.getMonsters().get(i).setLocation(Location.GRAVEYARD);
            rivalBoard.removeMonster(i);
        }
        return true;
    }

    public static boolean affectPotOfGreed(Board myBoard) {
        if (myBoard.getDeck().getMainDeck().size() < 2)
            return false;
        for (int i = 0; i < 2; i++) {
            if (myBoard.getDeck().getMainDeck().get(i).getCardType() == CardType.SPELL)
                myBoard.summonOrSetSpellAndTrap((SpellTrapCard) myBoard.getDeck().getMainDeck().get(i));
            else
                myBoard.summonOrSetMonster((MonsterCard) myBoard.getDeck().getMainDeck().get(i));
        }
        return true;
    }

    public static void removeSpellCard(SpellTrapCard spellCard, Board myBoard) {
        spellCard.setLocation(Location.GRAVEYARD);
        myBoard.removeSpellAndTrap(myBoard.getSpellTrapIndexInSpellTrapBoard(spellCard));
    }

}
