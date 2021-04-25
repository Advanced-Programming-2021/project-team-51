package models.cards;

import java.util.ArrayList;
import java.util.HashSet;

import models.cards.Card;

import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {

    private ArrayList<User> allUsers = new ArrayList<>();
    private HashSet<Card> allCards = new Hashset<>();
    private ArrayList<Deck> allDecks = new ArrayList<>();

    private void run() {
        saveAllUsers();
    }

    private String saveUserCards(User user) {
        StringBuilder stringCards = new StringBuilder();
        ArrayList<Card> userCards = user.getUserCards();
        for (int i = 0 ; i < userCards.size() ; i++) {
            if (i > 0) stringCards.append(", " + userCards.get(i).getName());
            else stringCards.append(userCards.get(i).getName());
        }

        return stringCards.toString();
    }

    private String saveUserDecks(User user) {
        StringBuilder stringDecks = new StringBuilder();
        ArrayList<Deck> userDecks = user.getUserDecks();
        for (int i = 0 ; i < userDecks.size() ; i++)
            stringDecks.append("normaldeck: {" + userDecks.get(i).saveString() + "}\n");

        return stringDecks.toString();
    }

    private void saveUser(User user, int counter) {
        try {
            File userFile = new File("Data\\usr_" + (counter+1));
            userFile.createNewFile();
            FileWriter userFileWrite = new FileWriter(userFile);
            userFileWrite.write("username: " + user.getUserName() + "\nnickname: " + user.getNickName() + 
            "\npassword: " + user.getPassword() + "\nscore: " + user.getScore() + "\t\tmoney: " + user.getMoney());
            if (user.getActiveDeck() == null) userFileWrite.append("\nactivedeck: NULL\n");
            else userFileWrite.append("\nactivedeck: {" + user.getActiveDeck().saveString() + "}\n");
            userFileWrite.append(saveUserDecks(user));
            userFileWrite.append("usercards: {" + saveUserCards(user) + "}");
        }   catch (IOException error) {
            System.out.println("cannot save all Data !");
        }
    }

    private void saveAllUsers() {
        try {
            File makeDirection = new File("Data");
            makeDirection.mkdir();
        }   catch (IOException error) {
            System.out.println("cannot save the Data !");
        }

        allUsers = User.getSortedUsers();
        for (int i = 0 ; i < allUsers.size() ; i++)
            saveUser(allUsers.get(i), i);
    }

    private Card loadCard(File file) {

    }

    private Deck loadDeck(File file) {

    }

    private ArrayList<Card> loadUserCards(File file) {

    }

    private ArrayList<Deck> loadUserDecks(File file) {

    }

    private User loadUser(File file) {

    }

    public ArrayList<User> loadAllUsers() {

    }

    public HashSet<Card> loadAllCards() {

    }

    public ArrayList<Deck> loadAllDecks() {

    }

    
}