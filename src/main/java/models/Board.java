package models;

import models.cards.Card;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;
import controller.duel.AI;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    private ArrayList<MonsterCard> monsterBoard = new ArrayList<>();
    private ArrayList<SpellTrapCard> spellAndTrapBoard = new ArrayList<>();
    private ArrayList<Card> graveyard = new ArrayList<>();
    private ArrayList<Card> swordOfDarkDestructionEquipped = new ArrayList<>();
    private ArrayList<Card> blackPendantEquipped = new ArrayList<>();
    private ArrayList<Card> unitedWeStandEquipped = new ArrayList<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private Player owner;
    private Card fieldZone;
    private Deck deck;
    private boolean isSuijinEffected;
    private int lifePoints;
    private int counter = 0;

    public Board(Player owner) throws CloneNotSupportedException {
        setOwner(owner);
        setFieldZone(null);
        setDeck((Deck) owner.getPlayerDeck().clone());
        setSuijinEffect(false);
        setLifePoints(8000);
        shuffleDeck();
        beginDeck();
    }

    public Board(AI bot) throws CloneNotSupportedException {
        setOwner(null);
        setFieldZone(null);
        setDeck((Deck) bot.getDeck().clone());
        setSuijinEffect(false);
        setLifePoints(8000);
        shuffleDeck();
        beginDeck();
    }

    public void resetTheBoard(Card main, Card side) throws CloneNotSupportedException {
        removeCopiedDeck();
        setFieldZone(null);
        setDeck((Deck) owner.getPlayerDeck().clone());
        setSuijinEffect(false);
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

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    public Card getFieldZone() {
        return this.fieldZone;
    }

    public ArrayList<Card> getHandCards() {
        return this.cardsInHand;
    }

    public ArrayList<MonsterCard> getMonsterCards() {
        return this.monsterBoard;
    }

    public boolean hasSpellTrapZoneSpace() {
        if (spellAndTrapBoard.size() == 5)
            return false;
        return true;
    }

    public boolean hasMonsterZoneSpace() {
        if (monsterBoard.size() == 5)
            return false;
        return true;
    }

    public void summonOrSetMonser(int index) {
        monsterBoard.add((MonsterCard) cardsInHand.get(index));
        removeCardsFromHand(index);
    }

    public void removeMonser(int index) {
        addToGraveyard(monsterBoard.get(index));
        monsterBoard.remove(index);
    }

    public void summonOrSetSpellAndTrap(int index) {
        spellAndTrapBoard.add((SpellTrapCard) cardsInHand.get(index));
        removeCardsFromHand(index);
    }

    public void removeSpellAndTrap(int index) {
        addToGraveyard(spellAndTrapBoard.get(index));
        spellAndTrapBoard.remove(index);
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
        deck.getMainDeck().remove(counter);
        counter++;
    }

    public void addLifePoints(int amount) {
        this.lifePoints += amount;
    }

    public void drawCard() {
        addCardsInHand(deck.getMainDeck().get(counter));
        removeCardFromDeck();
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

    public void addCardToSwordOfDarkDestruction(Card card) {
        swordOfDarkDestructionEquipped.add(card);
    }

    public void resetSwordOfDardDestruction() {
        swordOfDarkDestructionEquipped.clear();
    }

    public void addCardToBlackPendant(Card card) {
        blackPendantEquipped.add(card);
    }

    public void resetBlackPendant() {
        blackPendantEquipped.clear();
    }

    public void addCardToUnitedWeStand(Card card) {
        unitedWeStandEquipped.add(card);
    }

    public void resetUnitedWeStand() {
        unitedWeStandEquipped.clear();
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        if (this.fieldZone == null)
            boardString.append("E\t\t\t\t\t\t");
        else
            boardString.append("O\t\t\t\t\t\t");
        boardString.append(this.graveyard.size() + "\n\t");
        for (int i = 4; i > -1; i -= 2) {
            if (i + 1 > monsterBoard.size())
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }
        for (int i = 1; i < 4; i += 2) {
            if (i + 1 > monsterBoard.size())
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
            if (i + 1 > spellAndTrapBoard.size())
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }
        for (int i = 1; i < 4; i += 2) {
            if (i + 1 > spellAndTrapBoard.size())
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
            if (i + 1 > spellAndTrapBoard.size())
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }

        for (int i = 4; i > -1; i -= 2) {
            if (i + 1 > spellAndTrapBoard.size())
                boardString.append("E\t");
            else if (spellAndTrapBoard.get(i).getIsHidden())
                boardString.append("H\t");
            else
                boardString.append("O\t");
        }

        boardString.append("\n\t");

        for (int i = 1; i < 4; i += 2) {
            if (i + 1 > monsterBoard.size())
                boardString.append("E\t");
            else if (monsterBoard.get(i).getMode() == Mode.ATTACK)
                boardString.append("OO\t");
            else if (monsterBoard.get(i).getIsHidden())
                boardString.append("DH\t");
            else
                boardString.append("DO\t");
        }

        for (int i = 4; i > -1; i -= 2) {
            if (i + 1 > monsterBoard.size())
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
