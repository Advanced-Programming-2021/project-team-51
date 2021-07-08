package controller.GUI;

import javafx.event.ActionEvent;

import java.io.IOException;


public class MakeCardSceneController {

    public void makeMonster(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("MakeMonsterScene.fxml", actionEvent);
    }

    public void makeSpellTrap(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("MakeSpellTrapScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("MainMenuScene.fxml", actionEvent);
    }
}
