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

    public MonsterCard(String name, String description, int price, int level, Attribute attribute, MonsterType monsterType,
                       int attackPoint, int defensePoint, boolean hasRitualSummon, boolean hasEffect) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setLevel(level);
        this.setAttribute(attribute);
        this.setCardType(CardType.MONSTER);
        this.setMonsterType(monsterType);
        this.setAttackPoint(attackPoint);
        this.setDefensePoint(defensePoint);
        this.setHasRitualSummon(hasRitualSummon);
        this.setHasEffect(hasEffect);
        this.setCardNumber(++cardCounter);
    }

    public static MonsterCard getMonsterCardByNumber(int number) {
        for (MonsterCard monsterCard : allMonsterCards) {
            if (monsterCard.getCardNumber() == number)
                return monsterCard;
        }
        return null;
    }

    public static HashSet<MonsterCard> getAllMonsterCards() {
        return allMonsterCards;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    private void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    private void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public MonsterType getMonsterType() {
        return this.monsterType;
    }

    private void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    private void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getDefensePoint() {
        return this.defensePoint;
    }

    private void setHasRitualSummon(boolean hasRitualSummon) {
        this.hasRitualSummon = hasRitualSummon;
    }

    public boolean getHasRitualSummon() {
        return this.hasRitualSummon;
    }

    private void setHasEffect(boolean hasEffect) {
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
        return "Name: " + this.getName() + "\n" +
                "Level: " + this.getLevel() + "\n" +
                "Type: " + this.getMonsterType().getLabel() + "\n" +
                "ATK: " + this.getAttackPoint() + "\n" +
                "DEF: " + this.getDefensePoint() + "\n" +
                "Description: " + this.getDescription();
    }
}
