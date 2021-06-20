package models;

import models.cards.Card;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.util.ArrayList;
import java.util.Collections;

import controller.duel.singlePlayer.AI;


public class Board {

    private final ArrayList<MonsterCard> monsterBoard = new ArrayList<>();
    private final ArrayList<SpellTrapCard> spellAndTrapBoard = new ArrayList<>();
    private final ArrayList<Card> graveyard = new ArrayList<>();
    private MonsterCard swordOfDarkDestructionEquipped;
    private MonsterCard blackPendantEquipped;
    private MonsterCard unitedWeStandEquipped;
    private MonsterCard magnumShieldEquipped;
    private final ArrayList<Card> cardsInHand = new ArrayList<>();
    private Player owner;
    private Card fieldZone;
    private Deck deck;
    private boolean isSuijinEffected;
    private int lifePoints;

    public Board(Player owner) throws CloneNotSupportedException {
        setOwner(owner);
        setFieldZone(null);
        setDeck((Deck) owner.getPlayerDeck().clone());
        setSuijinEffect(false);
        setEquippedBlackPendant(null);
        setEquippedMagnumShield(null);
        setEquippedSwordOfDarkDestruction(null);
        setEquippedUnitedWeStand(null);
        setLifePoints(8000);
        shuffleDeck();
        beginDeck();
    }

    public Board(AI bot) throws CloneNotSupportedException {
        setOwner(null);
        setFieldZone(null);
        setDeck((Deck) bot.getDeck().clone());
        setSuijinEffect(false);
        setEquippedBlackPendant(null);
        setEquippedMagnumShield(null);
        setEquippedSwordOfDarkDestruction(null);
        setEquippedUnitedWeStand(null);
        setLifePoints(8000);
        shuffleDeck();
        beginDeck();
    }

