package models.cards;

public enum CardType {
    MONSTER("Monster"),
    SPELL("Spell"),
    TRAP("Trap");

    private final String label;

    CardType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}
