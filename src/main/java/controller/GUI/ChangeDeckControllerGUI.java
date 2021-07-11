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
import javafx.scene.image.Image;
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

    private final ArrayList<ImageView> cardImages = new ArrayList<>();
    private final ArrayList<ImageView> mainImages = new ArrayList<>();
    private final ArrayList<ImageView> sideImages = new ArrayList<>();
    private final VBox mainDeck = new VBox(2);
    private final VBox sideDeck = new VBox(2);
    private final VBox deckInfo = new VBox(10);
    private final VBox cardsBox = new VBox(1);

    private final Pane cardPane = new Pane();

    private ImageView bigImage = new ImageView();

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

    public void resetSelect(ImageView imageView) {
        if (imageView != null)
            imageView.setEffect(null);
    }

    public void initialize() {
        deckPane.getChildren().setAll(deckInfo);
        initCardImages();
        hideCard();
        resetSelect(selectedImage);
        selectedImage = null;
        selectedCard = null;
        deckName.setText("Deck Name: " + currentDeck.getName());
        deckName.setLayoutX(10);
        deckName.setLayoutY(10);
        deckPane.getStylesheets().add(getClass().getResource("/css/scroll_pane.css").toExternalForm());
        cardsPane.getStylesheets().add(getClass().getResource("/css/scroll_pane.css").toExternalForm());
        sideDeck.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
        mainDeck.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
        cardsBox.setPrefHeight(600);
        cardsBox.setPrefWidth(300);
        cardsBox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        cardsBox.getStylesheets().add(getClass().getResource("/css/deck.css").toExternalForm());
    }

    public void initCardImages() {
        bigImage.setFitWidth(160);
        bigImage.setFitHeight(240);

        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(LoginMenuController.currentUser.getUserCards());
        for (Card card: currentDeck.getMainDeck())
            cards.remove(card);
        for (Card card: currentDeck.getSideDeck())
            cards.remove(card);
        cardsPane.setContent(getCards(cards));

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
        deckInfo.getChildren().addAll(getMainList(),
                getSideList(), buttons);
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
                else {
                    deckControllerGUI.deckMenuController.addCard(currentDeck.getName(),
                            selectedCard.getName(), "main");
                    mainImages.add(selectedImage);
                    cardImages.remove(selectedImage);
                    ImageView imageView = mainImages.get(mainImages.size() - 1);
                    Card card = selectedCard;
                    imageView.setOnMouseClicked(event ->
                            setSelectedCard(card, true, true,
                                    imageView));
                    imageView.setOnMouseEntered(event -> hideCard());
                    imageView.setOnMouseExited(event -> hideCard());
                }
            }
            else {
                if (currentDeck.isSideFull())
                    AlertBox.display("Side Deck is FULL!");
                else if (!currentDeck.hasEnoughSpace(selectedCard))
                    AlertBox.display("You can't use a card more than 3 Times!");
                else {
                    deckControllerGUI.deckMenuController.addCard(currentDeck.getName(),
                            selectedCard.getName(), "side");
                    sideImages.add(selectedImage);
                    cardImages.remove(selectedImage);
                    ImageView imageView = sideImages.get(sideImages.size() - 1);
                    Card card = selectedCard;
                    imageView.setOnMouseClicked(event ->
                            setSelectedCard(card, true, false,
                                    imageView));
                    imageView.setOnMouseEntered(event -> hideCard());
                    imageView.setOnMouseExited(event -> hideCard());
                }
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
            if (isCardFromMain) {
                deckControllerGUI.deckMenuController.removeCardFromDeck(currentDeck.getName(),
                        selectedCard.getName(), "main");
                mainImages.remove(selectedImage);
                cardImages.add(selectedImage);
                ImageView imageView = cardImages.get(cardImages.size() - 1);
                Card card = selectedCard;
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(card,
                        false, false, imageView));
                imageView.setOnMouseEntered(event ->
                        showCard(event.getX() +
                                        imageView.getLayoutX(),
                                event.getY() +
                                        imageView.getLayoutY(),
                                imageView));
                imageView.setOnMouseExited(event -> hideCard());
            }
            else {
                deckControllerGUI.deckMenuController.removeCardFromDeck(currentDeck.getName(),
                        selectedCard.getName(), "side");
                sideImages.remove(selectedImage);
                cardImages.add(selectedImage);
                ImageView imageView = cardImages.get(cardImages.size() - 1);
                Card card = selectedCard;
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(card,
                                false, false, imageView));
                imageView.setOnMouseEntered(event ->
                        showCard(event.getX() +
                                        imageView.getLayoutX(),
                                event.getY() +
                                        imageView.getLayoutY(),
                                imageView));
                imageView.setOnMouseExited(event -> hideCard());
            }
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
                mainImages.add(imageView);
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getMainDeck().get(finalI), true, true, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) currentDeck.getMainDeck().get(i)).getImage());
                mainImages.add(imageView);
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
                sideImages.add(imageView);
                imageView.setFitWidth(40);
                imageView.setFitHeight(60);
                imageView.setOnMouseClicked(event ->
                        setSelectedCard(currentDeck.getSideDeck().get(finalI), true, false, imageView));
                listCards[i / 10].getChildren().add(imageView);
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) currentDeck.getSideDeck().get(i)).getImage());
                sideImages.add(imageView);
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

    public VBox updateMainDeck() {
        mainDeck.getChildren().clear();
        mainDeck.getChildren().add(new Label("Main Deck:"));
        HBox[] listCards = new HBox[(int) Math.ceil(currentDeck.getMainDeck().size() / 10.0)];
        for (int i = 0 ; i < currentDeck.getMainDeck().size(); i++) {
            if (i % 10 == 0)
                listCards[i / 10] = new HBox(2);
            listCards[i / 10].getChildren().add(mainImages.get(i));
        }
        mainDeck.getChildren().addAll(listCards);
        return mainDeck;
    }

    public VBox updateSideDeck() {
        sideDeck.getChildren().clear();
        sideDeck.getChildren().add(new Label("Side Deck:"));
        HBox[] listCards = new HBox[(int) Math.ceil(currentDeck.getSideDeck().size() / 10.0)];
        for (int i = 0 ; i < currentDeck.getSideDeck().size(); i++) {
            if (i % 10 == 0)
                listCards[i / 10] = new HBox(2);
            listCards[i / 10].getChildren().add(sideImages.get(i));
        }
        sideDeck.getChildren().addAll(listCards);
        return sideDeck;
    }

    public void showDeck() {
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
        deckInfo.getChildren().setAll(updateMainDeck(),
                updateSideDeck(), buttons);
    }

    public VBox getCards(ArrayList<Card> cards) {
        cardsBox.getChildren().clear();
        cardsBox.getChildren().add(new Label("Your Cards:"));
        HBox[] rows = new HBox[(int) Math.ceil(cards.size() / 7.0)];
        for (int i = 0 ; i < cards.size(); i++) {
            if (i % 7 == 0)
                rows[i / 7] = new HBox(1);
            int finalI = i;
            if (cards.get(i) instanceof MonsterCard) {
                ImageView imageView = new ImageView(((MonsterCard) cards.get(i)).getImage());
                rows[i / 7].getChildren().add(imageView);
                cardImages.add(imageView);
                imageView.setFitHeight(60);
                imageView.setFitWidth(40);
                imageView.setOnMouseClicked(event -> {
                    setSelectedCard(cards.get(finalI), false, false, imageView);
                });
            }
            else {
                ImageView imageView = new ImageView(((SpellTrapCard) cards.get(i)).getImage());
                cardImages.add(imageView);
                imageView.setFitHeight(60);
                imageView.setFitWidth(40);
                imageView.setOnMouseClicked(event -> {
                    setSelectedCard(cards.get(finalI), false, false, imageView);
                });
                rows[i / 7].getChildren().add(imageView);
            }
        }
        cardsBox.getChildren().addAll(rows);
        for (HBox hBox: rows)
            for (Node imageView: hBox.getChildren()) {
                imageView.setOnMouseEntered(event -> showCard(event.getX() + imageView.getLayoutX(),
                        event.getY() + imageView.getLayoutY(), (ImageView) imageView));
                imageView.setOnMouseExited(event -> hideCard());
            }
        return cardsBox;
    }

    public void showCards() {
        cardsBox.getChildren().clear();
        cardsBox.getChildren().add(new Label("Your Cards:"));
        HBox[] rows = new HBox[(int) Math.ceil(cardImages.size() / 7.0)];
        for (int i = 0 ; i < cardImages.size(); i++) {
            if (i % 7 == 0)
                rows[i / 7] = new HBox(1);
            rows[i / 7].getChildren().add(cardImages.get(i));
        }
        cardsBox.getChildren().addAll(rows);
        cardsPane.setContent(cardsBox);
    }

    public void showCard(double x, double y, ImageView imageView) {
        anchor.getChildren().add(cardPane);
        cardPane.getChildren().clear();
        cardPane.setLayoutX(x + 455);
        cardPane.setLayoutY(y + 5);
        bigImage.setImage(imageView.getImage());
        cardPane.getChildren().setAll(bigImage);
    }

    public void hideCard() {
        anchor.getChildren().remove(cardPane);
    }
}
