package models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.kie.commons.java.nio.file.Files;
import org.kie.commons.java.nio.file.Paths;

import models.cards.Card;
import models.cards.MonsterCard;
import models.cards.SpellTrapCard;

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
                for (int j = 0; j < fileNames.length; j++)
                    if (fileNames[j].equals("card" + i))
                        isThere = true;

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

    public ArrayList<Card> loadCustomCards() {
        ArrayList<Card> cards = new ArrayList<>();
            File file = new File("C:\\YuGiOhData\\savedCards");
            String[] fileNames = file.list();
            for (int i = 0; i < fileNames.length; i++) {
                String cardName = new String(Files.readAllBytes(Paths.get(fileNames[i])));
                cards.add(Card.getCardByName(cardName));
            }
        return cards;
    }

    public void save() {
        File makeDirection = new File("C:\\YuGiOhData");
        if (!makeDirection.isDirectory())
            makeDirection.mkdir();

            saveAllCards();
            saveAllDecks();
            saveAllUsers();
    }

    public void saveAllUsers() {
        ArrayList<User> allUsers = User.getSortedUsers();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\users.DAT");
            writer.write(new Gson().toJson(allUsers));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public void saveAllCards() {
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

    public void saveSpellsAndTraps() {
        ArrayList<SpellTrapCard> allSpellsAndTraps = SpellTrapCard.getAllSpellTrapCards();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\spells&traps.DAT");
            writer.write(new Gson().toJson(allSpellsAndTraps));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public void saveMonsters() {
        ArrayList<MonsterCard> allMonsters = MonsterCard.getAllMonsterCards();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\monsters.DAT");
            writer.write(new Gson().toJson(allMonsters));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public void saveAllDecks() {
        ArrayList<Deck> allDecks = Deck.getAllDecks();
        try {
            FileWriter writer = new FileWriter("C:\\YuGiOhData\\decks.DAT");
            writer.write(new Gson().toJson(allDecks));
            writer.close();
        } catch (Exception error) {
            System.out.println("Couldn't save Data!");
        }
        
    }

    public void load() {
        File makeDirection = new File("C:\\YuGiOhData");
        if (makeDirection.isDirectory()) {

            loadCards();
            loadDecks();
            loadUsers();
        }
            
    }

    public void loadCards() {

    }

    public void loadDecks() {
        String jsonDecks = new String(Files.readAllBytes(Paths.get("C:\\YuGiOhData\\decks.DAT")));
        ArrayList<Deck> decks = new ArrayList<>();
        decks = new Gson().fromJson(jsonDecks, new TypeToken<List<Deck>>(){}.getType());
        Deck.loadAllDecks(decks);
    }

    public void loadUsers() {
        String jsonUsers = new String(Files.readAllBytes(Paths.get("C:\\YuGiOhData\\users.DAT")));
        ArrayList<User> users = new ArrayList<>();
        users = new Gson().fromJson(jsonUsers, new TypeToken<List<User>>(){}.getType());
        User.loadAllUsers(users);
    }
}
