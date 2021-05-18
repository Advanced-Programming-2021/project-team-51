package models.cards.monsters;

import models.cards.Card;
import models.cards.CardType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonsterCard extends Card {

    private static ArrayList<MonsterCard> allMonsterCards = new ArrayList<>();
    private static final ArrayList<MonsterCard> allMonsterCardsToShow = new ArrayList<>();
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

    private MonsterCard(String name, int price) {
        this.setPrice(price);
        this.setName(name);
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

    public static ArrayList<MonsterCard> getAllMonsterCardsToShow() throws IOException {
        if (allMonsterCardsToShow.size() > 0)
            return allMonsterCardsToShow;

        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader("Monster.csv"));
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");
            allMonsterCardsToShow.add(new MonsterCard(values[0], Integer.parseInt(values[8])));
        }
        return allMonsterCardsToShow;
    }

    public static void setAllMonsterCards(ArrayList<MonsterCard> cards) {
        allMonsterCards = cards;
    }

    @Override
    public Object clone() {
        return new MonsterCard(this.getName(), this.getDescription(), this.getPrice(), this.getLevel(), this.getAttribute(),
                this.getMonsterType(), this.getAttackPoint(), this.getDefensePoint(), this.getTrait(), this.getCardNumber());
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

    public void setTrait(Trait trait) {
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
