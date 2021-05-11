package controller.duel;

import models.Board;
import models.Deck;
import models.cards.monsters.MonsterCard;
import models.Player;

public class EasyBot extends AI {

    public EasyBot(Deck deck) throws CloneNotSupportedException {
        setName("EasyBot");
        setDeck(deck);
        setBoard(new Board(this));
    }

    public void summonSpellTrapIfCan() {
        int indexTrap = getTrapCard(this.board.getHandCards());
        int indexSpell = getSpellCard(this.board.getHandCards());
        if (indexTrap != -1 && this.board.hasSpellTrapZoneSpace()) {
            this.board.getHandCards().get(indexTrap).setIsHidden(true);
            this.board.summonOrSetSpellAndTrap(indexTrap);
            if (indexSpell != -1 && this.board.hasSpellTrapZoneSpace()) {
                this.board.getHandCards().get(indexSpell).setIsHidden(true);
                this.board.summonOrSetSpellAndTrap(indexSpell);
            }
        }
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

            this.board.summonOrSetMonser(index);
        }
    }

    public void attack(Player opponent) {
        while (getOpponentMonsterIndex(opponent) != -1) {
            int monsterIndex = getBestMonsterToAttack(this.board.getMonsterCards());
            int opponentIndex = getOpponentMonsterIndex(opponent);
            this.board.getMonsterCards().get(monsterIndex).setHasAttacked(true);
            opponent.getPlayerBoard()
                    .setLifePoints(opponent.getPlayerBoard().getLifePoints()
                            + opponent.getPlayerBoard().getMonsterCards().get(opponentIndex).getAttackPoint()
                            - this.board.getMonsterCards().get(monsterIndex).getAttackPoint());
            opponent.getPlayerBoard().removeMonser(opponentIndex);
        }
    }

}
