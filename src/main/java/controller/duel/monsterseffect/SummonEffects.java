package controller.duel.monsterseffect;

import models.Board;
import models.cards.Card;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.util.Scanner;

public class SummonEffects {

    public void run(MonsterCard summonedMonster, Board rivalBoard, Board myBoard) {
        if (summonedMonster.getName().equals("Man-Eater Bug") && !summonedMonster.getIsHidden())
            affectManEaterBug(rivalBoard);
        else if (summonedMonster.getName().equals("Beast King Barbaros") && summonedMonster.getAttackPoint() == 3000)
            affectBeastKingBarbaros(rivalBoard);
        else if (summonedMonster.getName().equals("The Calculator"))
            setCalculatorAttackPoint(myBoard, summonedMonster);
        else if (summonedMonster.getName().equals("Terratiger, the Empowered Warrior"))
            affectTerratiger();
    }

    private void affectTerratiger() {
        System.out.println("Do you want to summon a monster?(y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to summon a monster?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        //TODO Summon a level 4 or lower monster in defense mode
    }

    private void setCalculatorAttackPoint(Board myBoard, MonsterCard calculator) {
        int levelsSum = 0;
        for (MonsterCard monsterCard : myBoard.getMonsters()) {
            levelsSum += monsterCard.getLevel();
        }
        calculator.setAttackPoint(levelsSum * 300);
    }

    private void affectBeastKingBarbaros(Board rivalBoard) {
        for (MonsterCard monster: rivalBoard.getMonsters())
            rivalBoard.removeMonster(rivalBoard.getMonsterIndexInMonsterBoard(monster));
        for (SpellTrapCard spellTrapCard: rivalBoard.getSpellTraps())
            rivalBoard.removeSpellAndTrap(rivalBoard.getSpellTrapIndexInSpellTrapBoard(spellTrapCard));
        for (Card graveyardCard : rivalBoard.getGraveyardCards()) {
            graveyardCard.setLocation(Location.GRAVEYARD);
        }
    }

    private void affectManEaterBug(Board rivalBoard) {
        System.out.println("Do you want to destroy one of rivals card?(y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to destroy one of rivals card?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        //TODO choose a rival card and destroy
    }
}
