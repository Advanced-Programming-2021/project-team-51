package controller.duel.traps;

import models.Board;
import models.cards.Location;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

public class GetAttackedTraps {
    public static boolean activate(SpellTrapCard trapCard, MonsterCard attackerCard, Board myBoard, Board rivalBoard) {
        boolean hasActivated = false;
        if (trapCard.getName().equals("Magic Cylinder"))
            hasActivated = activateMagicCylinder(attackerCard, rivalBoard);
        else if (trapCard.getName().equals("Mirror Force"))
            hasActivated = activateMirrorForce(rivalBoard);
        else if (trapCard.getName().equals("Negate Attack"))
            hasActivated = activateNegateAttack();
        if (hasActivated) {
            trapCard.setLocation(Location.GRAVEYARD);
            myBoard.getSpellTrapCards().remove(trapCard);
            myBoard.addToGraveyard(trapCard);
        }
        return hasActivated;
    }

    private static boolean activateMagicCylinder(MonsterCard attacker, Board rivalBoard) {
        rivalBoard.getOwner().setLifePoint(rivalBoard.getOwner().getLifePoint() - attacker.getAttackPoint());
        return true;
    }

    private static boolean activateMirrorForce(Board rivalBoard) {
        for (MonsterCard monsterCard : rivalBoard.getMonsterCards()) {
            if (monsterCard.getMode().equals(Mode.ATTACK)) {
                monsterCard.setLocation(Location.GRAVEYARD);
                rivalBoard.getMonsterCards().remove(monsterCard); //possible runtime error
                rivalBoard.addToGraveyard(monsterCard);
            }
        }
        return true;
    }

    private static boolean activateNegateAttack() {
        return true;
    }
}
