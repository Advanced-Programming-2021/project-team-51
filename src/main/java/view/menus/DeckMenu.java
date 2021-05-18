package view.menus;

import controller.menucontroller.DeckMenuController;
import models.User;
import models.cards.CardType;
import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class DeckMenu {

    private User currentUser;
    private DeckMenuController deckMenuController;

    public void run(String command) {

        deckMenuController = new DeckMenuController(currentUser);

        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.createDeck)).matches()){
            String deckName = matcher.group(1);
            createDeck(deckName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.deleteDeck)).matches()){
            String deckName = matcher.group(1);
            deleteDeck(deckName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.activateDeck)).matches()){
            String deckName = matcher.group(1);
            activateDeck(deckName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck1)).matches()){
            String cardName = matcher.group(2);
            String deckName = matcher.group(4);
            String mainOrSide;
            if (matcher.group(5) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck2)).matches()){
            String cardName = matcher.group(2);
            String deckName = matcher.group(5);
            String mainOrSide;
            if (matcher.group(3) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck3)).matches()){
            String cardName = matcher.group(4);
            String deckName = matcher.group(2);
            String mainOrSide;
            if (matcher.group(5) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck4)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(2);
            String mainOrSide;
            if (matcher.group(3) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck5)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(3);
            String mainOrSide;
            if (matcher.group(1) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.addCardToDeck6)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(4);
            String mainOrSide;
            if (matcher.group(1) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            addCard(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck1)).matches()){
            String cardName = matcher.group(2);
            String deckName = matcher.group(4);
            String mainOrSide;
            if (matcher.group(5) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck2)).matches()){
            String cardName = matcher.group(2);
            String deckName = matcher.group(5);
            String mainOrSide;
            if (matcher.group(3) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);

        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck3)).matches()){
            String cardName = matcher.group(4);
            String deckName = matcher.group(2);
            String mainOrSide;
            if (matcher.group(5) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);

        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck4)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(2);
            String mainOrSide;
            if (matcher.group(3) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);

        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck5)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(3);
            String mainOrSide;
            if (matcher.group(1) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);

        }
        else if ((matcher = Regex.getMatcher(command, Regex.removeCardFromDeck6)).matches()){
            String cardName = matcher.group(5);
            String deckName = matcher.group(4);
            String mainOrSide;
            if (matcher.group(1) == null){ //---------------------IN TIKARO SURE NISTAM:|-------------------
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            removeCardFromDeck(deckName,cardName,mainOrSide);

        }
        else if ((matcher = Regex.getMatcher(command, Regex.showAllDecks)).matches()){
            showAllUserDecks();
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showOptionalDeck1)).matches()){
            String deckName = matcher.group(2);
            String mainOrSide;
            if (matcher.group(3) == null){
                mainOrSide = "Main";
            }
            else{
                mainOrSide = "Side";
            }
            showOptionalDeck(deckName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showOptionalDeck2)).matches()){
            String deckName = matcher.group(3);
            String mainOrSide;
            if (matcher.group(1) == null){
                mainOrSide = "Main";
            }
            else{
                mainOrSide = "Side";
            }
            showOptionalDeck(deckName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showAllUserCards)).matches()){
            showAllCards();
        }
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches()) {
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command, Regex.enterMenu)).matches()) {
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showCurrentMenu)).matches()) {
            System.out.println("Deck");
        }
        else {
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }


    private void showAllUserDecks(){
        System.out.println("Decks:");
        System.out.println(currentUser.getActiveDeck().toString());
        System.out.println("Other decks:");
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            System.out.println(currentUser.getUserCards().get(i).toString());
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------

    private void createDeck(String deckName) {
        System.out.println(deckMenuController.createDeck(deckName));
    }

    private void deleteDeck(String deckName) {
        System.out.println(deckMenuController.deleteDeck(deckName));
    }

    private void activateDeck(String deckName) {
        System.out.println(deckMenuController.activateDeck(deckName));
    }

    private void addCard(String deckName, String cardName, String mainOrSide) {
        System.out.println(deckMenuController.addCard(deckName, cardName, mainOrSide));
    }

    private void removeCardFromDeck(String deckName, String cardName, String mainOrSide) {
        System.out.println(deckMenuController.removeCardFromDeck(deckName, cardName, mainOrSide));
    }

    private void showOptionalDeck(String deckName, String mainOrSide) {
        System.out.println("Deck: " + deckName);
        System.out.println(mainOrSide + "deck:");
        System.out.println("Monsters:");
        if (mainOrSide.equals("Main")) {
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getMainDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.MONSTER)) {
                    String cardName = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
            System.out.println("Spell and Traps:");
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getMainDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.SPELL) || currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.TRAP)) {
                    String cardName = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
        } else {
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getSideDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getCardType().equals(CardType.MONSTER)) {
                    String cardName = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
            System.out.println("Spell and Traps:");
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getSideDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getCardType().equals(CardType.SPELL) || currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.TRAP)) {
                    String cardName = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------

    private void showAllCards() {
        for (int i = 0; i < currentUser.getUserCards().size(); i++) {
            System.out.println(currentUser.getUserCards().get(i).getName() + ":" + currentUser.getUserCards().get(i).getDescription());
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------
}