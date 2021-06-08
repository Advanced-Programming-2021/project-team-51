package models;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.cards.Card;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {

    public void saveCustomCard(Card card) {
        try {
            File file = new File("C:\\YuGiOhData\\savedCards");
            String[] fileNames = file.list();
            int key = 0;
            for (int i = 0; i < fileNames.length; i++) {
                boolean isThere = false;
                for (String fileName : fileNames)
                    if (fileName.equals("card" + i)) {
                        isThere = true;
                        break;
                    }

                if (!isThere) {
                    key = i;
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter("C:\\YuGiOhData\\savedCards\\card" + key);
            fileWriter.write(card.getName() + ": " + card.getDescription());
            fileWriter.close();
        } catch (IOException error) {
            System.out.println("can't save the card!");
        }
    }

    public ArrayList<Card> loadCustomCards() throws IOException {
        ArrayList<Card> cards = new ArrayList<>();
            File file = new File("C:\\YuGiOhData\\savedCards");
            String[] fileNames = file.list();
        for (String fileName : fileNames) {
            String cardName = new String(Files.readAllBytes(Paths.get(fileName)));
            cards.add(Card.getCardByName(cardName));
        }
        return cards;
    }

    public static void save() {
        File makeDirection = new File("C:\\YuGiOhData");
        if (!makeDirection.isDirectory() || !makeDirection.exists())
            if (makeDirection.mkdir()) {
                saveAllCards();
                saveAllDecks();
                saveAllUsers();
            }
    }

    public static void saveAllUsers() {
        ArrayList<User> allUsers = User.getSortedUsers();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\users.DAT");
            writer.write(new Gson().toJson(allUsers));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public static void saveAllCards() {
        ArrayList<Card> allCards = Card.getAllCards();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\cards.DAT");
            writer.write(new Gson().toJson(allCards));
            writer.close();
            saveMonsters();
            saveSpellsAndTraps();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public static void saveSpellsAndTraps() {
        ArrayList<SpellTrapCard> allSpellsAndTraps = SpellTrapCard.getAllSpellTrapCards();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\spells&traps.DAT");
            writer.write(new Gson().toJson(allSpellsAndTraps));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public static void saveMonsters() {
        ArrayList<MonsterCard> allMonsters = MonsterCard.getAllMonsterCards();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\monsters.DAT");
            writer.write(new Gson().toJson(allMonsters));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public static void saveAllDecks() {
        ArrayList<Deck> allDecks = Deck.getAllDecks();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\decks.DAT");
            writer.write(new Gson().toJson(allDecks));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public static void load() throws IOException {
        File makeDirection = new File("C:\\YuGiOhData");
        if (makeDirection.isDirectory()) {
            loadCards();
            loadDecks();
            loadUsers();
        }
            
    }

    public static void loadCards() throws IOException {
        File file = new File("C:\\YuGiOhData\\cards.DAT");
        if (!file.exists()) {
            Card.setAllCards(new ArrayList<>());
        } else {
            String jsonCards = new String(Files.readAllBytes(Paths.get("C:\\YuGiOhData\\cards.DAT")));
            ArrayList<Card> cards;
            cards = new Gson().fromJson(jsonCards, new TypeToken<List<Card>>() {
            }.getType());
            Card.setAllCards(cards);
        }
    }

    public static void loadDecks() throws IOException {
        File file = new File("C:\\YuGiOhData\\cards.DAT");
        if (!file.exists()) {
            Card.setAllCards(new ArrayList<>());
        } else {
            String jsonDecks = new String(Files.readAllBytes(Paths.get("C:\\YuGiOhData\\decks.DAT")));
            ArrayList<Deck> decks;
            decks = new Gson().fromJson(jsonDecks, new TypeToken<List<Deck>>() {
            }.getType());
            Deck.loadAllDecks(decks);
        }
    }

    public static void loadUsers() throws IOException {
        File file = new File("C:\\YuGiOhData\\cards.DAT");
        if (!file.exists()) {
            Card.setAllCards(new ArrayList<>());
        } else {
            String jsonUsers = new String(Files.readAllBytes(Paths.get("C:\\YuGiOhData\\users.DAT")));
            ArrayList<User> users;
            users = new Gson().fromJson(jsonUsers, new TypeToken<List<User>>() {
            }.getType());
            User.loadAllUsers(users);
        }
    }
}
