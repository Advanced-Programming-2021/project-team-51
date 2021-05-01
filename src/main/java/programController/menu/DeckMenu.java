package main.java.programController.menu;

import main.java.models.Deck;
import main.java.models.User;
import main.java.models.cards.Card;
import main.java.programController.MenuEnum;
import main.java.programController.ProgramController;
import main.java.programController.Regex;
import main.java.programController.StatusEnum;

import java.util.regex.Matcher;


/*-----------------IS MAIN DECK FULL: WE CANT DISTINGUISH MAIN IS FULL OR SIDE---------------*/
/*-----------------doesCardExist: WE CANT DISTINGUISH MAIN OR SIDE---------------*/
/*------------------isThereThreeCards METHOD NOT FIXED YET------------------------*/



public class DeckMenu {
    private User currentUser;

    public void run(String command) {
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
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
            }
            showOptionalDeck(deckName,mainOrSide);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showOptionalDeck2)).matches()){
            String deckName = matcher.group(3);
            String mainOrSide;
            if (matcher.group(1) == null){
                mainOrSide = "main";
            }
            else{
                mainOrSide = "side";
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


    private Deck getDeckByName(String deckName){
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            if (currentUser.getUserDecks().get(i).getName().equals(deckName)){
                return currentUser.getUserDecks().get(i);
            }
        }
        return null;
    }
    private Card getCardByName(String cardName){
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            if (currentUser.getUserCards().get(i).getName().equals(cardName)){
                return currentUser.getUserCards().get(i);
            }
        }
        return null;
    }


    private void createDeck(String deckName) {
        if (doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "already exists");
        }
        else{
            currentUser.addDeck(new Deck(deckName, currentUser));
            System.out.println(StatusEnum.DECK_CREATE_SUCCESSFULLY);
        }
    }
    private boolean doesDeckExist(String deckName) {
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            if (currentUser.getUserDecks().get(i).getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }
    private boolean doesCardExist(String cardName) {
        for (int i = 0; i < currentUser.getUserCards().size(); i++) {
            if (currentUser.getUserCards().get(i).getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }
    private boolean isMainDeckFull(String deckName,String mainOrSide){
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            if (currentUser.getUserDecks().get(i).getName().equals(deckName)) {
                if (currentUser.getUserDecks().get(i).hasEnoughSpace(mainOrSide)){
                    return  true;
            }
        }
    }
        return false;
    }
    private boolean isThereThreeCards(String deckName,String mainOrSide){
        return  true;
    }//-------------PROBLEM-------
    private boolean doesCardExistInDeck(String  deckName,String cardName){return true;}//--------------PROBLEM--------------
    private void deleteDeck(String deckName){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else{
            currentUser.removeDeck(getDeckByName(deckName));
        }
    }
    private void activateDeck(String deckName){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else {
            currentUser.setActiveDeck(getDeckByName(deckName));
            System.out.println(StatusEnum.DECK_CREATE_SUCCESSFULLY);
        }
    }
    private void addCard(String deckName,String cardName,String mainOrSide){
        if (!doesCardExist(cardName)){
            System.out.println("card with name"+ cardName +"does not exist");
        }
        else if (!doesDeckExist(deckName)){
            System.out.println("deck with name"+ deckName +"does not exist");
        }
        else if (!isMainDeckFull(deckName,mainOrSide)){
            System.out.println("<main/side> deck is full");//---------------------PROBLEM-----------------------------
        }
        else if(isThereThreeCards(deckName,mainOrSide)){
            //-------------PROBLEM-------------
        }
        else {
            for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
                if (currentUser.getUserDecks().get(i).getName().equals(deckName)){
                    currentUser.getUserDecks().get(i).addCardToDeck(mainOrSide, Card.getCardByName(cardName));
                    System.out.println(StatusEnum.CARD_ADDED_TO_DECK_SUCCESSFULLY);
                    break;
                }
            }
        }
    }//----------------PROBLEM-------------
    private void removeCardFromDeck(String deckName,String cardName,String mainOrSide){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else if(!doesCardExistInDeck(deckName,cardName)){
            System.out.println("card with name <card name> does not exist in <main/side> deck");//------------PROBLEM--------
        }
        else{
            for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
                if (currentUser.getUserDecks().get(i).getName().equals(deckName)){
                    currentUser.getUserDecks().get(i).removeCardFromDeck(mainOrSide,getCardByName(cardName));
                    System.out.println(StatusEnum.CARD_ADDED_TO_DECK_SUCCESSFULLY);
                    break;
                }
            }
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
    private void showOptionalDeck(String deckName,String mainOrSide){}//--------------ISN''T FIXED YET-------------
    private void showAllCards(){
        for (int i = 0; i < currentUser.getUserCards().size(); i++) {
            System.out.println(currentUser.getUserCards().get(i).getName()+":"+currentUser.getUserCards().get(i).getDescription());
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------


}
