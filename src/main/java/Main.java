import controller.GUI.LoginControllerGUI;
import controller.GUI.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import models.Deck;
import models.User;
import models.cards.Card;
import models.cards.MakeCards;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;
    public static String mp4File = "./src/main/resources/video/trailer.mp4";
    public static Media media = new Media(new File(mp4File).toURI().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(media);

    @Override
    public void start(Stage stage) throws Exception {

        MediaView mediaView = new MediaView(mediaPlayer1);
        mediaPlayer1.play();
        mediaView.setFitHeight(600);
        mediaView.setFitWidth(800);

        Pane root = FXMLLoader.load(getClass().getResource("/fxml/pre_start.fxml"));
        root.getChildren().add(mediaView);
        stage.setTitle("Yu Gi Oh");
        stage.setScene(new Scene(root));
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        makeUserTest("a");
        makeUserTest("b");
        launch(args);
    }
    private static void makeUserTest(String name) {
        User mamad = new User(name, name, name);
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
        mamad.setActiveDeck(mamad.getUserDeckByName("s"));
    }

    public void goToStartScene(MouseEvent event) throws IOException {
        mediaPlayer1.setVolume(0);
        String musicFile = "./src/main/resources/sound/main.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        LoginControllerGUI.player = mediaPlayer;
        mediaPlayer.play();
        SceneController.switchSceneMouse("/fxml/start.fxml",event);
    }
}
