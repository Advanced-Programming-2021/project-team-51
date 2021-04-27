package models;

import java.util.ArrayList;
import models.cards.Card;

public class User {

    public static ArrayList<User> allUsers;
    private ArrayList<Card> userCards = new ArrayList<>();
    private ArrayList<Deck> userDecks = new ArrayList<>();
    private Deck activeDeck;
    private Board userBoard;
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
        setBoard(null);
        setUserName(userName);
        setNickName(nickName);
        setPassword(password);
        setScore(0);
        setMoney(0);
    }

    public void loadUser(int score, int money, Deck activeDeck, ArrayList<Card> userCards, ArrayList<Deck> userDecks) {
        setActiveDeck(activeDeck);
        setScore(score);
        setMoney(money);
        setUserCards(userCards);
        setUserDecks(userDecks);
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
        sortUsers(allUsers);
        return allUsers;
    }

    private static void sortUsers(ArrayList<User> allUsers) {
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
        return this.userDecks;
    }

    private void setUserCards(ArrayList<Card> cards) {
        this.userCards = cards;
    }

    public ArrayList<Card> getUserCards() {
        return this.userCards;
    }

    private void setUserName(String userName) {
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

    public void setBoard(Board board) {
        this.userBoard = board;
    }

    public Board getBoard() {
        return this.userBoard;
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
        this.userCards.add(card);
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

    public void changeNickName(String nickName) {
        setNickName(nickName);
    }

    public String toString() {
        return this.nickName + ": " + this.score;
    }
}
