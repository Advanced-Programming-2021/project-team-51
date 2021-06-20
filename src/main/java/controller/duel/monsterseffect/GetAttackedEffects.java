package controller.duel.monsterseffect;

import models.Board;
import models.cards.Location;
import models.cards.monsters.MonsterCard;

import java.util.ArrayList;
import java.util.Scanner;

public class GetAttackedEffects {

    private static final ArrayList<MonsterCard> hasBeenAttacked = new ArrayList<>();

    public static boolean run(MonsterCard attackerCard, MonsterCard attackedCard, Board myBoard, Board rivalBoard) {
        if ((attackedCard.getName().equals("Yomi Ship") || attackedCard.getName().equals("Exploder Dragon"))
                && attackedCard.getLocation().equals(Location.GRAVEYARD))
            return killAttacker(attackedCard, attackerCard, myBoard);
        else if (attackedCard.getName().equals("Suijin") && !attackedCard.getIsHidden())
            return affectSuijin(attackedCard);
        else if (attackedCard.getName().equals("Marshmallon"))
            return affectMarshMelon(myBoard, attackedCard);
        else if (attackedCard.getName().equals("Texchanger"))
            return affectTexChanger(rivalBoard);
        else if (attackedCard.getName().equals("Command Knight") && !attackedCard.getIsHidden())
            return affectCommandKnight(rivalBoard);
        return true;
    }

    private static boolean affectCommandKnight(Board rivalBoard) {
        return rivalBoard.getMonsters().size() != 1;
    }

    private static boolean affectTexChanger(Board rivalBoard) {
        //TODO Summon a normal Cyberse monster
        return false;
    }

    private static boolean affectMarshMelon(Board myBoard, MonsterCard attackedCard) {
        attackedCard.setLocation(Location.FIELD);

        if (!attackedCard.getIsHidden())
            return true;

        myBoard.getOwner().setLifePoint(myBoard.getOwner().getLifePoint() - 1000);
        return true;
    }

    private static boolean affectSuijin(MonsterCard attackedCard) {
        Scanner scanner = new Scanner(System.in);
        if (hasBeenAttacked.contains(attackedCard))
            return true;
        System.out.println("Do you want to activate Suijin?(y/n)");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to activate Suijin?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return true;
        attackedCard.setLocation(Location.FIELD);
        hasBeenAttacked.add(attackedCard);
        return false;
    }

    private static boolean killAttacker(MonsterCard attackedCard, MonsterCard attackerCard, Board myBoard) {
        attackerCard.setLocation(Location.GRAVEYARD);
        myBoard.removeMonster(myBoard.getMonsterIndexInMonsterBoard(attackedCard));
        return attackedCard.getName().equals("Yomi Ship");
    }
}
