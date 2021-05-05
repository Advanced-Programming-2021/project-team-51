package main.java.programController.menu;




//--------FAGHAT ALEPHNA----------------------


import main.java.cards.CardType;
import main.java.models.Deck;
import main.java.models.User;
import main.java.models.cards.Card;
import main.java.programController.StatusEnum;

import java.util.regex.Matcher;

public class DeckMenu {
    private User currentUser;

    public void run(String command) {
        Matcher matcher;
        if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.createDeck)).matches()){
            String deckName = matcher.group(1);
            createDeck(deckName);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.deleteDeck)).matches()){
            String deckName = matcher.group(1);
            deleteDeck(deckName);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.activateDeck)).matches()){
            String deckName = matcher.group(1);
            activateDeck(deckName);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck1)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck2)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck3)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck4)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck5)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.addCardToDeck6)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck1)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck2)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck3)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck4)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck5)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.removeCardFromDeck6)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showAllDecks)).matches()){
            showAllUserDecks();
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showOptionalDeck1)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showOptionalDeck2)).matches()){
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
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showAllUserCards)).matches()){
            showAllCards();
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.exitMenu)).matches()) {
            main.java.programController.ProgramController.currentMenu = main.java.programController.MenuEnum.MAIN_MENU;
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.enterMenu)).matches()) {
            System.out.println(main.java.programController.StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showCurrentMenu)).matches()) {
            System.out.println("Deck");
        }
        else {
            System.out.println(main.java.programController.StatusEnum.INVALID_COMMAND);
        }
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
    private void deleteDeck(String deckName){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else{
            Deck.getDeckByName(deckName).removeDeck();
            System.out.println(StatusEnum.DECK_DELETED_SUCCESSFULLY);
        }
    }
    private void activateDeck(String deckName){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else {
            currentUser.setActiveDeck(Deck.getDeckByName(deckName));
            System.out.println(main.java.programController.StatusEnum.DECK_CREATE_SUCCESSFULLY);
        }
    }
    private boolean doesDeckExist(String deckName) {
        for (Deck deck: currentUser.getUserDecks()) {
            if (deck.getName().equals(deckName)){
                return true;
            }
        }
        return false;
    }
    private boolean doesCardExist(String cardName) {
        for (Card card: currentUser.getUserCards()
             ) {
            if (card.getName().equals(cardName)){
                return true;
            }
        }
        return false;
    }


    private boolean isMainDeckFull(String deckName){
        if (Deck.getDeckByName(deckName).isMainFull()){
            return true;
        }
        return false;
    }
    private boolean isSideDeckFull(String deckName){
        if (Deck.getDeckByName(deckName).isSideFull()){
            return true;
        }
        return false;
    }
    private boolean isThereThreeCards(String deckName,String cardName){
        if (Deck.getDeckByName(deckName).hasEnoughSpace(Card.getCardByName(cardName))){
            return false;
        }

        return  true;
    }
    private void addCard(String deckName,String cardName,String mainOrSide){
        if (!doesCardExist(cardName)){
            System.out.println("card with name"+ cardName +"does not exist");
        }
        else if (!doesDeckExist(deckName)){
            System.out.println("deck with name"+ deckName +"does not exist");
        }
        else if (!isMainDeckFull(deckName) && mainOrSide.equals("main")){
            System.out.println("main deck is full");
        }
        else if (!isSideDeckFull(deckName) && mainOrSide.equals("side")){
            System.out.println("side deck is full");
        }
        else if(isThereThreeCards(deckName,cardName)){
            System.out.println("there are already three cards with name" + cardName + "in deck" + deckName);
        }
        else {
            boolean isMain;
            if (mainOrSide.equals("main")){
                isMain = true;
            }
            else{
                isMain = false;
            }
            currentUser.getUserDeckByName(deckName).addCardToDeck(isMain,currentUser.getUserCardByName(cardName));
        }
    }


    //INJA TOO YE ELSE IF MOMKENE DOTA PRINT BEDE-----------------------------
    private void removeCardFromDeck(String deckName,String cardName,String mainOrSide){
        if (!doesDeckExist(deckName)){
            System.out.println("deck with name" + deckName + "does not exist");
        }
        else if(!doesCardExistInDeck(deckName,cardName)){
            //    //INJA TOO YE ELSE IF MOMKENE DOTA PRINT BEDE-----------------------------
            if (!currentUser.getUserDeckByName(deckName).isThisCardUsedInMain(currentUser.getUserCardByName(cardName))){
                System.out.println("card with name" + cardName + "does not exist in main deck");
            }
            if (!currentUser.getUserDeckByName(deckName).isThisCardUsedInSide(currentUser.getUserCardByName(cardName))){
                System.out.println("card with name" + cardName + "does not exist in side deck");

            }
        }
        else{
            boolean isMain;
            if (mainOrSide.equals("main")){
                isMain = true;
            }
            else{
                isMain = false;
            }
            currentUser.getUserDeckByName(deckName).removeCardFromDeck(isMain,currentUser.getUserCardByName(cardName));
            System.out.println(StatusEnum.CARD_REMOVED_SUCCESSFULLY);
        }
    }
    private boolean doesCardExistInDeck(String  deckName,String cardName){
        if (currentUser.getUserDeckByName(deckName).isThisCardUsedInMain(currentUser.getUserCardByName(cardName)) || currentUser.getUserDeckByName(deckName).isThisCardUsedInSide(currentUser.getUserCardByName(cardName))){
            return true;
        }
        return false;
    }











    private void showAllUserDecks(){
        System.out.println("Decks:");
        System.out.println(currentUser.getActiveDeck().toString());
        System.out.println("Other decks:");
        for (int i = 0; i < currentUser.getUserDecks().size(); i++) {
            System.out.println(currentUser.getUserCards().get(i).toString());
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------
    private void showOptionalDeck(String deckName,String mainOrSide){
        System.out.println("Deck: " + deckName);
        System.out.println(mainOrSide + "deck:");
        System.out.println("Monsters:");
        if (mainOrSide.equals("Main")) {
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getMainDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.MONSTER)){
                    String cardName = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
            System.out.println("Spell and Traps:");
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getMainDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.SPELL) || currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.TRAP)){
                    String cardName = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
        }
        else{
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getSideDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getCardType().equals(CardType.MONSTER)){
                    String cardName = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
            System.out.println("Spell and Traps:");
            for (int i = 0; i < currentUser.getUserDeckByName(deckName).getSideDeck().size(); i++) {
                if (currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getCardType().equals(CardType.SPELL) || currentUser.getUserDeckByName(deckName).getMainDeck().get(i).getCardType().equals(CardType.TRAP)){
                    String cardName = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getName();
                    String cardDescription = currentUser.getUserDeckByName(deckName).getSideDeck().get(i).getDescription();
                    System.out.println(cardName + ": " + cardDescription);
                }
            }
        }


    }//--------------------IT'S NOT ALPHABETIC YET----------------------
    private void showAllCards(){
        for (int i = 0; i < currentUser.getUserCards().size(); i++) {
            System.out.println(currentUser.getUserCards().get(i).getName()+":"+currentUser.getUserCards().get(i).getDescription());
        }
    }//--------------------IT'S NOT ALPHABETIC YET----------------------


}
