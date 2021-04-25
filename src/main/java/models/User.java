package models.cards;

import java.util.ArrayList;

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

    public static void loadUsers(ArrayList<User> allUsers) {
        SaveData saveData = new SaveData();
        allUsers = saveData.loadAllUsers();
    }

    public static User getUserByUserName(String userName) {
        for (int i = 0 ; i < allUsers.size() ; i++)
            if (allUsers.get(i).getUserName().equals(userName)) return allUsers.get(i);
        
        return null;
    }

    public static boolean isUserNameTaken(String userName) {
        for (int i = 0 ; i < allUsers.size() ; i++)
            if (allUsers.get(i).getUserName().equals(userName)) return true;

        return false;
    }

    public static boolean isNickNameTaken(String nickName) {
        for (int i = 0 ; i < allUsers.size() ; i++)
            if (allUsers.get(i).getNickName().equals(nickName)) return true;

        return false;
    }

    public static ArrayList<User> getSortedUsers() {
        sortUsers(allUsers);
        return allUsers;
    }

    private static void sortUsers(ArrayList<User> allUsers) {
        for (int i = 1 ; i < allUsers.size() ; i++) {
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
        if (first.getScore() > second.getScore()) return true;
        else if (first.getScore() < second.getScore()) return false;
        else if (first.getNickName().compareTo(second.getNickName()) < 0) return true;
        else return false;
    }

    public ArrayList<Deck> getUserDecks() {
        return this.userDecks;
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

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return this.nickName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    private void setBoard(Board board) {
        this.userBoard = board;
    }

    public Board getBoard() {
        return this.userBoard;
    }

    private void setActiveDeck(Deck deck) {
        this.activeDeck = deck;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    private void setScore(int amount) {
        this.score = amount;
    }
    
    public int getScore() {
        return this.score;
    }

    private void setMoney(int amount) {
        this.money = amount;
    }

    public int getMoney() {
        return this.money;
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