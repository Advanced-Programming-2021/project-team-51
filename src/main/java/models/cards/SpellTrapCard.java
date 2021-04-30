package models.cards;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;
import java.util.HashSet;

public class SpellTrapCard extends Card{
    private static final ArrayList<SpellTrapCard> allSpellTrapCards = new ArrayList<>();
    Icon icon;

    public SpellTrapCard(String name, String description, int price, CardType cardType, Icon icon) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setCardType(cardType);
        this.setIcon(icon);
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

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
                this.getCardType().getLabel() + "\n" +
                "Type: " + this.getIcon().getLabel() + "\n" +
                "Description: " + this.getDescription();
    }
}
