package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import models.cards.Card;
import models.cards.CardType;
import models.cards.MakeCards;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;


public class Deck {

    public static ArrayList<Deck> allDecks;
    private ArrayList<MonsterCard> mainDeckMonsters = new ArrayList<>();
    private ArrayList<SpellTrapCard> mainDeckSpellTraps = new ArrayList<>();
    private ArrayList<MonsterCard> sideDeckMonsters = new ArrayList<>();
    private ArrayList<SpellTrapCard> sideDeckSpellTraps = new ArrayList<>();
    private final HashMap<String, Integer> cardsAmount = new HashMap<>();
    private String name;
    private String ownerName;

    static {
        allDecks = new ArrayList<>();
    }

    protected Object clone() throws CloneNotSupportedException {
        Deck newDeck = new Deck(this.name, this.ownerName, this.getCardsAmount());
        for (Card mainDeckCard : this.getMainDeck())
            newDeck.addCardToDeck(true, (Card) mainDeckCard.clone());
        for (Card sideDeckCard : this.getSideDeck())
            newDeck.addCardToDeck(false, (Card) sideDeckCard.clone());
        return newDeck;
    }

    public Deck(String name, String ownerName) {
        allDecks.add(this);
        setName(name);
        setOwner(ownerName);
    }

    public Deck(String name, String ownerName, ArrayList<Card> mainDeck, ArrayList<Card> sideDeck, HashMap<Card, Integer> cards) {
        allDecks.add(this);
        setName(name);
        setOwner(ownerName);
        setMainDeck(mainDeck);
        setSideDeck(sideDeck);
        setCardsAmount(cards);
    }

    public Deck(String name, String ownerName, HashMap<Card, Integer> cards) {
        setName(name);
        setOwner(ownerName);
        setCardsAmount(cards);
    }

    public static void loadAllDecks(ArrayList<Deck> decks) {
        allDecks = decks;
    }

    public static Deck getDeckByName(String name) {
        for (Deck allDeck : allDecks)
            if (allDeck.getName().equals(name))
                return allDeck;

        return null;
    }

    public boolean isMainFull() {
        return getMainDeck().size() > 59;
    }

    public boolean isSideFull() {
        return getSideDeck().size() > 14;
    }

    public boolean hasEnoughSpace(Card card) {
        if (hasUsedBefore(card))
            return getCardsAmount().get(card) <= 2;

        return true;
    }

