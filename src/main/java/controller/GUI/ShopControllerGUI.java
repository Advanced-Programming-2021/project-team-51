package controller.GUI;

import controller.menucontroller.LoginMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.cards.Card;
import models.cards.CardType;
import models.cards.MakeCards;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.io.IOException;
import java.util.ArrayList;

public class ShopControllerGUI {


    static Card selectedCard = null;

    private static Button buyButton;
    private static Label money;
    private static Label price;
    private static Label amount;
    @FXML
    private AnchorPane anchor;

    public static void setSelectedCard(Card card, CardType type) {
        selectedCard = card;
        resetSelect();
        updateAmount(LoginMenuController.currentUser.getCardAmount(selectedCard));
        updatePrice(selectedCard.getPrice());
        if (type == CardType.MONSTER)
            ((MonsterCard) selectedCard).getImage().setEffect(new Bloom());
        else
            ((SpellTrapCard) selectedCard).getImage().setEffect(new Bloom());

        resetButton();
    }

    public static Card getSelectedCard() {
        return selectedCard;
    }

    public static void resetButton() {
        buyButton.setVisible(getSelectedCard().getPrice() <= LoginMenuController.currentUser.getMoney());
    }

    public static void resetSelect() {
        try {
            ArrayList<SpellTrapCard> spells = SpellTrapCard.getAllSpellTrapCardsToShow();
            ArrayList<MonsterCard> monsters = MonsterCard.getAllMonsterCardsToShow();
            for (MonsterCard monster: monsters)
                monster.getImage().setEffect(null);
            for (SpellTrapCard spell: spells)
                spell.getImage().setEffect(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateMoney(int amount) {
        LoginMenuController.currentUser.setMoney(amount);
        money.setText("Money: " + amount);
    }

    public static void updateAmount(int amountAmount) {
        amount.setText("Amount: " + amountAmount);
    }

    public static void updatePrice(int amount) {
        price.setText("Price: " + amount);
    }

    public void buyNewCard() {
        updateMoney(LoginMenuController.currentUser.getMoney() - selectedCard.getPrice());
        addCard();
        resetButton();
    }

    public void addCard() {
        Card card = MakeCards.makeCard(selectedCard.getName());
        LoginMenuController.currentUser.addCard(card);
        updateAmount(LoginMenuController.currentUser.getCardAmount(card));
    }

    public void initialize() {
        try {
            resetSelect();
            selectedCard = null;
            buyButton = new Button("Buy");
            buyButton.setVisible(false);
            buyButton.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
            buyButton.setOnAction(event -> {
                if (buyButton.isVisible())
                    buyNewCard();
            });
            buyButton.setLayoutX(670);
            buyButton.setLayoutY(10);
            price = new Label();
            price.getStylesheets().add(getClass().getResource("/css/shop_money.css").toExternalForm());
            price.setLayoutX(270);
            price.setLayoutY(10);
            amount = new Label();
            amount.getStylesheets().add(getClass().getResource("/css/shop_money.css").toExternalForm());
            amount.setLayoutX(470);
            amount.setLayoutY(10);
            money = new Label();
            money.getStylesheets().add(getClass().getResource("/css/shop_money.css").toExternalForm());
            money.setLayoutX(10);
            money.setLayoutY(10);
            anchor.getChildren().add(price);
            anchor.getChildren().add(amount);
            anchor.getChildren().add(buyButton);
            anchor.getChildren().add(money);
            updateMoney(LoginMenuController.currentUser.getMoney());
            ArrayList<MonsterCard> monsters = MonsterCard.getAllMonsterCardsToShow();
            ArrayList<SpellTrapCard> spells = SpellTrapCard.getAllSpellTrapCardsToShow();
            HBox monstersBox = new HBox(50);
            HBox spellsBox = new HBox(50);
            ScrollPane monstersPane = new ScrollPane();
            ScrollPane spellsPane = new ScrollPane();
            for (MonsterCard monster: monsters) {
                monster.getImage().setFitHeight(240);
                monster.getImage().setFitWidth(160);
                monster.getImage().setOnMouseClicked(event -> {
                    setSelectedCard(monster, CardType.MONSTER);
                });
                monstersBox.getChildren().add(monster.getImage());
            }
            for (SpellTrapCard spellTrap: spells) {
                spellTrap.getImage().setFitHeight(240);
                spellTrap.getImage().setFitWidth(160);
                spellTrap.getImage().setOnMouseClicked(event -> {
                    setSelectedCard(spellTrap, CardType.SPELL);
                });
                spellsBox.getChildren().add(spellTrap.getImage());
            }
            String scrollPaneStyleAddress = getClass().getResource("/css/scroll_pane.css").toExternalForm();
            monstersPane.setLayoutY(60);
            monstersPane.setMaxWidth(800);
            monstersPane.setPrefHeight(260);
            monstersPane.getStylesheets().add(scrollPaneStyleAddress);
            spellsPane.setLayoutY(330);
            spellsPane.setMaxWidth(800);
            spellsPane.setPrefHeight(260);
            spellsPane.getStylesheets().add(scrollPaneStyleAddress);
            monstersBox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            spellsBox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            monstersPane.setContent(monstersBox);
            spellsPane.setContent(spellsBox);
            anchor.getChildren().add(monstersPane);
            anchor.getChildren().add(spellsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }
}
