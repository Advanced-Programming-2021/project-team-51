package controller.duel.singlePlayer;

import java.util.ArrayList;

import controller.duel.GamePhase;
import models.Deck;
import models.Player;
import models.Board;
import models.cards.Card;
import models.cards.CardType;
import models.cards.monsters.Attribute;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.MonsterType;
import models.cards.spelltrap.Icon;
import models.cards.spelltrap.SpellTrapCard;

abstract public class AI {
    protected Deck deck;
    protected Board board;
    protected String nickname;
    protected Player opponent;

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

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return this.opponent;
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

    protected int getBestSpecialMonsterIndex(ArrayList<Card> cards, boolean canSpecialSummon2) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) cards.get(i);
                if (monster.getLevel() > 6 && monster.getLevel() > maximum && canSpecialSummon2) {
                    maximum = monster.getLevel();
                    index = i;
                } else if (monster.getLevel() > 4 && monster.getLevel() < 7 && monster.getLevel() > maximum) {
                    maximum = monster.getLevel();
                    index = i;
                }
            }
        return index;
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
            this.board.removeMonser(index);
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

    protected ReasonableLevel isReasonableToAttack(MonsterCard aiMonster, MonsterCard opponentMonster) {
        if (opponentMonster.getMode() == Mode.ATTACK && aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (opponentMonster.getMode() == Mode.DEFENSE) {
            /*------that is based on the chance that a card with a certain
            attack point could be able to destroy a set (DEFENSED) card------*/
            int attackChance = (int) Math.random() * 100;
            if (aiMonster.getAttackPoint() > 3400)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 3000 && attackChance > 01)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 2500 && attackChance > 11)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 2000 && attackChance > 23)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 1500 && attackChance > 33)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 1000 && attackChance > 65)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 500 && attackChance > 90)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 0 && attackChance > 98)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            /*------Our HARD Bot takes Risk for Attacking a DEFENSED Card or not
                    but based on what its card attack point is------*/
            if (attackChance > 49)
                return ReasonableLevel.REASONABLE_FOR_EASY;
            /*------But EASY Bot Attacks without Caring about card attack point------*/
        }
        return ReasonableLevel.NOT_REASONABLE;
    }

    public int getOpponentMonsterIndexEasy(Player opponent) {
        int index = getBestMonsterToAttack(this.board.getMonsterCards());
        if (index != -1) {
            for (int i = 0; i < opponent.getPlayerBoard().getMonsterCards().size(); i++)
                if (isReasonableToAttack(this.board.getMonsterCards().get(index),
                        opponent.getPlayerBoard().getMonsterCards().get(i)) != ReasonableLevel.NOT_REASONABLE)
                    return i;
        }
        return -1;
    }

    public int getOpponentMonsterIndexHard(Player opponent) {
        int index = getBestMonsterToAttack(this.board.getMonsterCards());
        if (index != -1) {
            for (int i = 0; i < opponent.getPlayerBoard().getMonsterCards().size(); i++)
                if (isReasonableToAttack(this.board.getMonsterCards().get(index),
                        opponent.getPlayerBoard().getMonsterCards().get(i)) == ReasonableLevel.REASONABLE_FOR_HARD)
                    return i;
        }
        return -1;
    }

    protected ReasonableLevel isThisReasonableToActive(String name, Board opponentBoard, GamePhase phase) {
        switch (name) {
            case "Monster Reborn":
                ArrayList<Card> graveyardCards = new ArrayList<>();
                for (int i = 0; i < this.board.getGraveyardCards().size(); i++)
                    graveyardCards.add(this.board.getGraveyardCards().get(i));
                for (int i = 0; i < opponentBoard.getGraveyardCards().size(); i++)
                    graveyardCards.add(opponentBoard.getGraveyardCards().get(i));
                return isReasonableToActiveMonsterReborn(graveyardCards);
            case "Terraforming":
                return isReasonableToActiveTerraforming();
            case "Pot of Greed":
                return isReasonableToActivePotOfGreed();
            case "Raigeki":
                return isReasonableToActiveRaigeki();
            case "Change of Heart":
                return isReasonableToActiveChangeOfHeart(phase);
        }
    }

    public void activeSpellTrap(int index) {

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

    /*------ Spell & Trap Checkings ------*/
    /*------ Spell & Trap Checkings ------*/
    /*------ Spell & Trap Checkings ------*/
    /*------ Spell & Trap Checkings ------*/
    /*------ Spell & Trap Checkings ------*/

    public ReasonableLevel isReasonableToActiveMonsterReborn(ArrayList<Card> graveyardCards) {
        if (!canSpecialSummonLevel1() && !canSpecialSummonLevel2())
            return ReasonableLevel.NOT_REASONABLE;
        if (canSpecialSummonLevel2() && getBestSpecialMonsterIndex(graveyardCards, true) != -1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (getBestSpecialMonsterIndex(graveyardCards, false) != -1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveTerraforming() {
        for (int i = 0; i < this.board.getDeck().getMainDeck().size(); i++)
            if (this.board.getDeck().getMainDeck().get(i).getCardType() == CardType.SPELL) {
                SpellTrapCard spell = (SpellTrapCard) this.board.getDeck().getMainDeck().get(i);
                if (spell.getIcon() == Icon.FIELD)
                    switch (this.board.getDeck().getMainDeck().size()) {
                        case 1:
                            return ReasonableLevel.NOT_REASONABLE;
                        case 2:
                            int counter = 0;
                            switch (spell.getName()) {
                                case "Yami":
                                    for (int index = 0; index < this.board.getMonsterCards().size(); index++) {
                                        if (this.board.getMonsterCards().get(index)
                                                .getMonsterType() == MonsterType.FIEND
                                                || this.board.getMonsterCards().get(index)
                                                        .getMonsterType() == MonsterType.SPELL_CASTER)
                                            counter++;
                                        if (this.board.getMonsterCards().get(index)
                                                .getMonsterType() == MonsterType.FAIRY)
                                            counter--;
                                    }
                                    if (counter * 200 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                case "Forest":
                                    for (int index = 0; index < this.board.getMonsterCards().size(); index++)
                                        if (this.board.getMonsterCards().get(index)
                                                .getMonsterType() == MonsterType.INSECT
                                                || this.board.getMonsterCards().get(index)
                                                        .getMonsterType() == MonsterType.BEAST
                                                || this.board.getMonsterCards().get(index)
                                                        .getMonsterType() == MonsterType.BEAST_WARRIOR)
                                            counter++;
                                    if (counter * 200 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                case "Closed Forest":
                                    int monsterCounter = 0;
                                    for (int index = 0; index < this.board.getGraveyardCards().size(); index++)
                                        if (this.board.getGraveyardCards().get(index).getCardType() == CardType.MONSTER)
                                            monsterCounter++;
                                    for (int index = 0; index < this.board.getMonsterCards().size(); index++)
                                        if (this.board.getMonsterCards().get(index)
                                                .getMonsterType() == MonsterType.BEAST)
                                            counter++;
                                    if (counter * monsterCounter * 100 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                case "Umiiruka":
                                    for (int index = 0; index < this.board.getMonsterCards().size(); index++)
                                        if (this.board.getMonsterCards().get(index).getAttribute() == Attribute.WATER)
                                            counter++;
                                    if (counter * 500 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                            }
                        default:
                            return ReasonableLevel.REASONABLE_FOR_HARD;
                    }
            }

        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActivePotOfGreed() {
        switch (this.board.getDeck().getMainDeck().size()) {
            case 0:
            case 1:
            case 2:
                return ReasonableLevel.NOT_REASONABLE;
            case 3:
            case 4:
                return ReasonableLevel.REASONABLE_FOR_EASY;
            default:
                return ReasonableLevel.REASONABLE_FOR_HARD;
        }
    }

    public ReasonableLevel isReasonableToActiveRaigeki() {
        if (opponent.getPlayerBoard().getMonsterCards().size() > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveChangeOfHeart(GamePhase phase) {
        if (opponent.getPlayerBoard().getMonsterCards().size() == 0)
            return ReasonableLevel.NOT_REASONABLE;
        if (phase == GamePhase.BATTLE)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

}