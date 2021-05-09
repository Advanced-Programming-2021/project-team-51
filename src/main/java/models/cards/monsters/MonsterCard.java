package models.cards.monsters;

import models.cards.Card;
import models.cards.CardType;

import java.util.ArrayList;

public class MonsterCard extends Card {

    private static ArrayList<MonsterCard> allMonsterCards = new ArrayList<>();
    private int level;
    private Attribute attribute;
    private MonsterType monsterType;
    private int attackPoint;
    private int defensePoint;
    private Trait trait;
    private Mode mode;
    private boolean hasAttacked;

    public MonsterCard(String name, String description, int price, int level, Attribute attribute, MonsterType monsterType,
                       int attackPoint, int defensePoint, Trait trait) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setLevel(level);
        this.setAttribute(attribute);
        this.setCardType(CardType.MONSTER);
        this.setMonsterType(monsterType);
        this.setAttackPoint(attackPoint);
        this.setDefensePoint(defensePoint);
        this.setCardNumber(++cardCounter);
        this.setTrait(trait);
        allMonsterCards.add(this);
        allCards.add(this);
    }

    public MonsterCard(String name, String description, int price, int level, Attribute attribute, MonsterType monsterType,
                       int attackPoint, int defensePoint, Trait trait, int cardNumber) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setLevel(level);
        this.setAttribute(attribute);
        this.setCardType(CardType.MONSTER);
        this.setMonsterType(monsterType);
        this.setAttackPoint(attackPoint);
        this.setDefensePoint(defensePoint);
        this.setTrait(trait);
        this.setCardNumber(cardNumber);
    }
    public static MonsterCard getMonsterCardByNumber(int number) {
        for (MonsterCard monsterCard : allMonsterCards) {
            if (monsterCard.getCardNumber() == number)
                return monsterCard;
        }
        return null;
    }

    public static ArrayList<MonsterCard> getAllMonsterCards() {
        return allMonsterCards;
    }

    public static void setAllMonsterCards(ArrayList<MonsterCard> cards) {
        allMonsterCards = cards;
    }

    @Override
    public Object clone() {
        return new MonsterCard(this.getName(), this.getDescription(), this.getPrice(), this.getLevel(), this.getAttribute(),
                this.getMonsterType(), this.getAttackPoint(), this.getDefensePoint(), this.getTrait(), this.getCardNumber());
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

    private void setTrait(Trait trait) {
        this.trait = trait;
    }

    public Trait getTrait() {
        return this.trait;
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
