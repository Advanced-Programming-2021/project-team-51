import javafx.event.ActionEvent;


public class MakeCardSceneController {

    public void makeMonster(ActionEvent actionEvent) {
        new SceneController().switchScene("MakeMonsterScene.fxml", actionEvent);
    }

    public void makeSpellTrap(ActionEvent actionEvent) {
        new SceneController().switchScene("MakeSpellTrapScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) {
        new SceneController().switchScene("MainMenuScene.fxml", actionEvent);
    }
}
