package controller.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class ShopControllerGUI {

    SceneController sceneController = new SceneController();

    @FXML
    private ScrollPane cardsContainer;

    public void initialize() {

    }

    public void back(ActionEvent event) throws IOException {
        sceneController.switchScene("main_menu", event);
    }
}
