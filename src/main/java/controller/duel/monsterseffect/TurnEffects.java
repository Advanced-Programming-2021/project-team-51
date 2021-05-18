package controller.duel.monsterseffect;

import models.Board;
import models.cards.Card;
import models.cards.CardType;
import models.cards.monsters.MonsterCard;

import java.util.ArrayList;
import java.util.Scanner;

public class TurnEffects {
    private static final ArrayList<MonsterCard> scanners = new ArrayList<>();
    public static void run(Board myBoard, Board rivalBoard) {
        for (MonsterCard monsterCard : myBoard.getMonsterCards()) {
            if (monsterCard.getName().equals("Scanner") || scanners.contains(monsterCard))
                affectScanner(rivalBoard, monsterCard);
            else if (monsterCard.getName().equals("Herald of Creation"))
                affectHeraldOfCreation(myBoard);
        }
    }

    private static void affectHeraldOfCreation(Board myBoard) {
        if (!isAnyMonsterInGrave(myBoard))
            return;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to summon one of your dead monsters?(y/n)");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to summon one of your dead monsters?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        //TODO Summon a monster from graveyard
    }

    private static void affectScanner(Board rivalBoard, MonsterCard scannerCard) {
        if (!isAnyMonsterInGrave(rivalBoard))
            return;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to turn into a rival dead monster?(y/n)");
        String answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want to turn into a rival dead monster?(y/n)");
            answer = scanner.nextLine();
        }
        if (answer.equals("n"))
            return;
        System.out.println("choose a card from rival graveyard");
        String cardName = scanner.nextLine();
        for (Card graveyardCard : rivalBoard.getGraveyardCards()) {
            MonsterCard graveyardMonster;
            if (graveyardCard instanceof MonsterCard && cardName.equals(graveyardCard.getName())) {
                graveyardMonster = (MonsterCard) graveyardCard;
                scannerCard.setName(graveyardMonster.getName());
                scannerCard.setDescription(graveyardMonster.getDescription());
                scannerCard.setPrice(graveyardMonster.getPrice());
                scannerCard.setLevel(graveyardMonster.getLevel());
                scannerCard.setAttribute(graveyardMonster.getAttribute());
                scannerCard.setCardType(CardType.MONSTER);
                scannerCard.setMonsterType(graveyardMonster.getMonsterType());
                scannerCard.setAttackPoint(graveyardMonster.getAttackPoint());
                scannerCard.setDefensePoint(graveyardMonster.getDefensePoint());
                scannerCard.setTrait(graveyardMonster.getTrait());
            }
        }
    }

    public static boolean isAnyMonsterInGrave(Board board) {
        for (Card graveyardCard : board.getGraveyardCards()) {
            if (graveyardCard instanceof MonsterCard)
                return true;
        }
        return false;
    }
}
