import controller.GUI.LoginControllerGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import models.Deck;
import models.User;
import models.cards.Card;
import models.cards.MakeCards;

import java.io.File;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        String musicFile = "./src/main/resources/sound/main.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        LoginControllerGUI.player = mediaPlayer;
        mediaPlayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        stage.setTitle("Yu Gi Oh");
        stage.setScene(new Scene(root));
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        User mamad = new User("a","a","a");
        mamad.addCard(MakeCards.makeCard("Magic Jammer"));
        mamad.addCard(MakeCards.makeCard("Magic Jammer"));
        mamad.addCard(MakeCards.makeCard("Magic Jammer"));

        mamad.addCard(MakeCards.makeCard("Battle OX"));
        mamad.addCard(MakeCards.makeCard("Battle OX"));
        mamad.addCard(MakeCards.makeCard("Battle OX"));

        mamad.addCard(MakeCards.makeCard("Suijin"));
        mamad.addCard(MakeCards.makeCard("Suijin"));
        mamad.addCard(MakeCards.makeCard("Suijin"));


        mamad.addCard(MakeCards.makeCard("Bitron"));
        mamad.addCard(MakeCards.makeCard("Bitron"));
        mamad.addCard(MakeCards.makeCard("Bitron"));

        mamad.addDeck(new Deck("s","a"));
        for (Card cards: mamad.getUserCards()
             ) {
            mamad.getUserDeckByName("s").addCardToDeck(true,cards);
        }
        mamad.setActiveDeck(mamad.getUserDeckByName("S"));






        launch(args);
    }
}
