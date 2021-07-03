package Model.cards.spelltrap;

public enum Icon {
    EQUIP("Equip"),
    FIELD("Field"),
    QUICK_PLAY("Quick-Play"),
    RITUAL("Ritual"),
    CONTINUOUS("Continuous"),
    COUNTER("Counter"),
    NORMAL("Normal"),
    UNTITRAP("Untitrap");

    private final String label;

    Icon(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
