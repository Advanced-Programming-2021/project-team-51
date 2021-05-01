package models.cards;

import java.util.ArrayList;

public class SpellTrapCard extends Card{
    private static final ArrayList<SpellTrapCard> allSpellTrapCards = new ArrayList<>();
    Icon icon;
    boolean isLimited;

    public SpellTrapCard(String name, String description, int price, CardType cardType, Icon icon, boolean isLimited) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setCardType(cardType);
        this.setIcon(icon);
        this.setLimited(isLimited);
        this.setCardNumber(++cardCounter);
        allSpellTrapCards.add(this);
        allCards.add(this);
    }

    public static SpellTrapCard getSpellTrapCardByNumber(int number) {
        for (SpellTrapCard spellTrapCard : allSpellTrapCards) {
            if (spellTrapCard.getCardNumber() == number)
                return spellTrapCard;
        }
        return null;
    }

    public static ArrayList<SpellTrapCard> getAllSpellTrapCards() {
        return allSpellTrapCards;
    }

    private void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return this.icon;
    }

    private void setLimited(boolean isLimited) {
        this.isLimited = isLimited;
    }

    public boolean getLimited() {
        return this.isLimited;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
                this.getCardType().getLabel() + "\n" +
                "Type: " + this.getIcon().getLabel() + "\n" +
                "Description: " + this.getDescription();
    }
}