    public void resetTheBoard(Card main, Card side) {
        removeCopiedDeck();
        setFieldZone(null);
        try {
            setDeck((Deck) owner.getPlayerDeck().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        setSuijinEffect(false);
        setEquippedBlackPendant(null);
        setEquippedMagnumShield(null);
        setEquippedSwordOfDarkDestruction(null);
        setEquippedUnitedWeStand(null);
        setLifePoints(8000);
        if (main != null && side != null)
            changeDeck(side, main);
        shuffleDeck();
        beginDeck();
    }

    public void removeCopiedDeck() {
        this.deck.removeCopiedDeck();
    }

    public void setSuijinEffect(boolean effect) {
        this.isSuijinEffected = effect;
    }

    public boolean getSuijinEffect() {
        return this.isSuijinEffected;
    }

    public void setLifePoints(int amount) {
        this.lifePoints = amount;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return this.deck;
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public ArrayList<MonsterCard> getMonsterBoard() {
        return this.monsterBoard;
    }

    public ArrayList<SpellTrapCard> getSpellAndTrapBoard() {
        return this.spellAndTrapBoard;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    public Card getFieldZone() {
        return this.fieldZone;
    }

    public ArrayList<Card> getHandCards() {
        return this.cardsInHand;
    }

    public ArrayList<Card> getGraveyardCards() {
        return this.graveyard;
    }

    public MonsterCard getSwordOfDarkDestructionEquipped() {
        return this.swordOfDarkDestructionEquipped;
    }

    public MonsterCard getBlackPendantEquipped() {
        return this.blackPendantEquipped;
    }

    public MonsterCard getUnitedWeStandEquipped() {
        return this.unitedWeStandEquipped;
    }

    public MonsterCard getMagnumShieldEquipped() {
        return this.magnumShieldEquipped;
    }

    public ArrayList<MonsterCard> getMonsters() {
        ArrayList<MonsterCard> monsters = new ArrayList<>();
        for (MonsterCard monsterCard : monsterBoard)
            if (monsterCard != null) monsters.add(monsterCard);
        return monsters;
    }

    public ArrayList<SpellTrapCard> getSpellTraps() {
        ArrayList<SpellTrapCard> spellTraps = new ArrayList<>();
        for (SpellTrapCard spellTrapCard : spellAndTrapBoard)
            if (spellTrapCard != null) spellTraps.add(spellTrapCard);
        return spellTraps;
    }

    public int getMonsterIndexInMonsterBoard(MonsterCard monster) {
        for (int i = 0; i < monsterBoard.size(); i++)
            if (monsterBoard.get(i) == monster &&
                    monsterBoard.get(i).getName().equals(monster.getName())) return i;

        return -1;
    }

    public int getSpellTrapIndexInSpellTrapBoard(SpellTrapCard spellTrapCard) {
        for (int i = 0 ; i < spellAndTrapBoard.size(); i++)
            if (spellAndTrapBoard.get(i) == spellTrapCard &&
                    spellAndTrapBoard.get(i).getName().equals(spellTrapCard.getName())) return i;

            return -1;
    }

    public int getCardIndexInHand(Card key) {
        for (int i = 0; i < cardsInHand.size(); i++)
            if (cardsInHand.get(i) == key &&
                    cardsInHand.get(i).getName().equals(key.getName())) return i;

        return -1;
    }

    public boolean hasSpellTrapZoneSpace() {
        return getSpellTraps().size() != 5;
    }

    public boolean hasMonsterZoneSpace() {
        return getMonsters().size() != 5;
    }

    public void summonOrSetMonster(MonsterCard monster) {
        for (int i = 0; i < 5; i++)
            if (monsterBoard.get(i) == null) {
                monsterBoard.add(monster);
                removeCardsFromHand(getCardIndexInHand(monster));
                break;
            }
    }

    public void summonOrSetMonster(int index) {
        for (int i = 0; i < 5; i++)
            if (monsterBoard.get(i) == null) {
                monsterBoard.add((MonsterCard) cardsInHand.get(index));
                removeCardsFromHand(index);
                break;
            }
    }

    public void removeMonster(int index) {
        addToGraveyard(monsterBoard.get(index));
        monsterBoard.set(index, null);
    }

    public void summonOrSetSpellAndTrap(SpellTrapCard spellTrap) {
        for (int i = 0; i < 5; i++)
            if (spellAndTrapBoard.get(i) == null) {
                spellAndTrapBoard.add(spellTrap);
                removeCardsFromHand(getCardIndexInHand(spellTrap));
                break;
            }
    }

    public void summonOrSetSpellAndTrap(int index) {
        for (int i = 0; i < 5; i++)
            if (spellAndTrapBoard.get(i) == null) {
                spellAndTrapBoard.add((SpellTrapCard) cardsInHand.get(index));
                removeCardsFromHand(index);
                break;
            }
    }

    public void removeSpellAndTrap(int index) {
        addToGraveyard(spellAndTrapBoard.get(index));
        spellAndTrapBoard.set(index, null);
    }

    public void addToGraveyard(Card card) {
        graveyard.add(card);
    }

    public void removeFromGraveyard(int index) {
        graveyard.remove(index);
    }

    public void addCardsInHand(Card card) {
        cardsInHand.add(card);
    }

    public void removeCardsFromHand(int index) {
        cardsInHand.remove(index);
    }

    public void removeCardFromDeck() {
        deck.getMainDeck().remove(0);
    }

    public void addLifePoints(int amount) {
        this.lifePoints += amount;
    }

    public Card drawCard() {
        Card card;
        try {
            card = deck.getMainDeck().get(0);
        } catch (Exception exception) {
            card = null;
        }
        addCardsInHand(card);
        removeCardFromDeck();
        return card;
    }

    private void shuffleDeck() {
        Collections.shuffle(deck.getMainDeck());
    }

    private void beginDeck() {
        for (int i = 0; i < 4; i++)
            drawCard();
    }

    public void removeDeck() {
        deck.removeDeck();
    }

    public void changeDeck(Card side, Card main) {
        int mainIndex = 0;
        int sideIndex = 0;
        for (int i = 0; i < deck.getMainDeck().size(); i++)
            if (deck.getMainDeck().get(i).getName().equals(main.getName())) {
                mainIndex = i;
                break;
            }
        for (int i = 0; i < deck.getSideDeck().size(); i++)
            if (deck.getSideDeck().get(i).getName().equals(main.getName())) {
                sideIndex = i;
                break;
            }
        deck.getMainDeck().remove(mainIndex);
        deck.getMainDeck().add(side);
        deck.getSideDeck().remove(sideIndex);
        deck.getSideDeck().add(main);

    }

    public void setEquippedSwordOfDarkDestruction(MonsterCard monster) {
        this.swordOfDarkDestructionEquipped = monster;
    }

    public void setEquippedBlackPendant(MonsterCard monster) {
        this.blackPendantEquipped = monster;
    }

    public void setEquippedUnitedWeStand(MonsterCard monster) {
        this.unitedWeStandEquipped = monster;
    }

    public void setEquippedMagnumShield(MonsterCard monster) {
        this.magnumShieldEquipped = monster;
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        if (this.fieldZone == null)
            boardString.append("E\t\t\t\t\t\t");
        else
            boardString.append("O\t\t\t\t\t\t");
        boardString.append(this.graveyard.size() + "\n\t");
        for (int i = 4; i > -1; i -= 2) {
            if (monsterBoard.get(i) == null)
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }
        for (int i = 1; i < 4; i += 2) {
            if (monsterBoard.get(i) == null)
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }
        boardString.append("\n\t");
        for (int i = 4; i > -1; i -= 2) {
            if (spellAndTrapBoard.get(i) == null)
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }
        for (int i = 1; i < 4; i += 2) {
            if (spellAndTrapBoard.get(i) == null)
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }
        boardString.append("\n\t\t\t\t\t\t" + deck.getMainDeck().size() + "\n");

        for (int i = 0; i < cardsInHand.size(); i++)
            boardString.append("C\t");

        boardString.append("\n" + owner.getNickName() + ":" + this.lifePoints);

        return boardString.toString();
    }

    public String reverseToString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append(owner.getNickName() + ":" + this.lifePoints + "\n");

        for (int i = 0; i < cardsInHand.size(); i++)
            boardString.append("C\t");

        boardString.append("\n" + deck.getMainDeck().size() + "\n\t");

        for (int i = 1; i < 4; i += 2) {
            if (spellAndTrapBoard.get(i) == null)
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }

        for (int i = 4; i > -1; i -= 2) {
            if (spellAndTrapBoard.get(i) == null)
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }

        boardString.append("\n\t");

        for (int i = 1; i < 4; i += 2) {
            if (monsterBoard.get(i) == null)
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }

        for (int i = 4; i > -1; i -= 2) {
            if (monsterBoard.get(i) == null)
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }

        boardString.append("\n" + graveyard.size() + "\t\t\t\t\t\t");
        if (fieldZone == null)
            boardString.append("E");
        else
            boardString.append("O");

        return boardString.toString();
    }
}
