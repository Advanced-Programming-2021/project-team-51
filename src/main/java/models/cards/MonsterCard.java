package models.cards;

import java.util.HashSet;

public class MonsterCard extends Card{

    private static final HashSet<MonsterCard> allMonsterCards = new HashSet<>();
    private int level;
    private Attribute attribute;
    private MonsterType monsterType;
    private int attackPoint;
    private int defensePoint;
    private boolean hasRitualSummon;
    private boolean hasEffect;
    private Mode mode;
    private boolean hasAttacked;

    public MonsterCard() {
        // #TODO : complete constructor
    }

    public static MonsterCard getMonsterCardByNumber(int number) {
        for (MonsterCard monsterCard : allMonsterCards) {
            if (monsterCard.cardNumber == number)
                return monsterCard;
        }
        return null;
    }

    public static HashSet<MonsterCard> getAllMonsterCards() {
        return allMonsterCards;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public MonsterType getMonsterType() {
        return this.monsterType;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getDefensePoint() {
        return this.defensePoint;
    }

    public void setHasRitualSummon(boolean hasRitualSummon) {
        this.hasRitualSummon = hasRitualSummon;
    }

    public boolean getHasRitualSummon() {
        return this.hasRitualSummon;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public boolean getHasEffect() {
        return this.hasEffect;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public boolean getHasAttacked() {
        return this.hasAttacked;
    }

    @Override
    public String toString() {
        // #TODO : Define printing format for monster cards.
        return null;
    }
}
