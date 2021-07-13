import controller.GUI.DuelViewSceneController;
import controller.GUI.LoginControllerGUI;
import controller.GUI.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import models.SaveData;

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
        mediaPlayer1.setAutoPlay(true);
        mediaView.setFitHeight(600);
        mediaView.setFitWidth(800);
        mediaPlayer1.setOnEndOfMedia(() -> {
            try {
                goToStartScene();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        Pane root = FXMLLoader.load(getClass().getResource("/fxml/pre_start.fxml"));
        root.getChildren().add(mediaView);
        stage.setTitle("Yu Gi Oh");
        stage.setScene(new Scene(root));
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        try {
            SaveData.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
        SaveData.save();
    }

    public void goToStartScene(MouseEvent event) throws IOException {
        String musicFile = "./src/main/resources/sound/main.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        LoginControllerGUI.player = mediaPlayer;
        mediaPlayer.play();
        SceneController.switchSceneMouse("/fxml/start.fxml",event);
    }
    public void goToStartScene() throws IOException {
        String musicFile = "./src/main/resources/sound/main.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        LoginControllerGUI.player = mediaPlayer;
        mediaPlayer.play();

        FXMLLoader fxmlLoader = new FXMLLoader(SceneController.class.getResource("/fxml/start.fxml"));
        Pane pane = fxmlLoader.load();
        primaryStage.setScene(new Scene(pane));
        if (fxmlLoader.getController() instanceof DuelViewSceneController) {
            DuelViewSceneController gamePage = fxmlLoader.getController();
            gamePage.setPreparations();
        }
    }

}
