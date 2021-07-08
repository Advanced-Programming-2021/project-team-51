package controller.duel.monsterseffect;

import models.Board;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.SpecialSummonStatus;

import java.util.Scanner;

public class SummonEffects {

    public static void run(MonsterCard summonedMonster, Board rivalBoard, Board myBoard) {
        if (summonedMonster.getName().equals("Man-Eater Bug") && !summonedMonster.getIsHidden())
            affectManEaterBug(rivalBoard);
        else if (summonedMonster.getName().equals("Beast King Barbaros") && summonedMonster.getAttackPoint() == 3000)
            affectBeastKingBarbaros(rivalBoard);
        else if (summonedMonster.getName().equals("The Calculator"))
            setCalculatorAttackPoint(myBoard, summonedMonster);
        else if (summonedMonster.getName().equals("Terratiger, the Empowered Warrior"))
            affectTerratiger(myBoard);
    }

    private static void affectTerratiger(Board myBoard) {
        System.out.println("Do you want to summon a monster?(y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to summon a monster?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        myBoard.getEffectsStatus().setSpecialSummonStatus(SpecialSummonStatus.NORMAL_LEVEL4L_FROM_HAND);
    }

    private static void setCalculatorAttackPoint(Board myBoard, MonsterCard calculator) {
        int levelsSum = 0;
        for (MonsterCard monsterCard : myBoard.getMonsters()) {
            levelsSum += monsterCard.getLevel();
        }
        calculator.setAttackPoint(levelsSum * 300);
    }

    private static void affectBeastKingBarbaros(Board rivalBoard) {
        for (int i = rivalBoard.getMonsters().size() - 1; i >= 0; i--)
            rivalBoard.removeMonster(i);
        for (int i = rivalBoard.getSpellTraps().size() - 1; i >= 0; i--)
            rivalBoard.removeSpellAndTrap(i);
        for (Card graveyardCard : rivalBoard.getGraveyardCards()) {
            graveyardCard.setLocation(Location.GRAVEYARD);
        }
    }

    private static void affectManEaterBug(Board rivalBoard) {
        System.out.println("Do you want to destroy one of rivals card?(y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to destroy one of rivals card?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        System.out.println("enter monster index:");
        String index = scanner.nextLine();
        while (!index.matches("(\\d)") || Integer.parseInt(index) >= rivalBoard.getMonsters().size()
                || rivalBoard.getMonsters().get(Integer.parseInt(index)) == null) {
            System.out.println("enter monster index:");
            index = scanner.nextLine();
        }
        rivalBoard.getMonsters().get(Integer.parseInt(index)).setLocation(Location.GRAVEYARD);
        rivalBoard.removeMonster(Integer.parseInt(index));
    }
}
