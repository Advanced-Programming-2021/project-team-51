package controller.duel.spells;

import controller.duel.monsterseffect.TurnEffects;
import models.Board;
import models.Deck;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.Icon;
import models.cards.spelltrap.SpellTrapCard;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NormalActivate {

    private static final HashMap<SpellTrapCard, MonsterCard> changeOfHearts = new HashMap<>();

    public static void run(SpellTrapCard spellCard, Board myBoard, Board rivalBoard) {
        if (spellCard.getIsHidden())
            return;
        boolean hasAffected = false;
        Scanner scanner = new Scanner(System.in);
        if (spellCard.getName().equals("Monster Reborn"))
            hasAffected = affectMonsterReborn(myBoard, scanner);
        else if (spellCard.getName().equals("Terraforming"))
            hasAffected = affectTeraforming(myBoard, scanner);
        else if (spellCard.getName().equals("Pot of Greed"))
            hasAffected = affectPotOfGreed(myBoard);
        else if (spellCard.getName().equals("Raigeki"))
            hasAffected = affectReigeki(rivalBoard);
        else if (spellCard.getName().equals("Change of Heart"))
            hasAffected = affectChangeOfHeart(spellCard, myBoard, rivalBoard);
        else if (spellCard.getName().equals("Harpie's Feather Duster"))
            hasAffected = affectHarpie(rivalBoard);
        else if (spellCard.getName().equals("Dark Hole"))
            hasAffected = affectDarkHole(myBoard, rivalBoard);

        if (hasAffected)
            removeSpellCard(spellCard, myBoard);
    }

    private static boolean affectDarkHole(Board myBoard, Board rivalBoard) {
        for (MonsterCard monsterCard : myBoard.getMonsterCards()) {
            monsterCard.setLocation(Location.GRAVEYARD);
            myBoard.getMonsterCards().remove(monsterCard); //may error
            myBoard.addToGraveyard(monsterCard);
        }
        for (MonsterCard monsterCard : rivalBoard.getMonsterCards()) {
            monsterCard.setLocation(Location.GRAVEYARD);
            rivalBoard.getMonsterCards().remove(monsterCard); //may error
            rivalBoard.addToGraveyard(monsterCard);
        }
        return true;
    }

    private static boolean affectHarpie(Board rivalBoard) {
        for (SpellTrapCard spellTrapCard : rivalBoard.getSpellTrapCards()) {
            spellTrapCard.setLocation(Location.GRAVEYARD);
            rivalBoard.getSpellTrapCards().remove(spellTrapCard);//risk of error
            rivalBoard.addToGraveyard(spellTrapCard);
        }
        return true;
    }

    public void deActivateChangeOfHearts(Board myBoard, Board rivalBoard) {
        for (Map.Entry spellCard : changeOfHearts.entrySet()) {
            //TODO deactivate
            changeOfHearts.remove(spellCard.getKey());
        }
    }

    private static boolean affectChangeOfHeart(SpellTrapCard spellCard, Board myBoard, Board rivalBoard) {
        //TODO control a rival monster :||
        MonsterCard monsterCard = MonsterCard.getMonsterCardByNumber(-999999999); //none sense
        changeOfHearts.put(spellCard, monsterCard);
        return true;
    }

    private static boolean affectReigeki(Board rivalBoard) {
        for (MonsterCard monsterCard : rivalBoard.getMonsterCards()) {
            monsterCard.setLocation(Location.GRAVEYARD);
            rivalBoard.getMonsterCards().remove(monsterCard);
            rivalBoard.addToGraveyard(monsterCard);
        }
        return true;
    }

    private static boolean affectPotOfGreed(Board myBoard) {
        if (myBoard.getDeck().getMainDeck().size() < 2)
            return false;
        //TODO add 2 card from deck
        return true;
    }

    private static boolean affectTeraforming(Board myBoard, Scanner scanner) {
        if (isFieldSpellInDeck(myBoard))
            return false;
        //TODO get a field spell from deck
        SpellTrapCard fieldSpell = SpellTrapCard.getSpellTrapCardByNumber(-999999999); // This is none sense
        fieldSpell.setLocation(Location.FIELD);
        myBoard.getDeck().removeCardFromDeck(true, fieldSpell);
        //TODO add card to field in board
        return true;
    }

    private static void removeSpellCard(SpellTrapCard spellCard, Board myBoard) {
        spellCard.setLocation(Location.GRAVEYARD);
        myBoard.addToGraveyard(spellCard);
        myBoard.getSpellTrapCards().remove(spellCard);
    }

    private static boolean affectMonsterReborn(Board myBoard, Scanner scanner) {
        if (!TurnEffects.isAnyMonsterInGrave(myBoard))
            return false;
        //TODO Summon a card from graveyard
        return true;
    }

    private static boolean isFieldSpellInDeck(Board board) {
        for (Card card : board.getDeck().getMainDeck()) {
            if (card instanceof SpellTrapCard) {
                SpellTrapCard spellCard = (SpellTrapCard) card;
                if (spellCard.getIcon().equals(Icon.FIELD))
                    return true;
            }
        }
        return false;
    }
}
