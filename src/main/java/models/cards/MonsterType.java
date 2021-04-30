package models.cards;

public enum MonsterType {
    BEAST_WARRIOR("Beast-Warrior"),
    WARRIOR("Warrior"),
    FIEND("Fiend"),
    AQUA("Aqua"),
    BEAST("Beast"),
    PYRO("Pyro"),
    SPELL_CASTER("Spellcaster"),
    THUNDER("Thunder"),
    DRAGON("Dragon"),
    MACHINE("Machine"),
    ROCK("Rock"),
    INSECT("Insect"),
    CYBERSE("Cyberse"),
    FAIRY("Fairy"),
    SEA_SERPENT("Sea Serpent");

    private final String label;

    MonsterType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
