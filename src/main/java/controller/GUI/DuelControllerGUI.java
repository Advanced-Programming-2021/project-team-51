package controller.GUI;

import controller.duel.PhaseController;
import controller.duel.singlePlayer.GameController;
import controller.menucontroller.DuelMenuController;
import controller.menucontroller.LoginMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Player;
import view.GUI.AlertBox;

import java.io.File;
import java.io.IOException;

public class DuelControllerGUI {

    public static SceneController sceneController = new SceneController();
    public static DuelMenuController duelMenuController = new DuelMenuController();
    public static MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("./src/main/resources/sound/bt.mp3").toURI().toString()));

    public TextField secondUsernameForMulti;
    public TextField roundsMulti;


    public CheckBox easyCheck;
    public CheckBox hardCheck;
    public CheckBox roundOne;
    public CheckBox roundTwo;

    public void highlightBt(MouseEvent event) {
        mediaPlayer = new MediaPlayer(new Media(new File("./src/main/resources/sound/bt.mp3").toURI().toString()));
        mediaPlayer.play();
        ImageView imageView = ((ImageView) event.getSource());
        double w = imageView.getFitWidth();
        double h = imageView.getFitHeight();
        imageView.setFitHeight(h + 5);
        imageView.setFitWidth(w + 5);


    }

    public void returnNormalBt(MouseEvent event) {
        ImageView imageView = ((ImageView) event.getSource());
        double w = imageView.getFitWidth();
        double h = imageView.getFitHeight();
        imageView.setFitHeight(h - 5);
        imageView.setFitWidth(w - 5);
    }


    public void multiplayerScene(MouseEvent event) throws IOException {
        sceneController.switchSceneMouse("/fxml/multiplayerStart.fxml", event);
    }

    public void singleplayerScene(MouseEvent event) throws IOException {
        sceneController.switchSceneMouse("/fxml/singlePlayerStart.fxml", event);
    }

    public void multiPlayerStart(ActionEvent actionEvent) throws CloneNotSupportedException, IOException, InterruptedException {
        String name = secondUsernameForMulti.getText();
        String rounds = roundsMulti.getText();
        if (name.equals("") || rounds.equals("")) {
            AlertBox.display("Please fill the empty fields!");
        } else {
            String res = duelMenuController.startTwoPlayer(LoginMenuController.currentUser, name, rounds);
            if (!res.equals("")) {
                AlertBox.display(res);
            } else {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/coinS.fxml"));
                Pane pane = fxmlLoader.load();
                stage.setScene(new Scene(pane));
                Image image2;

                if (PhaseController.playerInTurn == Player.getFirstPlayer()) {
                    image2 = new Image("./image/x1.jpg");
                } else {
                    image2 = new Image("./image/x2.jpg");
                }

                Image image1 = new Image("./image/saw.gif");

                Image image3 = new Image("image/xxx.png");
                ImageView coinR = new ImageView();
                ImageView start = new ImageView();

                DuelViewSceneController.isMultiPlayer = true;

                coinR.setFitHeight(156);
                coinR.setFitWidth(150);
                coinR.setLayoutX(300);
                coinR.setLayoutY(300);

                start.setFitHeight(200);
                start.setFitWidth(200);
                start.setLayoutX(270);
                start.setLayoutY(450);

                start.setOnMouseClicked(event -> {
                    try {
                        gameStartScene(event);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });

                start.setCursor(Cursor.HAND);

                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(coinR.imageProperty(), image1)),
                        new KeyFrame(Duration.seconds(5), new KeyValue(coinR.imageProperty(), image2))
                );
                timeline.play();

                Timeline t = new Timeline(
                        new KeyFrame(Duration.seconds(6), new KeyValue(start.imageProperty(), image3))
                );
                t.play();

                pane.getChildren().add(coinR);
                pane.getChildren().add(start);
            }
        }


    }

    public void gameStartScene(MouseEvent event) throws IOException {
        sceneController.switchSceneMouse("/fxml/gameField.fxml", event);
    }

    public void singlePlayerStart(ActionEvent actionEvent) throws CloneNotSupportedException, IOException {

        if (!easyCheck.isSelected() && !hardCheck.isSelected()) {
            AlertBox.display("Please choose difficulty before start!");
        } else if (!roundOne.isSelected() && !roundTwo.isSelected()) {
            AlertBox.display("Please choose rounds before start!");
        } else if ((easyCheck.isSelected() && hardCheck.isSelected()) || (roundOne.isSelected() && roundTwo.isSelected())) {
            AlertBox.display("Please check only one box!");
        } else {
            String difficulty;
            String rounds;
            if (roundOne.isSelected()) {
                rounds = "1";
            } else {
                rounds = "3";
            }
            if (easyCheck.isSelected()) {
                difficulty = "easy";
            } else {
                difficulty = "hard";
            }
            String res = MainMenuControllerGUI.duelMenuController.startSinglePlayer(LoginMenuController.currentUser, rounds, difficulty);
            if (!res.equals("")) {
                AlertBox.display(res);
            } else {
                DuelViewSceneController.isMultiPlayer = false;
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/coinS.fxml"));
                Pane pane = fxmlLoader.load();
                stage.setScene(new Scene(pane));
                Image image2;

                if (GameController.isPlayerTurn) {
                    image2 = new Image("./image/x1.jpg");
                } else {
                    image2 = new Image("./image/x2.jpg");
                }

                Image image1 = new Image("./image/saw.gif");

                Image image3 = new Image("image/xxx.png");
                ImageView coinR = new ImageView();
                ImageView start = new ImageView();

                coinR.setFitHeight(156);
                coinR.setFitWidth(150);
                coinR.setLayoutX(300);
                coinR.setLayoutY(300);

                start.setFitHeight(200);
                start.setFitWidth(200);
                start.setLayoutX(270);
                start.setLayoutY(450);

                start.setOnMouseClicked(event -> {
                    try {
                        gameStartScene(event);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });

                start.setCursor(Cursor.HAND);

                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(coinR.imageProperty(), image1)),
                        new KeyFrame(Duration.seconds(5), new KeyValue(coinR.imageProperty(), image2))
                );
                timeline.play();

                Timeline t = new Timeline(
                        new KeyFrame(Duration.seconds(6), new KeyValue(start.imageProperty(), image3))
                );
                t.play();

                pane.getChildren().add(coinR);
                pane.getChildren().add(start);
            }

        }

    }

    public void exit(MouseEvent event) {
        LoginMenuController.currentUser = null;
        LoginMenuController.isLoggedOn = false;
        System.exit(0);
    }

    public void returnToDuelMenu(MouseEvent event) throws IOException {
        sceneController.switchSceneMouse("/fxml/duel_start_view.fxml", event);
    }

    public void returnToMain(MouseEvent event) throws IOException {
        sceneController.switchSceneMouse("/fxml/mainMenu.fxml", event);
    }
}
