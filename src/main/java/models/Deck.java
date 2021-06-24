package models;

import java.util.ArrayList;
import java.util.HashMap;

import models.cards.Card;
import models.cards.MakeCards;


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
            newDeck.addCardToDeck(true, (Card) mainDeckCard.clone());
        for (Card sideDeckCard : this.sideDeckCards)
            newDeck.addCardToDeck(false, (Card) sideDeckCard.clone());
        return newDeck;
    }

    public Deck(String name, String ownerName) {
        allDecks.add(this);
        setName(name);
        setOwner(ownerName);
    }

    public Deck(String name, String ownerName, ArrayList<Card> mainDeck, ArrayList<Card> sideDeck, HashMap<Card, Integer> cards) {
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
        if (cardsAmount.get(card) != null)
            return cardsAmount.get(card) <= 2;

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

    public boolean isDeckValid() {
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

    public void removeCopiedDeck() {
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

    private String getCardNameForGenerate(int number) {
        switch (number) {
            case 1:
                return "Monster Reborn";
            case 2:
                return "Terraforming";
            case 3:
                return "Pot of Greed";
            case 4:
                return "Raigeki";
            case 5:
                return "Change of Heart";
            case 6:
                return "Swords of Revealing Light";
            case 7:
                return "Harpie's Feather Duster";
            case 8:
                return "Dark Hole";
            case 9:
                return "Supply Squad";
            case 10:
                return "Spell Absorption";
            case 11:
                return "Messenger of peace";
            case 12:
                return "Twin Twisters";
            case 13:
                return "Mystical space typhoon";
            case 14:
                return "Ring of defense";
            case 15:
                return "Yami";
            case 16:
                return "Forest";
            case 17:
                return "Closed Forest";
            case 18:
                return "Umiiruka";
            case 19:
                return "Sword of dark destruction";
            case 20:
                return "Black Pendant";
            case 21:
                return "United We Stand";
            case 22:
                return "Magnum Shield";
            case 23:
                return "Advanced Ritual Art";
            case 24:
                return "Trap Hole";
            case 25:
                return "Mirror Force";
            case 26:
                return "Magic Cylinder";
            case 27:
                return "Mind Crush";
            case 28:
                return "Torrential Tribute";
            case 29:
                return "Time Seal";
            case 30:
                return "Negate Attack";
            case 31:
                return "Solemn Warning";
            case 32:
                return "Magic Jamamer";
            case 33:
                return "Call of The Haunted";
            case 34:
                return "Vanity's Emptiness";
            case 35:
                return "Wall of Revealing Light";
            case 36:
                return "Yomi Ship";
            case 37:
                return "Man-Eater Bug";
            case 38:
                return "Scanner";
            case 39:
                return "Marshmallon";
            case 40:
                return "Texchanger";
            case 41:
                return "The Calculator";
            case 42:
                return "Mirage Dragon";
            case 43:
                return "Herald of Creation";
            case 44:
                return "Exploder Dragon";
            case 45:
                return "Terratiger, the Empowered Warrior";
            case 46:
                return "The Tricky";
            case 47:
                return "Command Knight";
            case 48:
                return "Battle OX";
            case 49:
                return "Axe Raider";
            case 50:
                return "Horn Imp";
            case 51:
                return "Silver Fang";
            case 52:
                return "Fireyarou";
            case 53:
                return "Curtain of the dark ones";
            case 54:
                return "Feral Imp";
            case 55:
                return "Wattkid";
            case 56:
                return "Baby dragon";
            case 57:
                return "Hero of the east";
            case 58:
                return "Battle warrior";
            case 59:
                return "Crawling dragon";
            case 60:
                return "Flame manipulator";
            case 61:
                return "Haniwa";
            case 62:
                return "Bitron";
            case 63:
                return "Leotron";
            case 64:
                return "Alexandrite Dragon";
            case 65:
                return "Warrior Dai Grepher";
            case 66:
                return "Dark Blade";
            case 67:
                return "Crab Turtle";
            case 68:
                return "Skull Guardian";
            case 69:
                return "Suijin";
            case 70:
                return "Gate Guardian";
            case 71:
                return "Beast King Barbaros";
            case 72:
                return "Dark magician";
            case 73:
                return "Blue-Eyes white dragon";
            case 74:
                return "Slot Machine";
            case 75:
                return "Wattaildragon";
            case 76:
                return "Spiral Serpent";
            default:
                return "";

        }
    }

    public static Deck generateDeck(boolean isForEasy) {
        Deck deck = new Deck("", "Bot");
        int mainCardNumbers = (int) (Math.random() * 21) + 40;
        int card = 0;
        for (int i = 0; i < mainCardNumbers; i++) {
            if (isForEasy)
                card = (int) (Math.random() * 66) + 1;
            else
                card = (int) (Math.random() * 76) + 1;

            if (deck.hasEnoughSpace(Card.getCardByName(deck.getCardNameForGenerate(card))))
                deck.addCardToDeck(true, MakeCards.makeCard(deck.getCardNameForGenerate(card)));
            else
                mainCardNumbers++;
        }

        return deck;
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
