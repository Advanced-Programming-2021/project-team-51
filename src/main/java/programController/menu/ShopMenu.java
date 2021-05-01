package main.java.programController.menu;

import main.java.models.User;
import main.java.models.cards.Card;
import main.java.programController.MenuEnum;
import main.java.programController.ProgramController;
import main.java.programController.Regex;
import main.java.programController.StatusEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;


/*------------------CURRENT USER NOT FIXED-------------*/



public class ShopMenu {
    private User currentUser;
    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.buyCard)).matches()){
            String cardName = matcher.group(1);
            if (!isCardExist(cardName)){
                System.out.println(StatusEnum.INVALID_CARD_IN_SHOP);
            }
            else if (!doesUserHaveEnoughMoney(cardName)) {
                System.out.println(StatusEnum.NOT_ENOUGH_MONEY);
            }
            else {
                buyCard(cardName);
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCardsInShop)).matches()){
            showCardsInShop();
        }
        else if ((matcher = Regex.getMatcher(command,Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCurrentMenu)).matches()){
            System.out.println("Shop");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }

    }
    private void buyCard(String cardName){
        Card card = Card.getCardByName(cardName);
        currentUser.addCard(card);
        int cardPrice = card.getPrice();
        int userMoney = currentUser.getMoney();
        int userMoneyAfterPurchase = userMoney - cardPrice;
        currentUser.setMoney(userMoneyAfterPurchase);

    }
    private void showCardsInShop(){
        TreeMap<String, String> sorted = new TreeMap<>();
        sorted.putAll(allCards());
        for (Map.Entry<String, String> entry : sorted.entrySet())
            System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    private boolean isCardExist(String cardName){

        return (Card.getAllCards().contains(cardName));
    }
    private boolean doesUserHaveEnoughMoney(String cardName){
        Card card = Card.getCardByName(cardName);
        return  (card.getPrice() <= currentUser.getMoney());
    }
    private HashMap<String,String> allCards(){
            Iterator<Card> it = Card.getAllCards().iterator();
            HashMap<String,String> cardNameAndDescription = new HashMap<>();
            while (it.hasNext()){
                String cardName = it.next().getName();
                String description = it.next().getDescription();
                cardNameAndDescription.put(cardName,description);
            }
            return cardNameAndDescription;

    }
}
