package models.cards;

import java.util.HashSet;

public abstract class Card {

    private static final HashSet<Card> allCards = new HashSet<>();
    protected static int cardCounter = 0;
    protected int cardNumber;
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

    public static HashSet<Card> getAllCards() {
        return allCards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public void setPrice(int price) {
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

    abstract public String toString();
}
