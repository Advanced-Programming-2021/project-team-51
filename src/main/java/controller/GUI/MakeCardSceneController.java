package controller.GUI;

import javafx.event.ActionEvent;

import java.io.IOException;


public class MakeCardSceneController {

    public void makeMonster(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/MakeMonsterScene.fxml", actionEvent);
    }

    public void makeSpellTrap(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/MakeSpellTrapScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/main_menu.fxml", actionEvent);
    }
}
