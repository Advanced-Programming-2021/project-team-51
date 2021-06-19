package controller.duel.singlePlayer;

import models.Deck;
import controller.duel.GamePhase;
import models.Board;
import models.Player;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.Mode;

public class HardBot extends AI {

    public HardBot(Player opponent) throws CloneNotSupportedException {
        setName("HardBot");
        setDeck(Deck.generateDeck(false));
        setBoard(new Board(this));
        setOpponent(opponent);
        aiBot = this;
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

            if (monster.getAttackPoint() < monster.getDefensePoint())
                this.board.getHandCards().get(index).setIsHidden(true);
            this.board.summonOrSetMonster(index);
        }
    }

    public void attack() {
        while (getOpponentMonsterIndexHard(opponent) != -1) {
            int monsterIndex = getBestMonsterToAttack(getAIMonsters());
            int opponentIndex = getOpponentMonsterIndexHard(opponent);
            int opponentMonsterPower;
            int aiMonsterPower = getAIMonsters().get(monsterIndex).getAttackPoint();
            getAIMonsters().get(monsterIndex).setHasAttacked(true);
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
        for (int i = 0; i < getAISpellTraps().size(); i++)
            if (isSpellReasonableToActive(getAISpellTraps().get(i).getName(),
                    phase) == ReasonableLevel.REASONABLE_FOR_HARD)
                activeSpellTrap(i);
    }

    public void checkTrapForActivate(MonsterCard summoned, MonsterCard attacked) {
        for (int i = 0; i < getAISpellTraps().size(); i++)
            if (isTrapReasonableToActive(getAISpellTraps().get(i).getName(),
                    summoned, attacked) == ReasonableLevel.REASONABLE_FOR_HARD)
                activeSpellTrap(i);
    }
}
