package models.cards;

import java.util.ArrayList;

public abstract class Card {

    protected static ArrayList<Card> allCards = new ArrayList<>();
    protected static int cardCounter = 0;
    private int cardNumber;
    private String name;
    private String description;
    private CardType cardType;
    private int price;
    private Location location;
    private boolean isHidden;
    private boolean isSwitched;

    public static Card getCardByNumber(int number) {
        for (Card card : allCards) {
            if (card.cardNumber == number)
                return card;
        }
        return null;
    }

    public static Card getCardByName(String name) {
        for (Card card : allCards) {
            if (card.name.equals(name))
                return card;
        }
        return null;
    }
    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void setAllCards(ArrayList<Card> cards) {
        allCards = cards;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    protected void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    protected void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean getIsHidden() {
        return this.isHidden;
    }

    public void setSwitched(boolean isSwitched) {
        this.isSwitched = isSwitched;
    }

    public boolean getIsSwitched() {
        return this.isSwitched;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    protected void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    abstract public String toString();

    abstract public Object clone();
}
