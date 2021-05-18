package controller.duel.singlePlayer;

import controller.duel.GamePhase;
import models.Board;
import models.Deck;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.Player;

public class EasyBot extends AI {

    public EasyBot(Player opponent) throws CloneNotSupportedException {
        setName("EasyBot");
        setDeck(Deck.generateDeck(true));
        setBoard(new Board(this));
        setOpponent(opponent);
    }

    public void summonBestMonster() {
        int index = getBestMonsterCard(this.board.getHandCards());
        if (index != -1 && this.board.hasMonsterZoneSpace()) {
            MonsterCard monster = (MonsterCard) this.board.getHandCards().get(index);
            int level = monster.getLevel();
            if (level > 6) {
                sacrificeWeakestMonster();
                sacrificeWeakestMonster();
            } else if (level < 7 && level > 4)
                sacrificeWeakestMonster();

            this.board.summonOrSetMonster(index);
        }
    }

    public void attack() {
        while (getOpponentMonsterIndexEasy(opponent) != -1) {
            int monsterIndex = getBestMonsterToAttack(this.board.getMonsterCards());
            int opponentIndex = getOpponentMonsterIndexEasy(opponent);
            int opponentMonsterPower;
            int aiMonsterPower = this.board.getMonsterCards().get(monsterIndex).getAttackPoint();
            this.board.getMonsterCards().get(monsterIndex).setHasAttacked(true);
            if (opponent.getPlayerBoard().getMonsterCards().get(opponentIndex).getMode() == Mode.ATTACK)
                opponentMonsterPower = opponent.getPlayerBoard().getMonsterCards().get(opponentIndex).getAttackPoint();
            else
                opponentMonsterPower = opponent.getPlayerBoard().getMonsterCards().get(opponentIndex).getDefensePoint();
            if (opponentMonsterPower > aiMonsterPower) {
                this.board.setLifePoints(this.board.getLifePoints() + aiMonsterPower - opponentMonsterPower);
                this.board.removeMonster(monsterIndex);
            } else if (opponentMonsterPower < aiMonsterPower) {
                opponent.getPlayerBoard().setLifePoints(
                        opponent.getPlayerBoard().getLifePoints() + opponentMonsterPower - aiMonsterPower);
                opponent.getPlayerBoard().removeMonster(opponentIndex);
            } else {
                opponent.getPlayerBoard().removeMonster(opponentIndex);
                this.board.removeMonster(monsterIndex);
            }
        }
    }

    public void checkSpellForActivate(GamePhase phase) {
        for (int i = 0; i < this.board.getSpellTrapCards().size(); i++)
            if (isSpellReasonableToActive(this.board.getSpellTrapCards().get(i).getName(),
                    phase) != ReasonableLevel.NOT_REASONABLE)
                activeSpellTrap(i);
    }

    public void checkTrapForActivate(MonsterCard summoned, MonsterCard attacked) {
        for (int i = 0; i < this.board.getSpellTrapCards().size(); i++)
            if (isTrapReasonableToActive(this.board.getSpellTrapCards().get(i).getName(),
                    summoned, attacked) != ReasonableLevel.NOT_REASONABLE)
                activeSpellTrap(i);
    }

}
