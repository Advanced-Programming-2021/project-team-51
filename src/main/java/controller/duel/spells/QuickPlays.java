package controller.duel.spells;

import models.Chain;
import models.cards.Location;
import models.cards.spelltrap.SpellTrapCard;

import java.util.Scanner;

public class QuickPlays {

    public static boolean activate(SpellTrapCard spellTrapCard, Board myBoard, Board rivalBoard) {
        if (spellTrapCard.getName().equals("Twin Twisters")) {
            if (Chain.getSize() == 0) activateTwinTwister(myBoard, rivalBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null);
        }
        else if (spellTrapCard.getName().equals("Mystical space typhoon")) {
            if (Chain.getSize() == 0) activateMysticalSpaceTyphoon(rivalBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null);
        }
        else if (spellTrapCard.getName().equals("Ring of defense")) {
            if (Chain.getSize() == 0) activateRingOfDefenses(myBoard);
            else Chain.addSpell(spellTrapCard, myBoard, rivalBoard, null);
        }
        else
            return false;
        return true;
    }

    public static void activateRingOfDefenses(Board myBoard) {
        myBoard.getEffectsStatus().setRivalTrapsBlocked(true);
    }

    public static void activateMysticalSpaceTyphoon(Board rivalBoard) {
        removeTrapSpell(rivalBoard);
    }

    public static void activateTwinTwister(Board myBoard, Board rivalBoard) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter sacrificed card index:");
        String index = scanner.nextLine();
        while (!index.matches("(\\d)") || Integer.parseInt(index) >= myBoard.getHandCards().size()
                || myBoard.getHandCards().get(Integer.parseInt(index)) == null) {
            System.out.println("enter sacrificed card index:");
            index = scanner.nextLine();
        }
        myBoard.getHandCards().get(Integer.parseInt(index)).setLocation(Location.GRAVEYARD);
        myBoard.removeCardsFromHand(Integer.parseInt(index));
        System.out.println("Do you want to remove rival spells?(y/any key)");
        String answer = scanner.nextLine();
        for (int i = 0; i < 2; i++) {
            if (answer.equals("y"))
                removeTrapSpell(rivalBoard);
            System.out.println("Do you want to remove rival spells?(y/any key)");
            answer = scanner.nextLine();
        }
    }

    private static void removeTrapSpell(Board rivalBoard) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter spell or trap index:");
        String index = scanner.nextLine();
        while (!index.matches("(\\d)") || Integer.parseInt(index) >= rivalBoard.getSpellTraps().size()
                || rivalBoard.getSpellTraps().get(Integer.parseInt(index)) == null) {
            System.out.println("enter spell or trap index:");
            index = scanner.nextLine();
        }
        rivalBoard.getSpellTraps().get(Integer.parseInt(index)).setLocation(Location.GRAVEYARD);
        rivalBoard.removeSpellAndTrap(Integer.parseInt(index));
    }
}
