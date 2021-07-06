package controller.duel.effects.monsterseffect;

import models.Board;
import models.cards.Card;
import models.cards.CardType;
import models.cards.Location;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.SpecialSummonStatus;

import java.util.Scanner;

public class TurnEffects {
    public static void run(Board myBoard, Board rivalBoard) {
        for (MonsterCard monsterCard : myBoard.getMonsters()) {
            if (monsterCard.getName().equals("Scanner"))
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
        System.out.println("enter sacrificed card from hand index:");
        String index = scanner.nextLine();
        while (!index.matches("(\\d)") || Integer.parseInt(index) >= myBoard.getHandCards().size()
                || myBoard.getHandCards().get(Integer.parseInt(index)) == null) {
            System.out.println("enter sacrificed card from hand index:");
            index = scanner.nextLine();
        }
        myBoard.getHandCards().get(Integer.parseInt(index)).setLocation(Location.GRAVEYARD);
        myBoard.removeCardsFromHand(Integer.parseInt(index));
        myBoard.getEffectsStatus().setSpecialSummonStatus(SpecialSummonStatus.LEVEL7H_FROM_GRAVE);
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
