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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Deck;
import models.cards.Card;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;
import view.GUI.AlertBox;

import java.io.IOException;
import java.util.ArrayList;

public class ChangeDeckControllerGUI {

    private final DeckControllerGUI deckControllerGUI = new DeckControllerGUI();

    public static Deck currentDeck;

    private static Card selectedCard;
    private static ImageView selectedImage;
    private static boolean isCardFromDeck;
    private static boolean isCardFromMain;

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label deckName;
    @FXML
    private Pane deckPane;
    @FXML
    private ScrollPane cardsPane;

    private void setSelectedCard(Card card, boolean isFromDeck, boolean isFromMain, ImageView imageView) {
        isCardFromDeck = isFromDeck;
        isCardFromMain = isFromMain;
        resetSelect(selectedImage);
        selectedCard = card;
        selectedImage = imageView;
        if (selectedImage != null)
            selectedImage.setEffect(new Bloom());
    }

    private Card getSelectedCard() {
        return selectedCard;
    }

    private boolean isSelectedCardFromDeck() {
        return isCardFromDeck;
    }

    public void resetSelect(ImageView imageView) {
        if (imageView != null)
            imageView.setEffect(null);
    }

    public void initialize() {
        resetSelect(selectedImage);
        selectedImage = null;
        selectedCard = null;
        deckName.setText("Deck Name: " + currentDeck.getName());
        deckName.setLayoutX(10);
        deckName.setLayoutY(10);
        deckPane.getStylesheets().add(getClass().getResource("/css/scroll_pane.css").toExternalForm());
        cardsPane.getStylesheets().add(getClass().getResource("/css/scroll_pane.css").toExternalForm());
        showDeck();
        showCards();
    }

    public void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/deck_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void addCard(boolean isMain) {
        if (selectedCard == null)
            AlertBox.display("Select a card to add!");
        else if (isCardFromDeck)
            AlertBox.display("this card is already in your Deck!");
        else {
            if (isMain) {
                if (currentDeck.isMainFull())
                    AlertBox.display("Main Deck is FULL!");
                else if (!currentDeck.hasEnoughSpace(selectedCard))
                    AlertBox.display("You Can't use a Card more than 3 Times!");
                else
                    deckControllerGUI.deckMenuController.addCard(currentDeck.getName(),
                            selectedCard.getName(), "main");
            }
            else {
                if (currentDeck.isSideFull())
                    AlertBox.display("Side Deck is FULL!");
                else if (!currentDeck.hasEnoughSpace(selectedCard))
                    AlertBox.display("You can't use a card more than 3 Times!");
                else
                    deckControllerGUI.deckMenuController.addCard(currentDeck.getName(),
                            selectedCard.getName(), "side");
            }
            setSelectedCard(null, false, false, null);
            showDeck();
            showCards();
        }
    }

    public void removeCard() {
        if (selectedCard == null)
            AlertBox.display("Select a card to remove!");
        else if (!isCardFromDeck)
            AlertBox.display("You should remove a card from your Deck");
        else {
            if (isCardFromMain)
                deckControllerGUI.deckMenuController.removeCardFromDeck(currentDeck.getName(),
                    selectedCard.getName(), "main");
            else
                deckControllerGUI.deckMenuController.removeCardFromDeck(currentDeck.getName(),
                        selectedCard.getName(), "side");
            setSelectedCard(null, false, false, null);
            showDeck();
            showCards();
        }
    }

    public VBox getMainList() {
        VBox main = new VBox(2);
        main.getChildren().add(new Label("Main Deck:"));
        HBox[] listCards = new HBox[(int) Math.ceil(currentDeck.getMainDeck().size() / 10.0)];
        for (int i = 0 ; i < currentDeck.getMainDeck().size(); i++) {
            if (i % 10 == 0)
                listCards[i / 10] = new HBox(2);
            int finalI = i;
            if (currentDeck.getMainDeck().get(i) instanceof MonsterCard) {
                ImageView imageView = new ImageView(((MonsterCard) currentDeck.getMainDeck().get(i)).getImage());
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getMainDeck().get(finalI), true, true, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) currentDeck.getMainDeck().get(i)).getImage());
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getMainDeck().get(finalI), true, true, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
        }
        main.getChildren().addAll(listCards);
        main.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
        return main;
    }

    public VBox getSideList() {
        VBox side = new VBox(2);
        side.getChildren().add(new Label("Side Deck:"));
        HBox[] listCards = new HBox[(int) Math.ceil(currentDeck.getSideDeck().size() / 10.0)];
        for (int i = 0 ; i < currentDeck.getSideDeck().size(); i++) {
            if (i % 10 == 0)
                listCards[i / 10] = new HBox(2);
            int finalI = i;
            if (currentDeck.getSideDeck().get(i) instanceof MonsterCard) {
                ImageView imageView = new ImageView(((MonsterCard) currentDeck.getSideDeck().get(i)).getImage());
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getSideDeck().get(finalI), true, false, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) currentDeck.getSideDeck().get(i)).getImage());
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getSideDeck().get(finalI), true, false, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
        }
        side.getChildren().addAll(listCards);
        side.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
        return side;
    }

    public void showDeck() {
        deckPane.getChildren().clear();
        VBox deckCards = new VBox(10);
        HBox buttons = new HBox(5);
        Button addMain = new Button("Add to Main");
        addMain.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        addMain.setOnAction(event -> addCard(true));
        Button addSide = new Button("Add to Side");
        addSide.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        addSide.setOnAction(event -> addCard(false));
        Button remove = new Button("Remove Card");
        remove.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        remove.setOnAction(event -> removeCard());
        buttons.getChildren().addAll(addMain, addSide, remove);
        deckCards.getChildren().addAll(getMainList(),
                getSideList(), buttons);
        deckPane.getChildren().add(deckCards);
        anchor.getChildren().remove(deckPane);
        anchor.getChildren().add(deckPane);
    }

    public VBox getCards(ArrayList<Card> cards) {
        VBox cardsNode = new VBox(1);
        cardsNode.setPrefHeight(600);
        cardsNode.setPrefWidth(300);
        cardsNode.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        cardsNode.getChildren().add(new Label("Your Cards:"));
        cardsNode.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
        HBox[] rows = new HBox[(int) Math.ceil(cards.size() / 7.0)];
        for (int i = 0 ; i < cards.size(); i++) {
            if (i % 7 == 0)
                rows[i / 7] = new HBox(1);
            int finalI = i;
            if (cards.get(i) instanceof MonsterCard) {
                ImageView imageView = new ImageView(((MonsterCard) cards.get(i)).getImage());
                imageView.setFitHeight(60);
                imageView.setFitWidth(40);
                imageView.setOnMouseClicked(event -> {
                    setSelectedCard(cards.get(finalI), false, false, imageView);
                });
                rows[i / 7].getChildren().add(imageView);
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) cards.get(i)).getImage());
                imageView.setFitHeight(60);
                imageView.setFitWidth(40);
                imageView.setOnMouseClicked(event -> {
                    setSelectedCard(cards.get(finalI), false, false, imageView);
                });
                rows[i / 7].getChildren().add(imageView);
            }
        }
        cardsNode.getChildren().addAll(rows);
        return cardsNode;
    }

    public void showCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(LoginMenuController.currentUser.getUserCards());
        for (Card card: currentDeck.getMainDeck())
            cards.remove(card);
        for (Card card: currentDeck.getSideDeck())
            cards.remove(card);
        cardsPane.setContent(getCards(cards));
    }
}
