import javafx.event.ActionEvent;

public class ImportExportSceneController {
    public void importCard(ActionEvent actionEvent) {
        new SceneController().switchScene("ImportScene.fxml", actionEvent);
    }

    public void exportCard(ActionEvent actionEvent) {
        new SceneController().switchScene("ExportScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) {
        new SceneController().switchScene("MainMenuScene.fxml", actionEvent);
    }
}
