package controller.menucontroller;


//--------FAGHAT ALEPHNA----------------------


import view.StatusEnum;
import models.Deck;
import models.User;
import models.cards.Card;
import models.cards.CardType;


public class DeckMenuController {
    private final User currentUser;

    public DeckMenuController(User user) {
        currentUser = user;
    }

    public String createDeck(String deckName) {
        if (doesDeckExist(deckName))
            return "deck with name" + deckName + "already exists";

        currentUser.addDeck(new Deck(deckName, currentUser.getUserName()));
        return StatusEnum.DECK_CREATE_SUCCESSFULLY.getStatus();
    }

    public String deleteDeck(String deckName) {
        if (!doesDeckExist(deckName))
            return "deck with name" + deckName + "does not exist";

        Deck.getDeckByName(deckName).removeDeck();
        return StatusEnum.DECK_DELETED_SUCCESSFULLY.getStatus();
    }

    public String activateDeck(String deckName) {
        if (!doesDeckExist(deckName))
            return "deck with name" + deckName + "does not exist";

        currentUser.setActiveDeck(Deck.getDeckByName(deckName));
        return StatusEnum.DECK_ACTIVATED_SUCCESSFULLY.getStatus();
    }

    private boolean doesDeckExist(String deckName) {
        for (Deck deck : currentUser.getUserDecks()) {
            if (deck.getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesCardExist(String cardName) {
        for (Card card : currentUser.getUserCards()
        ) {
            if (card.getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }


    private boolean isMainDeckFull(String deckName) {
        return Deck.getDeckByName(deckName).isMainFull();
    }

    private boolean isSideDeckFull(String deckName) {
        return Deck.getDeckByName(deckName).isSideFull();
    }

    private boolean isThereThreeCards(String deckName, String cardName) {
        return !Deck.getDeckByName(deckName).hasEnoughSpace(Card.getCardByName(cardName));
    }

    public String addCard(String deckName, String cardName, String mainOrSide) {
        if (!doesCardExist(cardName))
            return "card with name" + cardName + "does not exist";
        if (!doesDeckExist(deckName))
            return "deck with name" + deckName + "does not exist";
        if (isMainDeckFull(deckName) && mainOrSide.equals("main"))
            return StatusEnum.FULL_MAIN_DECK.getStatus();
        if (isSideDeckFull(deckName) && mainOrSide.equals("side"))
            return StatusEnum.FULL_SIDE_DECK.getStatus();
        if (isThereThreeCards(deckName, cardName))
            return "there are already three cards with name" + cardName + "in deck" + deckName;

        boolean isMain;
        isMain = mainOrSide.equals("main");
        currentUser.getUserDeckByName(deckName).addCardToDeck(isMain, currentUser.getUserCardByName(cardName));
        return StatusEnum.CARD_ADDED_TO_DECK_SUCCESSFULLY.getStatus();
    }

    public String removeCardFromDeck(String deckName, String cardName, String mainOrSide) {
        if (!doesDeckExist(deckName))
            return "deck with name" + deckName + "does not exist";

        if (mainOrSide.equals("main") && !currentUser.getUserDeckByName(deckName).isThisCardUsedInMain(currentUser.getUserCardByName(cardName)))
            return "card with name" + cardName + "does not exist in main deck";

        if (mainOrSide.equals("side") && !currentUser.getUserDeckByName(deckName).isThisCardUsedInSide(currentUser.getUserCardByName(cardName)))
            return "card with name" + cardName + "does not exist in side deck";

        boolean isMain;
        isMain = mainOrSide.equals("main");
        currentUser.getUserDeckByName(deckName).removeCardFromDeck(isMain, currentUser.getUserCardByName(cardName));
        return StatusEnum.CARD_REMOVED_SUCCESSFULLY.getStatus();
    }

    private boolean doesCardExistInDeck(String deckName, String cardName) {
        return currentUser.getUserDeckByName(deckName).isThisCardUsedInMain(currentUser.getUserCardByName(cardName)) || currentUser.getUserDeckByName(deckName).isThisCardUsedInSide(currentUser.getUserCardByName(cardName));
    }

}
