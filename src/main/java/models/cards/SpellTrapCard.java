package models.cards;

import java.util.HashSet;

public class SpellTrapCard extends Card{
    private static final HashSet<SpellTrapCard> allSpellTrapCards = new HashSet<>();
    Icon icon;

    public SpellTrapCard() {
        // #TODO : complete constructor
    }

    public static SpellTrapCard getSpellTrapCardByNumber(int number) {
        for (SpellTrapCard spellTrapCard : allSpellTrapCards) {
            if (spellTrapCard.cardNumber == number)
                return spellTrapCard;
        }
        return null;
    }

    public static HashSet<SpellTrapCard> getAllSpellTrapCards() {
        return allSpellTrapCards;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        // #TODO : Define printing format for spell_trap cards.
        return null;
    }
}
