package models;

import java.util.ArrayList;
import java.util.HashMap;

import models.cards.Card;

public class Deck {

    public static ArrayList<Deck> allDecks;
    private ArrayList<Card> mainDeckCards = new ArrayList<>();
    private ArrayList<Card> sideDeckCards = new ArrayList<>();
    private HashMap<Card, Integer> cardsAmount = new HashMap<>();
    private String name;
    private String ownerName;

    static {
        allDecks = new ArrayList<>();
    }

    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Deck newDeck = new Deck(this.name, this.ownerName, this.cardsAmount);
        for (Card mainDeckCard : this.mainDeckCards)
            newDeck.addCardToDeck(true, (Card)mainDeckCard.clone());
        for (Card sideDeckCard : this.sideDeckCards)
            newDeck.addCardToDeck(false, (Card)sideDeckCard.clone());
        return newDeck;
    }

    public Deck(String name, String ownerName) {
        allDecks.add(this);
        setName(name);
        setOwner(ownerName);
    }

    public Deck(String name, String ownerName, ArrayList<Card> mainDeck, ArrayList<Card> sideDeck,
            HashMap<Card, Integer> cards) {
        allDecks.add(this);
        setName(name);
        setOwner(ownerName);
        setMainDeck(mainDeck);
        setSideDeck(sideDeck);
        setCardsAmount(cards);
    }

    public Deck(String name, String ownerName, HashMap<Card, Integer> cards) {
        setName(name);
        setOwner(ownerName);
        setCardsAmount(cards);
    }

    public static void loadAllDecks(ArrayList<Deck> decks) {
        allDecks = decks;
    }

    public static Deck getDeckByName(String name) {
        for (int i = 0; i < allDecks.size(); i++)
            if (allDecks.get(i).getName().equals(name))
                return allDecks.get(i);

        return null;
    }

    public static boolean isNameTaken(String name) {
        for (int i = 0; i < allDecks.size(); i++)
            if (allDecks.get(i).getName().equals(name))
                return true;

        return false;
    }

    public boolean isMainFull() {
        if (mainDeckCards.size() > 59)
            return true;

        return false;
    }

    public boolean isSideFull() {
        if (sideDeckCards.size() > 14)
            return true;

        return false;
    }

    public boolean hasEnoughSpace(Card card) {
        if (cardsAmount.get(card) > 2)
            return false;

        return true;
    }

    private boolean hasUsedBefore(Card card) {
        for (Card key : cardsAmount.keySet())
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    public boolean isThisCardUsedInMain(Card card) {
        for (Card key : mainDeckCards)
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    public boolean isThisCardUsedInSide(Card card) {
        for (Card key : mainDeckCards)
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    private boolean isDeckValid() {
        if (mainDeckCards.size() > 39)
            return true;

        return false;
    }

    public static ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setOwner(String ownerName) {
        this.ownerName = ownerName;
    }

    public User getOwner() {
        return User.getUserByUserName(this.ownerName);
    }

    private void setMainDeck(ArrayList<Card> mainCards) {
        this.mainDeckCards = mainCards;
    }

    public ArrayList<Card> getMainDeck() {
        return this.mainDeckCards;
    }

    private void setSideDeck(ArrayList<Card> sideCards) {
        this.sideDeckCards = sideCards;
    }

    public ArrayList<Card> getSideDeck() {
        return this.sideDeckCards;
    }

    private void setCardsAmount(HashMap<Card, Integer> cards) {
        this.cardsAmount = cards;
    }

    public HashMap<Card, Integer> getCardsAmount() {
        return this.cardsAmount;
    }

    public void addCardToDeck(boolean isMain, Card card) {
        if (hasUsedBefore(card))
            cardsAmount.put(card, cardsAmount.get(card) + 1);
        else
            cardsAmount.put(card, 1);
        if (isMain)
            mainDeckCards.add(card);
        else
            sideDeckCards.add(card);
    }

    public void removeCardFromDeck(boolean isMain, Card card) {
        cardsAmount.put(card, cardsAmount.get(card) - 1);
        if (isMain) {
            for (int i = 0; i < mainDeckCards.size(); i++)
                if (mainDeckCards.get(i).getName().equals(card.getName())) {
                    mainDeckCards.remove(i);
                    break;
                }
        } else {
            for (int i = 0; i < sideDeckCards.size(); i++)
                if (sideDeckCards.get(i).getName().equals(card.getName())) {
                    sideDeckCards.remove(i);
                    break;
                }
        }
    }

    public void removeDeck() {
        User.getUserByUserName(ownerName).removeDeck(this);
        allDecks.remove(this);
    }

    public String saveString() {
        StringBuilder stringDeck = new StringBuilder();
        stringDeck.append("Main = {");
        for (int i = 0; i < mainDeckCards.size(); i++) {
            if (i > 0)
                stringDeck.append(", " + mainDeckCards.get(i).getName());
            else
                stringDeck.append(mainDeckCards.get(i).getName());
        }
        stringDeck.append("}, Side = {");
        for (int i = 0; i < sideDeckCards.size(); i++) {
            if (i > 0)
                stringDeck.append(", " + sideDeckCards.get(i).getName());
            else
                stringDeck.append(sideDeckCards.get(i).getName());
        }
        stringDeck.append("}");

        return stringDeck.toString();
    }

    public String toString() {
        if (isDeckValid())
            return this.name + ": main deck " + mainDeckCards.size() + ", side deck " + sideDeckCards.size()
                    + ", valid";
        else
            return this.name + ": main deck " + mainDeckCards.size() + ", side deck " + sideDeckCards.size()
                    + ", invalid";
    }
}