    private boolean hasUsedBefore(Card card) {
        for (Card key : getCardsAmount().keySet())
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    public boolean isThisCardUsedInMain(Card card) {
        if (card == null)
            return false;
        for (Card key : getMainDeck())
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    public boolean isThisCardUsedInSide(Card card) {
        for (Card key : getSideDeck())
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    public boolean isDeckValid() {
        return getMainDeck().size() > 39;
    }

    public static ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setOwner(String ownerName) {
        this.ownerName = ownerName;
    }

    private void setMainDeck(ArrayList<Card> mainCards) {
        ArrayList<MonsterCard> monsters = new ArrayList<>();
        ArrayList<SpellTrapCard> spellTraps = new ArrayList<>();
        for (Card mainCard: mainCards) {
            if (mainCard.getCardType() == CardType.MONSTER)
                monsters.add((MonsterCard) mainCard);
            else
                spellTraps.add((SpellTrapCard) mainCard);
        }
        this.mainDeckMonsters = monsters;
        this.mainDeckSpellTraps = spellTraps;
    }

    public ArrayList<Card> getMainDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(mainDeckMonsters);
        cards.addAll(mainDeckSpellTraps);
        return cards;
    }

    private void setSideDeck(ArrayList<Card> sideCards) {
        ArrayList<MonsterCard> monsters = new ArrayList<>();
        ArrayList<SpellTrapCard> spellTraps = new ArrayList<>();
        for (Card sideCard: sideCards) {
            if (sideCard.getCardType() == CardType.MONSTER)
                monsters.add((MonsterCard) sideCard);
            else
                spellTraps.add((SpellTrapCard) sideCard);
        }
        this.sideDeckMonsters = monsters;
        this.sideDeckSpellTraps = spellTraps;
    }

    public ArrayList<Card> getSideDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(sideDeckMonsters);
        cards.addAll(sideDeckSpellTraps);
        return cards;
    }

    private void setCardsAmount(HashMap<Card, Integer> cards) {
        cardsAmount.clear();
        for (Card card : cards.keySet()) {
            cardsAmount.put(card.getName(), cards.get(card));
        }
    }

    public HashMap<Card, Integer> getCardsAmount() {
        HashMap<Card, Integer> cards = new HashMap<>();
        for (String cardName: cardsAmount.keySet())
            cards.put(Card.getCardByName(cardName), cardsAmount.get(cardName));

        return cards;
    }

    public void addCardToDeck(boolean isMain, Card card) {
        if (hasUsedBefore(card))
            cardsAmount.put(card.getName(), cardsAmount.get(card.getName()) + 1);
        else
            cardsAmount.put(card.getName(), 1);
        if (card.getCardType() == CardType.MONSTER) {
            if (isMain)
                mainDeckMonsters.add((MonsterCard) card);
            else
                sideDeckMonsters.add((MonsterCard) card);
        } else {
            if (isMain)
                mainDeckSpellTraps.add((SpellTrapCard) card);
            else
                sideDeckSpellTraps.add((SpellTrapCard) card);
        }
    }

    public void removeCardFromDeck(boolean isMain, Card card) {
            cardsAmount.put(card.getName(), cardsAmount.get(card.getName()) - 1);
        if (isMain) {
            if (card.getCardType() == CardType.MONSTER) {
                for (int i = 0; i < mainDeckMonsters.size(); i++)
                    if (mainDeckMonsters.get(i).getName().equals(card.getName())) {
                        mainDeckMonsters.remove(i);
                        break;
                    }
            } else
                for (int i = 0; i < mainDeckSpellTraps.size(); i++)
                    if (mainDeckSpellTraps.get(i).getName().equals(card.getName())) {
                        mainDeckSpellTraps.remove(i);
                        break;
                    }
        } else {
            if (card.getCardType() == CardType.MONSTER) {
                for (int i = 0; i < sideDeckMonsters.size(); i++)
                    if (sideDeckMonsters.get(i).getName().equals(card.getName())) {
                        sideDeckMonsters.remove(i);
                        break;
                    }
            } else
                for (int i = 0; i < sideDeckSpellTraps.size(); i++)
                    if (sideDeckSpellTraps.get(i).getName().equals(card.getName())) {
                        sideDeckSpellTraps.remove(i);
                        break;
                    }
        }
    }

    public void removeDeck() {
        Objects.requireNonNull(User.getUserByUserName(ownerName)).removeDeck(this);
        allDecks.remove(this);
    }

    public void removeCopiedDeck() {
        allDecks.remove(this);
    }

    private String getCardNameForGenerate(int number) {
        return switch (number) {
            case 1 -> "Monster Reborn";
            case 2 -> "Terraforming";
            case 3 -> "Pot of Greed";
            case 4 -> "Raigeki";
            case 5 -> "Change of Heart";
            case 6 -> "Swords of Revealing Light";
            case 7 -> "Harpie's Feather Duster";
            case 8 -> "Dark Hole";
            case 9 -> "Supply Squad";
            case 10 -> "Spell Absorption";
            case 11 -> "Messenger of peace";
            case 12 -> "Twin Twisters";
            case 13 -> "Mystical space typhoon";
            case 14 -> "Ring of defense";
            case 15 -> "Yami";
            case 16 -> "Forest";
            case 17 -> "Closed Forest";
            case 18 -> "Umiiruka";
            case 19 -> "Sword of dark destruction";
            case 20 -> "Black Pendant";
            case 21 -> "United We Stand";
            case 22 -> "Magnum Shield";
            case 23 -> "Advanced Ritual Art";
            case 24 -> "Trap Hole";
            case 25 -> "Mirror Force";
            case 26 -> "Magic Cylinder";
            case 27 -> "Mind Crush";
            case 28 -> "Torrential Tribute";
            case 29 -> "Time Seal";
            case 30 -> "Negate Attack";
            case 31 -> "Solemn Warning";
            case 32 -> "Magic Jammer";
            case 33 -> "Call of The Haunted";
            case 34 -> "Vanity's Emptiness";
            case 35 -> "Wall of Revealing Light";
            case 36 -> "Yomi Ship";
            case 37 -> "Man-Eater Bug";
            case 38 -> "Scanner";
            case 39 -> "Marshmallon";
            case 40 -> "Texchanger";
            case 41 -> "The Calculator";
            case 42 -> "Mirage Dragon";
            case 43 -> "Herald of Creation";
            case 44 -> "Exploder Dragon";
            case 45 -> "Terratiger, the Empowered Warrior";
            case 46 -> "The Tricky";
            case 47 -> "Command Knight";
            case 48 -> "Battle OX";
            case 49 -> "Axe Raider";
            case 50 -> "Horn Imp";
            case 51 -> "Silver Fang";
            case 52 -> "Fireyarou";
            case 53 -> "Curtain of the dark ones";
            case 54 -> "Feral Imp";
            case 55 -> "Wattkid";
            case 56 -> "Baby dragon";
            case 57 -> "Hero of the east";
            case 58 -> "Battle warrior";
            case 59 -> "Crawling dragon";
            case 60 -> "Flame manipulator";
            case 61 -> "Haniwa";
            case 62 -> "Bitron";
            case 63 -> "Leotron";
            case 64 -> "Alexandrite Dragon";
            case 65 -> "Warrior Dai Grepher";
            case 66 -> "Dark Blade";
            case 67 -> "Crab Turtle";
            case 68 -> "Skull Guardian";
            case 69 -> "Suijin";
            case 70 -> "Gate Guardian";
            case 71 -> "Beast King Barbaros";
            case 72 -> "Dark magician";
            case 73 -> "Blue-Eyes white dragon";
            case 74 -> "Slot Machine";
            case 75 -> "Wattaildragon";
            case 76 -> "Spiral Serpent";
            default -> "";
        };
    }

    public static Deck generateDeck(boolean isForEasy) {
        Deck deck = new Deck("", "Bot");
        int mainCardNumbers = (int) (Math.random() * 21) + 40;
        int card;
        for (int i = 0; i < mainCardNumbers; i++) {
            if (isForEasy)
                card = (int) (Math.random() * 66) + 1;
            else
                card = (int) (Math.random() * 76) + 1;

            if (deck.hasEnoughSpace(Card.getCardByName(deck.getCardNameForGenerate(card))))
                deck.addCardToDeck(true, MakeCards.makeCard(deck.getCardNameForGenerate(card)));
            else
                mainCardNumbers++;
        }

        return deck;
    }

    public String toString() {
        if (isDeckValid())
            return this.name + ": main deck " + getMainDeck().size() + ", side deck " + getSideDeck().size()
                    + ", valid";
        else
            return this.name + ": main deck " + getMainDeck().size() + ", side deck " + getSideDeck().size()
                    + ", invalid";
    }
}
