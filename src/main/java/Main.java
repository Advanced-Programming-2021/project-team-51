import controller.GUI.LoginControllerGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/start.fxml")));
        stage.setTitle("Yu Gi Oh");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
