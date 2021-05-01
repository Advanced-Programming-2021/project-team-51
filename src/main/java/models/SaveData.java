package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import models.cards.Card;
import models.cards.MakeCards;

import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SaveData {

    public SaveData(boolean isEndingTheProgram) {
        if (isEndingTheProgram)
            saveAllUsers();
        else
            loadAllUsers();
    }

    private String saveUserCards(User user) {
        StringBuilder stringCards = new StringBuilder();
        ArrayList<Card> userCards = user.getUserCards();
        for (int i = 0; i < userCards.size(); i++) {
            if (i > 0)
                stringCards.append(", " + userCards.get(i).getName());
            else
                stringCards.append(userCards.get(i).getName());
        }

        return stringCards.toString();
    }

    private String saveUserDecks(User user) {
        StringBuilder stringDecks = new StringBuilder();
        ArrayList<Deck> userDecks = user.getUserDecks();
        for (int i = 0; i < userDecks.size(); i++)
            stringDecks.append(userDecks.get(i).getName() + "_deck: {" + userDecks.get(i).saveString() + "}\n");

        return stringDecks.toString();
    }

    private void saveUser(User user, int counter) {
        try {
            File userFile = new File("Data\\usr_" + (counter + 1));
            userFile.createNewFile();
            FileWriter userFileWrite = new FileWriter(userFile);
            userFileWrite.write("username: " + user.getUserName() + "\nnickname: " + user.getNickName() + "\npassword: "
                    + user.getPassword() + "\nscore: " + user.getScore() + "\t\tmoney: " + user.getMoney());
            if (user.getActiveDeck() == null)
                userFileWrite.append("\nactivedeck: \n");
            else
                userFileWrite.append("\nactivedeck: " + user.getActiveDeck().getName() + "\n");
            userFileWrite.append(saveUserDecks(user));
            userFileWrite.append("usercards: {" + saveUserCards(user) + "}");
            userFileWrite.close();
        } catch (IOException error) {
            System.out.println("cannot save all Data !");
        }
    }

    private void saveAllUsers() {
        File makeDirection = new File("Data");
        makeDirection.mkdir();

        ArrayList<User> allUsers = User.getSortedUsers();
        for (int i = 0; i < allUsers.size(); i++)
            saveUser(allUsers.get(i), i);
    }

    private Card loadUserCard(String cardName) {
        return MakeCards.makeCard(cardName);
    }

    private Card loadCard(String cardName) {
        return Card.getCardByName(cardName);
    }

    private boolean hasUsedBefore(Card card, HashMap<Card, Integer> cards) {
        for (Card key : cards.keySet())
            if (key.getName().equals(card.getName()))
                return true;

        return false;
    }

    private Deck loadDeck(String deckCards, String deckName, User owner) {
        ArrayList<Card> mainCards = new ArrayList<>();
        ArrayList<Card> sideCards = new ArrayList<>();
        HashMap<Card, Integer> cards = new HashMap<>();
        Pattern pattern = Pattern.compile("Main = {(.*)}, Side = {(.*)}");
        Matcher matcher = pattern.matcher(deckCards);
        if (matcher.find()) {
            String[] mainCardNames = matcher.group(1).split(", ");
            String[] sideCardNames = matcher.group(2).split(", ");

            for (int i = 0; i < mainCardNames.length; i++) {
                mainCards.add(loadCard(mainCardNames[i]));
                if (hasUsedBefore(loadCard(mainCardNames[i]), cards))
                    cards.put(loadCard(mainCardNames[i]), cards.get(loadCard(mainCardNames[i])) + 1);
                else
                    cards.put(loadCard(mainCardNames[i]), 1);
            }
            for (int i = 0; i < sideCardNames.length; i++) {
                sideCards.add(loadCard(sideCardNames[i]));
                if (hasUsedBefore(loadCard(mainCardNames[i]), cards))
                    cards.put(loadCard(mainCardNames[i]), cards.get(loadCard(mainCardNames[i])) + 1);
                else
                    cards.put(loadCard(mainCardNames[i]), 1);
            }
        }
        Deck deck = new Deck(deckName, owner, mainCards, sideCards, cards);

        return deck;
    }

    private ArrayList<Card> loadUserCards(String[] cards) {
        ArrayList<Card> userCards = new ArrayList<>();
        for (int i = 0; i < cards.length; i++)
            userCards.add(loadUserCard(cards[i]));
        return userCards;
    }

    private ArrayList<Deck> loadUserDecks(String[] decks, User owner) {
        ArrayList<Deck> userDecks = new ArrayList<>();
        for (int i = 0; i < decks.length; i++) {
            Pattern pattern = Pattern.compile("(.*)_deck: {(.*)}");
            Matcher matcher = pattern.matcher(decks[i]);
            if (matcher.find()) {
                String deckName = matcher.group(1);
                String deckCards = matcher.group(2);
                userDecks.add(loadDeck(deckCards, deckName, owner));
            }
        }

        return userDecks;
    }

    private void loadUser(FileReader file) {
        try {
            Pattern pattern = Pattern.compile(
                    "username: (.*)\nnickname: (.*)\npassword: (.*)\nscore: (.*)\t\tmoney: (.*)\nactivedeck: (.*)\n(.*)usercards: {(.*)}");
            char[] fileData = new char[10000];
            file.read(fileData);
            String fileDataString = String.valueOf(fileData);
            Matcher matcher = pattern.matcher(fileDataString);
            if (matcher.find()) {
                String username = matcher.group(1);
                String nickname = matcher.group(2);
                String password = matcher.group(3);
                User user = new User(username, nickname, password);
                int score = Integer.parseInt(matcher.group(4));
                int money = Integer.parseInt(matcher.group(5));
                Deck activeDeck = Deck.getDeckByName(matcher.group(6));
                String[] decksStrings = matcher.group(7).split("\n");
                String[] cardsStrings = matcher.group(8).split(", ");
                ArrayList<Card> userCards = loadUserCards(cardsStrings);
                ArrayList<Deck> userDecks = loadUserDecks(decksStrings, user);
                user.loadUser(score, money, activeDeck, userCards, userDecks);
            }
        } catch (IOException error) {
            System.out.println("There's a problem while loading the Data");
        }
    }

    public void loadAllUsers() {
        File directory = new File("Data");
        if (directory.isDirectory()) {
            String[] filenames = directory.list();
            for (int i = 0; i < filenames.length; i++) {
                try {
                    FileReader file = new FileReader("Data\\" + filenames[i]);
                    loadUser(file);
                } catch (IOException error) {
                    System.out.println("Some of Files can't open !");
                }
            }
        } else {
            System.out.println("can't load Data !");
        }

    }
}
