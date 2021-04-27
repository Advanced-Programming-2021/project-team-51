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
    private User owner;

    static {
        allDecks = new ArrayList<>();
    }

    public Deck(String name, User owner) {
        allDecks.add(this);
        setName(name);
        setOwner(owner);
    }

    public Deck(String name, User owner, ArrayList<Card> mainDeck, ArrayList<Card> sideDeck,
            HashMap<Card, Integer> cards) {
        allDecks.add(this);
        setName(name);
        setOwner(owner);
        setMainDeck(mainDeck);
        setSideDeck(sideDeck);
        setCardsAmount(cards);
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

    public boolean hasEnoughSpace(String mainOrSide) {
        if (mainOrSide.equals("main") && mainDeckCards.size() > 59)
            return false;
        else if (sideDeckCards.size() > 14)
            return false;

        return true;
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

    private boolean isDeckValid() {
        if (mainDeckCards.size() > 39)
            return true;

        return false;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return this.owner;
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

    public void addCardToDeck(String mainOrSide, Card card) {
        if (hasUsedBefore(card))
            cardsAmount.put(card, cardsAmount.get(card) + 1);
        else
            cardsAmount.put(card, 1);
        if (mainOrSide.equals("main"))
            mainDeckCards.add(card);
        else
            sideDeckCards.add(card);
    }

    public void removeCardFromDeck(String mainOrSide, Card card) {
        cardsAmount.put(card, cardsAmount.get(card) - 1);
        if (mainOrSide.equals("main")) {
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
        this.owner.removeDeck(this);
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
