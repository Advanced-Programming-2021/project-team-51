package controller.menucontroller;

import models.SaveData;
import models.User;
import models.cards.Card;
import view.StatusEnum;

import java.io.IOException;
import java.util.ArrayList;

public class ImportExportController {

    private final User currentUser;

    public ImportExportController() {
        this.currentUser = LoginMenuController.currentUser;
    }


    public String importCard(String cardName) {
        for (Card card: currentUser.getUserCards())
            if (card.getName().equals(cardName)) {
                SaveData.saveCustomCard(Card.getCardByName(cardName));
                return StatusEnum.IMPORTED_SUCCESSFULLY.getStatus();
            }
        return StatusEnum.CARD_NOT_FOUND.getStatus();
    }

    public String exportCard(String cardName) throws IOException {
        ArrayList<Card> cards = SaveData.loadCustomCards();
        for (Card card: cards)
            if (card.getName().equals(cardName)) {
                System.out.println(card.toString());
                return StatusEnum.EXPORTED_SUCCESSFULLY.getStatus();
            }
        return StatusEnum.CARD_NOT_FOUND.getStatus();
    }
}
