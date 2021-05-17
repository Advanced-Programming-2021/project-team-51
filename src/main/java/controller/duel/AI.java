package controller.duel;

import java.util.ArrayList;

import models.Deck;
import models.Player;
import models.Board;
import models.cards.Card;
import models.cards.CardType;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;

abstract public class AI {
    protected Deck deck;
    protected Board board;
    protected String nickname;

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getName() {
        return this.nickname;
    }

    protected int getTrapCard(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.TRAP)
                return i;

        return -1;
    }

    protected int getSpellCard(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.SPELL)
                return i;

        return -1;
    }

    protected boolean canSpecialSummonLevel2() {
        ArrayList<MonsterCard> cards = new ArrayList<>();
        cards = this.board.getMonsterCards();
        int counter = 0;
        for (MonsterCard card : cards) {
            if (card.getLevel() < 5)
                counter++;
        }
        if (counter > 1)
            return true;
        return false;
    }

    protected boolean canSpecialSummonLevel1() {
        ArrayList<MonsterCard> cards = new ArrayList<>();
        cards = this.board.getMonsterCards();
        int counter = 0;
        for (MonsterCard card : cards) {
            if (card.getLevel() < 5)
                counter++;
        }
        if (counter > 0)
            return true;
        return false;
    }

    protected void sacrificeWeakestMonster() {
        ArrayList<MonsterCard> cards = new ArrayList<>();
        cards = this.board.getMonsterCards();
        int minimum = 10;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getLevel() < minimum) {
                minimum = cards.get(i).getLevel();
                index = i;
            }
        if (index != -1)
            this.board.removeMonster(index);
    }

    protected int getBestMonsterCard(ArrayList<Card> cards) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) cards.get(i);
                if (monster.getLevel() > maximum) {
                    if (monster.getLevel() > 6 && !canSpecialSummonLevel2())
                        continue;
                    if (monster.getLevel() < 7 && monster.getLevel() > 4 && !canSpecialSummonLevel1())
                        continue;
                    maximum = monster.getLevel();
                    index = i;
                }
            }

        return index;

    }

    protected int getBestMonsterToAttack(ArrayList<MonsterCard> monsters) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < monsters.size(); i++)
            if (!monsters.get(i).getHasAttacked() && monsters.get(i).getMode() == Mode.ATTACK
                    && monsters.get(i).getAttackPoint() > maximum) {
                maximum = monsters.get(i).getAttackPoint();
                index = i;
            }

        return index;
    }

    protected boolean isReasonableToAttack(MonsterCard aiMonster, MonsterCard opponentMonster) {
        if (opponentMonster.getMode() == Mode.ATTACK && aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
            return true;
        return false;
    }

    protected int getOpponentMonsterIndex(Player opponent) {
        int index = getBestMonsterToAttack(this.board.getMonsterCards());
        if (index != -1) {
            for (int i = 0; i < opponent.getPlayerBoard().getMonsterCards().size(); i++)
                if (isReasonableToAttack(this.board.getMonsterCards().get(index),
                        opponent.getPlayerBoard().getMonsterCards().get(i)))
                    return i;
        }
        return -1;
    }

}