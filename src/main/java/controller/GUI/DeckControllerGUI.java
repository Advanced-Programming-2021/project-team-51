package controller.GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class DeckControllerGUI {

    SceneController sceneController = new SceneController();

    public void back(ActionEvent event) throws IOException {
        sceneController.switchScene("main_menu", event);
    }
}
