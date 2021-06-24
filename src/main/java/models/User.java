package models;

import java.util.ArrayList;
import java.util.Arrays;

import models.cards.Card;
import models.cards.CardType;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

public class User {

    public static ArrayList<User> allUsers;
    private ArrayList<MonsterCard> userMonsters = new ArrayList<>();
    private ArrayList<SpellTrapCard> userSpellTraps = new ArrayList<>();
    private ArrayList<Deck> userDecks = new ArrayList<>();
    private Deck activeDeck;
    private String userName;
    private String nickName;
    private String password;
    private int score;
    private int money;

    static {
        allUsers = new ArrayList<>();
    }

    public User(String userName, String nickName, String password) {
        allUsers.add(this);
        setActiveDeck(null);
        setUserName(userName);
        setNickName(nickName);
        setPassword(password);
        setScore(0);
        setMoney(0);
    }

    public static void loadAllUsers(ArrayList<User> users) {
        allUsers = users;
    }

    public static User getUserByUserName(String userName) {
        for (int i = 0; i < allUsers.size(); i++)
            if (allUsers.get(i).getUserName().equals(userName))
                return allUsers.get(i);

        return null;
    }

    public static boolean isUserNameTaken(String userName) {
        for (int i = 0; i < allUsers.size(); i++)
            if (allUsers.get(i).getUserName().equals(userName))
                return true;

        return false;
    }

    public static boolean isNickNameTaken(String nickName) {
        for (int i = 0; i < allUsers.size(); i++)
            if (allUsers.get(i).getNickName().equals(nickName))
                return true;

        return false;
    }

    public static ArrayList<User> getSortedUsers() {
        sortUsers();
        return allUsers;
    }

    private void sortUserDecks() {
        String[] deckNames = new String[userDecks.size()];
        for (int i = 0; i < deckNames.length; i++)
            deckNames[i] = userDecks.get(i).getName();

        Arrays.sort(deckNames);
        for (int i = 0; i < deckNames.length; i++)
            userDecks.set(i, Deck.getDeckByName(deckNames[i]));
    }

    private void sortUserCards() {
        String[] monsterNames = new String[userMonsters.size()];
        String[] spellTrapNames = new String[userSpellTraps.size()];
        for (int i = 0; i < monsterNames.length; i++)
            monsterNames[i] = userMonsters.get(i).getName();
        for (int i = 0; i < spellTrapNames.length; i++)
            spellTrapNames[i] = userSpellTraps.get(i).getName();

        Arrays.sort(monsterNames);
        Arrays.sort(spellTrapNames);
        for (int i = 0; i < monsterNames.length; i++)
            userMonsters.set(i, (MonsterCard) Card.getCardByName(monsterNames[i]));
        for (int i = 0; i < spellTrapNames.length; i++)
            userSpellTraps.set(i, (SpellTrapCard) Card.getCardByName(spellTrapNames[i]));
    }

    private static void sortUsers() {
        for (int i = 1; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            int j = i - 1;
            while (j >= 0 && !User.compareTwoUsers(allUsers.get(j), user)) {
                allUsers.set(j + 1, allUsers.get(j));
                j--;
            }
            allUsers.set(j + 1, user);
        }
    }

    private static boolean compareTwoUsers(User first, User second) {
        if (first.getScore() > second.getScore())
            return true;
        else if (first.getScore() < second.getScore())
            return false;
        else if (first.getNickName().compareTo(second.getNickName()) < 0)
            return true;
        else
            return false;
    }

    private void setUserDecks(ArrayList<Deck> decks) {
        this.userDecks = decks;
    }

    public ArrayList<Deck> getUserDecks() {
        sortUserDecks();
        return this.userDecks;
    }

    private void setUserMonsters(ArrayList<MonsterCard> monsters) {
        this.userMonsters = monsters;
    }

    private void setUserSpellTraps(ArrayList<SpellTrapCard> spellTraps) {
        this.userSpellTraps = spellTraps;
    }

    public ArrayList<Card> getUserCards() {
        sortUserCards();
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(userMonsters);
        cards.addAll(userSpellTraps);
        return cards;
    }

    public Card getUserCardByName(String name) {
        if (Card.getCardByName(name) == null) {
            return null;
        } else if (Card.getCardByName(name).getCardType() == CardType.MONSTER)
                for (MonsterCard monster : userMonsters)
                    if (monster.getName().equals(name))
                        return monster;
            else
                for (SpellTrapCard spellTrap : userSpellTraps)
                    if (spellTrap.getName().equals(name))
                        return spellTrap;


        return null;
    }

    public Deck getUserDeckByName(String name) {
        for (Deck deck : userDecks)
            if (deck.getName().equals(name))
                return deck;

        return null;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setActiveDeck(Deck deck) {
        this.activeDeck = deck;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    public void setScore(int amount) {
        this.score = amount;
    }

    public int getScore() {
        return this.score;
    }

    public void setMoney(int amount) {
        this.money = amount;
    }

    public int getMoney() {
        return this.money;
    }

    public void addCard(Card card) {
        if (card.getCardType() == CardType.MONSTER)
            this.userMonsters.add((MonsterCard) card);
        else
            this.userSpellTraps.add((SpellTrapCard) card);
    }

    public void addDeck(Deck deck) {
        this.userDecks.add(deck);
    }

    public void removeDeck(Deck deck) {
        this.userDecks.remove(deck);
    }

    public void changePassword(String password) {
        setPassword(password);
    }

    public String toString() {
        return this.nickName + ": " + this.score;
    }
}
