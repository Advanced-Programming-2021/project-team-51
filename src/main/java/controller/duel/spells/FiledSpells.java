package controller.duel.spells;

import models.Board;
import models.cards.Card;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.MonsterType;
import models.cards.spelltrap.SpellTrapCard;

import java.util.ArrayList;
import java.util.HashMap;

public class FiledSpells {
    private static final HashMap<SpellTrapCard, Integer> closedForestValues = new HashMap<>();

    private static final ArrayList<SpellTrapCard> activatedFieldSpells = new ArrayList<>();


    public static void check(Board myBoard, Board rivalBoard) {
        removeDeadFromList(myBoard, rivalBoard);
        activate(myBoard, rivalBoard);
        activate(rivalBoard, myBoard);
    }

    private static void activate(Board myBoard, Board rivalBoard) {
        for (SpellTrapCard spellTrapCard : myBoard.getSpellTrapCards()) {
            if (activatedFieldSpells.contains(spellTrapCard))
                continue;
            if (spellTrapCard.getName().equals("Yami"))
                activateYami(myBoard, rivalBoard);
            else if (spellTrapCard.getName().equals("Forest"))
                activateForest(myBoard, rivalBoard);
            else if (spellTrapCard.getName().equals("Closed Forest"))
                activateClosedForest(spellTrapCard, myBoard);
            else if (spellTrapCard.getName().equals("Umiiruka"))
                activateUmirika(myBoard, rivalBoard);
            activatedFieldSpells.add(spellTrapCard);
        }
    }

    private static void activateYami(Board myBoard, Board rivalBoard) {
        //FIEND
        changeAttackDefensePoints(myBoard, true, MonsterType.FIEND, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.FIEND, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.FIEND, 200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.FIEND, 200);
        //SPELL CASTER
        changeAttackDefensePoints(myBoard, true, MonsterType.SPELL_CASTER, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.SPELL_CASTER, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.SPELL_CASTER, 200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.SPELL_CASTER, 200);
        //Fairy
        changeAttackDefensePoints(myBoard, true, MonsterType.FAIRY, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.FAIRY, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.FAIRY, -200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.FAIRY, -200);
    }

    private static void activateForest(Board myBoard, Board rivalBoard) {
        //INSECT
        changeAttackDefensePoints(myBoard, true, MonsterType.INSECT, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.INSECT, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.INSECT, 200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.INSECT, 200);
        //BEAST
        changeAttackDefensePoints(rivalBoard, false, MonsterType.BEAST, 200);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.BEAST, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.BEAST, 200);
        //BEAST_WARRIOR
        changeAttackDefensePoints(rivalBoard, false, MonsterType.BEAST_WARRIOR, 200);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST_WARRIOR, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.BEAST_WARRIOR, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.BEAST_WARRIOR, 200);
    }

    private static void activateClosedForest(SpellTrapCard spellTrapCard, Board myBoard) {
        int value = 0;
        for (Card graveyardCard : myBoard.getGraveyardCards()) {
            if (graveyardCard instanceof MonsterCard)
                value++;
        }
        value *= 100;
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST_WARRIOR, value);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST, value);
        closedForestValues.put(spellTrapCard, value);
    }

    private static void activateUmirika(Board myBoard, Board rivalBoard) {
        //AQUA
        changeAttackDefensePoints(myBoard, true, MonsterType.AQUA, 500);
        changeAttackDefensePoints(myBoard, false, MonsterType.AQUA, -400);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.AQUA, 500);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.AQUA, -400);
    }

    private static void removeDeadFromList(Board myBoard, Board rivalBoard) {
        for (SpellTrapCard activatedFieldSpell : activatedFieldSpells) {
            if (myBoard.getSpellTrapCards().contains(activatedFieldSpell) || rivalBoard.getSpellTrapCards().contains(activatedFieldSpell))
                continue;
            if (activatedFieldSpell.getName().equals("Yami"))
                deactivateYami(myBoard, rivalBoard);
            else if (activatedFieldSpell.getName().equals("Forest"))
                deactivateForest(myBoard, rivalBoard);
            else if (activatedFieldSpell.getName().equals("Closed Forest"))
                deactivateClosedForest(activatedFieldSpell, myBoard);
            else if (activatedFieldSpell.getName().equals("Umiiruka"))
                deactivateUmirika(myBoard, rivalBoard);
        }
    }

    private static void deactivateYami(Board myBoard, Board rivalBoard) {
        //FIEND
        changeAttackDefensePoints(myBoard, true, MonsterType.FIEND, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.FIEND, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.FIEND, -200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.FIEND, -200);
        //SPELL CASTER
        changeAttackDefensePoints(myBoard, true, MonsterType.SPELL_CASTER, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.SPELL_CASTER, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.SPELL_CASTER, -200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.SPELL_CASTER, -200);
        //Fairy
        changeAttackDefensePoints(myBoard, true, MonsterType.FAIRY, 200);
        changeAttackDefensePoints(myBoard, false, MonsterType.FAIRY, 200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.FAIRY, 200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.FAIRY,  200);
    }

    private static void deactivateForest(Board myBoard, Board rivalBoard) {
        //INSECT
        changeAttackDefensePoints(myBoard, true, MonsterType.INSECT, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.INSECT, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.INSECT, -200);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.INSECT, -200);
        //BEAST
        changeAttackDefensePoints(rivalBoard, false, MonsterType.BEAST, -200);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.BEAST, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.BEAST, -200);
        //BEAST_WARRIOR
        changeAttackDefensePoints(rivalBoard, false, MonsterType.BEAST_WARRIOR, -200);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST_WARRIOR, -200);
        changeAttackDefensePoints(myBoard, false, MonsterType.BEAST_WARRIOR, -200);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.BEAST_WARRIOR, -200);
    }

    private static void deactivateClosedForest(SpellTrapCard activatedFieldSpell, Board myBoard) {
        int value = closedForestValues.get(activatedFieldSpell);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST_WARRIOR, -value);
        changeAttackDefensePoints(myBoard, true, MonsterType.BEAST, -value);
        closedForestValues.remove(activatedFieldSpell);
    }

    private static void deactivateUmirika(Board myBoard, Board rivalBoard) {
        //AQUA
        changeAttackDefensePoints(myBoard, true, MonsterType.AQUA, -500);
        changeAttackDefensePoints(myBoard, false, MonsterType.AQUA, 400);
        changeAttackDefensePoints(rivalBoard, true, MonsterType.AQUA, -500);
        changeAttackDefensePoints(rivalBoard, false, MonsterType.AQUA, 400);
    }

    private static void changeAttackDefensePoints(Board board, boolean isAttackPoint, MonsterType monsterType, int value) {
        for (MonsterCard monsterCard : board.getMonsterCards()) {
            if (!monsterCard.getMonsterType().equals(monsterType))
                continue;
            if (isAttackPoint)
                monsterCard.setAttackPoint(monsterCard.getAttackPoint() + value);
            else
                monsterCard.setDefensePoint(monsterCard.getDefensePoint() + value);
        }
    }
}
